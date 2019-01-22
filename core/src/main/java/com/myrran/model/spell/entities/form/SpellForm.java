package com.myrran.model.spell.entities.form;

import com.myrran.model.spell.parameters.SpellDebuffParams;
import com.myrran.model.spell.parameters.SpellFormParams;

import java.util.List;

/** @author Ivan Delgado Huerta */
public interface SpellForm
{
    void setSpellFormParams(SpellFormParams spellFormParams);
    void setSpellEffectData(List<SpellDebuffParams> spellDebuffParamsList);
}
