package com.myrran.model.spell.templates;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

/** @author Ivan Delgado Huerta */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TemplateSpellBook
{
    private Map<String, TemplateSpellForm> spellFromTemplates = new HashMap<>();
    private Map<String, TemplateSpellDebuff> spellDebuffTemplates = new HashMap<>();

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    public Map<String, TemplateSpellForm> getSpellFormTemplates()
    {   return spellFromTemplates; }

    public Map<String, TemplateSpellDebuff> getSpellDebuffTemplates()
    {   return spellDebuffTemplates; }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public TemplateSpellForm getSpellFormTemplate(String templateID)
    {   return spellFromTemplates.get(templateID); }

    public TemplateSpellDebuff getSpellDebuffTemplate(String templateID)
    {   return spellDebuffTemplates.get(templateID); }

    public void addSpellFormTemplate(TemplateSpellForm spellFormTemplate)
    {   this.spellFromTemplates.put(spellFormTemplate.getID(), spellFormTemplate); }

    public void addSpellDebuffTemplate(TemplateSpellDebuff spellDebuffTemplate)
    {   this.spellDebuffTemplates.put(spellDebuffTemplate.getID(), spellDebuffTemplate); }
}
