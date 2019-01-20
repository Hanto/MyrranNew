package com.myrran.view.ui.formview2;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.spell.generators.custom.CustomSpellForm;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.widgets.WidgetText;

/** @author Ivan Delgado Huerta */
public class SpellFormView2 extends Group implements Disposable
{
    private CustomSpellForm model;

    private Table table;
    private WidgetText name;
    private WidgetText totalCost;
    private SpellStatsView2 stats;

    private static final BitmapFont font20 = Atlas.get().getFont("20");
    private static final BitmapFont font14 = Atlas.get().getFont("14");
    private static final Color magenta = new Color(170/255f, 70/255f, 255/255f, 1f);

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SpellFormView2()
    {
        table       = new Table().top().left();
        stats       = new SpellStatsView2();
        name        = new WidgetText(font20, Color.ORANGE, Color.BLACK, 2);
        totalCost   = new WidgetText(font14, magenta, Color.BLACK, 2);

        addActor(table);
        table.add(stats.getTable()).row();
    }

    @Override public void dispose()
    {   stats.dispose(); }

    // CREATE / UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomSpellForm customSpellForm)
    {
        dispose();

        model = customSpellForm;
        stats.setModel(model.getSpellStats());

        update();
    }

    public void removeModel()
    {
        dispose();

        model = null;
        stats.setModel(null);
        table.clear();
        name.setText("");
        totalCost.setText("");
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