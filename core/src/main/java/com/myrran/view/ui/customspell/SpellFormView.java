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
import com.myrran.view.ui.listeners.MoveActorListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class SpellFormView extends Table implements PropertyChangeListener, Disposable
{
    private CustomSpellForm model;
    private CustomSpellController controller;

    private WidgetImage spellIcon;
    private Image background;
    private Table tableIcons;
    private SpellStats stats;
    private WidgetText name;
    private WidgetText totalCost;
    private List<SpellDebuffDetails>formDebuffs;
    private List<SpellDebuffIcon>debuffIcons;

    private static final BitmapFont font20 = Atlas.get().getFont("20");
    private static final BitmapFont font14 = Atlas.get().getFont("14");
    private static final Color magenta = new Color(170/255f, 70/255f, 255/255f, 1f);

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SpellFormView(CustomSpellController spellController)
    {
        controller  = spellController;
        spellIcon   = new WidgetImage();
        background  = Atlas.get().getImage("TexturasMisc/Casillero2");
        stats       = new SpellStats(controller);
        name        = new WidgetText(font20, Color.ORANGE, Color.BLACK, 2);
        totalCost   = new WidgetText(font14, magenta, Color.BLACK, 2);
    }

    @Override public void dispose()
    {
        stats.dispose();

        if (formDebuffs != null)
            formDebuffs.forEach(SpellDebuffDetails::dispose);

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
        Table header = new Table().top().left();
        header.add(name).bottom().left().padBottom(-5);
        header.add(totalCost).bottom().right().padBottom(-5).row();

        Table tableStats = new Table().top().left();
        tableStats.add(header).left().row();
        tableStats.add(stats).left().row();

        tableIcons = new Table().top().left().padTop(10);

        clear();
        top().left();
        add(spellIcon).top().left().padTop(10).padRight(3);
        add(tableStats).left().padRight(3);
        add(tableIcons).top().left();

        formDebuffs = model.getDebuffSlots().values().stream()
            .map(this::addDebuffViews)
            .collect(Collectors.toList());

        debuffIcons = model.getDebuffSlots().values().stream()
            .map(this::addDebuffSlotIcons)
            .collect(Collectors.toList());

        formDebuffs.forEach(debuffDetails -> tableStats.add(debuffDetails).left().row());
        debuffIcons.forEach(debuffIcon -> tableIcons.add(debuffIcon).left().row());

        background.setColor(1f, 1f, 1f, 0.55f);
        spellIcon.addListener(new MoveActorListener(this));

        update();
    }

    private SpellDebuffDetails addDebuffViews(CustomDebuffSlot slot)
    {
        SpellDebuffDetails details = new SpellDebuffDetails(controller);
        details.setModel(slot);
        return details;
    }

    private SpellDebuffIcon addDebuffSlotIcons(CustomDebuffSlot slot)
    {
        SpellDebuffIcon icon = new SpellDebuffIcon(controller);
        icon.setModel(slot);
        return icon;
    }

    public void invalidateHierarchy()
    {
        super.invalidateHierarchy();
        setBackgroundBounds();
    }

    private void setBackgroundBounds()
    {   background.setSize(getMinWidth() -tableIcons.getMinWidth() -spellIcon.getWidth(), -getMinHeight() +5); }

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