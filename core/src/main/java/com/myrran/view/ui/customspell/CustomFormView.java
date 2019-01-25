package com.myrran.view.ui.customspell;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.custom.CustomDebuffSlot;
import com.myrran.model.spell.generators.custom.CustomSpellForm;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.listeners.ActorMoveListener;
import com.myrran.view.ui.listeners.TouchDownListener;
import com.myrran.view.ui.widgets.DetailedActorI;
import com.myrran.view.ui.widgets.WidgetImage;
import com.myrran.view.ui.widgets.WidgetText;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class CustomFormView extends Table implements PropertyChangeListener, Disposable, DetailedActorI
{
    private CustomSpellForm model;
    private CustomSpellController controller;

    private WidgetImage spellIcon;
    private Table tableHeader;
    private Table tableIcons;
    private Table tableStats;
    private WidgetText name;
    private WidgetText templateID;
    private WidgetText totalCost;
    private CustomStatsView stats;
    private List<CustomDebuffDetailsView> debuffStats;
    private List<CustomDebuffIcon> debuffIcons;

    private boolean detailsVisible = true;
    private Cell<Actor>cellIcons;
    private Cell<Actor>cellStats;

    private static final int VPAD = -4;
    private static final BitmapFont font20 = Atlas.get().getFont("20");
    private static final BitmapFont font14 = Atlas.get().getFont("14");
    private static final BitmapFont font10 = Atlas.get().getFont("10");
    private static final Color magenta = new Color(170/255f, 70/255f, 255/255f, 1f);

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomFormView(CustomSpellController spellController, boolean movable)
    {
        controller  = spellController;
        tableHeader = new Table();
        tableIcons  = new Table().top().left();
        tableStats  = new Table().top().left();
        spellIcon   = new WidgetImage();
        stats       = new CustomStatsView(controller);
        name        = new WidgetText(font20, Color.ORANGE, Color.BLACK, 2);
        templateID  = new WidgetText(font10, Color.WHITE, Color.BLACK, 1);
        totalCost   = new WidgetText(font14, magenta, Color.BLACK, 1);

        if (movable)
            spellIcon.addListener(new ActorMoveListener(this));

        name.addListener(new TouchDownListener(o -> showDetails()));

        createLayout();
        createHeaderLayout();
        cellStats = getCell(tableStats);
        cellIcons = getCell(tableIcons);
    }

    public CustomFormView(CustomSpellController spellController)
    {   this(spellController, true); }

    @Override public void dispose()
    {
        stats.dispose();

        if (debuffStats != null)
            debuffStats.forEach(CustomDebuffDetailsView::dispose);

        if (model != null)
            model.removeObserver(this);
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
            createStatsLayout();
            createDebuffIconsLayout();
            update();
        }
    }

    private void removeModel()
    {
        clear();
        model = null;
        spellIcon.setTexureRegion((Atlas.get().getTexture("TexturasIconos/IconoVacio")));
        stats.setModel(null);
        name.setText(null);
        templateID.setText(null);
        totalCost.setText(null);
    }

    private void update()
    {
        spellIcon.setTexureRegion((Atlas.get().getTexture("TexturasIconos/FireBall")));
        stats.setModel(model);
        name.setText(model.getName());
        templateID.setText(model.getTemplateID().toUpperCase());
        totalCost.setText(String.format("%s(%s)", model.getStatsCost(), model.getTotalCost()));
    }

    // CREATE LAYOUTS:
    //--------------------------------------------------------------------------------------------------------

    private void createLayout()
    {
        clear();
        top().left();
        add(spellIcon).bottom().left().padRight(3);
        add(tableHeader).bottom().left().row();
        add();
        add(tableStats).top().left().padRight(3);
        add(tableIcons).top().left();
        tableStats.padBottom(8);
    }

    private void createHeaderLayout()
    {
        tableHeader.clear();
        tableHeader.bottom().left();
        tableHeader.add(templateID) .bottom().padTop(VPAD).padBottom(VPAD).left().row();
        tableHeader.add(name)       .bottom().padTop(VPAD).padBottom(VPAD).left();
        tableHeader.add(totalCost)  .bottom().padTop(VPAD).padBottom(VPAD+2).left().row();
    }

    private void createStatsLayout()
    {
        debuffStats = model.getDebuffSlots().values().stream()
            .sorted(Comparator.comparing(CustomDebuffSlot::getID))
            .map(this::addDebuffViews)
            .collect(Collectors.toList());

        tableStats.clear();
        tableStats.add(stats).left().row();
        debuffStats.forEach(debuffDetails -> tableStats.add(debuffDetails).left().row());
    }

    private void createDebuffIconsLayout()
    {
        debuffIcons = model.getDebuffSlots().values().stream()
            .sorted(Comparator.comparing(CustomDebuffSlot::getID))
            .map(this::addDebuffSlotIcons)
            .collect(Collectors.toList());

        tableIcons.clear();
        debuffIcons.forEach(debuffIcon -> tableIcons.add(debuffIcon).left().row());
    }

    private CustomDebuffDetailsView addDebuffViews(CustomDebuffSlot slot)
    {
        CustomDebuffDetailsView details = new CustomDebuffDetailsView(controller);
        details.setModel(slot);
        return details;
    }

    private CustomDebuffIcon addDebuffSlotIcons(CustomDebuffSlot slot)
    {
        CustomDebuffIcon icon = new CustomDebuffIcon(controller);
        icon.setModel(slot);
        return icon;
    }

    // MISC:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void showDetails(boolean visible)
    {
        if (!visible)
        {
            cellStats.setActor(null);
            cellIcons.setActor(null);
        }
        else
        {
            cellIcons.setActor(tableIcons);
            cellStats.setActor(tableStats);
        }
        detailsVisible = !visible;
    }

    public void showDetails()
    {   showDetails(detailsVisible); }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   update(); }
}