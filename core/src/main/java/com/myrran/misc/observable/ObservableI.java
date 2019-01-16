package com.myrran.misc.observable;

import java.beans.PropertyChangeListener;

/** @author Ivan Delgado Huerta */
public interface ObservableI
{
    void notify(String propertyName, Object oldValue, Object newValue);
    void addObserver(PropertyChangeListener observer);
    void removeObserver(PropertyChangeListener observer);
    void removeAllObservers();
}
