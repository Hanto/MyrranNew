package com.myrran.view.mob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.esotericsoftware.spine.*;
import com.myrran.model.mob.Player;
import com.myrran.view.Atlas;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EnumMap;

/** @author Ivan Delgado Huerta */
public class PlayerView implements PropertyChangeListener, Disposable
{
    private Player model;

    private float lastDirectionX = 0;
    private Skeleton skeleton;
    private AnimationState animationState;
    private EnumMap<State, Animation>animations = new EnumMap<>(State.class);
    public enum State { idle, run, jump, death, fall }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public PlayerView(Player player)
    {
        model = player;
        SkeletonData skeletonData = Atlas.get().getSkeletonData("spine/spineboy");
        AnimationStateData animationData = Atlas.get().getAnimationStateData("spine/spineboy");
        skeleton = new Skeleton(skeletonData);
        animationState = new AnimationState(animationData);

        for (State current : State.values())
            animations.put(current, skeletonData.findAnimation(current.toString()));

        animationState.setAnimation(0, animations.get(State.idle), true);
        skeleton.setPosition(550, 200);
        model.addObserver(this);
    }

    @Override public void dispose()
    {   model.removeObserver(this); }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    private void setMovement()
    {
        Vector2 vector = model.getDirectionVector();

        if (lastDirectionX != vector.x)
        {
            lastDirectionX = vector.x;

            if (vector.x != 0)
            {
                animationState.setAnimation(0, animations.get(State.run), true);
                skeleton.setFlipX(vector.x < 0);
            }
            else
            {   animationState.setAnimation(0, animations.get(State.idle), true); }
        }
    }

    // DRAW:
    //--------------------------------------------------------------------------------------------------------

    public void render(SkeletonRenderer skeletonRenderer, SpriteBatch batch)
    {
        animationState.update(Gdx.graphics.getDeltaTime());
        animationState.apply(skeleton);
        skeleton.updateWorldTransform();

        skeletonRenderer.draw(batch, skeleton);
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   setMovement(); }
}
