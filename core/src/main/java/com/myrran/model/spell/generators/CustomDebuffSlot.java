package com.myrran.model.spell.generators;

import com.myrran.model.components.Identifiable;
import com.myrran.model.components.observable.Observable;
import com.myrran.model.components.observable.ObservableDeco;
import com.myrran.model.components.observable.ObservableI;
import com.myrran.model.spell.parameters.SpellDebuffParams;
import com.myrran.model.spell.templates.TemplateSpellDebuff;
import com.myrran.model.spell.templates.TemplateSpellSlot;
import com.myrran.view.ui.customspell.form.SpellSlotI;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** @author Ivan Delgado Huerta */
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomDebuffSlot implements ObservableDeco, Identifiable, SpellSlotI<CustomSpellDebuff>
{
    @XmlAttribute
    private String id;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String type;
    private List<CustomSpellSlotKey> lock = new ArrayList<>();
    private CustomSpellDebuff customSpellDebuff = new CustomSpellDebuff();
    @XmlTransient
    private ObservableI observable = new Observable(this);

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    @Override public String getID()                             { return id; }
    public String getName()                                     { return name; }
    public String getSlotType()                                 { return type; }
    public List<CustomSpellSlotKey>getLock()                    { return lock; }
    public CustomSpellDebuff getContent()                       { return customSpellDebuff; }
    public boolean hasData()                                    { return customSpellDebuff.hasData(); }
    @Override public void setID(String id)                      { this.id = id; }
    public void setName(String name)                            { this.name = name; notifyChanges(); }
    public void setType(String type)                            { this.type = type; notifyChanges(); }
    public void setLock(CustomSpellSlotKey...integers)          { lock.addAll(Arrays.asList(integers)); notifyChanges(); }
    @Override public ObservableI getObservable()                { return observable; }

    // TEMPLATE TO CUSTOM:
    //--------------------------------------------------------------------------------------------------------

    public CustomDebuffSlot() {}
    public CustomDebuffSlot(TemplateSpellSlot template)
    {   setDebuffSlotTemplate(template); }

    public void setDebuffSlotTemplate(TemplateSpellSlot template)
    {
        this.id = template.getID();
        this.name = template.getName();
        this.type = template.getSlotType();
        this.lock = new ArrayList<>(template.getLock());
    }

    // CUSTOM TO ENTITY DATA:
    //--------------------------------------------------------------------------------------------------------

    public SpellDebuffParams getSpellDebuffParams()
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

    public boolean setCustomSpellDebuff(TemplateSpellDebuff template)
    {
        if (opensLock(template.getKeys()))
        {
            customSpellDebuff.setSpellDebuffTemplate(template);
            notifyChanges();
            return true;
        }
        else return false;
    }

    public void removeCustomSpellDebuff()
    {
        customSpellDebuff.setSpellDebuffTemplate(null);
        notifyChanges();
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    private void notifyChanges()
    {   notify("debuffSlot", null, null); }
}