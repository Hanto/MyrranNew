package com.myrran.view.ui.spellbook.icon.iconview;

import com.badlogic.gdx.utils.Disposable;
import com.myrran.model.components.observable.ObservableI;
import com.myrran.view.ui.spellbook.icon.AbstractSpellIcon;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** @author Ivan Delgado Huerta */
public abstract class AbstractIconView<T extends ObservableI> extends AbstractSpellIcon
    implements PropertyChangeListener, Disposable
{
    protected T model;

    protected void disposeObservers()
    {
        if (model != null)
            model.removeObserver(this);
    }

    @Override public void dispose()
    {   disposeObservers(); }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(T spellForm)
    {
        disposeObservers();

        if (spellForm == null)
            removeModel();
        else
        {
            model = spellForm;
            model.addObserver(this);
            update();
        }
    }

    public void removeModel()
    {
        background.setTexureRegion(null);
        name1.setText(null);
        name2.setText(null);
        corner.setText(null);
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   update(); }

    // ABSTRACT:
    //--------------------------------------------------------------------------------------------------------

    protected abstract void update();
    protected abstract void setModelImp();
    protected abstract void disposeImp();
}
