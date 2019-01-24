package com.myrran.view.ui.customspell;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.custom.CustomDebuffSlot;
import com.myrran.model.spell.generators.custom.CustomSpellForm;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.widgets.WidgetImage;
import com.myrran.view.ui.widgets.WidgetText;
import com.myrran.view.ui.listeners.ActorMoveListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class CustomFormView extends Table implements PropertyChangeListener, Disposable
{
    private CustomSpellForm model;
    private CustomSpellController controller;

    private WidgetImage spellIcon;
    private Image background;
    private Table tableHeader;
    private Table tableIcons;
    private Table tableStats;
    private CustomStatsView stats;
    private WidgetText name;
    private WidgetText totalCost;
    private List<CustomDebuffDetailsView>formDebuffs;
    private List<CustomDebuffIcon>debuffIcons;

    private static final BitmapFont font20 = Atlas.get().getFont("20");
    private static final BitmapFont font14 = Atlas.get().getFont("14");
    private static final Color magenta = new Color(170/255f, 70/255f, 255/255f, 1f);

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomFormView(CustomSpellController spellController)
    {
        controller  = spellController;
        tableHeader = new Table().top().left();
        tableIcons  = new Table().top().left().padTop(10);
        tableStats  = new Table().top().left();
        spellIcon   = new WidgetImage();
        background  = Atlas.get().getImage("TexturasMisc/Casillero2");
        stats       = new CustomStatsView(controller);
        name        = new WidgetText(font20, Color.ORANGE, Color.BLACK, 2);
        totalCost   = new WidgetText(font14, magenta, Color.BLACK, 2);
    }

    @Override public void dispose()
    {
        stats.dispose();

        if (formDebuffs != null)
            formDebuffs.forEach(CustomDebuffDetailsView::dispose);

        if (model != null)
            model.removeObserver(this);
    }

    // CREATE / UPDATE:
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
            createLayout();
        }
    }

    private void removeModel()
    {
        clear();
        model = null;
        spellIcon.setTexureRegion((Atlas.get().getTexture("TexturasIconos/IconoVacio")));
        stats.setModel(null);
        name.setText(null);
        totalCost.setText(null);
    }

    private void update()
    {
        spellIcon.setTexureRegion((Atlas.get().getTexture("TexturasIconos/FireBall")));
        stats.setModel(model);
        name.setText(model.getName());
        totalCost.setText(String.format("%s(%s)", model.getStatsCost(), model.getTotalCost()));
    }

    private void createLayout()
    {
        clear();
        top().left();
        add(spellIcon).top().left().padTop(10).padRight(3);
        add(tableStats).top().left().padRight(3);
        add(tableIcons).top().left();

        tableHeader.clear();
        tableHeader.add(name).bottom().left().padBottom(-5);
        tableHeader.add(totalCost).bottom().right().padBottom(-3).row();

        tableStats.clear();
        tableStats.add(tableHeader).left().row();
        tableStats.add(stats).left().row();

        tableIcons.clear();

        formDebuffs = model.getDebuffSlots().values().stream()
            .sorted(Comparator.comparing(CustomDebuffSlot::getID))
            .map(this::addDebuffViews)
            .collect(Collectors.toList());

        debuffIcons = model.getDebuffSlots().values().stream()
            .sorted(Comparator.comparing(CustomDebuffSlot::getID))
            .map(this::addDebuffSlotIcons)
            .collect(Collectors.toList());

        formDebuffs.forEach(debuffDetails -> tableStats.add(debuffDetails).left().row());
        debuffIcons.forEach(debuffIcon -> tableIcons.add(debuffIcon).left().row());

        background.setColor(1f, 1f, 1f, 0.40f);
        spellIcon.addListener(new ActorMoveListener(this));

        update();
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

    public void invalidateHierarchy()
    {
        super.invalidateHierarchy();
        setBackgroundBounds();
    }

    private void setBackgroundBounds()
    {   background.setSize(tableStats.getMinWidth() +5, -tableStats.getMinHeight() +5); }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   update(); }

    @Override public void draw (Batch batch, float alpha)
    {
        background.setPosition(spellIcon.getWidth() + getX() -2, getY()-10);
        background.draw(batch, this.getColor().a * alpha);
        super.draw(batch, this.getColor().a * alpha);
    }
}