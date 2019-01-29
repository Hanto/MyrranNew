package com.myrran.model.spell.templates;

import com.myrran.misc.dataestructures.quantitymap.QuantifiableI;
import com.myrran.model.components.Identifiable;
import com.myrran.model.spell.entities.subform.SpellSubformFactory;
import com.myrran.model.spell.generators.CustomSpellSlotKey;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** @author Ivan Delgado Huerta */
@XmlAccessorType(XmlAccessType.FIELD)
public class TemplateSpellSubform implements QuantifiableI, Identifiable
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
    private SpellSubformFactory factory;
    private List<TemplateSpellStat> spellStats;
    private List<TemplateSpellSlot> spellSlots;
    private int baseCost;
    private List<CustomSpellSlotKey> keys = new ArrayList<>();

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    @Override public String getID()                         { return id; }
    public String getName()                                 { return name; }
    @Override public Integer getTotal()                     { return total; }
    @Override public Integer getAvailable()                 { return available; }
    public SpellSubformFactory getFactory()                 { return factory; }
    public List<TemplateSpellStat> getSpellStats()          { return spellStats; }
    public List<TemplateSpellSlot> getSpellSlots()          { return spellSlots; }
    public Integer getBaseCost()                            { return baseCost; }
    public List<CustomSpellSlotKey> getKeys()               { return keys; }

    @Override public void setID(String id)                  { this.id = id; }
    public void setName(String name)                        { this.name = name; }
    @Override public void setAvailable(Integer available)   { this.available = available; }
    @Override public void setTotal(Integer total)           { this.total = total; }
    public void setFactory(SpellSubformFactory factory)     { this.factory = factory; }
    public void setSpellStats(TemplateSpellStat...stats)    { this.spellStats = Arrays.asList(stats); }
    public void setSpellDebuffs(TemplateSpellSlot...slots)    { this.spellSlots = Arrays.asList(slots); }
    public void setBaseCost(int baseCost)                   { this.baseCost = baseCost; }
    public void setKeys(CustomSpellSlotKey...keys)          { this.keys.addAll(Arrays.asList(keys)); }
}
