package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.templates.TemplateSpellForm;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.widgets.WidgetGroup;
import com.myrran.view.ui.widgets.WidgetImage;
import com.myrran.view.ui.widgets.WidgetText;

/** @author Ivan Delgado Huerta */
public class TemplateFormView extends Table
{
    private TemplateSpellForm model;
    private CustomSpellController controller;

    private WidgetGroup icon;
    private WidgetImage debuffIcon;
    private WidgetText availableTotal;

    private WidgetText name;
    private WidgetText cost;

    private Cell<TemplateStatsView> cell;

    private static final BitmapFont font20 = Atlas.get().getFont("20");
    private static final BitmapFont font14 = Atlas.get().getFont("14");
    private static final BitmapFont font11 = Atlas.get().getFont("10");
    private static final Color magenta = new Color(170/255f, 70/255f, 255/255f, 1f);

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public TemplateFormView(CustomSpellController customSpellController)
    {
        controller      = customSpellController;
        icon            = new WidgetGroup();
        debuffIcon      = new WidgetImage();
        availableTotal  = new WidgetText(font14, Color.ORANGE, Color.BLACK, 1);
        name            = new WidgetText(font20, Color.ORANGE, Color.BLACK, 1);

        createLayout();
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

    private void createLayout()
    {
        int vPad = -4;

        clear();
        icon.addActor(debuffIcon);
        icon.addActor(availableTotal);
        availableTotal.setPosition(2, 0);

        Table nameCostTable = new Table().bottom().left();
        nameCostTable.add(name).bottom().left().padTop(vPad);
        nameCostTable.add(cost).bottom().left().padTop(vPad).padBottom(1);

        Table textTable = new Table().bottom().left();
        textTable.add().left().padTop(-2).padBottom(vPad).row();
        textTable.add(nameCostTable).left().padTop(-2).padBottom(vPad).row();

        top().left();
        add(icon).top().left();
        add(textTable).left().row();
        add();
        add().left().padBottom(4);

        //cell = getCell(statsView);
    }

    // MISC:
    //--------------------------------------------------------------------------------------------------------

    public void showDetails(boolean showDetails)
    {
        //if (!showDetails) cell.setActor(null);
        //else cell.setActor(null);
    }

}
