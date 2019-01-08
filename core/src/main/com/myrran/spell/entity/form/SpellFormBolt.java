package main.com.myrran.spell.entity.form;

import main.com.myrran.misc.Consumable;
import main.com.myrran.utils.HashMapArrayList;
import main.com.myrran.utils.MapList;
import main.com.myrran.spell.data.entitydata.SpellDebuffData;
import main.com.myrran.spell.data.entitydata.SpellFormData;

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

    private SpellFormData spellFormData;
    private MapList<String, SpellDebuffData>spellEffectsData = new HashMapArrayList<>();

    // CONSUMABLE:
    //------------------------------------------------------------------------------------------------------------------

    @Override public float getActualDuration()                      { return actualDuration; }
    @Override public float getMaxDuration()                         { return maxDuration; }
    @Override public void setMaxDuration(float maxDuration)         { this.maxDuration = maxDuration; }
    @Override public void setActualDuration(float actualDuration)   { this.actualDuration = actualDuration; }

    // DATA:
    //------------------------------------------------------------------------------------------------------------------

    @Override public void setSpellFormData(SpellFormData data)
    {
        this.spellFormData = data;
        setMaxDuration(spellFormData.getStat(DURATION).getTotal());
    }

    @Override public void setSpellEffectData(List<SpellDebuffData> spellDebuffDataList)
    {
        for(SpellDebuffData data : spellDebuffDataList)
            spellEffectsData.add(data.getSlotType(), data);
    }
}
