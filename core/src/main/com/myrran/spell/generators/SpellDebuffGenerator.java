package main.com.myrran.spell.generators;

import main.com.myrran.spell.data.entityparams.SpellDebuffParams;
import main.com.myrran.spell.data.templatedata.SpellDebuffTemplate;
import main.com.myrran.spell.generators.custom.CustomSpellDebuff;

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
