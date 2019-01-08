package main.com.myrran.spell.generators.form;

import main.com.myrran.spell.data.templatedata.SpellFormTemplate;
import main.com.myrran.spell.data.entitydata.SpellEffectData;
import main.com.myrran.spell.entity.form.SpellForm;
import main.com.myrran.spell.entity.form.SpellFormFactory;
import main.com.myrran.spell.data.entitydata.SpellFormData;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class CustomSpellForm implements SpellFormI
{
    private String id;
    private String name;
    private String templateID;
    private SpellFormFactory factory;
    private List<CustomSpellStat> customSpellStats;
    private List<CustomSpellSlot> customSpellSlots;

    // SETTERS GETTERS:
    //------------------------------------------------------------------------------------------------------------------

    @Override public String getId()                                     { return id; }
    @Override public String getName()                                   { return name; }
    public String getTemplateID()                                       { return templateID; }
    public List<CustomSpellStat> getCustomSpellStats()                  { return customSpellStats; }
    public List<CustomSpellSlot> getCustomSpellSlots()                  { return customSpellSlots; }

    @Override public CustomSpellForm setId(String id)                   { this.id = id; return this; }
    @Override public CustomSpellForm setName(String name)               { this.name = name; return this; }

    // TEMPLATE TO CUSTOM:
    //------------------------------------------------------------------------------------------------------------------

    public CustomSpellForm(SpellFormTemplate spellFormTemplate)
    {   setSpellFormTemplate(spellFormTemplate);}

    @Override public void setSpellFormTemplate(SpellFormTemplate spellFormTemplate)
    {
        templateID = spellFormTemplate.getId();
        factory = spellFormTemplate.getFactory();

        customSpellStats = spellFormTemplate.getSpellStats().stream()
            .map(CustomSpellStat::new)
            .sorted(Comparator.comparing(CustomSpellStat::getID))
            .collect(Collectors.toList());

        customSpellSlots = spellFormTemplate.getSpellSlots().stream()
            .map(CustomSpellSlot::new)
            .sorted(Comparator.comparing(CustomSpellSlot::getID))
            .collect(Collectors.toList());
    }

    // CUSTOM TO ENTITY DATA:
    //------------------------------------------------------------------------------------------------------------------

    @Override public SpellFormData getSpellFormData()
    {
        SpellFormData data = new SpellFormData();
        data.setFactory(factory);

        for (CustomSpellStat stat: getCustomSpellStats())
            data.addStat(stat.getSpellStatData());

        return data;
    }

    @Override public List<SpellEffectData> getSpellEffectDataList()
    {
        return customSpellSlots.stream()
            .filter(customSpellSlot -> customSpellSlot.getCustomSpellEffect() != null)
            .map(CustomSpellSlot::getSpellEffectData)
            .collect(Collectors.toList());
    }

    // MAIN:
    //------------------------------------------------------------------------------------------------------------------

    @Override public SpellForm cast()
    {
        SpellForm entity = factory.getFormEntity();
        entity.setSpellFormData(getSpellFormData());
        entity.setSpellEffectData(getSpellEffectDataList());
        return entity;
    }
}