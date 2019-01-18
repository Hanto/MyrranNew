package com.myrran.view.ui.customspell.debuff;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.spell.generators.custom.CustomSpellDebuff;
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

    private TextView name;
    private DebuffIconView debuffIcon;
    private SpellStatsView debuffStats;

    public boolean hasDebuff()                  { return model.getCustomSpellDebuff() != null; }
    public TextView getName()                   { return name; }
    public SpellStatsView getDebuffStats()      { return debuffStats; }
    public DebuffIconView getDebufIcon()        { return debuffIcon; }

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
        debuffStats.dispose();
    }

    // CREATE / UPDATE:
    //--------------------------------------------------------------------------------------------------------

    private void createView()
    {
        BitmapFont font11 = Atlas.get().getFont("11");

        name        = new TextView(font11, Color.ORANGE, Color.BLACK, 1);
        debuffIcon  = new DebuffIconView(model);
        debuffStats = new SpellStatsView();
    }

    private void updateView()
    {
        CustomSpellDebuff debuff = model.getCustomSpellDebuff();

        debuffIcon.updateView();

        if (debuff != null)
        {
            name.setText(debuff.getName());
            debuffStats.setModel(debuff.getSpellStats());
        }
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {   updateView(); }
}
