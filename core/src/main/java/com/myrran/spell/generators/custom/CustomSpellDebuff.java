package com.myrran.spell.generators.custom;

import com.myrran.spell.data.entityparams.SpellDebuffParams;
import com.myrran.spell.data.templatedata.SpellDebuffTemplate;
import com.myrran.spell.data.templatedata.SpellStatTemplate;
import com.myrran.spell.entity.debuff.SpellDebuffFactory;
import com.myrran.spell.generators.SpellDebuffGenerator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** @author Ivan Delgado Huerta */
public class CustomSpellDebuff implements SpellDebuffGenerator
{
    private String id;
    private String name;
    private String templateID;
    private int baseCost;
    private SpellDebuffFactory factory;
    private List<CustomSpellSlotKey> keys;
    private Map<String, CustomSpellStat> customSpellStats = new HashMap<>();

    // SETTERS GETTERS:
    //------------------------------------------------------------------------------------------------------------------

    @Override public String getId()                                     { return id; }
    @Override public String getName()                                   { return name; }
    public String getTemplateID()                                       { return templateID; }
    public SpellDebuffFactory getFactory()                              { return factory; }
    public Map<String, CustomSpellStat> getCustomSpellStats()           { return customSpellStats; }
    public List<CustomSpellSlotKey> getKeys()                           { return keys; }

    @Override public CustomSpellDebuff setId(String id)                 { this.id = id; return this; }
    @Override public CustomSpellDebuff setName(String name)             { this.name = name; return this; }
    public CustomSpellDebuff setKeys(CustomSpellSlotKey... keys)        { this.keys = Arrays.asList(keys); return this; }

    // TEMPLATE TO CUSTOM:
    //------------------------------------------------------------------------------------------------------------------

    @Override public void setSpellEffectTemplate(SpellDebuffTemplate spellDebuffTemplate)
    {
        templateID = spellDebuffTemplate.getId();
        baseCost = spellDebuffTemplate.getBaseCost();
        factory = spellDebuffTemplate.getFactory();
        keys = spellDebuffTemplate.getKeys();

        spellDebuffTemplate.getSpellStats()
            .forEach(this::setSpellStatTemplate);
    }

    private void setSpellStatTemplate(SpellStatTemplate template)
    {
        CustomSpellStat customSpellStat = customSpellStats.get(template.getID());
        if (customSpellStat == null)
        {
            customSpellStat = new CustomSpellStat();
            customSpellStats.put(template.getID(), customSpellStat);
        }
        customSpellStat.setSpellStatTemplate(template);
    }

    // CUSTOM TO ENTITY DATA:
    //------------------------------------------------------------------------------------------------------------------

    @Override public SpellDebuffParams getSpellEffectData()
    {
        SpellDebuffParams data = new SpellDebuffParams().
            setFactory(factory);

        for (CustomSpellStat stat: getCustomSpellStats().values())
            data.addStat(stat.getSpellStatData());

        return data;
    }

    // MAIN:
    //------------------------------------------------------------------------------------------------------------------

    public int getTotalCost()
    {
        return customSpellStats.values().stream()
            .mapToInt(CustomSpellStat::getTotalCost)
            .sum()

            + baseCost;
    }
}

