package main.com.myrran.spell.entity.form;

import main.com.myrran.spell.data.entityparams.SpellDebuffParams;
import main.com.myrran.spell.data.entityparams.SpellFormParams;

import java.util.List;

/** @author Ivan Delgado Huerta */
public interface SpellForm
{
    void setSpellFormParams(SpellFormParams spellFormParams);
    void setSpellEffectData(List<SpellDebuffParams> spellDebuffParamsList);
}
