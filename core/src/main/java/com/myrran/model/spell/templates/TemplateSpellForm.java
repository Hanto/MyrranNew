package com.myrran.model.spell.templates;

import com.myrran.misc.dataestructures.quantitymap.QuantifiableI;
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
public class TemplateSpellForm implements QuantifiableI, Identifiable
{
    @XmlAttribute
    private String id;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private Integer available = 0;
    @XmlAttribute
    private Integer total = 0;
    @XmlAttribute
    private SpellFormFactory factory;
    private List<TemplateSpellStat> spellStats;
    private List<TemplateSpellSlot> spellDebuffs;
    private List<TemplateSpellSlot> spellSubforms;

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    @Override public String getID()                             { return id; }
    public String getName()                                     { return name; }
    @Override public Integer getTotal()                         { return total; }
    @Override public Integer getAvailable()                     { return available; }
    public SpellFormFactory getFactory()                        { return factory; }
    public List<TemplateSpellStat> getSpellStats()              { return spellStats; }
    public List<TemplateSpellSlot> getSpellDebuffs()            { return spellDebuffs; }
    public List<TemplateSpellSlot> getSpellSubforms()           { return spellSubforms; }

    @Override public void setID(String id)                      { this.id = id; }
    public void setName(String name)                            { this.name = name; }
    @Override public void setAvailable(Integer available)       { this.available = available; }
    @Override public void setTotal(Integer total)               { this.total = total; }
    public void setFactory(SpellFormFactory type)               { this.factory = type; }
    public void setSpellStats(TemplateSpellStat...stats)        { this.spellStats = Arrays.asList(stats); }
    public void setSpellDebuffs(TemplateSpellSlot...slots)      { this.spellDebuffs = Arrays.asList(slots); }
    public void setSpellSubforms(TemplateSpellSlot...slots)     { this.spellSubforms = Arrays.asList(slots); }
}
