package main.com.myrran.spell.spellform.generators;

import main.com.myrran.spell.SpellSlot;
import main.com.myrran.spell.SpellStat;
import main.com.myrran.spell.data.SpellFormTemplate;
import main.com.myrran.spell.data.SpellSlotDataTemplate;
import main.com.myrran.spell.data.SpellStatTemplate;
import main.com.myrran.spell.spelleffect.generators.SpellEffectData;
import main.com.myrran.spell.spelleffect.generators.SpellEffectI;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SpellForm implements SpellFormI
{
    private String id;
    private String name;
    private String spellFormDataID;
    private SpellFormType type;
    private List<SpellStat> spellStats = new ArrayList<SpellStat>();
    private List<SpellSlot> spellSlots = new ArrayList<SpellSlot>();

    // GET:
    //------------------------------------------------------------------------------------------------------------------

    @Override public String getId()                                     { return id; }
    @Override public String getName()                                   { return name; }
    public String getSpellFormDataID()                                  { return spellFormDataID; }
    public List<SpellStat> getSpellStats()                              { return spellStats; }
    public List<SpellSlot> getSpellSlots()                              { return spellSlots; }

    // SET:
    //------------------------------------------------------------------------------------------------------------------

    @Override public SpellForm setId(String id)                         { this.id = id; return this; }
    @Override public SpellForm setName(String name)                     { this.name = name; return this; }
    public SpellForm setSpellFormDataID(String dataID)                  { this.spellFormDataID = dataID; return this; }
    public SpellForm setSpellStats(List<SpellStat> spellStats)          { this.spellStats = spellStats; return this; }
    public SpellForm setSpellSlots(List<SpellSlot>spellSlots)           { this.spellSlots = spellSlots; return this; }

    // MAIN:
    //------------------------------------------------------------------------------------------------------------------

    public void setSpellFormTemplate(SpellFormTemplate spellFormTemplate)
    {
        spellFormDataID = spellFormTemplate.getId();
        type = spellFormTemplate.getType();

        for (SpellStatTemplate stat: spellFormTemplate.getSpellStats())
        {
            SpellStat spellStat = new SpellStat();
            spellStat.setSpellStatTemplate(stat);
            spellStats.add(spellStat);
        }

        for (SpellSlotDataTemplate slot: spellFormTemplate.getSpellSlots())
        {
            SpellSlot spellSlot = new SpellSlot();
            spellSlot.setSpellSlotTemplate(slot);
            spellSlots.add(spellSlot);
        }
    }

    public SpellFormData generateSpellFormData()
    {
        SpellFormData data = new SpellFormData();
        data.setSpellForm(this);
        return data;
    }

    public List<SpellEffectData> generateSpellEffectData()
    {
        List<SpellEffectData>dataList = new Stack<SpellEffectData>();
        for (SpellSlot slot: spellSlots)
        {
            SpellEffectI spellEffect = slot.getSpellEffect();
            if (spellEffect != null)
            {
                SpellEffectData data = new SpellEffectData();
                data.setSpellEffect(spellEffect);
                data.setSpellSlot(slot);
                dataList.add(data);
            }
        }
        return dataList;
    }
}
