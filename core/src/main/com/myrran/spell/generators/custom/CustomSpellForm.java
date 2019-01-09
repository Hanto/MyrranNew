package main.com.myrran.spell.generators.custom;

import main.com.myrran.spell.data.entityparams.SpellDebuffParams;
import main.com.myrran.spell.data.entityparams.SpellFormParams;
import main.com.myrran.spell.data.templatedata.SpellFormTemplate;
import main.com.myrran.spell.data.templatedata.SpellStatTemplate;
import main.com.myrran.spell.entity.form.SpellForm;
import main.com.myrran.spell.entity.form.SpellFormFactory;
import main.com.myrran.spell.generators.SpellFormGenerator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomSpellForm implements SpellFormGenerator
{
    private String id;
    private String name;
    private String templateID;
    private Map<String, CustomSpellStat> customSpellStats;
    private SpellFormFactory factory;
    private List<CustomSpellSlot> customSpellSlots;

    // SETTERS GETTERS:
    //------------------------------------------------------------------------------------------------------------------

    @Override public String getId()                                     { return id; }
    @Override public String getName()                                   { return name; }
    public String getTemplateID()                                       { return templateID; }
    public Map<String, CustomSpellStat> getCustomSpellStats()           { return customSpellStats; }
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

        spellFormTemplate.getSpellStats()
            .forEach(this::setSpellStatTemplate);

        customSpellSlots = spellFormTemplate.getSpellSlots().stream()
            .map(CustomSpellSlot::new)
            .sorted(Comparator.comparing(CustomSpellSlot::getID))
            .collect(Collectors.toList());
    }

    private void setSpellStatTemplate(SpellStatTemplate template)
    {
        CustomSpellStat customSpellStat = customSpellStats.get(template.getID());
        customSpellStat.setSpellStatTemplate(template);
    }

    // CUSTOM TO ENTITY DATA:
    //------------------------------------------------------------------------------------------------------------------

    @Override public SpellFormParams getSpellFormData()
    {
        SpellFormParams data = new SpellFormParams();
        data.setFactory(factory);

        for (CustomSpellStat stat: getCustomSpellStats().values())
            data.addStat(stat.getSpellStatData());

        return data;
    }

    @Override public List<SpellDebuffParams> getSpellEffectDataList()
    {
        return customSpellSlots.stream()
            .filter(customSpellSlot -> customSpellSlot.getCustomSpellDebuff() != null)
            .map(CustomSpellSlot::getSpellEffectData)
            .collect(Collectors.toList());
    }

    // MAIN:
    //------------------------------------------------------------------------------------------------------------------

    @Override public SpellForm cast()
    {
        SpellForm entity = factory.getFormEntity();
        entity.setSpellFormParams(getSpellFormData());
        entity.setSpellEffectData(getSpellEffectDataList());
        return entity;
    }

    public int getTotalCost()
    {
        return customSpellStats.values().stream()
            .mapToInt(CustomSpellStat::getTotalCost)
            .sum() +

        customSpellSlots.stream()
            .mapToInt(CustomSpellSlot::getTotalCost)
            .sum();
    }
}