package com.myrran.view.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.spell.generators.custom.CustomSpellForm;
import com.myrran.spell.generators.custom.stats.CustomSpellStat;
import com.myrran.utils.InvalidIDException;

import java.util.List;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class SpellFormView extends Group implements Disposable
{
    private CustomSpellForm model;

    private Table table;
    private List<SpellStatView> spellStats;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SpellFormView(CustomSpellForm customSpellForm)
    {
        this.model = customSpellForm;
        this.table = new Table().top().left();
        this.table.setTransform(false);

        spellStats = model.getSpellStats().values().stream()
            .map(SpellStatView::new)
            .collect(Collectors.toList());

        spellStats.forEach(this::addListener);

        createView();
        this.addActor(table);
    }

    private void addListener(SpellStatView stat)
    {
        stat.getUpgradesView().addListener(new InputListener()
        {
            public boolean touchDown(InputEvent even, float x, float y, int pointer, int button)
            {
                float width = stat.getUpgradesView().getWidth();
                if (button == Input.Buttons.LEFT)
                {
                    if (x < (width / 2))       modifyStatBy(stat.getModelID(), -1);
                    else if (x > (width / 2))  modifyStatBy(stat.getModelID(), +1);
                }
                if (button == Input.Buttons.RIGHT)
                    modifyStatTo(stat.getModelID(), (int)(x/(width/25)));

                return true;
            }
        });
    }

    private void modifyStatBy(String id, int modifyBy)
    {
        table.invalidate();
        try
        {
            CustomSpellStat stat = model.getCustomSpellStat(id);
            stat.setNumUpgrades(stat.getNumUpgrades() + modifyBy);
        }
        catch (InvalidIDException e) {}
    }

    private void modifyStatTo(String id, int modifyTo)
    {
        table.invalidate();
        try
        {
            CustomSpellStat stat = model.getCustomSpellStat(id);
            if (stat.getNumUpgrades() > 25)
                modifyTo = modifyTo + 25;
            stat.setNumUpgrades(modifyTo);
        }
        catch (InvalidIDException e) {}
    }


    @Override public void dispose()
    {   spellStats.forEach(SpellStatView::dispose); }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    private void createView()
    {
        table.clear();
        createHeader();
        spellStats.forEach(this::addStats);
    }

    private void createHeader()
    {
        BitmapFont font11 = new BitmapFont(Gdx.files.internal("fonts/11.fnt"), false);
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
    }
}
