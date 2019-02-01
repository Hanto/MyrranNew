package com.myrran.view.ui.customspell.header;

import com.badlogic.gdx.graphics.Color;
import com.myrran.model.spell.templates.TemplateSpellDebuff;
import com.myrran.view.ui.Atlas;

/** @author Ivan Delgado Huerta */
public class TDebuffHeaderView extends SpellHeaderView<TemplateSpellDebuff>
{
    @Override
    protected void update()
    {
        setIcon(Atlas.get().getTexture("TexturasIconos/FireBall"));
        setAvailableTotal(String.format("%s/%s", model.getAvailable(), model.getTotal()));
        setrAvailableTotalColor(model.getAvailable() > 0 ? Color.GREEN : Color.RED);
        setKeys(model.getKeys().toString());
        setIconName(model.getName());
        setCost(model.getBaseCost().toString());
    }
}
