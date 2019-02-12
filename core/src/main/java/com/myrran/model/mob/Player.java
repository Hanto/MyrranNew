package com.myrran.model.mob;

import com.badlogic.gdx.math.Vector2;
import com.myrran.model.components.observable.Observable;
import com.myrran.model.components.observable.ObservableDeco;
import com.myrran.model.components.observable.ObservableI;

/** @author Ivan Delgado Huerta */
public class Player implements ObservableDeco
{
    private Vector2 position = new Vector2(0, 0);
    private Vector2 directionVector = new Vector2(0, 0);
    private float orientation;
    private ObservableI observable = new Observable(this);

    public Vector2 getPosition()                    { return position; }
    public float getOrientation()                   { return orientation; }
    public Vector2 getDirectionVector()           { return directionVector; }
    @Override public ObservableI getObservable()    { return observable; }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public void setOrientation(Vector2 vector)
    {
        directionVector.set(vector);
        orientation = directionVector.angleRad();
        notifyChange();
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    private void notifyChange()
    {   notify("fieldChante", null, null); }
}
