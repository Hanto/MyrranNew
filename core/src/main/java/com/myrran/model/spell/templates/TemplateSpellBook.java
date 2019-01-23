package com.myrran.model.spell.templates;

import com.myrran.misc.InvalidIDException;

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
    private Map<String, TemplateSpellForm> spellFormTemplates = new HashMap<>();
    private Map<String, TemplateSpellDebuff> spellDebuffTemplates = new HashMap<>();

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    public Map<String, TemplateSpellForm> getSpellFormTemplates()
    {   return spellFormTemplates; }

    public Map<String, TemplateSpellDebuff> getSpellDebuffTemplates()
    {   return spellDebuffTemplates; }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public TemplateSpellForm getSpellFormTemplate(String templateID) throws InvalidIDException
    {
        TemplateSpellForm template = spellFormTemplates.get(templateID);
        if (template != null) return template;
        else throw new InvalidIDException("A TemplateSpellForm with the following ID doesn't exist: %s", templateID);
    }

    public TemplateSpellDebuff getSpellDebuffTemplate(String templateID) throws InvalidIDException
    {
        TemplateSpellDebuff template = spellDebuffTemplates.get(templateID);
        if (template != null) return template;
        else throw new InvalidIDException("A TemplateSpellDebuff with the following ID doesn't exist: %s", templateID);
    }

    public void addSpellFormTemplate(TemplateSpellForm spellFormTemplate)
    {   this.spellFormTemplates.put(spellFormTemplate.getID(), spellFormTemplate); }

    public void addSpellDebuffTemplate(TemplateSpellDebuff spellDebuffTemplate)
    {   this.spellDebuffTemplates.put(spellDebuffTemplate.getID(), spellDebuffTemplate); }
}
