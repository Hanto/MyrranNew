package main.com.myrran.spell.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SpellBookTemplates
{
    private Map<String, SpellFormTemplate> spellFromTemplates;

    // GET:
    //------------------------------------------------------------------------------------------------------------------

    public Map<String, SpellFormTemplate> getSpellFromTemplates()                             { return spellFromTemplates; }

    // SET:
    //------------------------------------------------------------------------------------------------------------------

    public SpellBookTemplates setSpellFromTemplates(Map<String, SpellFormTemplate> spellFromTemplates){ this.spellFromTemplates = spellFromTemplates; return this; }
}
