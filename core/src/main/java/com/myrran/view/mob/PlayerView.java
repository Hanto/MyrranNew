package com.myrran.view.mob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.spine.*;
import com.myrran.model.mob.Player;
import com.myrran.view.Atlas;

import java.util.EnumMap;

/** @author Ivan Delgado Huerta */
public class PlayerView
{
    enum State { idle, run, jump, death, fall }

    private Skeleton skeleton;
    private AnimationState animationState;

    private Player model;
    private Player.AnimationState currentAnimState;

    private EnumMap<State, Animation>animations = new EnumMap<>(State.class);

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public PlayerView(Player player)
    {
        model = player;
        //currentAnimState = model.getAnimationState();

        SkeletonData skeletonData = Atlas.get().getSkeletonData("spine/spineboy");
        AnimationStateData animationData = Atlas.get().getAnimationStateData("spine/spineboy");
        skeleton = new Skeleton(skeletonData);
        animationState = new AnimationState(animationData);
        animations.put(State.run, skeletonData.findAnimation("run"));
        animations.put(State.idle, skeletonData.findAnimation("idle"));

        animationState.setAnimation(0, animations.get(State.idle), true);
        skeleton.setPosition(550, 200);
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    private void setAnimationState()
    {
        /*if (currentAnimState != model.getAnimationState())
        {
            currentAnimState = model.getAnimationState();
            if (currentAnimState == Player.AnimationState.idle)
                animationState.setAnimation(0, animations.get(State.idle), true);
            else
            {
                animationState.setAnimation(0, animations.get(State.run), true);

                if (currentAnimState == Player.AnimationState.runningLeft)
                    skeleton.setFlipX(true);
                else
                    skeleton.setFlipX(false);
            }

        }*/
    }

    // UNINPLEMENTED:
    //--------------------------------------------------------------------------------------------------------

    public void render(SkeletonRenderer skeletonRenderer, SpriteBatch batch)
    {
        setAnimationState();

        animationState.update(Gdx.graphics.getDeltaTime());
        animationState.apply(skeleton);
        skeleton.updateWorldTransform();

        skeletonRenderer.draw(batch, skeleton);
    }
}
