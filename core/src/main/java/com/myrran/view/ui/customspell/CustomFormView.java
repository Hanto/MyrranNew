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
import com.myrran.view.ui.spellbook.SpellHeaderView;
import com.myrran.view.ui.widgets.DetailedActorI;
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

    private SpellHeaderView header;

    private Table tableDetails;
    private Table tableStats;
    private Table tableDebuffIcons;
    private Table tableSubformIcons;
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
        header      = new SpellHeaderView();
        tableDetails= new Table();
        tableDebuffIcons = new Table().top().left();
        tableSubformIcons= new Table();
        tableStats  = new Table().top().left();
        stats       = new CustomStatsView(controller);
        totalCost   = new WidgetText(font14, magenta, Color.BLACK, 1);

        if (movable)
            header.getIcon().addListener(new ActorMoveListener(this));

        header.getIconName().addListener(new TouchDownListener(o -> showDetails()));

        createLayout();
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
        header.removeAll();
        model = null;
        stats.setModel(null);
        totalCost.setText(null);
    }

    private void update()
    {
        header.setIcon(Atlas.get().getTexture("TexturasIconos/FireBall"));
        header.setIconName(model.getName());
        header.setKeys(model.getTemplateID().toUpperCase());
        header.setCost(model.getTotalCost().toString());
        stats.setModel(model);
        totalCost.setText(String.format("%s(%s)", model.getStatsCost(), model.getTotalCost()));
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