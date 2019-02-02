package com.myrran.view.ui.customspell.icon;

import com.badlogic.gdx.graphics.Color;
import com.myrran.model.spell.generators.CustomSpellForm;
import com.myrran.view.ui.Atlas;

/** @author Ivan Delgado Huerta */
public class FormIconView extends AbstractSpellIconView<CustomSpellForm>
{
    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    @Override
    protected void update()
    {
        setBackground(Atlas.get().getTexture("TexturasIconos/FireBall2"));
        setCorner(model.getStatsCost().toString());
        setName1(model.getName());
        setName1Color(Color.ORANGE);
        setName2(model.getTemplateID().toUpperCase());
        setName2Color(Color.WHITE);
    }
}