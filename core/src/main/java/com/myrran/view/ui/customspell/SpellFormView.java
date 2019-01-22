package com.myrran.view.ui.customspell;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.spell.generators.custom.CustomSpellForm;
import com.myrran.spell.generators.custom.stats.CustomSpellStat;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.widgets.WidgetText;
import com.myrran.view.ui.customspell.debuff.DebuffSlotView;
import com.myrran.view.ui.customspell.debuff.DebuffSlotsView;
import com.myrran.view.ui.customspell.stats.SpellHeaderView;
import com.myrran.view.ui.customspell.stats.SpellStatRow;
import com.myrran.view.ui.formview2.SpellStatView;
import com.myrran.view.ui.customspell.stats.SpellStatsView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** @author Ivan Delgado Huerta */
public class SpellFormView extends Group implements PropertyChangeListener, Disposable
{
    private CustomSpellForm model;

    private Table tableStats;
    private Image icon;
    private Image background;
    private WidgetText name;
    private WidgetText totalCost;
    private SpellHeaderView header;
    private SpellStatsView formStats;
    private DebuffSlotsView debuffSlots;

    private static final Color pink = new Color(255/255f, 84/255f, 118/255f, 1);
    private static final Color magenta = new Color(170/255f, 70/255f, 255/255f, 1f);

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SpellFormView(CustomSpellForm customSpellForm)
    {
        model = customSpellForm;

        createView();
        updateView();
    }

    @Override public void dispose()
    {
        formStats.dispose();
        debuffSlots.dispose();
    }

    // CREATE:
    //--------------------------------------------------------------------------------------------------------

    private void createView()
    {
        BitmapFont font20 = Atlas.get().getFont("20");
        BitmapFont font14 = Atlas.get().getFont("14");

        icon        = Atlas.get().getImage("TexturasIconos/FireBall");
        background  = Atlas.get().getImage("TexturasMisc/Casillero2");
        header      = new SpellHeaderView();
        name        = new WidgetText(font20, Color.ORANGE, Color.BLACK, 2);
        totalCost   = new WidgetText(font14, magenta, Color.BLACK, 2);
        formStats   = new SpellStatsView(model.getSpellStats());
        debuffSlots = new DebuffSlotsView(model.getDebuffSlots());
        tableStats  = new Table().bottom().left();

        background.setColor(1f, 1f, 1f, 0.55f);

        addActor(debuffSlots.getDebuffIconTable());
        addActor(icon);
        addActor(background);
        addActor(tableStats);
    }

    // UPDATE TABLE:
    //--------------------------------------------------------------------------------------------------------

    private void updateView()
    {
        updateFields();
        updateTableStats();
    }

    private void updateFields()
    {
        name.setText(model.getName());
        totalCost.setText("Rank: "+model.getTotalCost().toString());
    }

    private void updateTableStats()
    {
        tableStats.clear();
        tableStats.add(name).bottom().padBottom(-4).padLeft(4).left();
        tableStats.add();
        tableStats.add(totalCost).bottom().center().row();

        tableAddRow(header);
        tableStatsAddFormStats();
        tableStatsAddDebuffsStats();
        updatePositions();
    }

    private void tableStatsAddFormStats()
    {
        formStats.getStats().
            forEach(this::tableAddRow);

        addListener(formStats);
    }

    private void tableStatsAddDebuffsStats()
    {
        debuffSlots.updateView();

        debuffSlots.getSlots().stream()
            .filter(DebuffSlotView::hasDebuff)
            .forEach(this::tableStatsAddDebuffStats);
    }

    private void tableStatsAddDebuffStats(DebuffSlotView debuff)
    {
        tableStats.add(debuff.getName()).bottom().padBottom(-4).left();
        tableStats.add();
        tableStats.add(debuff.getTotalCost()).bottom().center().row();

        debuff.getDebuffStats().getStats()
            .forEach(this::tableAddRow);

        addListener(debuff.getDebuffStats());
    }

    private void tableAddRow(SpellStatRow row)
    {
        int pad = -4;
        tableStats.add(row.getName()).bottom().left().padTop(pad).padBottom(pad).padLeft(4);
        tableStats.add(row.getBaseValue()).bottom().right().padTop(pad).padBottom(pad);
        tableStats.add(row.getUpgradesView()).center().padTop(pad).padBottom(pad).padRight(+3).padLeft(+3);
        tableStats.add(row.getTotal()).bottom().right().padRight(2).padTop(pad).padBottom(pad);
        tableStats.add(row.getNumUpgrades()).bottom().right().padRight(2).padTop(pad).padBottom(pad);
        tableStats.add(row.getUpgradeCost()).bottom().right().padRight(2).padTop(pad).padBottom(pad);
        tableStats.add(row.getBonusPerUpgrade()).bottom().right().padRight(2).padTop(pad).padBottom(pad);
        tableStats.add(row.getMaxUpgrades()).bottom().right().padRight(2).padTop(pad).padBottom(pad);
        tableStats.add(row.getGearBonus()).bottom().right().padRight(2).padTop(pad).padBottom(pad).padRight(4);
        tableStats.row();
    }

    private void updatePositions()
    {
        icon.setPosition(-icon.getWidth(), tableStats.getMinHeight()-icon.getHeight()-name.getHeight()/2);
        debuffSlots.getDebuffIconTable().setPosition(tableStats.getMinWidth(), tableStats.getMinHeight() -name.getHeight()/2);
        background.setBounds(0, 0, tableStats.getMinWidth(), tableStats.getMinHeight() - name.getHeight()/2);
    }

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
        //tableStats.invalidate();
        stat.setNumUpgrades(stat.getNumUpgrades() + modifyBy);
        updateFields();
    }

    private void modifyStatTo(CustomSpellStat stat, int modifyTo)
    {
        //tableStats.invalidate();
        modifyTo = stat.getNumUpgrades() > 25 ? modifyTo +25 : modifyTo;
        stat.setNumUpgrades(modifyTo);
        updateFields();
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getPropertyName().equals("fieldChange"))
            updateFields();

        else if (evt.getPropertyName().equals("fullChange"))
            updateView();
    }
}
