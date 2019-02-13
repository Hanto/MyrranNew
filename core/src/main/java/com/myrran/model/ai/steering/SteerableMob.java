package com.myrran.model.ai.steering;

import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.math.Vector2;
import com.myrran.model.ai.steering.SteerableAgent;
import com.myrran.model.ai.steering.SteerableAgentDeco;

/** @author Ivan Delgado Huerta */
public class SteerableMob implements SteerableAgentDeco
{
    private SteeringBehavior<Vector2> steeringBehavior;   // SteerableAgent:
    private SteeringAcceleration<Vector2> steeringOutput = new SteeringAcceleration<>(new Vector2());
    private boolean independentFacing = false;

    private SteerableAgent steerable = new SteerableAgent();
    @Override public SteerableAgent getSteerableAgent()              { return steerable; }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public SteeringBehavior<Vector2> getSteeringBehavior()
    {   return steeringBehavior; }

    public void setSteeringBehavior(SteeringBehavior<Vector2> steeringBehavior)
    {   this.steeringBehavior = steeringBehavior; }

    public void setIndependentFacing(boolean b)
    {   this.independentFacing = b; }

    public void calculateSteering(float delta)
    {
        if (steeringBehavior != null)
        {
            steeringBehavior.calculateSteering(steeringOutput);
            applySteering(steeringOutput, delta);
        }
    }

    // HELPER:
    //--------------------------------------------------------------------------------------------------------

    private void applySteering(SteeringAcceleration<Vector2> steering, float delta)
    {
        Vector2 vector = new Vector2();

        //Position:
        vector.set(getPosition());
        vector.mulAdd(getLinearVelocity(), delta);
        setPosition(vector.x, vector.y);

        //Velocity:
        vector.set(getLinearVelocity());
        vector.mulAdd(steering.linear, delta);
        setLinearVelocity(vector);

        //Orientation:
        if (independentFacing)
        {
            setOrientation(getOrientation() + getAngularVelocity() * delta);
            setAngularVelocity(getAngularVelocity() + steering.angular * delta);
        }
        else
        {
            if (!getLinearVelocity().isZero(getZeroLinearSpeedThreshold()))
            {
                float newOrientation = vectorToAngle(getLinearVelocity());
                setOrientation(newOrientation);
                setAngularVelocity((newOrientation - getOrientation()) * delta);
            }
        }
    }
}