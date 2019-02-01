package com.myrran.view.ui.customspell.header;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.model.spell.templates.TemplateSpellSubform;
import com.myrran.view.ui.Atlas;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** @author Ivan Delgado Huerta */
public class TSubformHeaderView extends SpellHeaderView implements PropertyChangeListener, Disposable
{
    private TemplateSpellSubform model;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    @Override public void dispose()
    {
        if (model != null)
            model.removeObserver(this);
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(TemplateSpellSubform spellForm)
    {
        dispose();

        if (spellForm == null)
            removeModel();
        else
        {
            model = spellForm;
            model.addObserver(this);
            update();
        }
    }

    private void removeModel()
    {   removeAll(); }

    private void update()
    {
        setIcon(Atlas.get().getTexture("TexturasIconos/FireBall"));
        setAvailableTotal(String.format("%s/%s", model.getAvailable(), model.getTotal()));
        setrAvailableTotalColor(model.getAvailable() > 0 ? Color.GREEN : Color.RED);
        setKeys(model.getKeys().toString());
        setIconName(model.getName());
        setCost(model.getBaseCost().toString());
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   update(); }
}