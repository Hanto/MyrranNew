package com.myrran.model.spell.generators;

import com.myrran.model.spell.parameters.SpellFormParams;
import com.myrran.model.spell.templates.SpellFormTemplate;
import com.myrran.model.spell.entities.form.SpellForm;

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
