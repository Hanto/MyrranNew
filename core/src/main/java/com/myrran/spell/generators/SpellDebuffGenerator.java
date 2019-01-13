package com.myrran.spell.generators;

import com.myrran.spell.data.entityparams.SpellDebuffParams;
import com.myrran.spell.data.templatedata.SpellDebuffTemplate;

/** @author Ivan Delgado Huerta */
public interface SpellDebuffGenerator
{
    String getID();
    String getName();

    void setID(String id);
    void setName(String name);

    void setSpellDebuffTemplate(SpellDebuffTemplate spellDebuffTemplate);
    SpellDebuffParams getSpellEffectData();
}
