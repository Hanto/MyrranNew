package com.myrran.model.mob;

import com.badlogic.gdx.math.Vector2;
import com.myrran.model.components.observable.Observable;
import com.myrran.model.components.observable.ObservableDeco;
import com.myrran.model.components.observable.ObservableI;
import com.myrran.model.ai.steering.SteerableAgent;
import com.myrran.model.ai.steering.SteerableAgentDeco;

/** @author Ivan Delgado Huerta */
public class Player implements ObservableDeco, SteerableAgentDeco
{
    private Vector2 directionVector = new Vector2(0, 0);
    private SteerableAgent steerable = new SteerableAgent();
    private ObservableI observable = new Observable(this);

    public Vector2 getDirectionVector()                 { return directionVector; }
    @Override public SteerableAgent getSteerableAgent() { return steerable; }
    @Override public ObservableI getObservable()        { return observable; }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public void setOrientation(Vector2 vector)
    {
        directionVector.set(vector);
        setOrientation(directionVector.angleRad());
        notifyChange();
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    private void notifyChange()
    {   notify("fieldChante", null, null); }
}
