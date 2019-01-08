package main.com.myrran.spell.generators.debuff;

import main.com.myrran.spell.data.entitydata.SpellDebuffData;
import main.com.myrran.spell.data.templatedata.SpellDebuffTemplate;

/** @author Ivan Delgado Huerta */
public interface SpellDebuffGenerator
{
    String getId();
    String getName();

    CustomSpellDebuff setId(String id);
    CustomSpellDebuff setName(String name);

    void setSpellEffectTemplate(SpellDebuffTemplate spellDebuffTemplate);
    SpellDebuffData getSpellEffectData();
}
