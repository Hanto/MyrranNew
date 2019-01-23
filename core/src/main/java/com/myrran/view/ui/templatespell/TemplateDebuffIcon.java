package com.myrran.view.ui.templatespell;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.custom.CustomDebuffSlot;
import com.myrran.model.spell.templates.TemplateSpellDebuff;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.widgets.WidgetText;

/** @author Ivan Delgado Huerta */
public class TemplateDebuffIcon extends Table
{
    private TemplateSpellDebuff model;
    private CustomSpellController controller;

    private WidgetText name;

    private static final BitmapFont font14 = Atlas.get().getFont("20");

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public TemplateDebuffIcon(CustomSpellController customSpellController)
    {
        controller  = customSpellController;
        name        = new WidgetText(font14, Color.ORANGE, Color.BLACK, 1);
        createLayout();
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(TemplateSpellDebuff templateSpellDebuff)
    {
        if (templateSpellDebuff == null)
            removeModel();
        else
        {
            model = templateSpellDebuff;
            update();
        }
    }

    private void removeModel()
    {

    }

    private void update()
    {
        name.setText(model.getName());
    }

    private void createLayout()
    {
        add(name).bottom().left();
    }
}
