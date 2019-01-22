package com.myrran.view.ui.customspell.debuff;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.model.spell.generators.custom.CustomSpellDebuff;
import com.myrran.model.spell.generators.custom.CustomDebuffSlot;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.widgets.WidgetText;
import com.myrran.view.ui.customspell.stats.SpellStatsView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** @author Ivan Delgado Huerta */
public class DebuffSlotView implements PropertyChangeListener, Disposable
{
    private CustomDebuffSlot model;

    private WidgetText name;
    private WidgetText totalCost;
    private DebuffIconView debuffIcon;
    private SpellStatsView debuffStats;

    public boolean hasDebuff()                  { return model.getCustomSpellDebuff() != null; }
    public WidgetText getName()                   { return name; }
    public WidgetText getTotalCost()              { return totalCost; }
    public SpellStatsView getDebuffStats()      { return debuffStats; }
    public DebuffIconView getDebufIcon()        { return debuffIcon; }

    private static final Color magenta = new Color(130/255f, 50/255f, 255/255f, 1f);

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
        BitmapFont font11 = Atlas.get().getFont("14");

        name        = new WidgetText(font11, Color.ORANGE, Color.BLACK, 1);
        totalCost   = new WidgetText(font11, magenta, Color.BLACK, 1);
        debuffIcon  = new DebuffIconView(model);
        debuffStats = new SpellStatsView();
    }

    public void updateView()
    {
        CustomSpellDebuff debuff = model.getCustomSpellDebuff();

        debuffIcon.updateView();

        if (debuff != null)
        {
            name.setText(debuff.getName());
            debuffStats.setModel(debuff.getSpellStats());
            totalCost.setText(String.format("Rank: %s(%s)", debuff.getTotalCost()-debuff.getBaseCost(), debuff.getBaseCost()));
        }
    }

    public void setModel(CustomDebuffSlot customDebuffSlot)
    {
        dispose();
        model = customDebuffSlot;
        model.addObserver(this);
        updateView();
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {   updateView(); }
}
