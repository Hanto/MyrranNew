package com.myrran.view.ui.spellbook.icon.iconview;

import com.badlogic.gdx.graphics.Color;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomSpellForm;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.listeners.TouchDownRightListener;

/** @author Ivan Delgado Huerta */
public class FormIconView extends AbstractIconView<CustomSpellForm>
{
    public FormIconView(CustomSpellController controller)
    {   addListener(new TouchDownRightListener(event -> controller.removeCustomSpellDebuffs(model))); }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    @Override protected void setModelImp() {}
    @Override protected void disposeImp() {}
    @Override protected void update()
    {
        setBackground(Atlas.get().getTexture("TexturasIconos/FireBall2"));
        setCorner(model.getStatsCost().toString());
        setName1(model.getName());
        setName1Color(Color.ORANGE);
        setName2(model.getTemplateID().toUpperCase());
        setName2Color(Color.WHITE);
    }
}