package com.myrran.view.ui.customspell;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomSpellForm;
import com.myrran.model.spell.generators.CustomSubformSlot;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.listeners.ActorMoveListener;
import com.myrran.view.ui.listeners.TouchDownListener;
import com.myrran.view.ui.spellbook.SpellHeaderView;
import com.myrran.view.ui.widgets.DetailedActorI;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class CustomSpellView extends Table implements PropertyChangeListener, Disposable, DetailedActorI
{
    private CustomSpellForm model;
    private CustomSpellController controller;

    private SpellHeaderView header;
    private CustomFormView formView;
    private CustomFormIconView icon;

    private Table tableDetails;
    private Table tableSubformIcons;
    private List<CustomFormView> subformIcons;

    private boolean detailsVisible = false;
    private Cell<Actor> cellDetails;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomSpellView(CustomSpellController spellController, boolean movable)
    {
        controller  = spellController;
        header      = new SpellHeaderView();
        icon        = new CustomFormIconView(controller);
        formView    = new CustomFormView(controller, icon);


        tableDetails= new Table();
        tableSubformIcons= new Table();

        if (movable)
            header.getIcon().addListener(new ActorMoveListener(this));

        header.getIconName().addListener(new TouchDownListener(o -> showDetails()));

        createLayout();
        createDetailsLayout();
        cellDetails = getCell(tableDetails);
        showDetails();
    }

    public CustomSpellView(CustomSpellController spellController)
    {   this(spellController, true); }

    @Override public void dispose()
    {
        if (model != null)
            model.removeObserver(this);

        if (icon != null)
            icon.dispose();
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomSpellForm customSpellForm)
    {
        dispose();

        if (customSpellForm == null)
            removeModel();
        else
        {
            model = customSpellForm;
            model.addObserver(this);
            formView.setModel(model);
            createSubformIconsLayout();
            update();
        }
    }

    private void removeModel()
    {
        clear();
        icon.setModel(null);
        header.removeAll();
        model = null;
    }

    private void update()
    {
        icon.setModel(model);
        header.setIcon(Atlas.get().getTexture("TexturasIconos/FireBall"));
        header.setIconName(model.getName());
        header.setKeys(model.getTemplateID().toUpperCase());
        header.setCost(model.getTotalCost().toString());
    }

    // CREATE LAYOUTS:
    //--------------------------------------------------------------------------------------------------------

    private void createLayout()
    {
        clear();
        top().left();
        add(header).bottom().left().row();
        add(tableDetails).top().left();
    }

    private void createDetailsLayout()
    {
        tableDetails.clear();
        tableDetails.top().left();
        tableDetails.padBottom(8);

        tableDetails.add().size(32+3, 0);
        tableDetails.add(formView).top().left().row();
        tableDetails.add().size(32+3, 0);
        tableDetails.add(tableSubformIcons).top().left().row();
    }

    private void createSubformIconsLayout()
    {
        subformIcons = model.getSubformSlots().getCustomSubformSlots().stream()
            .sorted(Comparator.comparing(CustomSubformSlot::getID))
            .map(this::addSubformIcons)
            .collect(Collectors.toList());

        tableSubformIcons.clear();
        tableSubformIcons.top().left();
        subformIcons.forEach(icon -> tableSubformIcons.add(icon).left().row());
    }

    private CustomFormView addSubformIcons(CustomSubformSlot slot)
    {
        CustomSubformIconView icon = new CustomSubformIconView(controller);
        CustomFormView subFormView = new CustomFormView(controller, icon);
        icon.setModel(slot);
        if (slot.hasData())
            subFormView.setModel(slot.getCustomSpellSubform());
        return subFormView;
    }

    // MISC:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void showDetails(boolean visible)
    {
        cellDetails.setActor(visible ? tableDetails : null);
        detailsVisible = !visible;
    }

    public void showDetails()
    {   showDetails(detailsVisible); }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   update(); }
}