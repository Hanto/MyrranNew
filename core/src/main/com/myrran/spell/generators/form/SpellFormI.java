package main.com.myrran.spell.generators.form;

import main.com.myrran.spell.data.entitydata.SpellEffectData;
import main.com.myrran.spell.data.entitydata.SpellFormData;
import main.com.myrran.spell.data.templatedata.SpellFormTemplate;
import main.com.myrran.spell.entity.form.SpellForm;

import java.util.List;

public interface SpellFormI
{
    String getId();
    String getName();

    CustomSpellForm setId(String id);
    CustomSpellForm setName(String name);

    void setSpellFormTemplate(SpellFormTemplate spellFormTemplate);
    SpellFormData getSpellFormData();
    List<SpellEffectData> getSpellEffectDataList();
    SpellForm cast();
}
