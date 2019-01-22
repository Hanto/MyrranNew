package com.myrran.model.spell.generators;

import com.myrran.model.spell.parameters.SpellDebuffParams;
import com.myrran.model.spell.templates.SpellDebuffTemplate;

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
