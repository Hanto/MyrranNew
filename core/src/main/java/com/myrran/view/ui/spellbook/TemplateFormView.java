package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.templates.TemplateSpellForm;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.widgets.DetailedActorI;
import com.myrran.view.ui.widgets.WidgetGroup;
import com.myrran.view.ui.widgets.WidgetImage;
import com.myrran.view.ui.widgets.WidgetText;

/** @author Ivan Delgado Huerta */
public class TemplateFormView extends Table implements DetailedActorI
{
    private TemplateSpellForm model;
    private CustomSpellController controller;

    private WidgetGroup icon;
    private WidgetImage debuffIcon;
    private WidgetText availableTotal;

    private Table tableHeader;
    private WidgetText name;
    private WidgetText cost;

    private Cell<TemplateStatsView> cell;

    private static final int VPAD = -4;
    private static final BitmapFont font20 = Atlas.get().getFont("20");
    private static final BitmapFont font14 = Atlas.get().getFont("14");
    private static final BitmapFont font11 = Atlas.get().getFont("10");
    private static final Color magenta = new Color(170/255f, 70/255f, 255/255f, 1f);

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public TemplateFormView(CustomSpellController customSpellController)
    {
        controller      = customSpellController;
        tableHeader     = new Table();
        icon            = new WidgetGroup();
        debuffIcon      = new WidgetImage();
        availableTotal  = new WidgetText(font14, Color.ORANGE, Color.BLACK, 1);
        name            = new WidgetText(font20, Color.ORANGE, Color.BLACK, 1);

        createLayout();
        createHeaderLayout();
        createIconLayout();
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(TemplateSpellForm templateSpellForm)
    {
        if (templateSpellForm == null)
            removeModel();
        else
        {
            model = templateSpellForm;
            update();
        }
    }

    private void removeModel()
    {
        debuffIcon.setTexureRegion((Atlas.get().getTexture("TexturasIconos/IconoVacio")));
        availableTotal.setText(null);
        name.setText(null);
        cost.setText(null);
    }

    private void update()
    {
        debuffIcon.setTexureRegion((Atlas.get().getTexture("TexturasIconos/FireBall")));
        availableTotal.setText(String.format("%s/%s", model.getAvailable(), model.getTotal()));
        availableTotal.setTextColor(model.getAvailable() > 0 ? Color.GREEN : Color.RED);
        name.setText(model.getName());
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
        add().left().padBottom(8);
    }

    private void createHeaderLayout()
    {
        tableHeader.clear();
        tableHeader.bottom().left();
        tableHeader.add().bottom().padTop(VPAD).padBottom(VPAD).left().row();
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
    public void showDetails(boolean showDetails)
    {
        //if (!showDetails) cell.setActor(null);
        //else cell.setActor(null);
    }

}
