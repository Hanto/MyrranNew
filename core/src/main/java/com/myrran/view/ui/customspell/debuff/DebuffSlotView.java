package com.myrran.view.ui.customspell.debuff;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.spell.generators.custom.debuffslot.CustomDebuffSlot;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.TextView;
import com.myrran.view.ui.customspell.stats.SpellStatsView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** @author Ivan Delgado Huerta */
public class DebuffSlotView implements Disposable, PropertyChangeListener
{
    private CustomDebuffSlot model;

    private boolean isFull = false;
    private TextView name;
    private SpellStatsView debuffStats;
    private DebuffView debuffView;

    public boolean isFull()                     { return isFull; }
    public TextView getName()                   { return name; }
    public SpellStatsView getDebuffStats()      { return debuffStats; }
    public DebuffView getDebuffView()           { return debuffView; }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public DebuffSlotView(CustomDebuffSlot customDebuffSlot)
    {
        this.model = customDebuffSlot;
        this.model.addObserver(this);

        createView();
        updateView();
    }

    @Override public void dispose()
    {
        model.removeObserver(this);
        if (debuffStats!= null) debuffStats.dispose();
    }

    // CREATE / UPDATE:
    //--------------------------------------------------------------------------------------------------------

    private void createView()
    {
        BitmapFont font11 = Atlas.get().getFont("11");

        name = new TextView(font11, Color.ORANGE, Color.BLACK, 1);
        debuffView  = new DebuffView(model);
    }

    private void updateView()
    {
        if (model.getCustomSpellDebuff() != null)
        {
            isFull = true;
            name.setText(model.getCustomSpellDebuff().getName());
            debuffStats = new SpellStatsView(model.getCustomSpellDebuff().getSpellStats());
        }
        else
        {
            isFull = false;
            name = null;
            debuffStats = null;
        }
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {

    }
}
