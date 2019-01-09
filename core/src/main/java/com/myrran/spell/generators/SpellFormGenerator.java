package com.myrran.spell.generators;

import com.myrran.spell.data.entityparams.SpellDebuffParams;
import com.myrran.spell.data.entityparams.SpellFormParams;
import com.myrran.spell.data.templatedata.SpellFormTemplate;
import com.myrran.spell.entity.form.SpellForm;
import com.myrran.spell.generators.custom.CustomSpellForm;

import java.util.List;

/** @author Ivan Delgado Huerta */
public interface SpellFormGenerator
{
    String getId();
    String getName();

    CustomSpellForm setId(String id);
    CustomSpellForm setName(String name);

    void setSpellFormTemplate(SpellFormTemplate spellFormTemplate);
    SpellFormParams getSpellFormData();
    List<SpellDebuffParams> getSpellEffectDataList();
    SpellForm cast();
}
