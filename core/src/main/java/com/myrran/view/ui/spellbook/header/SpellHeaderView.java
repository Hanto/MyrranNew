package com.myrran.view.ui.spellbook.header;

import com.myrran.model.spell.generators.CustomSpellForm;
import com.myrran.view.ui.Atlas;

/** @author Ivan Delgado Huerta */
public class SpellHeaderView extends AbstractSpellHeaderView<CustomSpellForm>
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