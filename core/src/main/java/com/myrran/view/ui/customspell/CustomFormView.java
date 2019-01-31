package com.myrran.view.ui.customspell;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomDebuffSlot;
import com.myrran.model.spell.generators.CustomFormI;
import com.myrran.view.ui.listeners.TouchDownListener;
import com.myrran.view.ui.widgets.DetailedActorI;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class CustomFormView extends Table implements PropertyChangeListener, Disposable, DetailedActorI
{
    private CustomFormI model;
    private CustomSpellController controller;

    private Actor icon;
    private CustomStatsView formStats;
    private Table slots;
    private Table stats;
    private List<CustomDebuffIconView> slotList;
    private List<CustomDebuffStatsView> statList;

    private boolean detailsVisible = false;
    private Cell<Actor> detailsCell;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomFormView(CustomSpellController spellController, Actor actor)
    {
        controller  = spellController;
        icon        = actor;
        formStats   = new CustomStatsView(controller);
        slots       = new Table();
        stats       = new Table();

        createLayout();
        detailsCell = getCell(stats);
        showDetails();

        addListener(new TouchDownListener(o -> {if (o.getButton() == Input.Buttons.LEFT) showDetails();}));
    }

    private void disposeObservers()
    {
        if (model != null)
            model.removeObserver(this);

        if (slotList != null)
            slotList.forEach(CustomDebuffIconView::dispose);

        if (statList != null)
            statList.forEach(CustomDebuffStatsView::dispose);
    }

    @Override public void dispose()
    {
        disposeObservers();
        formStats.dispose();
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomFormI customForm)
    {
        disposeObservers();

        if (customForm == null)
            removeModel();
        else
        {
            model = customForm;
            model.addObserver(this);
            update();
            createSlotsLayout();
            createStatsLayout();
        }
    }

    private void removeModel()
    {
        clear();
        formStats.setModel(null);
    }

    private void update()
    {  formStats.setModel(model); }

    // CREATE LAYOUT:
    //--------------------------------------------------------------------------------------------------------

    private void createLayout()
    {
        clear();
        top().left();
        add(slots).left().bottom().row();
        add(stats).left().row();
    }

    private void createSlotsLayout()
    {
        slots.clear();
        slots.top().left();

        slotList =model.getCustomDebuffSlots().stream()
            .sorted(Comparator.comparing(CustomDebuffSlot::getID))
            .map(this::addDebuffIcons)
            .collect(Collectors.toList());

        slots.add(icon).left().bottom();
        slotList.forEach(icon -> slots.add(icon).left());
    }

    private void createStatsLayout()
    {
        stats.clear();
        stats.top().left();
        stats.padBottom(4).padLeft(4).padTop(2);

        statList = model.getCustomDebuffSlots().stream()
            .sorted(Comparator.comparing(CustomDebuffSlot::getID))
            .map(this::addDebuffStats)
            .collect(Collectors.toList());

        stats.add(formStats).left().bottom().row();
        statList.forEach(debuff -> stats.add(debuff).left().row());
    }

    private CustomDebuffStatsView addDebuffStats(CustomDebuffSlot slot)
    {
        CustomDebuffStatsView details = new CustomDebuffStatsView(controller);
        details.setModel(slot);
        return details;
    }

    private CustomDebuffIconView addDebuffIcons(CustomDebuffSlot slot)
    {
        CustomDebuffIconView icon = new CustomDebuffIconView(controller);
        icon.setModel(slot);
        return icon;
    }

    // MISC:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void showDetails(boolean visible)
    {
        detailsCell.setActor(visible ? stats : null);
        detailsVisible = !visible;
    }

    public void showDetails()
    {   showDetails(detailsVisible); }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   update(); }

}
