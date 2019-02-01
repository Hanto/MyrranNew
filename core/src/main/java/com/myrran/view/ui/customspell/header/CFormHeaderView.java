package com.myrran.view.ui.customspell.header;

import com.myrran.model.spell.generators.CustomSpellForm;
import com.myrran.view.ui.Atlas;

/** @author Ivan Delgado Huerta */
public class CFormHeaderView extends SpellHeaderView<CustomSpellForm>
{
    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    @Override
    protected void update()
    {
        setIcon(Atlas.get().getTexture("TexturasIconos/FireBall"));
        setIconName(model.getName());
        setKeys(model.getTemplateID().toUpperCase());
        setCost(model.getTotalCost().toString());
    }
}