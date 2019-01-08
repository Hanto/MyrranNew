package main.com.myrran.spell.data.templatedata;

import main.com.myrran.spell.SpellSlotKey;
import main.com.myrran.spell.entity.debuff.SpellDebuffFactory;

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
public class SpellDebuffTemplate
{
    @XmlAttribute
    private String id;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private SpellDebuffFactory type;
    private List<SpellStatTemplate> spellStats;
    private int baseCost;
    private List<SpellSlotKey> keys = new ArrayList<>();

    // SETTERS GETTERS:
    //------------------------------------------------------------------------------------------------------------------

    public String getId()                                                           { return id; }
    public String getName()                                                         { return name; }
    public SpellDebuffFactory getFactory()                                          { return type; }
    public List<SpellStatTemplate> getSpellStats()                                  { return spellStats; }
    public int getBaseCost()                                                        { return baseCost; }
    public List<SpellSlotKey> getKeys()                                             { return keys; }

    public SpellDebuffTemplate setId(String id)                                     { this.id = id; return this; }
    public SpellDebuffTemplate setName(String name)                                 { this.name = name; return this; }
    public SpellDebuffTemplate setFactory(SpellDebuffFactory type)                  { this.type = type; return this; }
    public SpellDebuffTemplate setSpellStats(List<SpellStatTemplate> spellStats)    { this.spellStats = spellStats; return this; }
    public SpellDebuffTemplate setBaseCost(int baseCost)                            { this.baseCost = baseCost; return this; }
    public SpellDebuffTemplate setKeys(List<SpellSlotKey>keys)                      { this.keys = keys; return this; }
    public SpellDebuffTemplate setKeys(SpellSlotKey...keys)                         { this.keys.addAll(Arrays.asList(keys)); return this; }
}