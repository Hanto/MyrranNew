package main.com.myrran.spell.spellform.generates;

import main.com.myrran.utils.HashMapArrayList;
import main.com.myrran.utils.MapList;
import main.com.myrran.spell.spelleffect.generators.SpellEffectData;
import main.com.myrran.spell.spellform.generators.SpellFormData;

import java.util.List;

public class FormEntityBolt implements FormEntity
{
    private static final String DURATION = "duration";
    private static final String COOLDOWN = "cooldown";
    private static final String SPEED = "speed";

    private static final String IMPACTO = "impacto";
    private static final String AOE = "aoe";
    private static final String GROUND = "ground";

    private SpellFormData spellFormData;
    private MapList<String, SpellEffectData>spellEffectsData = new HashMapArrayList<String, SpellEffectData>();

    // MAIN:
    //------------------------------------------------------------------------------------------------------------------

    public void setSpellFormData(SpellFormData spellFormData)
    {   this.spellFormData = spellFormData; }

    public void setSpellEffectData(List<SpellEffectData> spellEffectDataList)
    {
        for(SpellEffectData spellEffectData : spellEffectDataList)
            spellEffectsData.add(spellEffectData.getSlotType(), spellEffectData);
    }
}
