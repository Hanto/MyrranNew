package com.myrran.view.ui.customspell;

import com.badlogic.gdx.utils.Disposable;
import com.myrran.model.spell.generators.CustomSpellForm;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.spellbook.SpellHeaderView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** @author Ivan Delgado Huerta */
public class CustomFormHeaderView extends SpellHeaderView implements PropertyChangeListener, Disposable
{
    private CustomSpellForm model;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    @Override public void dispose()
    {
        if (model != null)
            model.removeObserver(this);
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomSpellForm spellForm)
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
        setIconName(model.getName());
        setKeys(model.getTemplateID().toUpperCase());
        setCost(model.getTotalCost().toString());
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   update(); }
}
