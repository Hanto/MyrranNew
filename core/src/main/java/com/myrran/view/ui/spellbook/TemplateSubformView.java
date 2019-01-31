package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.templates.TemplateSpellSubform;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.widgets.DetailedActorI;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** @author Ivan Delgado Huerta */
public class TemplateSubformView extends Table implements DetailedActorI, Disposable, PropertyChangeListener
{
    private TemplateSpellSubform model;
    private CustomSpellController controller;
    private SpellHeaderView header;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public TemplateSubformView(CustomSpellController customSpellController)
    {
        controller  = customSpellController;
        header      = new SpellHeaderView();

        createLayout();
    }


    @Override public void dispose()
    {
        disposeObservers();
   }

    private void disposeObservers()
    {
        if (model != null)
            model.removeObserver(this);
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(TemplateSpellSubform templateSpellSubform)
    {
        disposeObservers();

        if (templateSpellSubform == null)
            removeModel();
        else
        {
            model = templateSpellSubform;
            model.addObserver(this);
            update();
        }

    }

    private void removeModel()
    {   header.removeAll(); }

    private void update()
    {
        header.setIcon(Atlas.get().getTexture("TexturasIconos/FireBall"));
        header.setAvailableTotal(String.format("%s/%s", model.getAvailable(), model.getTotal()));
        header.setrAvailableTotalColor(model.getAvailable() > 0 ? Color.GREEN : Color.RED);
        header.setKeys(model.getKeys().toString());
        header.setIconName(model.getName());
        header.setCost(model.getBaseCost().toString());
    }

    private void createLayout()
    {
        clear();
        top().left();
        add(header).bottom().left().row();
    }

    // MISC:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void showDetails(boolean visible)
    {

    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   update(); }
}
