package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.controller.DadDebuffSource;
import com.myrran.model.spell.templates.TemplateSpellDebuff;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.listeners.TouchDownListener;
import com.myrran.view.ui.widgets.DetailedActorI;
import com.myrran.view.ui.widgets.WidgetGroup;
import com.myrran.view.ui.widgets.WidgetImage;
import com.myrran.view.ui.widgets.WidgetText;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;

/** @author Ivan Delgado Huerta */
public class TemplateDebuffView extends Table implements DetailedActorI, Disposable, PropertyChangeListener
{
    private TemplateSpellDebuff model;
    private CustomSpellController controller;

    private WidgetGroup icon;
    private WidgetImage debuffIcon;
    private WidgetText availableTotal;
    private DadDebuffSource dadSource;

    private Table tableHeader;
    private WidgetText keys;
    private WidgetText name;
    private WidgetText cost;

    private TemplateStatsView statsView;
    private boolean detailsVisible = true;
    private Cell<TemplateStatsView> cell;

    private static final int VPAD = -4;
    private static final BitmapFont font20 = Atlas.get().getFont("20");
    private static final BitmapFont font14 = Atlas.get().getFont("14");
    private static final BitmapFont font10 = Atlas.get().getFont("10");
    private static final Color magenta = new Color(170/255f, 70/255f, 255/255f, 1f);
    private static final DecimalFormat df = Atlas.get().getFormater();

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public TemplateDebuffView(CustomSpellController customSpellController)
    {
        controller      = customSpellController;
        tableHeader     = new Table();
        icon            = new WidgetGroup();
        dadSource       = new DadDebuffSource(icon);
        debuffIcon      = new WidgetImage();
        availableTotal  = new WidgetText(font14, Color.ORANGE, Color.BLACK, 1);
        keys            = new WidgetText(font10, Color.WHITE, Color.BLACK, 1);
        name            = new WidgetText(font20, Color.ORANGE, Color.BLACK, 1);
        cost            = new WidgetText(font14, magenta, Color.BLACK, 1);
        statsView       = new TemplateStatsView(customSpellController);

        controller.getDadDebuff().addSource(dadSource);
        name.addListener(new TouchDownListener(o -> showDetails()));

        createLayout();
        createHeaderLayout();
        createIconLayout();
        cell = getCell(statsView);
    }

    @Override public void dispose()
    {
        disposeObservers();
        controller.getDadDebuff().removeSource(dadSource);
    }

    private void disposeObservers()
    {
        if (model != null)
            model.removeObserver(this);
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(TemplateSpellDebuff templateSpellDebuff)
    {
        disposeObservers();

        if (templateSpellDebuff == null)
            removeModel();
        else
        {
            model = templateSpellDebuff;
            model.addObserver(this);
            dadSource.setModel(model);
            update();
        }
    }

    private void removeModel()
    {
        debuffIcon.setTexureRegion((Atlas.get().getTexture("TexturasIconos/IconoVacio")));
        availableTotal.setText(null);
        keys.setText(null);
        name.setText(null);
        cost.setText(null);
        statsView.setModel(null);
    }

    private void update()
    {
        debuffIcon.setTexureRegion((Atlas.get().getTexture("TexturasIconos/FireBall")));
        availableTotal.setText(String.format("%s/%s", model.getAvailable(), model.getTotal()));
        availableTotal.setTextColor(model.getAvailable() > 0 ? Color.GREEN : Color.RED);
        keys.setText(model.getKeys().toString());
        name.setText(model.getName());
        cost.setText(model.getBaseCost().toString());
        statsView.setModel(model.getSpellStats());
    }

    // CREATE LAYOUTS:
    //--------------------------------------------------------------------------------------------------------

    private void createLayout()
    {
        clear();
        top().left();
        add(icon).top().left();
        add(tableHeader).left().row();
        add();
        add(statsView).left();
        statsView.padBottom(8);
    }

    private void createHeaderLayout()
    {
        tableHeader.clear();
        tableHeader.bottom().left();
        tableHeader.add(keys).bottom().padTop(VPAD).padBottom(VPAD).left().row();
        tableHeader.add(name).bottom().padTop(VPAD).padBottom(VPAD).left();
        tableHeader.add(cost).bottom().padTop(VPAD).padBottom(VPAD+2).left().row();
    }

    private void createIconLayout()
    {
        icon.addActor(debuffIcon);
        icon.addActor(availableTotal);
        availableTotal.setPosition(2, 0);
    }

    // MISC:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void showDetails(boolean visible)
    {
        if (!visible)
            cell.setActor(null);
        else
            cell.setActor(statsView);

        detailsVisible = !visible;
    }

    public void showDetails()
    {   showDetails(detailsVisible); }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   update(); }
}
