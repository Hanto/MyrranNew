package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
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

    private TemplateHeaderView header;
    private DadDebuffSource dadSource;

    private Table details;
    private TemplateStatsView statsView;
    private boolean detailsVisible = true;
    private Cell<Actor> cell;

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
        header          = new TemplateHeaderView();
        dadSource       = new DadDebuffSource(header.getIcon(), controller);
        details         = new Table();
        statsView       = new TemplateStatsView(customSpellController);

        controller.getDadDebuff().addSource(dadSource);
        header.getIconName().addListener(new TouchDownListener(o -> showDetails()));

        createLayout();
        cell = getCell(details);
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
        header.removeAll();
        statsView.setModel(null);
    }

    private void update()
    {
        header.setIcon(Atlas.get().getTexture("TexturasIconos/FireBall"));
        header.setAvailableTotal(String.format("%s/%s", model.getAvailable(), model.getTotal()));
        header.setrAvailableTotalColor(model.getAvailable() > 0 ? Color.GREEN : Color.RED);
        header.setKeys(model.getKeys().toString());
        header.setIconName(model.getName());
        header.setCost(model.getBaseCost().toString());
        statsView.setModel(model.getSpellStats());
    }

    // CREATE LAYOUTS:
    //--------------------------------------------------------------------------------------------------------

    private void createLayout()
    {
        clear();
        top().left();
        add(header).bottom().left().row();
        add(details).top().left().row();

        details.clear();
        details.top().left();
        details.padBottom(8);
        details.add().size(32, 0);
        details.add(statsView).top().left().row();
    }

    // MISC:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void showDetails(boolean visible)
    {
        if (!visible)
            cell.setActor(null);
        else
            cell.setActor(details);

        detailsVisible = !visible;
    }

    public void showDetails()
    {   showDetails(detailsVisible); }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   update(); }
}
