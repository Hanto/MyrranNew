package com.myrran.spell.data.templatedata;

import com.myrran.spell.generators.custom.CustomSpellSlotKey;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** @author Ivan Delgado Huerta */
@XmlAccessorType(XmlAccessType.FIELD)
public class SpellSlotTemplate
{
    @XmlAttribute
    private String id;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String slotType;
    private List<CustomSpellSlotKey> lock = new ArrayList<>();

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------
    public String getId()                                       { return id; }
    public String getName()                                     { return name; }
    public String getSlotType()                                 { return slotType; }
    public List<CustomSpellSlotKey> getLock()                         { return lock; }

    public SpellSlotTemplate setId(String id)                   { this.id = id; return this; }
    public SpellSlotTemplate setName(String name)               { this.name = name; return this; }
    public SpellSlotTemplate setSlotType(String slotType)       { this.slotType = slotType; return this; }
    public SpellSlotTemplate setLock(CustomSpellSlotKey... lock)      { this.lock.addAll(Arrays.asList(lock)); return this; }
}
