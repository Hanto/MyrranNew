package com.myrran.view.ui.customspell;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomSpellForm;
import com.myrran.model.spell.generators.CustomSubformSlot;
import com.myrran.view.ui.customspell.header.CFormHeaderView;
import com.myrran.view.ui.listeners.ActorMoveListener;
import com.myrran.view.ui.listeners.TouchDownListener;
import com.myrran.view.ui.widgets.DetailedActorI;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class CustomSpellView extends Table implements Disposable, DetailedActorI
{
    private CustomSpellForm model;
    private CustomSpellController controller;

    private CFormHeaderView header;
    private CustomFormView formView;
    private List<CustomSubFormSlotView> subForms;

    private Table tableDetails;
    private Table tableSubforms;

    private boolean detailsVisible = false;
    private Cell<Actor> cellDetails;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomSpellView(CustomSpellController spellController, boolean movable)
    {
        controller      = spellController;
        header          = new CFormHeaderView();
        formView        = new CustomFormView(controller);
        tableDetails    = new Table();
        tableSubforms   = new Table();

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
        header.dispose();
        formView.dispose();

        if (subForms != null)
            subForms.forEach(CustomSubFormSlotView::dispose);
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
            formView.setModel(model);
            header.setModel(model);
            createSubformsLayout();
        }
    }

    private void removeModel()
    {
        clear();
        header.removeAll();
        model = null;
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
        tableDetails.add(tableSubforms).top().left().row();
    }

    private void createSubformsLayout()
    {
        subForms = model.getSubformSlots().getCustomSubformSlots().stream()
            .sorted(Comparator.comparing(CustomSubformSlot::getID))
            .map(this::addSubformIcons)
            .collect(Collectors.toList());

        tableSubforms.clear();
        tableSubforms.top().left();
        subForms.forEach(icon -> tableSubforms.add(icon).left().row());
    }

    private CustomSubFormSlotView addSubformIcons(CustomSubformSlot slot)
    {
        CustomSubFormSlotView view = new CustomSubFormSlotView(controller);
        view.setModel(slot);
        return view;
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
}