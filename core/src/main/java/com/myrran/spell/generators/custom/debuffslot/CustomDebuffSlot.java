package com.myrran.spell.generators.custom.debuffslot;

import com.myrran.misc.Identifiable;
import com.myrran.misc.observable.Observable;
import com.myrran.misc.observable.ObservableDeco;
import com.myrran.misc.observable.ObservableI;
import com.myrran.spell.data.entityparams.SpellDebuffParams;
import com.myrran.spell.data.templatedata.SpellDebuffSlotTemplate;
import com.myrran.spell.generators.custom.CustomSpellDebuff;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** @author Ivan Delgado Huerta */
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomDebuffSlot implements ObservableDeco, Identifiable
{
    private String id;
    private String name;
    private String type;
    private List<CustomSpellSlotKey> lock = new ArrayList<>();
    private CustomSpellDebuff customSpellDebuff;
    @XmlTransient
    private ObservableI observable = new Observable(this);

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    @Override public String getID()                             { return id; }
    public String getName()                                     { return name; }
    public String getSlotType()                                 { return type; }
    public List<CustomSpellSlotKey>getLock()                    { return lock; }
    public CustomSpellDebuff getCustomSpellDebuff()             { return customSpellDebuff; }
    @Override public void setID(String id)                      { this.id = id; }
    public void setName(String name)                            { this.name = name; notifyChanges(); }
    public void setType(String type)                            { this.type = type; notifyChanges(); }
    public void setLock(CustomSpellSlotKey...integers)          { lock.addAll(Arrays.asList(integers)); notifyChanges(); }
    @Override public ObservableI getObservable()                { return observable; }

    // TEMPLATE TO CUSTOM:
    //--------------------------------------------------------------------------------------------------------

    public CustomDebuffSlot() {}
    public CustomDebuffSlot(SpellDebuffSlotTemplate template)
    {   setDebuffSlotTemplate(template); }

    public void setDebuffSlotTemplate(SpellDebuffSlotTemplate template)
    {
        this.id = template.getID();
        this.name = template.getName();
        this.type = template.getSlotType();
        this.lock = new ArrayList<>(template.getLock());
    }

    // CUSTOM TO ENTITY DATA:
    //--------------------------------------------------------------------------------------------------------

    public SpellDebuffParams getSpellEffectData()
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

    public boolean setCustomSpellDebuff(CustomSpellDebuff effect)
    {
        if(opensLock(effect.getKeys()))
        {
            customSpellDebuff = effect;
            notifyChanges();
            return true;
        }
        else return false;
    }

    public void removeCustomSpellDebuff()
    {
        this.customSpellDebuff =  null;
        notifyChanges();
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    private void notifyChanges()
    {   notify("debuffSlot", null, null); }
}