package com.myrran.spell.data.templatedata;

import com.myrran.misc.Identifiable;
import com.myrran.spell.entity.debuff.SpellDebuffFactory;
import com.myrran.spell.generators.custom.debuffslot.CustomSpellSlotKey;

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
public class SpellDebuffTemplate implements Identifiable
{
    @XmlAttribute
    private String id;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private SpellDebuffFactory type;
    private List<SpellStatTemplate> spellStats;
    private int baseCost;
    private List<CustomSpellSlotKey> keys = new ArrayList<>();

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    @Override public String getID()                         { return id; }
    public String getName()                                 { return name; }
    public SpellDebuffFactory getFactory()                  { return type; }
    public List<SpellStatTemplate> getSpellStats()          { return spellStats; }
    public int getBaseCost()                                { return baseCost; }
    public List<CustomSpellSlotKey> getKeys()               { return keys; }

    @Override public void setID(String id)                  { this.id = id; }
    public void setName(String name)                        { this.name = name; }
    public void setFactory(SpellDebuffFactory type)         { this.type = type; }
    public void setSpellStats(SpellStatTemplate...stats)    { this.spellStats = Arrays.asList(stats); }
    public void setBaseCost(int baseCost)                   { this.baseCost = baseCost; }
    public void setKeys(CustomSpellSlotKey...keys)          { this.keys.addAll(Arrays.asList(keys)); }
}
