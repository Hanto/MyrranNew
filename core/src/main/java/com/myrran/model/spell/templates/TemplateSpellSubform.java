package com.myrran.model.spell.templates;

import com.myrran.misc.dataestructures.quantitymap.QuantifiableI;
import com.myrran.model.components.Identifiable;
import com.myrran.model.components.observable.Observable;
import com.myrran.model.components.observable.ObservableDeco;
import com.myrran.model.components.observable.ObservableI;
import com.myrran.model.spell.entities.subform.SpellSubformFactory;
import com.myrran.model.spell.generators.CustomSpellSlotKey;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** @author Ivan Delgado Huerta */
@XmlAccessorType(XmlAccessType.FIELD)
public class TemplateSpellSubform implements QuantifiableI, Identifiable, ObservableDeco
{
    @XmlAttribute
    private String id;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private Integer available = 0;
    @XmlAttribute
    private Integer total = 0;
    @XmlAttribute
    private SpellSubformFactory factory;
    private List<TemplateSpellStat> spellStats;
    private List<TemplateSpellSlot> spellSlots;
    private int baseCost;
    private List<CustomSpellSlotKey> keys = new ArrayList<>();
    @XmlTransient private ObservableI observable = new Observable(this);

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    @Override public String getID()                         { return id; }
    public String getName()                                 { return name; }
    @Override public Integer getTotal()                     { return total; }
    @Override public Integer getAvailable()                 { return available; }
    public SpellSubformFactory getFactory()                 { return factory; }
    public List<TemplateSpellStat> getSpellStats()          { return spellStats; }
    public List<TemplateSpellSlot> getSpellSlots()          { return spellSlots; }
    public Integer getBaseCost()                            { return baseCost; }
    public List<CustomSpellSlotKey> getKeys()               { return keys; }

    @Override public void setID(String id)                  { this.id = id; notifyChange(); }
    public void setName(String name)                        { this.name = name; notifyChange(); }
    @Override public void setAvailable(Integer available)   { this.available = available; notifyChange(); }
    @Override public void setTotal(Integer total)           { this.total = total; notifyChange(); }
    public void setFactory(SpellSubformFactory factory)     { this.factory = factory; notifyChange(); }
    public void setSpellStats(TemplateSpellStat...stats)    { this.spellStats = Arrays.asList(stats); notifyChange(); }
    public void setSpellDebuffs(TemplateSpellSlot...slots)  { this.spellSlots = Arrays.asList(slots); notifyChange(); }
    public void setBaseCost(int baseCost)                   { this.baseCost = baseCost; notifyChange(); }
    public void setKeys(CustomSpellSlotKey...keys)          { this.keys.addAll(Arrays.asList(keys)); notifyChange(); }
    @Override public ObservableI getObservable()            { return observable; }

    private void notifyChange()
    {   notify("debuffTemplate", null, null); }
}
