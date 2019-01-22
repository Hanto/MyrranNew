package com.myrran.model.components.observable;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/** @author Ivan Delgado Huerta */
public class Observable implements ObservableI
{
    private PropertyChangeSupport observed;

    // CONSTRUCTOR
    //--------------------------------------------------------------------------------------------------------

    public Observable(Object sourceBean)
    {   observed = new PropertyChangeSupport(sourceBean); }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override public void notify(String propertyName, Object oldValue, Object newValue)
    {   observed.firePropertyChange(propertyName, oldValue, newValue ); }

    @Override public void addObserver(PropertyChangeListener observer)
    {   observed.addPropertyChangeListener(observer); }

    @Override public void removeObserver(PropertyChangeListener observer)
    {   observed.removePropertyChangeListener(observer); }

    @Override public void removeAllObservers()
    {
        PropertyChangeListener[] observers = observed.getPropertyChangeListeners();
        for (PropertyChangeListener observer: observers)
            observed.removePropertyChangeListener(observer);
    }
}
