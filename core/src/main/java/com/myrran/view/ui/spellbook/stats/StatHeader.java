package com.myrran.view.ui.spellbook.stats;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.myrran.model.spell.generators.CustomSpellDebuff;
import com.myrran.model.spell.generators.CustomSpellStatsI;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.widgets.WidgetText;

/** @author Ivan Delgado Huerta */
public class StatHeader extends Table
{
    private int vPad = -4;
    private int hPad = +3;
    private BitmapFont font11 = Atlas.get().getFont("11");
    private BitmapFont font10 = Atlas.get().getFont("10");
    private Color magenta = new Color(170/255f, 70/255f, 255/255f, 1f);
    private Color orange = Color.ORANGE;

    private Table nameTable            = new Table().top().left();
    private WidgetText name            = new WidgetText("Name", font10, orange,   Color.BLACK,1);
    private WidgetText totalCost       = new WidgetText(font10, magenta, Color.BLACK, 1);

    private WidgetText baseValue       = new WidgetText("Min", font10, orange,  Color.BLACK,1);
    private WidgetText upgradesView    = new WidgetText("Upgrades", font10, orange,  Color.BLACK,1);
    private WidgetText total           = new WidgetText("Max", font10, orange, Color.BLACK,1);
    private WidgetText numUpgrades     = new WidgetText("ups", font10, orange, Color.BLACK,1);
    private WidgetText upgradeCost     = new WidgetText("cost", font10, orange, Color.BLACK,1);
    private WidgetText bonusPerUpgrade = new WidgetText("bon", font10, orange, Color.BLACK,1);
    private WidgetText maxUpgrades     = new WidgetText("ups", font10, orange, Color.BLACK,1);
    private WidgetText gearBonus       = new WidgetText("Max", font10, orange, Color.BLACK,1);

    public StatHeader()
    {
        nameTable.add(name).bottom().left();
        nameTable.add(totalCost).bottom().right().row();
    }

    // CREATE LAYOUT:
    //--------------------------------------------------------------------------------------------------------

    public Table createTemplateStatsHeader()
    {
        clearChildren();
        add(nameTable).left()            .minWidth(80).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(baseValue).right()      .minWidth(30).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(total).right()          .minWidth(30).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(upgradeCost).right()    .minWidth(20).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(bonusPerUpgrade).right().minWidth(20).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(maxUpgrades).right()    .minWidth(20).padRight(hPad).padTop(vPad).padBottom(vPad);
        row();
        return this;
    }

    // CREATE LAYOUT:
    //--------------------------------------------------------------------------------------------------------

    public Table createCustomStatsHeader()
    {

        clearChildren();
        add(nameTable).left()            .minWidth(80).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(baseValue).right()      .minWidth(30).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(upgradesView).center()  .minWidth(75).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(total).right()          .minWidth(30).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(numUpgrades).right()    .minWidth(20).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(upgradeCost).right()    .minWidth(20).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(bonusPerUpgrade).right().minWidth(20).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(maxUpgrades).right()    .minWidth(20).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(gearBonus).right()      .minWidth(20).padRight(hPad).padTop(vPad).padBottom(vPad);
        row();
        return this;
    }

    public Table createCustomStatsHeader(CustomSpellStatsI model)
    {
        createCustomStatsHeader();

        if (model instanceof CustomSpellDebuff)
        {
            name.setText(((CustomSpellDebuff) model).getName());
            totalCost.setText(((CustomSpellDebuff) model).getTotalCost().toString());
        }

        return this;
    }
}
