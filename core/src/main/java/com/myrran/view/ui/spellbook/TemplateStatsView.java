package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.templates.TemplateSpellStat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class TemplateStatsView extends Table implements Disposable
{
    private List<TemplateSpellStat> model;
    private CustomSpellController controller;

    private List<TemplateStatView>statsViewList = new ArrayList<>();

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public TemplateStatsView(CustomSpellController customSpellController)
    {
        controller = customSpellController;
        createLayout();
    }

    @Override public void dispose()
    { }


    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(List<TemplateSpellStat> statList)
    {
        dispose();

        if (statList == null)
            removeModel();
        else
        {
            model = statList;
            update();
        }
    }

    public void removeModel()
    {
        clear();
        model = null;
        statsViewList.clear();
    }

    public void update()
    {
        clear();
        statsViewList.clear();

        statsViewList = model.stream()
            .map(this::getView)
            .collect(Collectors.toList());

        statsViewList.forEach(this::tableAddRow);
    }

    private void createLayout()
    {
        top().left();
    }

    // CREATE LAYOUT:
    //--------------------------------------------------------------------------------------------------------

    private TemplateStatView getView(TemplateSpellStat templateStat)
    {
        TemplateStatView view = new TemplateStatView();
        view.setModel(templateStat);
        return view;
    }

    private void tableAddRow(TemplateStatView row)
    {
        int vPad = -4;
        int hPad = +3;

        add(row.getName()).left()             .minWidth(100).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(row.getBaseValue()).right()       .minWidth(45).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(row.getUpgradeCost()).right()     .padRight(hPad).padTop(vPad).padBottom(vPad);
        add(row.getBonusPerUpgrade()).right() .padRight(hPad).padTop(vPad).padBottom(vPad);
        add(row.getMaxUpgrades()).right()     .padRight(hPad).padTop(vPad).padBottom(vPad);
        row();
    }
}
