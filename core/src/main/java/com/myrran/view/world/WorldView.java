package com.myrran.view.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ObjectFloatMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.esotericsoftware.spine.*;
import com.myrran.view.Atlas;

/** @author Ivan Delgado Huerta */
public class WorldView extends Stage
{
    static float fps = 1 / 60f;
    SkeletonJson skeletonJson;
    SkeletonData skeletonData;
    AnimationStateData animationStateData;
    ObjectMap<State, StateView> playerStates = new ObjectMap();
    enum State { idle, run, jump, death, fall }

    Skeleton skeleton;
    Bone rearUpperArmBone, rearBracerBone, gunBone, headBone, torsoBone, frontUpperArmBone;

    SkeletonRenderer skeletonRenderer;
    AnimationState animationState;


    public WorldView()
    {
        loadAssets();

        /*rearUpperArmBone = skeleton.findBone("rear_upper_arm");
        rearBracerBone = skeleton.findBone("rear_bracer");
        gunBone = skeleton.findBone("gun");
        headBone = skeleton.findBone("head");
        torsoBone = skeleton.findBone("torso");
        frontUpperArmBone = skeleton.findBone("front_upper_arm");*/

        skeleton = new Skeleton(skeletonData);
        animationState = new AnimationState(animationStateData);
        skeleton.setPosition(30, 30);
        animationState.setAnimation(0, "run", true);
    }

    private void loadAssets()
    {
        skeletonRenderer = new SkeletonRenderer();
        skeletonRenderer.setPremultipliedAlpha(true);

        skeletonJson = new SkeletonJson(Atlas.get().spineAtlas);
        skeletonJson.setScale(0.1f);
        skeletonData = skeletonJson.readSkeletonData(Gdx.files.internal("spine/spineboy.json"));
        animationStateData = new AnimationStateData(skeletonData);
        animationStateData.setDefaultMix(0.2f);

        setMix(animationStateData, "idle", "run", 0.3f);
        setMix(animationStateData, "run", "idle", 0.1f);
        setMix(animationStateData, "shoot", "shoot", 0);

        setupState(playerStates, State.death, skeletonData, "death", false);
        StateView idle = setupState(playerStates, State.idle, skeletonData, "idle", true);
        StateView jump = setupState(playerStates, State.jump, skeletonData, "jump", false);
        StateView run = setupState(playerStates, State.run, skeletonData, "run", true);

        if (idle.animation != null) run.startTimes.put(idle.animation, 8 * fps);
        if (jump.animation != null) run.startTimes.put(jump.animation, 22 * fps);
        StateView fall = setupState(playerStates, State.fall, skeletonData, "jump", false);
        fall.defaultStartTime = 22 * fps;
    }

    StateView setupState (ObjectMap map, State state, SkeletonData skeletonData, String name, boolean loop)
    {
        StateView stateView = new StateView();
        stateView.animation = skeletonData.findAnimation(name);
        stateView.loop = loop;
        map.put(state, stateView);
        return stateView;
    }

    static class StateView
    {
        Animation animation;
        boolean loop;
        // Controls the start frame when changing from another animation to this animation.
        ObjectFloatMap<Animation> startTimes = new ObjectFloatMap();
        float defaultStartTime;
    }

    private void setMix (AnimationStateData data, String from, String to, float mix)
    {
        Animation fromAnimation = data.getSkeletonData().findAnimation(from);
        Animation toAnimation = data.getSkeletonData().findAnimation(to);
        if (fromAnimation == null || toAnimation == null) return;
        data.setMix(fromAnimation, toAnimation, mix);
    }

    public void render(SpriteBatch batch)
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        animationState.update(Gdx.graphics.getDeltaTime());
        animationState.apply(skeleton);
        skeleton.updateWorldTransform();

        skeletonRenderer.draw(batch, skeleton);

    }
}
