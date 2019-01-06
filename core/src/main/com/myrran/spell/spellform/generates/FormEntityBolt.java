package main.com.myrran.spell.spellform.generates;

import main.com.myrran.misc.Consumable;
import main.com.myrran.utils.HashMapArrayList;
import main.com.myrran.utils.MapList;
import main.com.myrran.spell.spelleffect.generators.SpellEffectData;
import main.com.myrran.spell.spellform.generators.SpellFormData;

import java.util.List;

public class FormEntityBolt implements FormEntity, Consumable
{
    private static final String DURATION = "duration";
    private static final String SPEED = "speed";

    private static final String IMPACTO = "impacto";
    private static final String AOE = "aoe";
    private static final String GROUND = "ground";

    private float actualDuration = 0.0f;
    private float maxDuration = 5.0f;

    private SpellFormData spellFormData;
    private MapList<String, SpellEffectData>spellEffectsData = new HashMapArrayList<>();

    // CONSUMABLE:
    //------------------------------------------------------------------------------------------------------------------

    @Override public float getActualDuration()                      { return actualDuration; }
    @Override public float getMaxDuration()                         { return maxDuration; }
    @Override public void setMaxDuration(float maxDuration)         { this.maxDuration = maxDuration; }
    @Override public void setActualDuration(float actualDuration)   { this.actualDuration = actualDuration; }

    // MAIN:
    //------------------------------------------------------------------------------------------------------------------

    public void setSpellFormData(SpellFormData spellFormData)
    {   this.spellFormData = spellFormData; }

    public void setSpellEffectData(List<SpellEffectData> spellEffectDataList)
    {
        for(SpellEffectData spellEffectData : spellEffectDataList)
            spellEffectsData.add(spellEffectData.getSlotType(), spellEffectData);
    }

    // INIT:
    //------------------------------------------------------------------------------------------------------------------

    public void init()
    {
        setMaxDuration(spellFormData.getStat(DURATION));
    }

}
