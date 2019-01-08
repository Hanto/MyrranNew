package main.com.myrran.spell.data.templatedata;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

/** @author Ivan Delgado Huerta */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SpellBookTemplates
{
    private Map<String, SpellFormTemplate> spellFromTemplates;

    // SETTERS GETTERS:
    //------------------------------------------------------------------------------------------------------------------

    public Map<String, SpellFormTemplate> getSpellFromTemplates()                             { return spellFromTemplates; }
    public SpellBookTemplates setSpellFromTemplates(Map<String, SpellFormTemplate> spellFromTemplates){ this.spellFromTemplates = spellFromTemplates; return this; }
}
