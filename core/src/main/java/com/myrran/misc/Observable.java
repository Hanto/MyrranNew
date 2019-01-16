package com.myrran.misc;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/** @author Ivan Delgado Huerta */
public interface Observable
{
    PropertyChangeSupport getObserved();

    // DEFAULT:
    //--------------------------------------------------------------------------------------------------------

    default void notify(String propertyName, Object oldValue, Object newValue)
    {   getObserved().firePropertyChange(propertyName, oldValue, newValue ); }

    default void addObserver(PropertyChangeListener observer)
    {   getObserved().addPropertyChangeListener(observer); }

    default void removeObserver(PropertyChangeListener observer)
    {   getObserved().removePropertyChangeListener(observer); }

    default void removeAllObservers()
    {
        PropertyChangeListener observers[] = getObserved().getPropertyChangeListeners();
        for (PropertyChangeListener observer: observers)
            getObserved().removePropertyChangeListener(observer);
    }
}
