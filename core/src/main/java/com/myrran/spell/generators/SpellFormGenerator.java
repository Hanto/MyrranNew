package com.myrran.spell.generators;

import com.myrran.spell.data.entityparams.SpellFormParams;
import com.myrran.spell.data.templatedata.SpellFormTemplate;
import com.myrran.spell.entity.form.SpellForm;

/** @author Ivan Delgado Huerta */
public interface SpellFormGenerator
{
    String getID();
    String getName();

    void setID(String id);
    void setName(String name);
    void setSpellFormTemplate(SpellFormTemplate spellFormTemplate);

    SpellFormParams getSpellFormParams();
    SpellForm cast();
}
