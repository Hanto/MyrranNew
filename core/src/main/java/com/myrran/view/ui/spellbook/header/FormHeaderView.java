package com.myrran.view.ui.spellbook.header;

import com.badlogic.gdx.graphics.Color;
import com.myrran.model.spell.templates.TemplateSpellForm;
import com.myrran.view.Atlas;

/** @author Ivan Delgado Huerta */
public class FormHeaderView extends AbstractSpellHeaderView<TemplateSpellForm>
{
    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    @Override
    protected void update()
    {
        setIcon(Atlas.get().getTexture("TexturasIconos/FireBall"));
        setAvailableTotal(String.format("%s/%s", model.getAvailable(), model.getTotal()));
        setrAvailableTotalColor(model.getAvailable() > 0 ? Color.GREEN : Color.RED);
        setKeys(model.getFactory().getName().toUpperCase());
        setIconName(model.getName());
    }
}