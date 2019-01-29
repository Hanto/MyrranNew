package com.myrran.model.spell.templates;

import com.myrran.model.components.Identifiable;
import com.myrran.model.spell.generators.CustomSpellSlotKey;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** @author Ivan Delgado Huerta */
@XmlAccessorType(XmlAccessType.FIELD)
public class TemplateSpellSlot implements Identifiable
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

    @Override public String getID()                     { return id; }
    public String getName()                             { return name; }
    public String getSlotType()                         { return slotType; }
    public List<CustomSpellSlotKey> getLock()           { return lock; }

    @Override public void setID(String id)              { this.id = id; }
    public void setName(String name)                    { this.name = name; }
    public void setSlotType(String slotType)            { this.slotType = slotType; }
    public void setLock(CustomSpellSlotKey... lock)     { this.lock.addAll(Arrays.asList(lock)); }
}
