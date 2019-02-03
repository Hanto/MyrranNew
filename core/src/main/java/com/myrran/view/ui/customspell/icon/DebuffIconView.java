package com.myrran.view.ui.customspell.icon;

import com.badlogic.gdx.graphics.Color;
import com.myrran.model.spell.templates.TemplateSpellSlot;
import com.myrran.view.ui.Atlas;

public class DebuffIconView extends AbstractSpellIconView<TemplateSpellSlot>
{
    public DebuffIconView(TemplateSpellSlot model)
    {   setModel(model); }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    @Override protected void setModelImp() {}
    @Override protected void disposeImp() {}
    @Override protected void update()
    {
        setBackground(Atlas.get().getTexture("TexturasIconos/IconoVacio2"));
        setCorner(null);
        setName1(model.getLock().toString().toLowerCase());
        setName1Color(Color.LIGHT_GRAY);
        setName2(model.getSlotType());
    }


}
