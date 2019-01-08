package main.com.myrran.spell.generators.debuff;

import main.com.myrran.spell.SpellSlotKey;
import main.com.myrran.spell.data.entitydata.SpellDebuffData;
import main.com.myrran.spell.data.templatedata.SpellDebuffTemplate;
import main.com.myrran.spell.entity.debuff.SpellDebuffFactory;
import main.com.myrran.spell.generators.form.CustomSpellStat;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class CustomSpellDebuff implements SpellDebuffGenerator
{
    private String id;
    private String name;
    private String templateID;
    private SpellDebuffFactory factory;
    private List<CustomSpellStat> customSpellStats;
    private int baseCost;
    private List<SpellSlotKey> keys;

    // SETTERS GETTERS:
    //------------------------------------------------------------------------------------------------------------------

    @Override public String getId()                                     { return id; }
    @Override public String getName()                                   { return name; }
    public String getTemplateID()                                       { return templateID; }
    public SpellDebuffFactory getFactory()                              { return factory; }
    public List<CustomSpellStat> getCustomSpellStats()                  { return customSpellStats; }
    public List<SpellSlotKey> getKeys()                                 { return keys; }

    @Override public CustomSpellDebuff setId(String id)                 { this.id = id; return this; }
    @Override public CustomSpellDebuff setName(String name)             { this.name = name; return this; }
    public CustomSpellDebuff setKeys(SpellSlotKey... spellSlotKeys)     { this.keys = Arrays.asList(spellSlotKeys); return this; }

    // TEMPLATE TO CUSTOM:
    //------------------------------------------------------------------------------------------------------------------

    public CustomSpellDebuff(SpellDebuffTemplate spellDebuffTemplate)
    {   setSpellEffectTemplate(spellDebuffTemplate); }

    @Override public void setSpellEffectTemplate(SpellDebuffTemplate spellDebuffTemplate)
    {
        templateID = spellDebuffTemplate.getId();
        factory = spellDebuffTemplate.getFactory();
        baseCost = spellDebuffTemplate.getBaseCost();
        keys = spellDebuffTemplate.getKeys();

        customSpellStats = spellDebuffTemplate.getSpellStats().stream()
            .map(CustomSpellStat::new)
            .sorted(Comparator.comparing(CustomSpellStat::getID))
            .collect(Collectors.toList());
    }

    // CUSTOM TO ENTITY DATA:
    //------------------------------------------------------------------------------------------------------------------

    @Override public SpellDebuffData getSpellEffectData()
    {
        SpellDebuffData data = new SpellDebuffData();
        data.setFactory(factory);

        for (CustomSpellStat stat: getCustomSpellStats())
            data.addStat(stat.getSpellStatData());

        return data;
    }

    // MAIN:
    //------------------------------------------------------------------------------------------------------------------

    public int getTotalCost()
    {
        return customSpellStats.stream()
            .mapToInt(CustomSpellStat::getTotalCost)
            .sum()

            + baseCost;
    }
}

