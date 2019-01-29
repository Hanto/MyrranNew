package com.myrran.model.spell.entities.form;

import com.myrran.model.components.consumer.ConsumableDeco;
import com.myrran.model.components.consumer.Consumable;
import com.myrran.model.spell.parameters.SpellDebuffParams;
import com.myrran.model.spell.parameters.SpellFormParams;
import com.myrran.misc.dataestructures.maplist.MapListI;
import com.myrran.misc.dataestructures.maplist.MapList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** @author Ivan Delgado Huerta */
public class SpellFormBolt implements SpellForm, ConsumableDeco
{
    private static final String DURATION = "duration";
    private static final String SPEED = "speed";

    private static final String IMPACTO = "impacto";
    private static final String AOE = "aoe";
    private static final String GROUND = "ground";

    private SpellFormParams spellFormParams;

    private Consumable consumable = new Consumable();

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    @Override public Consumable getConsumable()      { return consumable; }

    // DATA:
    //--------------------------------------------------------------------------------------------------------

    @Override public void setSpellFormParams(SpellFormParams params)
    {
        this.spellFormParams = params;
        setMaxDuration(spellFormParams.getFormStatParams().get(DURATION).getTotal());
        List<SpellDebuffParams> debuffsAppliedOnImpact = spellFormParams.getDebuffParams().get(IMPACTO);
        List<SpellDebuffParams> debuffsAppliedOnAOE = spellFormParams.getDebuffParams().get(AOE);
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

}
