package com.myrran.view.ui.formview2;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.custom.CustomSpellForm;
import com.myrran.model.spell.generators.custom.CustomDebuffSlot;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.widgets.WidgetText;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class SpellFormView2 extends Group implements PropertyChangeListener, Disposable
{
    private CustomSpellForm model;
    private CustomSpellController controller;

    private Table table;
    private SpellStatsView2 stats;
    private WidgetText name;
    private WidgetText totalCost;
    private List<SpellDebuffDetails>formDebuffs;
    private List<DebuffSlotIcon>debuffIcons;

    private static final BitmapFont font20 = Atlas.get().getFont("20");
    private static final BitmapFont font14 = Atlas.get().getFont("14");
    private static final Color magenta = new Color(170/255f, 70/255f, 255/255f, 1f);

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SpellFormView2(CustomSpellController spellController)
    {
        controller  = spellController;
        table       = new Table().top().left();
        stats       = new SpellStatsView2(controller);
        name        = new WidgetText(font20, Color.ORANGE, Color.BLACK, 2);
        totalCost   = new WidgetText(font14, magenta, Color.BLACK, 2);
        addActor(table);
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

    public void removeModel()
    {
        dispose();

        table.clear();
        model = null;
        stats.setModel(null);
        name.setText(null);
        totalCost.setText(null);
    }

    private void update()
    {
        stats.setModel(model);
        name.setText(model.getName());
        totalCost.setText(model.getTotalCost().toString());
    }

    private void createLayout()
    {
        Table header = new Table().top().left();
        header.add(name).bottom().left().padBottom(-5);
        header.add(totalCost).bottom().right().padBottom(-5).row();

        Table tableStats = new Table().top().left();
        tableStats.add(header).left().row();
        tableStats.add(stats).left().row();

        Table tableIcons = new Table().top().left().padTop(20);

        table.clear();
        table.top().left();
        table.add(tableStats).left();
        table.add(tableIcons).top().left();

        if (model != null)
        {
            formDebuffs = model.getDebuffSlots().values().stream()
                .map(this::addDebuffViews)
                .collect(Collectors.toList());

            debuffIcons = model.getDebuffSlots().values().stream()
                .map(this::addDebuffSlotIcons)
                .collect(Collectors.toList());

            formDebuffs.forEach(spellDebuffDetails -> tableStats.add(spellDebuffDetails).left().row());

            debuffIcons.forEach(debuffSlotIcon -> tableIcons.add(debuffSlotIcon).left().row());
        }
        update();
    }

    private SpellDebuffDetails addDebuffViews(CustomDebuffSlot slot)
    {
        SpellDebuffDetails details = new SpellDebuffDetails(controller);
        details.setModel(slot);
        return details;
    }

    private DebuffSlotIcon addDebuffSlotIcons(CustomDebuffSlot slot)
    {
        DebuffSlotIcon icon = new DebuffSlotIcon(controller);
        icon.setModel(slot);
        return icon;
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   update(); }


}