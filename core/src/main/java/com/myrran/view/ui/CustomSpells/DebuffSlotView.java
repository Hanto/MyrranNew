package com.myrran.view.ui.CustomSpells;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.spell.generators.custom.debuffslot.CustomDebuffSlot;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.TextView;

/** @author Ivan Delgado Huerta */
public class DebuffSlotView implements Disposable
{
    private CustomDebuffSlot model;

    private boolean isFull = false;
    private TextView name;
    private SpellStatsView debuffStats;
    private DebuffIcon debuffIcon;

    public boolean isFull()                     { return isFull; }
    public TextView getName()                   { return name; }
    public SpellStatsView getDebuffStats()      { return debuffStats; }
    public DebuffIcon getDebuffIcon()           { return debuffIcon; }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public DebuffSlotView(CustomDebuffSlot customDebuffSlot)
    {
        model = customDebuffSlot;
        debuffIcon = new DebuffIcon(model);
        updateView();
    }

    @Override public void dispose()
    {   debuffStats.dispose(); }

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
}
