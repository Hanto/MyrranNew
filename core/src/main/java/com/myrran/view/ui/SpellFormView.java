package com.myrran.view.ui;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.spell.generators.custom.CustomSpellForm;

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

        createView();
        this.addActor(table);
    }

    @Override public void dispose()
    {   spellStats.forEach(SpellStatView::dispose); }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    private void createView()
    {
        table.clear();
        spellStats.forEach(this::addStats);
    }

    private void addStats(SpellStatView spellStatView)
    {
        table.add(spellStatView.getName()).bottom().left().padTop(-4).padBottom(-4);
        table.add(spellStatView.getBaseValue()).bottom().right().padTop(-4).padBottom(-4);
        table.add(spellStatView.getTotal()).bottom().right().padTop(-4).padBottom(-4);
        table.add(spellStatView.getNumUpgrades()).bottom().right().padTop(-4).padBottom(-4);
        table.row();
    }

}
