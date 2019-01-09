package com.myrran.spell.generators;

import com.myrran.spell.data.entityparams.SpellDebuffParams;
import com.myrran.spell.data.templatedata.SpellDebuffTemplate;
import com.myrran.spell.generators.custom.CustomSpellDebuff;

/** @author Ivan Delgado Huerta */
public interface SpellDebuffGenerator
{
    String getId();
    String getName();

    CustomSpellDebuff setId(String id);
    CustomSpellDebuff setName(String name);

    void setSpellEffectTemplate(SpellDebuffTemplate spellDebuffTemplate);
    SpellDebuffParams getSpellEffectData();
}
