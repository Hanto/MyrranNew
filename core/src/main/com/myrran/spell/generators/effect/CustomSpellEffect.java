package main.com.myrran.spell.generators.effect;

import main.com.myrran.spell.SpellSlotKey;
import main.com.myrran.spell.data.entitydata.SpellEffectData;
import main.com.myrran.spell.data.templatedata.SpellEffectTemplate;
import main.com.myrran.spell.entity.effect.SpellEffectFactory;
import main.com.myrran.spell.generators.form.CustomSpellStat;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class CustomSpellEffect implements SpellEffectI
{
    private String id;
    private String name;
    private String templateID;
    private SpellEffectFactory factory;
    private List<CustomSpellStat> customSpellStats;
    private List<SpellSlotKey> keys;

    // SETTERS GETTERS:
    //------------------------------------------------------------------------------------------------------------------

    @Override public String getId()                                     { return id; }
    @Override public String getName()                                   { return name; }
    public String getTemplateID()                                       { return templateID; }
    public SpellEffectFactory getFactory()                              { return factory; }
    public List<CustomSpellStat> getCustomSpellStats()                  { return customSpellStats; }
    public List<SpellSlotKey> getKeys()                                 { return keys; }

    @Override public CustomSpellEffect setId(String id)                 { this.id = id; return this; }
    @Override public CustomSpellEffect setName(String name)             { this.name = name; return this; }
    public CustomSpellEffect setKeys(SpellSlotKey... spellSlotKeys)     { this.keys = Arrays.asList(spellSlotKeys); return this; }

    // TEMPLATE TO CUSTOM:
    //------------------------------------------------------------------------------------------------------------------

    public CustomSpellEffect(SpellEffectTemplate spellEffectTemplate)
    {   setSpellEffectTemplate(spellEffectTemplate); }

    @Override public void setSpellEffectTemplate(SpellEffectTemplate spellEffectTemplate)
    {
        templateID = spellEffectTemplate.getId();
        factory = spellEffectTemplate.getFactory();

        customSpellStats = spellEffectTemplate.getSpellStats().stream()
            .map(CustomSpellStat::new)
            .sorted(Comparator.comparing(CustomSpellStat::getID))
            .collect(Collectors.toList());

        keys = spellEffectTemplate.getKeys();
    }

    // CUSTOM TO ENTITY DATA:
    //------------------------------------------------------------------------------------------------------------------

    @Override public SpellEffectData getSpellEffectData()
    {
        SpellEffectData data = new SpellEffectData();
        data.setFactory(factory);

        for (CustomSpellStat stat: getCustomSpellStats())
            data.addStat(stat.getSpellStatData());

        return data;
    }
}

