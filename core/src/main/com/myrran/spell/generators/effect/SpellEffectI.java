package main.com.myrran.spell.generators.effect;

import main.com.myrran.spell.data.entitydata.SpellEffectData;
import main.com.myrran.spell.data.templatedata.SpellEffectTemplate;

/** @author Ivan Delgado Huerta */
public interface SpellEffectI
{
    String getId();
    String getName();

    CustomSpellEffect setId(String id);
    CustomSpellEffect setName(String name);

    void setSpellEffectTemplate(SpellEffectTemplate spellEffectTemplate);
    SpellEffectData getSpellEffectData();
}
