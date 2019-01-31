package com.myrran.view.ui.customspell;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomDebuffSlot;
import com.myrran.model.spell.generators.CustomSpellForm;
import com.myrran.model.spell.generators.CustomSubformSlot;
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
    private Table tableDetails;
    private Table tableStats;
    private Table tableDebuffIcons;
    private Table tableSubformIcons;
    private WidgetText name;
    private WidgetText templateID;
    private WidgetText totalCost;
    private CustomStatsView stats;
    private List<CustomDebuffStatsView> debuffStats;
    private List<CustomDebuffIconView> debuffIcons;
    private List<CustomSubformIconView> subformIcons;

    private boolean detailsVisible = false;
    private Cell<Actor> cellDetails;

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
        tableDetails= new Table();
        tableDebuffIcons = new Table().top().left();
        tableSubformIcons= new Table();
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
        createDetailsLayout();
        cellDetails = getCell(tableDetails);
        showDetails();
    }

    public CustomFormView(CustomSpellController spellController)
    {   this(spellController, true); }

    @Override public void dispose()
    {
        stats.dispose();

        if (debuffStats != null)
            debuffStats.forEach(CustomDebuffStatsView::dispose);

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
            createSubformIconsLayout();
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
        add(tableHeader).bottom().left().row();
        add(tableDetails).top().left();
    }

    private void createHeaderLayout()
    {
        Table nameID = new Table().bottom().left();
        nameID.add(templateID)      .bottom().left().padTop(VPAD).padBottom(VPAD).row();
        nameID.add(name)            .bottom().left().padTop(VPAD).padBottom(VPAD).row();

        tableHeader.clear();
        tableHeader.bottom().left();
        tableHeader.add(spellIcon)  .bottom().left().padRight(3);
        tableHeader.add(nameID)     .bottom().left().minWidth(100);
        //tableHeader.add(totalCost)  .bottom().padTop(VPAD).padBottom(VPAD+2f).left();
        //tableHeader.add(tableDebuffIcons).bottom().left();
    }

    private void createDetailsLayout()
    {
        tableDetails.clear();
        tableDetails.top().left();
        tableDetails.add().size(32+3, 0);
        tableDetails.add(tableDebuffIcons).bottom().left().row();
        tableDetails.add().size(32+3, 0);
        tableDetails.add(tableStats).top().left().padRight(3);
        //tableDetails.add(tableSubformIcons).top().left().row();
    }

    private void createStatsLayout()
    {
        debuffStats = model.getDebuffSlots().getCustomDebuffSlots().stream()
            .sorted(Comparator.comparing(CustomDebuffSlot::getID))
            .map(this::addDebuffStats)
            .collect(Collectors.toList());

        tableStats.clear();
        tableStats.add(stats).left().row();
        tableStats.padTop(4);
        tableStats.padBottom(8);
        debuffStats.forEach(debuffDetails -> tableStats.add(debuffDetails).left().row());
    }

    private void createDebuffIconsLayout()
    {
        debuffIcons = model.getDebuffSlots().getCustomDebuffSlots().stream()
            .sorted(Comparator.comparing(CustomDebuffSlot::getID))
            .map(this::addDebuffIcons)
            .collect(Collectors.toList());

        tableDebuffIcons.clear();
        debuffIcons.forEach(debuffIcon -> tableDebuffIcons.add(debuffIcon).left());
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

    private CustomSubformIconView addSubformIcons(CustomSubformSlot slot)
    {
        CustomSubformIconView icon = new CustomSubformIconView(controller);
        icon.setModel(slot);
        return icon;
    }

    // MISC:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void showDetails(boolean visible)
    {
        cellDetails.setActor(visible ? tableDetails: null);
        detailsVisible = !visible;
    }

    public void showDetails()
    {   showDetails(detailsVisible); }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   update(); }
}