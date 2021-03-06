package com.myrran.view.ui.spellbook.stats;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.model.spell.generators.CustomSpellDebuff;
import com.myrran.model.spell.generators.CustomSpellStatsI;
import com.myrran.model.spell.templates.TemplateSpellStat;
import com.myrran.view.Atlas;
import com.myrran.view.ui.widgets.WidgetText;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/** @author Ivan Delgado Huerta */
public class StatHeader extends Table implements Disposable, PropertyChangeListener
{
    private CustomSpellDebuff model;


    private int vPad = -4;
    private int hPad = +3;
    private BitmapFont font12 = Atlas.get().getFont("Calibri12");
    private BitmapFont font10 = Atlas.get().getFont("10");
    private Color magenta = new Color(170/255f, 70/255f, 255/255f, 1f);
    private Color orange = Color.LIGHT_GRAY;
    private Table nameTable            = new Table().top().left();
    private WidgetText name            = new WidgetText("Name", font12, orange,   Color.BLACK,1);
    private WidgetText totalCost       = new WidgetText(font12, magenta, Color.BLACK, 1);
    private WidgetText baseValue       = new WidgetText("Min", font10, orange,  Color.BLACK,1);
    private WidgetText upgradesView    = new WidgetText("", font10, orange,  Color.BLACK,1);
    private WidgetText total           = new WidgetText("Max", font10, orange, Color.BLACK,1);
    private WidgetText upgrades         = new WidgetText("ranks", font10, orange, Color.BLACK, 1);
    private WidgetText maxUpgrades     = new WidgetText("ranks", font10, orange, Color.BLACK,1);
    private WidgetText upgradeCost     = new WidgetText("cost", font10, orange, Color.BLACK,1);
    private WidgetText bonusPerUpgrade = new WidgetText("bon", font10, orange, Color.BLACK,1);
    private WidgetText gearBonus       = new WidgetText("gear", font10, orange, Color.BLACK,1);

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public StatHeader()
    {   createNameLayout();}

    private void disposeObservers()
    {
        if (model != null)
            model.removeObserver(this);
    }

    @Override public void dispose()
    {   disposeObservers(); }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(List<TemplateSpellStat> templateSpellStats)
    {
        disposeObservers();
        createTemplateLayout();
    }

    public void setModel(CustomSpellStatsI customSpellStats)
    {
        disposeObservers();
        createCustomLayout();

        if (customSpellStats instanceof CustomSpellDebuff)
        {
            model = (CustomSpellDebuff)customSpellStats;
            model.addObserver(this);
            update();
        }
    }

    private void update()
    {
        if (model != null)
        {
            name.setTextColor(Color.ORANGE);
            name.setText(model.getName());
            totalCost.setText(model.getTotalCost().toString());
        }
    }

    // CREATE LAYOUT:
    //--------------------------------------------------------------------------------------------------------

    private void createTemplateLayout()
    {
        clearChildren();
        add(nameTable).left()       .minWidth(90).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(baseValue).right()      .minWidth(35).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(total).right()          .minWidth(35).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(maxUpgrades).right()    .minWidth(30).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(upgradeCost).right()    .minWidth(20).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(bonusPerUpgrade).right().minWidth(20).padRight(hPad).padTop(vPad).padBottom(vPad);
        row();
    }

    // CREATE LAYOUT:
    //--------------------------------------------------------------------------------------------------------

    private void createCustomLayout()
    {

        clearChildren();
        add(nameTable).left()       .minWidth(90).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(baseValue).right()      .minWidth(35).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(upgradesView).center()  .minWidth(75).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(total).right()          .minWidth(35).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(upgrades).right()       .minWidth(30).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(upgradeCost).right()    .minWidth(20).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(bonusPerUpgrade).right().minWidth(20).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(gearBonus).right()      .minWidth(20).padRight(hPad).padTop(vPad).padBottom(vPad);
        row();
    }

    private void createNameLayout()
    {
        nameTable.add(name).bottom().left();
        nameTable.add(totalCost).padBottom(-1).bottom().right().row();
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   update(); }
}
