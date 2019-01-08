package main.com.myrran.spell.data.templatedata;

import main.com.myrran.spell.SpellSlotKey;
import main.com.myrran.spell.entity.effect.SpellEffectFactory;

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
public class SpellEffectTemplate
{
    @XmlAttribute
    private String id;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private SpellEffectFactory type;
    private List<SpellStatTemplate> spellStats;
    private List<SpellSlotKey> keys = new ArrayList<>();

    // SETTERS GETTERS:
    //------------------------------------------------------------------------------------------------------------------

    public String getId()                                                           { return id; }
    public String getName()                                                         { return name; }
    public SpellEffectFactory getFactory()                                          { return type; }
    public List<SpellStatTemplate> getSpellStats()                                  { return spellStats; }
    public List<SpellSlotKey> getKeys()                                             { return keys; }

    public SpellEffectTemplate setId(String id)                                     { this.id = id; return this; }
    public SpellEffectTemplate setName(String name)                                 { this.name = name; return this; }
    public SpellEffectTemplate setFactory(SpellEffectFactory type)                  { this.type = type; return this; }
    public SpellEffectTemplate setSpellStats(List<SpellStatTemplate> spellStats)    { this.spellStats = spellStats; return this; }
    public SpellEffectTemplate setKeys(List<SpellSlotKey>keys)                      { this.keys = keys; return this; }
    public SpellEffectTemplate setKeys(SpellSlotKey...keys)                         { this.keys.addAll(Arrays.asList(keys)); return this; }
}
