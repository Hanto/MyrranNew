package com.myrran.view.ui.CustomSpells;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.spell.generators.custom.CustomSpellForm;
import com.myrran.spell.generators.custom.stats.CustomSpellStat;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.CustomSpells.spellStats.SpellStatView;
import com.myrran.view.ui.TextView;

/** @author Ivan Delgado Huerta */
public class SpellFormView extends Group implements Disposable
{
    private CustomSpellForm model;

    private Table tableStats;
    private Table tableDebuffs;
    private Image background;
    private SpellHeaderView header;
    private TextView name;
    private TextView totalCost;
    private SpellStatsView formStats;
    private DebuffSlotsView slots;
    private DragAndDrop dad;

    private static final Color magenta = new Color(170/255f, 70/255f, 255/255f, 1f);

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SpellFormView(CustomSpellForm customSpellForm)
    {
        model = customSpellForm;

        tableStats = new Table().bottom().left();
        tableStats.setTransform(false);
        tableDebuffs = new Table().top().left();
        header = new SpellHeaderView();
        background = Atlas.get().getImage("TexturasMisc/Casillero2");
        background.setColor(1f, 1f, 1f, 0.55f);
        dad = new DragAndDrop();

        addActor(background);
        addActor(tableStats);
        addActor(tableDebuffs);
        updateView();
        updateTable();
    }

    @Override public void dispose()
    {
        if (formStats != null) formStats.dispose();
        if (slots != null) slots.dispose();
    }

    // CREATE:
    //--------------------------------------------------------------------------------------------------------

    private void updateView()
    {
        dispose();
        name = new TextView(model.getName(), Atlas.get().getFont("20"), Color.ORANGE, Color.BLACK, 2);
        formStats = new SpellStatsView(model.getSpellStats());
        slots = new DebuffSlotsView(model.getDebuffSlots());
    }

    // UPDATE TABLE:
    //--------------------------------------------------------------------------------------------------------

    private void updateTable()
    {
        tableStats.clear();
        tableDebuffs.clear();
        tableAddForm();
        tableAddDebuffs();

        slots.getSlots().stream()
            .map(DebuffSlotView::getDebuffIcon)
            .forEach(this::tableAddDebuffIcon);

        tableDebuffs.setPosition(tableStats.getMinWidth(), tableStats.getMinHeight() -name.getHeight()/2);
        background.setBounds(0, 0, tableStats.getMinWidth(), tableStats.getMinHeight() - name.getHeight()/2);
    }

    private void tableAddForm()
    {
        tableStats.add(name).bottom().padBottom(-4).left().row();
        tableAddRow(header);
        formStats.getStats()
            .forEach(this::tableAddRow);

        addListener(formStats);
    }

    private void tableAddDebuffs()
    {
        slots.getSlots().stream()
            .filter(DebuffSlotView::isFull)
            .forEach(this::tableAddDebuff);
    }

    private void tableAddDebuff(DebuffSlotView debuff)
    {
        tableStats.add(debuff.getName()).bottom().padBottom(-4).left().row();
        debuff.getDebuffStats().getStats()
            .forEach(this::tableAddRow);

        addListener(debuff.getDebuffStats());
    }

    private void tableAddRow(SpellStatRow row)
    {
        int pad = -4;
        tableStats.add(row.getName()).bottom().left().padTop(pad).padBottom(pad);
        tableStats.add(row.getBaseValue()).bottom().right().padTop(pad).padBottom(pad);
        tableStats.add(row.getUpgradesView()).center().padTop(pad).padBottom(pad).padRight(+3).padLeft(+3);
        tableStats.add(row.getTotal()).bottom().right().padRight(2).padTop(pad).padBottom(pad);
        tableStats.add(row.getNumUpgrades()).bottom().right().padRight(2).padTop(pad).padBottom(pad);
        tableStats.add(row.getUpgradeCost()).bottom().right().padRight(2).padTop(pad).padBottom(pad);
        tableStats.add(row.getBonusPerUpgrade()).bottom().right().padRight(2).padTop(pad).padBottom(pad);
        tableStats.add(row.getMaxUpgrades()).bottom().right().padRight(2).padTop(pad).padBottom(pad);
        tableStats.add(row.getGearBonus()).bottom().right().padRight(2).padTop(pad).padBottom(pad);
        tableStats.row();
    }

    private void tableAddDebuffIcon(DebuffIcon icon)
    {   tableDebuffs.add(icon).row(); }

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
        tableStats.invalidate();
        stat.setNumUpgrades(stat.getNumUpgrades() + modifyBy);
    }

    private void modifyStatTo(CustomSpellStat stat, int modifyTo)
    {
        tableStats.invalidate();
        modifyTo = stat.getNumUpgrades() > 25 ? modifyTo +25 : modifyTo;
        stat.setNumUpgrades(modifyTo);
    }
}
