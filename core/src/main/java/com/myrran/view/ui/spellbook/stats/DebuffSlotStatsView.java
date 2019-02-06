package com.myrran.view.ui.spellbook.stats;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomSpellDebuff;
import com.myrran.model.spell.generators.SpellSlotI;
import com.myrran.model.spell.templates.TemplateSpellDebuff;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.widgets.WidgetText;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


/** @author Ivan Delgado Huerta */
public class DebuffSlotStatsView extends Table implements PropertyChangeListener, Disposable
{
    private SpellSlotI<CustomSpellDebuff, TemplateSpellDebuff> debuffSlot;
    private CustomSpellController controller;

    private CustomStatsView stats;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public DebuffSlotStatsView(CustomSpellController customSpellController)
    {
        controller      = customSpellController;
        stats           = new CustomStatsView(controller);

        createLayout();
    }

    @Override public void dispose()
    {
        stats.dispose();

        if (debuffSlot != null)
            debuffSlot.removeObserver(this);
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(SpellSlotI<CustomSpellDebuff, TemplateSpellDebuff> customDebuffSlot)
    {
        dispose();

        if (customDebuffSlot == null)
            stats.setModel(null);
        else
        {
            debuffSlot = customDebuffSlot;
            debuffSlot.addObserver(this);
            update();
        }
    }

    private void update()
    {
        if (debuffSlot.hasData())
            stats.setModel(debuffSlot.getContent());
        else
            stats.setModel(null);
    }

    // CREATE LAYOUT:
    //--------------------------------------------------------------------------------------------------------

    private void createLayout()
    {
        top().left();
        add(stats).left().row();
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   update(); }
}
