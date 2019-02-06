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
    private CustomSpellDebuff spellDebuff;
    private CustomSpellController controller;

    private CustomStatsView stats;
    private WidgetText name;
    private WidgetText totalCost;

    private static final BitmapFont font14 = Atlas.get().getFont("11");
    private static final BitmapFont font10 = Atlas.get().getFont("10");
    private static final Color magenta = new Color(170/255f, 70/255f, 255/255f, 1f);

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public DebuffSlotStatsView(CustomSpellController customSpellController)
    {
        controller      = customSpellController;
        name            = new WidgetText(font14, Color.ORANGE, Color.BLACK, 1);
        totalCost       = new WidgetText(font10, magenta, Color.BLACK, 1);
        stats           = new CustomStatsView(controller);

        createLayout();
    }

    @Override public void dispose()
    {
        stats.dispose();

        if (debuffSlot != null)
            debuffSlot.removeObserver(this);

        if (spellDebuff != null)
            spellDebuff.removeObserver(this);
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(SpellSlotI<CustomSpellDebuff, TemplateSpellDebuff> customDebuffSlot)
    {
        dispose();

        if (customDebuffSlot == null)
            removeModel();
        else
        {
            debuffSlot = customDebuffSlot;
            spellDebuff = debuffSlot.getContent();
            debuffSlot.addObserver(this);
            spellDebuff.addObserver(this);
            update();
        }
    }

    private void removeModel()
    {
        debuffSlot = null;
        stats.setModel(null);
        name.setText(null);
        totalCost.setText(null);
    }

    private void update()
    {
        if (debuffSlot.hasData())
        {
            stats.setModel(spellDebuff);
            name.setText(spellDebuff.getName());
            totalCost.setText(spellDebuff.getTotalCost().toString());
        }
        else
        {
            stats.setModel(null);
            name.setText(null);
            totalCost.setText(null);
        }
    }

    // CREATE LAYOUT:
    //--------------------------------------------------------------------------------------------------------

    private void createLayout()
    {
        int vPad = -4;

        Table header = new Table().top().left();
        header.add(name).bottom().left();
        header.add(totalCost).bottom().right().row();

        top().left();
        add(stats).left().row();
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   update(); }
}
