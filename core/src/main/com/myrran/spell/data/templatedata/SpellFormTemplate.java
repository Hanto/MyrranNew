package main.com.myrran.spell.data.templatedata;

import main.com.myrran.spell.entity.form.SpellFormFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
import java.util.List;

/** @author Ivan Delgado Huerta */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SpellFormTemplate
{
    @XmlAttribute
    private String id;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private SpellFormFactory type;
    private List<SpellStatTemplate>spellStats;
    private List<SpellSlotTemplate>spellSlots;

    // SETTERS GETTERS:
    //------------------------------------------------------------------------------------------------------------------

    public String getId()                                                           { return id; }
    public String getName()                                                         { return name; }
    public SpellFormFactory getFactory()                                            { return type; }
    public List<SpellStatTemplate> getSpellStats()                                  { return spellStats; }
    public List<SpellSlotTemplate> getSpellSlots()                                  { return spellSlots; }

    public SpellFormTemplate setId(String id)                                       { this.id = id; return this; }
    public SpellFormTemplate setName(String name)                                   { this.name = name; return this; }
    public SpellFormTemplate setFactory(SpellFormFactory type)                      { this.type = type; return this; }
    public SpellFormTemplate setSpellStats(List<SpellStatTemplate> spellStats)      { this.spellStats = spellStats; return this; }
    public SpellFormTemplate setSpellStats(SpellStatTemplate...stats)               { this.spellStats = Arrays.asList(stats); return this; }
    public SpellFormTemplate setSpellSlots(List<SpellSlotTemplate> spellSlots)      { this.spellSlots = spellSlots; return this; }
    public SpellFormTemplate setSpellSlots(SpellSlotTemplate...slots)               { this.spellSlots = Arrays.asList(slots); return this; }
}
