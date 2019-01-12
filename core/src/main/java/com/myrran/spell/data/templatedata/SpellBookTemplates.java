package com.myrran.spell.data.templatedata;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

/** @author Ivan Delgado Huerta */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SpellBookTemplates
{
    private Map<String, SpellFormTemplate> spellFromTemplates = new HashMap<>();
    private Map<String, SpellDebuffTemplate> spellDebuffTemplates = new HashMap<>();

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    public Map<String, SpellFormTemplate> getSpellFromTemplates()
    {   return spellFromTemplates; }

    public Map<String, SpellDebuffTemplate> getSpellDebuffTemplates()
    {   return spellDebuffTemplates; }

    public SpellBookTemplates addSpellFormTemplate(SpellFormTemplate spellFormTemplate)
    {   this.spellFromTemplates.put(spellFormTemplate.getId(), spellFormTemplate); return this; }

    public SpellBookTemplates addSpellDebuffTemplate(SpellDebuffTemplate spellDebuffTemplate)
    {   this.spellDebuffTemplates.put(spellDebuffTemplate.getId(), spellDebuffTemplate); return this; }
}
