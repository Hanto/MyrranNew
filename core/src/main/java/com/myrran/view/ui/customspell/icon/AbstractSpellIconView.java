package com.myrran.view.ui.customspell.icon;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.model.components.observable.ObservableI;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** @author Ivan Delgado Huerta */
public abstract class AbstractSpellIconView<T extends ObservableI> extends SpellIcon
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

    // CREATE LAYOUT:
    //--------------------------------------------------------------------------------------------------------

    private void createLayout()
    {
        float pad = -4;

        Table tableRow2 = new Table().bottom().left();
        tableRow2.add(name2).left().padTop(pad).padBottom(pad+1);
        tableRow2.add(corner).expand().fillX().right().padTop(pad).padBottom(pad-6).padRight(2);

        Table table = new Table().bottom().left().padLeft(5).padBottom(8);
        table.setWidth(64);
        table.add(name1).left().padTop(pad).padBottom(pad+1).row();
        table.add(tableRow2).expand().fillX();

        addActor(background);
        addActor(table);
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
