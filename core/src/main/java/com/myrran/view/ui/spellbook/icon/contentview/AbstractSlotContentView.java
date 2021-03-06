package com.myrran.view.ui.spellbook.icon.contentview;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.model.components.observable.ObservableI;
import com.myrran.model.spell.generators.SpellSlotI;
import com.myrran.view.ui.spellbook.icon.AbstractSpellIcon;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** @author Ivan Delgado Huerta */
public abstract class AbstractSlotContentView<U extends ObservableI, V> extends AbstractSpellIcon
    implements PropertyChangeListener, Disposable
{
    protected SpellSlotI<U, V> model;
    protected U contentModel;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    protected void disposeObservers()
    {
        if (model != null)
            model.removeObserver(this);

        if (contentModel != null)
            contentModel.removeObserver(this);
    }

    @Override public void dispose()
    {
        disposeObservers();
        disposeImp();
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(SpellSlotI<U, V> spellSlot)
    {
        disposeObservers();

        if (spellSlot == null)
            removeModel();
        else
        {
            model = spellSlot;
            contentModel = spellSlot.getContent();
            model.addObserver(this);
            contentModel.addObserver(this);
            setModelImp();
            update();
        }
    }

    protected void removeModel()
    {
        background.setTexureRegion(null);
        name1.setText(null);
        name2.setText(null);
        corner.setText(null);
    }

    // DRAG AND DROP COLOR:
    //--------------------------------------------------------------------------------------------------------

    public void setLockColor(Color color)
    {
        setName1Color(color);
        name1.addAction(Actions.forever(Actions.sequence(
            Actions.fadeOut(0.2f, Interpolation.circleIn),
            Actions.fadeIn(1.0f, Interpolation.circleOut))));
    }

    public void setDefaultLockColor()
    {
        name1.clearActions();
        name1.addAction(Actions.fadeIn(0.4f));
        setName1Color(model.hasData() ? Color.ORANGE : Color.LIGHT_GRAY);
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
