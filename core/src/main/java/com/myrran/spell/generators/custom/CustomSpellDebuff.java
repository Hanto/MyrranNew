package com.myrran.spell.generators.custom;

import com.myrran.misc.Identifiable;
import com.myrran.spell.data.entityparams.SpellDebuffParams;
import com.myrran.spell.data.templatedata.SpellDebuffTemplate;
import com.myrran.spell.entity.debuff.SpellDebuffFactory;
import com.myrran.spell.generators.SpellDebuffGenerator;
import com.myrran.spell.generators.custom.stats.CustomSpellStats;
import com.myrran.spell.generators.custom.stats.CustomSpellStatsable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Arrays;
import java.util.List;

/** @author Ivan Delgado Huerta */
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomSpellDebuff implements SpellDebuffGenerator, CustomSpellStatsable, Identifiable
{
    private String id;
    private String name;
    private String templateID;
    private int baseCost;
    private SpellDebuffFactory factory;
    private List<CustomSpellSlotKey> keys;
    private CustomSpellStats spellStats = new CustomSpellStats();

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    @Override public String getID()                             { return id; }
    @Override public String getName()                           { return name; }
    public String getTemplateID()                               { return templateID; }
    public SpellDebuffFactory getFactory()                      { return factory; }
    public List<CustomSpellSlotKey> getKeys()                   { return keys; }
    @Override public CustomSpellStats getSpellStats()           { return spellStats; }
    @Override public void setID(String id)                      { this.id = id; }
    @Override public void setName(String name)                  { this.name = name; }
    public void setKeys(CustomSpellSlotKey... keys)             { this.keys = Arrays.asList(keys); }

    // TEMPLATE TO CUSTOM:
    //--------------------------------------------------------------------------------------------------------

    public CustomSpellDebuff(SpellDebuffTemplate template)
    {   setSpellDebuffTemplate(template); }

    @Override public void setSpellDebuffTemplate(SpellDebuffTemplate template)
    {
        id = template.getID();
        name = template.getName();
        templateID = template.getID();
        baseCost = template.getBaseCost();
        factory = template.getFactory();
        keys = template.getKeys();

        template.getSpellStats()
            .forEach(this::setSpellStatTemplate);
    }

    // CUSTOM TO ENTITY DATA:
    //--------------------------------------------------------------------------------------------------------

    @Override public SpellDebuffParams getSpellEffectData()
    {
        return new SpellDebuffParams()
            .setFactory(factory)
            .setSpellStatParams(getSpellStatParams());
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public int getTotalCost()
    {   return getStatsTotalCost() + baseCost; }
}