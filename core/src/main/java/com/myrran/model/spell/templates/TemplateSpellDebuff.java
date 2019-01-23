package com.myrran.model.spell.templates;

import com.myrran.model.components.Identifiable;
import com.myrran.model.spell.entities.debuff.SpellDebuffFactory;
import com.myrran.model.spell.generators.custom.CustomSpellSlotKey;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** @author Ivan Delgado Huerta */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TemplateSpellDebuff implements Identifiable
{
    @XmlAttribute
    private String id;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private SpellDebuffFactory type;
    private List<TemplateSpellStat> spellStats;
    private int baseCost;
    private List<CustomSpellSlotKey> keys = new ArrayList<>();

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    @Override public String getID()                         { return id; }
    public String getName()                                 { return name; }
    public SpellDebuffFactory getFactory()                  { return type; }
    public List<TemplateSpellStat> getSpellStats()          { return spellStats; }
    public int getBaseCost()                                { return baseCost; }
    public List<CustomSpellSlotKey> getKeys()               { return keys; }

    @Override public void setID(String id)                  { this.id = id; }
    public void setName(String name)                        { this.name = name; }
    public void setFactory(SpellDebuffFactory type)         { this.type = type; }
    public void setSpellStats(TemplateSpellStat...stats)    { this.spellStats = Arrays.asList(stats); }
    public void setBaseCost(int baseCost)                   { this.baseCost = baseCost; }
    public void setKeys(CustomSpellSlotKey...keys)          { this.keys.addAll(Arrays.asList(keys)); }
}