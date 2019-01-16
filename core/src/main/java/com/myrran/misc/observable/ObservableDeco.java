package com.myrran.misc.observable;

import java.beans.PropertyChangeListener;

/** @author Ivan Delgado Huerta */
public interface ObservableDeco extends ObservableI
{
    ObservableI getObservable();

    // DECORATOR:
    //--------------------------------------------------------------------------------------------------------

    default void notify(String propertyName, Object oldValue, Object newValue)
    {   getObservable().notify(propertyName, oldValue, newValue ); }

    default void addObserver(PropertyChangeListener observer)
    {   getObservable().addObserver(observer); }

    default void removeObserver(PropertyChangeListener observer)
    {   getObservable().removeObserver(observer); }

    default void removeAllObservers()
    {   getObservable().removeAllObservers(); }
}
