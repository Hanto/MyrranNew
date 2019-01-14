package com.myrran.spell.generators.custom;

import com.myrran.misc.Identifiable;
import com.myrran.spell.data.entityparams.SpellDebuffParams;
import com.myrran.spell.data.templatedata.SpellSlotTemplate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** @author Ivan Delgado Huerta */
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomSpellSlot implements Identifiable
{
    private String id;
    private String name;
    private String type;
    private List<CustomSpellSlotKey> lock = new ArrayList<>();
    private CustomSpellDebuff customSpellDebuff;

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    @Override public String getID()                             { return id; }
    public String getName()                                     { return name; }
    public String getSlotType()                                 { return type; }
    public List<CustomSpellSlotKey>getLock()                    { return lock; }
    public CustomSpellDebuff getCustomSpellDebuff()             { return customSpellDebuff; }
    @Override public void setID(String id)                      { this.id = id; }
    public void setName(String name)                            { this.name = name; }
    public void setType(String type)                            { this.type = type; }
    public void setLock(CustomSpellSlotKey...integers)          { lock.addAll(Arrays.asList(integers)); }

    // TEMPLATE TO CUSTOM:
    //--------------------------------------------------------------------------------------------------------

    void setSpellSlotTemplate(SpellSlotTemplate template)
    {
        this.id = template.getID();
        this.name = template.getName();
        this.type = template.getSlotType();
        this.lock = new ArrayList<>(template.getLock());
    }

    // CUSTOM TO ENTITY DATA:
    //--------------------------------------------------------------------------------------------------------

    SpellDebuffParams getSpellEffectData()
    {
        SpellDebuffParams data = customSpellDebuff.getSpellEffectData();
        data.setSlotType(type);

        return data;
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public boolean opensLock(List<CustomSpellSlotKey>keys)
    {   return !Collections.disjoint(keys, lock); }

    public int getTotalCost()
    {   return customSpellDebuff.getTotalCost(); }

    void setCustomSpellDebuff(CustomSpellDebuff effect)
    {
        if(opensLock(effect.getKeys()))
            this.customSpellDebuff = effect;
    }

    void removeCustomSpellDebuff()
    {   this.customSpellDebuff =  null; }

}