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

    public Map<String, SpellFormTemplate> getSpellFormTemplates()
    {   return spellFromTemplates; }

    public Map<String, SpellDebuffTemplate> getSpellDebuffTemplates()
    {   return spellDebuffTemplates; }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public SpellFormTemplate getSpellFormTemplate(String templateID)
    {   return spellFromTemplates.get(templateID); }

    public SpellDebuffTemplate getSpellDebuffTemplate(String templateID)
    {   return spellDebuffTemplates.get(templateID); }

    public void addSpellFormTemplate(SpellFormTemplate spellFormTemplate)
    {   this.spellFromTemplates.put(spellFormTemplate.getID(), spellFormTemplate); }

    public void addSpellDebuffTemplate(SpellDebuffTemplate spellDebuffTemplate)
    {   this.spellDebuffTemplates.put(spellDebuffTemplate.getID(), spellDebuffTemplate); }
}
