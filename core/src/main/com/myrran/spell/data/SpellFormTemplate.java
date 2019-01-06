package main.com.myrran.spell.data;

import main.com.myrran.spell.spellform.generates.FormEntityFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SpellFormTemplate
{
    @XmlAttribute
    private String id;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private FormEntityFactory type;
    private List<SpellStatTemplate>spellStats;
    private List<SpellSlotDataTemplate>spellSlots;

    // GET:
    //------------------------------------------------------------------------------------------------------------------

    public String getId()                                                           { return id; }
    public String getName()                                                         { return name; }
    public FormEntityFactory getFactory()                                           { return type; }
    public List<SpellStatTemplate> getSpellStats()                                  { return spellStats; }
    public List<SpellSlotDataTemplate> getSpellSlots()                              { return spellSlots; }

    // SET:
    //------------------------------------------------------------------------------------------------------------------

    public SpellFormTemplate setId(String id)                                       { this.id = id; return this; }
    public SpellFormTemplate setName(String name)                                   { this.name = name; return this; }
    public SpellFormTemplate setType(FormEntityFactory type)                        { this.type = type; return this; }
    public SpellFormTemplate setSpellStats(List<SpellStatTemplate> spellStats)      { this.spellStats = spellStats; return this; }
    public SpellFormTemplate setSpellSlots(List<SpellSlotDataTemplate> spellSlots)  { this.spellSlots = spellSlots; return this; }
}
