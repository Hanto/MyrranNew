package main.com.myrran.spell.generators;

import main.com.myrran.spell.data.entityparams.SpellDebuffParams;
import main.com.myrran.spell.data.entityparams.SpellFormParams;
import main.com.myrran.spell.data.templatedata.SpellFormTemplate;
import main.com.myrran.spell.entity.form.SpellForm;
import main.com.myrran.spell.generators.custom.CustomSpellForm;

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
