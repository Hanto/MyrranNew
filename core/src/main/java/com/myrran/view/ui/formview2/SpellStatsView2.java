package com.myrran.view.ui.formview2;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.spell.generators.custom.stats.CustomSpellStat;
import com.myrran.spell.generators.custom.stats.CustomSpellStats;
import com.myrran.view.ui.customspell.stats.SpellStatRow;
import com.myrran.view.ui.customspell.stats.SpellStatView;

import java.util.ArrayList;
import java.util.List;

/** @author Ivan Delgado Huerta */
public class SpellStatsView2 implements Disposable
{
    private CustomSpellStats model;

    private Table table;
    private List<SpellStatView> statsViewList;

    public Table getTable()             { return table; }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SpellStatsView2()
    {
        table           = new Table().top().left();
        statsViewList   = new ArrayList<>();
    }

    @Override public void dispose()
    {   statsViewList.forEach(SpellStatView::dispose); }

    // CREATE / UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomSpellStats customSpellStats)
    {
        dispose();

        if (customSpellStats == null)
            removeModel();
        else
        {
            model = customSpellStats;
            update();
        }
    }

    private void removeModel()
    {
        dispose();

        model = null;
        table.clear();
        statsViewList.clear();
    }

    private void update()
    {
        table.clear();
        statsViewList.clear();

        model.values().stream()
            .map(this::getView)
            .forEach(statView -> statsViewList.add(statView));

        statsViewList.forEach(this::tableAddRow);
    }

    private void create()
    {

    }

    // MISC:
    //--------------------------------------------------------------------------------------------------------

    private SpellStatView getView(CustomSpellStat customSpellStat)
    {   return new SpellStatView(customSpellStat); }

    private void tableAddRow(SpellStatRow row)
    {
        int vPad = -4;
        int hPad = +3;

        table.add(row.getName()).left()             .padRight(hPad).padTop(vPad).padBottom(vPad);
        table.add(row.getBaseValue()).right()       .padRight(hPad).padTop(vPad).padBottom(vPad);
        table.add(row.getUpgradesView()).center()   .padRight(hPad).padTop(vPad).padBottom(vPad);
        table.add(row.getTotal()).right()           .padRight(hPad).padTop(vPad).padBottom(vPad);
        table.add(row.getNumUpgrades()).right()     .padRight(hPad).padTop(vPad).padBottom(vPad);
        table.add(row.getUpgradeCost()).right()     .padRight(hPad).padTop(vPad).padBottom(vPad);
        table.add(row.getBonusPerUpgrade()).right() .padRight(hPad).padTop(vPad).padBottom(vPad);
        table.add(row.getMaxUpgrades()).right()     .padRight(hPad).padTop(vPad).padBottom(vPad);
        table.add(row.getGearBonus()).right()       .padRight(hPad).padTop(vPad).padBottom(vPad);
        table.row();
    }
}
