package com.myrran.view.ui.customspell;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.custom.CustomSpellDebuff;
import com.myrran.model.spell.generators.custom.CustomDebuffSlot;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.widgets.WidgetText;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


/** @author Ivan Delgado Huerta */
public class SpellDebuffDetails extends Table implements PropertyChangeListener, Disposable
{
    private CustomDebuffSlot debuffSlot;
    private CustomSpellDebuff spellDebuff;
    private CustomSpellController controller;

    private SpellStats stats;
    private WidgetText name;
    private WidgetText totalCost;

    private static final BitmapFont font14 = Atlas.get().getFont("14");
    private static final BitmapFont font11 = Atlas.get().getFont("11");
    private static final Color magenta = new Color(170/255f, 70/255f, 255/255f, 1f);

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SpellDebuffDetails(CustomSpellController customSpellController)
    {
        controller      = customSpellController;
        name            = new WidgetText(font14, Color.ORANGE, Color.BLACK, 1);
        totalCost       = new WidgetText(font14, magenta, Color.BLACK, 1);
        stats           = new SpellStats(controller);

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

    public void setModel(CustomDebuffSlot customDebuffSlot)
    {
        dispose();

        if (customDebuffSlot == null)
            removeModel();
        else
        {
            debuffSlot = customDebuffSlot;
            spellDebuff = debuffSlot.getCustomSpellDebuff();
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
        if (debuffSlot.hasDebuff())
        {
            stats.setModel(spellDebuff);
            name.setText(spellDebuff.getName());
            totalCost.setText(String.format("%s(%s)", spellDebuff.getStatsCost(), spellDebuff.getTotalCost()));
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
        header.add(name).bottom().left().padBottom(vPad);
        header.add(totalCost).bottom().right().padBottom(vPad).row();

        top().left();
        add(header).left().row();
        add(stats);
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   update(); }
}
