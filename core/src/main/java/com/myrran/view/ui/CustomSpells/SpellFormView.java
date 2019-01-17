package com.myrran.view.ui.CustomSpells;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.spell.generators.custom.CustomSpellForm;
import com.myrran.spell.generators.custom.stats.CustomSpellStat;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.TextView;

/** @author Ivan Delgado Huerta */
public class SpellFormView extends Group implements Disposable
{
    private CustomSpellForm model;

    private Table table;
    private Image background;
    private SpellHeaderView header;
    private TextView name;
    private SpellStatsView formStats;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SpellFormView(CustomSpellForm customSpellForm)
    {
        model = customSpellForm;

        table = new Table().bottom().left();
        table.setTransform(false);
        header = new SpellHeaderView();
        background = Atlas.get().getImage("TexturasMisc/Casillero2");
        background.setColor(1f, 1f, 1f, 0.55f);

        addActor(background);
        addActor(table);
        updateView();
        updateTable();
    }

    @Override public void dispose()
    {    formStats.dispose(); }

    // CREATE:
    //--------------------------------------------------------------------------------------------------------

    private void updateView()
    {
        name = new TextView(model.getName(), Atlas.get().getFont("20"), Color.ORANGE, Color.BLACK, 2);
        formStats = new SpellStatsView(model.getSpellStats());
    }

    // UPDATE TABLE:
    //--------------------------------------------------------------------------------------------------------

    private void updateTable()
    {
        table.clear();

        table.add(name).bottom().padBottom(-4).left().row();
        tableAddRow(header);
        formStats.getStats().forEach(this::tableAddRow);

        addListener(formStats);
        background.setBounds(0, 0, table.getMinWidth(), table.getMinHeight() - name.getHeight()/2);
    }

    private void tableAddRow(SpellStatRow row)
    {
        int pad = -3;
        table.add(row.getName()).bottom().left().padTop(pad).padBottom(pad);
        table.add(row.getBaseValue()).bottom().right().padTop(pad).padBottom(pad);
        table.add(row.getUpgradesView()).center().left().padTop(pad).padBottom(pad).padRight(+3).padLeft(+3);
        table.add(row.getTotal()).bottom().right().padTop(pad).padBottom(pad);
        table.add(row.getNumUpgrades()).bottom().right().padTop(pad).padBottom(pad);
        table.add(row.getUpgradeCost()).bottom().right().padTop(pad).padBottom(pad);
        table.add(row.getBonusPerUpgrade()).bottom().right().padTop(pad).padBottom(pad);
        table.add(row.getMaxUpgrades()).bottom().right().padTop(pad).padBottom(pad);
        table.add(row.getGearBonus()).bottom().right().padTop(pad).padBottom(pad);
        table.row();
    }


    // MAIN:
    //--------------------------------------------------------------------------------------------------------

/*

    private void createSmallName(String stringName)
    {
        name = new TextView(stringName, Atlas.get().getFont("14"), Color.ORANGE, Color.BLACK, 2);
        table.add(name).bottom().padBottom(pad).left();
        table.row();
    }

    private void addDebuffsStats()
    {
        model.getDebuffSlots().values().stream()
            .map(CustomDebuffSlot::getCustomSpellDebuff)
            .filter(Objects::nonNull)
            .forEach(this::addDebuffStats);
    }

    private void addDebuffStats(CustomSpellDebuff debuff)
    {
        createSmallName(debuff.getName());

        List<SpellStatView> stats = debuff.getSpellStats().values().stream()
            .map(SpellStatView::new)
            .collect(Collectors.toList());

        stats.forEach(this::addStat);
        this.stats.addAll(stats);
    }*/



    // INPUT:
    //--------------------------------------------------------------------------------------------------------

    private void addListener(SpellStatsView stats)
    {
        stats.getStats().forEach(stat -> stat.getUpgradesView().addListener(new InputListener()
        {
            @Override public boolean touchDown(InputEvent even, float x, float y, int pointer, int button)
            {   return processUpgradeClick(stat, x, button); }
        }));
    }

    private boolean processUpgradeClick(SpellStatView stat, float x, int button)
    {
        float width = stat.getUpgradesView().getWidth();
        if (button == Input.Buttons.LEFT)
        {
            if      (x < (width / 2))   modifyStatBy(stat.getModel(), -1);
            else if (x > (width / 2))   modifyStatBy(stat.getModel(), +1);
        }
        else if (button == Input.Buttons.RIGHT)
            modifyStatTo(stat.getModel(), (int)(x/(width/25)));
        return true;
    }

    private void modifyStatBy(CustomSpellStat stat, int modifyBy)
    {
        table.invalidate();
        stat.setNumUpgrades(stat.getNumUpgrades() + modifyBy);
    }

    private void modifyStatTo(CustomSpellStat stat, int modifyTo)
    {
        table.invalidate();
        modifyTo = stat.getNumUpgrades() > 25 ? modifyTo +25 : modifyTo;
        stat.setNumUpgrades(modifyTo);
    }
}
