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

    public Table debuffStats;
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

        createLayout();
    }

    @Override public void dispose()
    {   stats.dispose(); }

    // CREATE / UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void createLayout()
    {
        Table header = new Table().top().left();
        header.add(name).left().padBottom(-5);
        header.add(totalCost).right().padBottom(-5).row();

        debuffStats.add(header).left().row();
        debuffStats.add(stats.getTable());
    }

    public void setModel(CustomSpellDebuff spellDebuff)
    {
        dispose();

        if (spellDebuff == null)
            removeModel();
        else
        {
            model = spellDebuff;
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
        stats.setModel(model.getSpellStats());
        name.setText(model.getName());
        totalCost.setText(String.format("%s(%s)", model.getTotalCost() - model.getBaseCost(), model.getBaseCost()));
    }
}
