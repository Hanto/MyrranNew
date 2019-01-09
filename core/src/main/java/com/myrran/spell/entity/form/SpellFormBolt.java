package com.myrran.spell.entity.form;

import com.myrran.misc.Consumable;
import com.myrran.spell.data.entityparams.SpellDebuffParams;
import com.myrran.spell.data.entityparams.SpellFormParams;
import com.myrran.utils.HashMapArrayList;
import com.myrran.utils.MapList;

import java.util.List;

/** @author Ivan Delgado Huerta */
public class SpellFormBolt implements SpellForm, Consumable
{
    private static final String DURATION = "duration";
    private static final String SPEED = "speed";

    private static final String IMPACTO = "impacto";
    private static final String AOE = "aoe";
    private static final String GROUND = "ground";

    private float actualDuration = 0.0f;
    private float maxDuration = 5.0f;

    private SpellFormParams spellFormParams;
    private MapList<String, SpellDebuffParams> spellEffectsData = new HashMapArrayList<>();

    // CONSUMABLE:
    //------------------------------------------------------------------------------------------------------------------

    @Override public float getActualDuration()                      { return actualDuration; }
    @Override public float getMaxDuration()                         { return maxDuration; }
    @Override public void setMaxDuration(float maxDuration)         { this.maxDuration = maxDuration; }
    @Override public void setActualDuration(float actualDuration)   { this.actualDuration = actualDuration; }

    // DATA:
    //------------------------------------------------------------------------------------------------------------------

    @Override public void setSpellFormParams(SpellFormParams data)
    {
        this.spellFormParams = data;
        setMaxDuration(spellFormParams.getStat(DURATION).getTotal());
    }

    @Override public void setSpellEffectData(List<SpellDebuffParams> spellDebuffParamsList)
    {
        for(SpellDebuffParams data : spellDebuffParamsList)
            spellEffectsData.add(data.getSlotType(), data);
    }
}
