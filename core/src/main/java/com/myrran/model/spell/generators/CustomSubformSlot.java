package com.myrran.model.spell.generators;

import com.myrran.model.components.Identifiable;
import com.myrran.model.components.observable.Observable;
import com.myrran.model.components.observable.ObservableDeco;
import com.myrran.model.components.observable.ObservableI;
import com.myrran.model.spell.parameters.SpellSubformParams;
import com.myrran.model.spell.templates.TemplateSpellSlot;
import com.myrran.model.spell.templates.TemplateSpellSubform;

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
public class CustomSubformSlot implements ObservableDeco, Identifiable,
    SpellSlotI<CustomSpellSubform, TemplateSpellSubform>
{
    @XmlAttribute
    private String id;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String type;
    private List<CustomSpellSlotKey> lock = new ArrayList<>();
    private CustomSpellSubform customSpellSubform = new CustomSpellSubform();
    @XmlTransient
    private ObservableI observable = new Observable(this);

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    @Override public String getID()                             { return id; }
    public String getName()                                     { return name; }
    public String getSlotType()                                 { return type; }
    public List<CustomSpellSlotKey>getLock()                    { return lock; }
    public CustomSpellSubform getContent()                      { return customSpellSubform; }
    public boolean hasData()                                    { return customSpellSubform.hasData(); }
    @Override public void setID(String id)                      { this.id = id; }
    public void setName(String name)                            { this.name = name; notifyChanges(); }
    public void setType(String type)                            { this.type = type; notifyChanges(); }
    public void setLock(CustomSpellSlotKey...integers)          { lock.addAll(Arrays.asList(integers)); notifyChanges(); }
    @Override public ObservableI getObservable()                { return observable; }

    // TEMPLATE TO CUSTOM:
    //--------------------------------------------------------------------------------------------------------

    public CustomSubformSlot() {}
    public CustomSubformSlot(TemplateSpellSlot template)
    {   setSubformSlotTemplate(template);}

    public void setSubformSlotTemplate(TemplateSpellSlot template)
    {
        this.id = template.getID();
        this.name = template.getName();
        this.type = template.getSlotType();
        this.lock = new ArrayList<>(template.getLock());
    }

    // CUSTOM TO ENTITY DATA:
    //--------------------------------------------------------------------------------------------------------

    public SpellSubformParams getSpellDebuffParams()
    {
        SpellSubformParams data = customSpellSubform.getSpellFormData();
        data.setSlotType(type);

        return data;
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public boolean opensLock(List<CustomSpellSlotKey>keys)
    {   return !Collections.disjoint(keys, lock); }

    public int getTotalCost()
    {   return customSpellSubform.getTotalCost(); }

    public boolean setContent(TemplateSpellSubform template)
    {
        if (opensLock(template.getKeys()))
        {
            customSpellSubform.setSpellSubformTemplate(template);
            notifyChanges();
            return true;
        }
        return false;
    }

    public void removeContent()
    {
        customSpellSubform.setSpellSubformTemplate(null);
        notifyChanges();
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    private void notifyChanges()
    {   notify("debuffSlot", null, null); }
}
