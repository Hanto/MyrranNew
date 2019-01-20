package com.myrran.view.ui.formview2;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.spell.generators.custom.CustomSpellDebuff;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.widgets.WidgetText;

/** @author Ivan Delgado Huerta */
public class SpellDebuffView2 implements Disposable
{
    private CustomSpellDebuff model;

    private Table debuffStats;
    private WidgetText name;
    private WidgetText totalCost;
    private SpellStatsView2 stats;

    private static final BitmapFont font20 = Atlas.get().getFont("20");
    private static final BitmapFont font14 = Atlas.get().getFont("14");
    private static final Color magenta = new Color(170/255f, 70/255f, 255/255f, 1f);

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SpellDebuffView2()
    {
        debuffStats = new Table().top().left();
        name        = new WidgetText(font14, Color.ORANGE, Color.BLACK, 2);
        totalCost   = new WidgetText(font14, magenta, Color.BLACK, 2);
        stats       = new SpellStatsView2();


        debuffStats.addActor(name);
        debuffStats.addActor(totalCost);
        debuffStats.add(stats.getTable());
        totalCost.setPosition(50, 0);
    }

    @Override public void dispose()
    {   stats.dispose(); }

    // CREATE / UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void createLayour()
    {

    }

    public void setModel(CustomSpellDebuff spellDebuff)
    {
        dispose();

        if (spellDebuff == null)
            removeModel();
        else
        {
            model = spellDebuff;
            stats.setModel(spellDebuff.getSpellStats());
            update();
        }
    }

    private void removeModel()
    {
        dispose();

        model = null;
        stats.setModel(null);
        name.setText(null);
        totalCost.setText(null);
    }

    private void update()
    {
        name.setText(model.getName());
        totalCost.setText(model.getTotalCost().toString());
    }

    private void create()
    {

    }
}
