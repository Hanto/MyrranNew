package com.myrran.view.ui;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.spell.generators.custom.CustomSpellDebuff;
import com.myrran.spell.generators.custom.CustomSpellForm;
import com.myrran.spell.generators.custom.debuffslot.CustomDebuffSlot;
import com.myrran.spell.generators.custom.stats.CustomSpellStat;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class SpellFormView extends Group implements Disposable
{
    private CustomSpellForm model;

    private Table table;
    private TextView name;
    private Image background;
    private List<SpellStatView> stats = new ArrayList<>();

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SpellFormView(CustomSpellForm customSpellForm)
    {
        model = customSpellForm;
        table = new Table().bottom().left();
        table.setTransform(false);

        createTable();
        createBackground();
    }

    @Override public void dispose()
    {    stats.forEach(SpellStatView::dispose);}

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    private void createTable()
    {
        table.clear();
        addActor(table);
        createName(model.getName());
        createHeader();
        addFormStats();
        addDebuffsStats();
    }

    private void createName(String stringName)
    {
        name = new TextView(stringName, Atlas.get().getFont("20"), Color.ORANGE, Color.BLACK, 2);
        table.add(name).bottom().padBottom(-4).left();
        table.row();
    }

    private void createSmallName(String stringName)
    {
        name = new TextView(stringName, Atlas.get().getFont("14"), Color.ORANGE, Color.BLACK, 2);
        table.add(name).bottom().padBottom(-4).left();
        table.row();
    }

    private void createHeader()
    {
        BitmapFont font11 = Atlas.get().getFont("14");
        Color pink = new Color(255/255f, 84/255f, 118/255f, 1);
        Color black = Color.BLACK;

        table.add(new TextView("Name", font11, pink, black, 1)).bottom().left().padBottom(-4).padTop(-4);
        table.add(new TextView("Base", font11, pink, black, 1)).bottom().right().padBottom(-4).padTop(-4);
        table.add(new TextView("Level",font11, pink, black, 1)).bottom().center().padBottom(-4).padTop(-4);
        table.add(new TextView("Total",font11, pink, black, 1)).bottom().right().padBottom(-4).padTop(-4);
        table.add(new TextView("nv",   font11, pink, black, 1)).bottom().right().padBottom(-4).padTop(-4);
        table.add(new TextView("c",    font11, pink, black, 1)).bottom().right().padBottom(-4).padTop(-4);
        table.add(new TextView("bon",  font11, pink, black, 1)).bottom().right().padBottom(-4).padTop(-4);
        table.add(new TextView("mx",   font11, pink, black, 1)).bottom().right().padBottom(-4).padTop(-4);
        table.add(new TextView("gear", font11, pink, black, 1)).bottom().right().padBottom(-4).padTop(-4);
        table.row();
    }
    
    private void addStats(SpellStatView spellStatView)
    {
        table.add(spellStatView.getName()).bottom().left().padTop(-4).padBottom(-4);
        table.add(spellStatView.getBaseValue()).bottom().right().padTop(-4).padBottom(-4);
        table.add(spellStatView.getUpgradesView()).center().left().padTop(-4).padBottom(-4).padRight(+3).padLeft(+3);
        table.add(spellStatView.getTotal()).bottom().right().padTop(-4).padBottom(-4);
        table.add(spellStatView.getNumUpgrades()).bottom().right().padTop(-4).padBottom(-4);
        table.add(spellStatView.getUpgradeCost()).bottom().right().padTop(-4).padBottom(-4);
        table.add(spellStatView.getBonusPerUpgrade()).bottom().right().padTop(-4).padBottom(-4);
        table.add(spellStatView.getMaxUpgrades()).bottom().right().padTop(-4).padBottom(-4);
        table.add(spellStatView.getGearBonus()).bottom().right().padTop(-4).padBottom(-4);
        table.row();
        addListener(spellStatView);
    }

    private void addFormStats()
    {
        List<SpellStatView> stats = model.getSpellStats().values().stream()
            .map(SpellStatView::new)
            .collect(Collectors.toList());

        stats.forEach(this::addStats);
        this.stats.addAll(stats);
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

        stats.forEach(this::addStats);
        this.stats.addAll(stats);
    }

    private void createBackground()
    {
        background = Atlas.get().getImage("TexturasMisc/Casillero2");
        background.setColor(1f, 1f, 1f, 0.55f);
        updateTable();
        addActor(background);
        background.toBack();
    }

    private void updateTable()
    {
        float nameH = name.getHeight()/2;
        background.setBounds(0, 0, table.getMinWidth(), table.getMinHeight() -nameH);
    }

    // INPUT:
    //--------------------------------------------------------------------------------------------------------

    private void addListener(SpellStatView stat)
    {
        stat.getUpgradesView().addListener(new InputListener()
        {
            @Override public boolean touchDown(InputEvent even, float x, float y, int pointer, int button)
            {   return processUpgradeClick(stat, x, button); }
        });
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
