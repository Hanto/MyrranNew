package com.myrran.view.ui.formview2;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.controller.SpellUpgradesListener;
import com.myrran.controller.SpellUpgradesListenerFactory;
import com.myrran.spell.generators.custom.CustomSpellDebuff;
import com.myrran.spell.generators.custom.debuffslot.CustomDebuffSlot;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.widgets.WidgetText;
import static com.myrran.controller.SpellUpgradesListener.StatsType;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** @author Ivan Delgado Huerta */
public class SpellDebuffDetails extends Table implements PropertyChangeListener, Disposable
{
    private CustomDebuffSlot model;
    private CustomSpellController controller;
    private SpellUpgradesListenerFactory listenerFactory;

    private WidgetText name;
    private WidgetText totalCost;
    private SpellStatsView2 stats;

    private static final BitmapFont font14 = Atlas.get().getFont("14");
    private static final Color magenta = new Color(170/255f, 70/255f, 255/255f, 1f);

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SpellDebuffDetails(CustomSpellController customSpellController)
    {
        controller      = customSpellController;
        listenerFactory = new SpellUpgradesListenerFactory(controller, "Bolt_00", StatsType.DebuffStats, "Spot 1");
        name            = new WidgetText(font14, Color.ORANGE, Color.BLACK, 2);
        totalCost       = new WidgetText(font14, magenta, Color.BLACK, 2);
        stats           = new SpellStatsView2();

        createLayout();
    }

    @Override public void dispose()
    {
        stats.dispose();
        if (model != null)
            model.removeObserver(this);
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomDebuffSlot debuffSlot)
    {
        dispose();

        if (debuffSlot == null)
            removeModel();
        else
        {
            model = debuffSlot;
            model.addObserver(this);
            update();
        }
    }

    private void removeModel()
    {
        model = null;
        stats.setModel(null);
        name.setText(null);
        totalCost.setText(null);
    }

    private void update()
    {
        CustomSpellDebuff debuff = model.getCustomSpellDebuff();

        if (debuff != null)
        {
            stats.setModel(debuff.getSpellStats());
            stats.createListeners(listenerFactory);
            name.setText(debuff.getName());
            totalCost.setText(String.format("%s(%s)", debuff.getTotalCost() - debuff.getBaseCost(), debuff.getBaseCost()));
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
        Table header = new Table().top().left();
        header.add(name).left().padBottom(-5);
        header.add(totalCost).right().padBottom(-5).row();

        top().left();
        add(header).left().row();
        add(stats);
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   update(); }
}
