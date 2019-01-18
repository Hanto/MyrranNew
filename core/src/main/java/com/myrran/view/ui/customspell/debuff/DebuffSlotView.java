package com.myrran.view.ui.customspell.debuff;

import com.badlogic.gdx.graphics.Color;
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
        model = customDebuffSlot;
        debuffView = new DebuffView(model);
        updateView();
    }

    @Override public void dispose()
    {
        if (debuffStats != null)
            debuffStats.dispose();
    }

    // CREATE:
    //--------------------------------------------------------------------------------------------------------

    private void updateView()
    {
        if (model.getCustomSpellDebuff() != null)
        {
            isFull = true;
            name = new TextView(model.getCustomSpellDebuff().getName(), Atlas.get().getFont("11"), Color.ORANGE, Color.BLACK, 2);
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
