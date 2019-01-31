package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
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

    private TemplateHeaderView header;

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
        header          = new TemplateHeaderView();

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
    {   header.removeAll(); }

    private void update()
    {
        header.setIcon(Atlas.get().getTexture("TexturasIconos/FireBall"));
        header.setAvailableTotal(String.format("%s/%s", model.getAvailable(), model.getTotal()));
        header.setrAvailableTotalColor(model.getAvailable() > 0 ? Color.GREEN : Color.RED);
        header.setKeys(model.getFactory().getName().toUpperCase());
        header.setIconName(model.getName());
    }

    // CREATE LAYOUTS:
    //--------------------------------------------------------------------------------------------------------

    private void createLayout()
    {
        clear();
        top().left();
        add(header).top().left();
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
