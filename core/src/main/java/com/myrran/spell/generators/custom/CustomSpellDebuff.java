package com.myrran.spell.generators.custom;

import com.myrran.misc.Identifiable;
import com.myrran.spell.data.entityparams.SpellDebuffParams;
import com.myrran.spell.data.templatedata.SpellDebuffTemplate;
import com.myrran.spell.data.templatedata.SpellStatTemplate;
import com.myrran.spell.entity.debuff.SpellDebuffFactory;
import com.myrran.spell.generators.SpellDebuffGenerator;
import com.myrran.utils.InvalidIDException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** @author Ivan Delgado Huerta */
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomSpellDebuff implements SpellDebuffGenerator, Identifiable
{
    private String id;
    private String name;
    private String templateID;
    private int baseCost;
    private SpellDebuffFactory factory;
    private List<CustomSpellSlotKey> keys;
    private Map<String, CustomSpellStat> stats = new HashMap<>();

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    @Override public String getID()                             { return id; }
    @Override public String getName()                           { return name; }
    public String getTemplateID()                               { return templateID; }
    public SpellDebuffFactory getFactory()                      { return factory; }
    public List<CustomSpellSlotKey> getKeys()                   { return keys; }
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

    private void setSpellStatTemplate(SpellStatTemplate template)
    {
        CustomSpellStat customSpellStat = stats.get(template.getID());
        if (customSpellStat == null)
        {
            customSpellStat = new CustomSpellStat();
            stats.put(template.getID(), customSpellStat);
        }
        customSpellStat.setSpellStatTemplate(template);
    }

    // CUSTOM TO ENTITY DATA:
    //--------------------------------------------------------------------------------------------------------

    @Override public SpellDebuffParams getSpellEffectData()
    {
        SpellDebuffParams data = new SpellDebuffParams().
            setFactory(factory);

        for (CustomSpellStat stat: stats.values())
            data.addStat(stat.getSpellStatData());

        return data;
    }

    // STATS:
    //--------------------------------------------------------------------------------------------------------

    public CustomSpellStat getCustomSpellStat(String statID) throws InvalidIDException
    {
        CustomSpellStat stat = stats.get(statID);
        if (stat != null) return stat;
        else throw new InvalidIDException("SpellStat with the following ID doesn't exist: %s", statID);
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public int getTotalCost()
    {
        return stats.values().stream()
            .mapToInt(CustomSpellStat::getTotalCost)
            .sum()

            + baseCost;
    }
}