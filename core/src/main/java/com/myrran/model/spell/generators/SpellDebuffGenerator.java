package com.myrran.model.spell.generators;

import com.myrran.model.spell.parameters.SpellDebuffParams;
import com.myrran.model.spell.templates.TemplateSpellDebuff;

/** @author Ivan Delgado Huerta */
public interface SpellDebuffGenerator
{
    String getID();
    String getName();

    void setID(String id);
    void setName(String name);

    void setSpellDebuffTemplate(TemplateSpellDebuff spellDebuffTemplate);
    SpellDebuffParams getSpellEffectData();
}
