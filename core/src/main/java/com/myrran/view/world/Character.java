package com.myrran.view.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.spine.*;
import com.myrran.view.Atlas;

import java.util.EnumMap;

/** @author Ivan Delgado Huerta */
public class Character
{
    static float fps = 1 / 60f;
    SkeletonJson skeletonJson;
    SkeletonData skeletonData;
    AnimationStateData animationStateData;
    enum State { idle, run, jump, death, fall }

    Skeleton skeleton;
    Bone rearUpperArmBone, rearBracerBone, gunBone, headBone, torsoBone, frontUpperArmBone;

    SkeletonRenderer skeletonRenderer;
    AnimationState animationState;

    private EnumMap<State, Animation>animations = new EnumMap<>(State.class);


    public Character()
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

        skeleton.setPosition(440, 30);
        animations.put(State.run, skeletonData.findAnimation("run"));
        animations.put(State.idle, skeletonData.findAnimation("idle"));

        animationState.setAnimation(0, animations.get(State.idle), false);
        animationState.addAnimation(0, animations.get(State.run), true, 0f);
        animationState.addAnimation(0, animations.get(State.idle), true, 4f);
        animationState.addAnimation(0, animations.get(State.run), true, 6f);
        //skeleton.setFlipX(true);
    }

    private void loadAssets()
    {
        //spine renderer:
        skeletonRenderer = new SkeletonRenderer();
        skeletonRenderer.setPremultipliedAlpha(true);

        //character:
        skeletonJson = new SkeletonJson(Atlas.get().spineAtlas);
        skeletonJson.setScale(0.080f);
        skeletonData = skeletonJson.readSkeletonData(Gdx.files.internal("spine/spineboy.json"));

        animationStateData = new AnimationStateData(skeletonData);
        animationStateData.setDefaultMix(0.1f);
        animationStateData.setMix("idle", "run", 0.3f);
        animationStateData.setMix("run", "idle", 0.1f);
        animationStateData.setMix("shoot", "shoot", 0.0f);

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
