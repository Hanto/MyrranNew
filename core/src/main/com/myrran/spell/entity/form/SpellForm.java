package main.com.myrran.spell.entity.form;

import main.com.myrran.spell.data.entitydata.SpellDebuffData;
import main.com.myrran.spell.data.entitydata.SpellFormData;

import java.util.List;

/** @author Ivan Delgado Huerta */
public interface SpellForm
{
    void setSpellFormData(SpellFormData spellFormData);
    void setSpellEffectData(List<SpellDebuffData> spellDebuffDataList);
}
