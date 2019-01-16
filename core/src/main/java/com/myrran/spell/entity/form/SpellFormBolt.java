package com.myrran.spell.entity.form;

import com.myrran.misc.Consumable;
import com.myrran.misc.ConsumableImp;
import com.myrran.spell.data.entityparams.SpellDebuffParams;
import com.myrran.spell.data.entityparams.SpellFormParams;
import com.myrran.dataestructures.MapList.MapList;
import com.myrran.dataestructures.MapList.MapListImp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** @author Ivan Delgado Huerta */
public class SpellFormBolt implements SpellForm, Consumable
{
    private static final String DURATION = "duration";
    private static final String SPEED = "speed";

    private static final String IMPACTO = "impacto";
    private static final String AOE = "aoe";
    private static final String GROUND = "ground";

    private SpellFormParams spellFormParams;
    private MapList<String, SpellDebuffParams> spellEffectsData
        = new MapListImp<>(new HashMap<>(), ArrayList::new);
    private ConsumableImp consumable = new ConsumableImp();

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    @Override public ConsumableImp getConsumable()      { return consumable; }

    // DATA:
    //--------------------------------------------------------------------------------------------------------

    @Override public void setSpellFormParams(SpellFormParams params)
    {
        this.spellFormParams = params;
        setMaxDuration(spellFormParams.getStat(DURATION).getTotal());
    }

    @Override public void setSpellEffectData(List<SpellDebuffParams> spellDebuffParamsList)
    {
        for(SpellDebuffParams data : spellDebuffParamsList)
            spellEffectsData.add(data.getSlotType(), data);
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

}
