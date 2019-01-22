package com.myrran.model.spell.templates;

import com.myrran.model.components.Identifiable;
import com.myrran.model.spell.entities.form.SpellFormFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
import java.util.List;

/** @author Ivan Delgado Huerta */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SpellFormTemplate implements Identifiable
{
    @XmlAttribute
    private String id;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private SpellFormFactory type;
    private List<SpellStatTemplate> spellStats;
    private List<SpellDebuffSlotTemplate> spellSlots;

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    @Override public String getID()                         { return id; }
    public String getName()                                 { return name; }
    public SpellFormFactory getFactory()                    { return type; }
    public List<SpellStatTemplate> getSpellStats()          { return spellStats; }
    public List<SpellDebuffSlotTemplate> getSpellSlots()          { return spellSlots; }

    @Override public void setID(String id)                  { this.id = id; }
    public void setName(String name)                        { this.name = name; }
    public void setFactory(SpellFormFactory type)           { this.type = type; }
    public void setSpellStats(SpellStatTemplate...stats)    { this.spellStats = Arrays.asList(stats); }
    public void setSpellSlots(SpellDebuffSlotTemplate...slots)    { this.spellSlots = Arrays.asList(slots); }
}
