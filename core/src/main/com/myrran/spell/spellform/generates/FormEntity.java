package main.com.myrran.spell.spellform.generates;

import main.com.myrran.spell.spelleffect.generators.SpellEffectData;
import main.com.myrran.spell.spellform.generators.SpellFormData;

import java.util.List;

public interface FormEntity
{
    void setSpellFormData(SpellFormData spellFormData);
    void setSpellEffectData(List<SpellEffectData> spellEffectDataList);
}
