package com.myrran.view.ui.spellbook.icon.iconview;

import com.badlogic.gdx.graphics.Color;
import com.myrran.model.spell.templates.TemplateSpellSlot;
import com.myrran.view.Atlas;

/** @author Ivan Delgado Huerta */
public class SlotIconView extends AbstractIconView<TemplateSpellSlot>
{
    public SlotIconView(TemplateSpellSlot model)
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
