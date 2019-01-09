package main.com.myrran.spell.generators.custom;

import main.com.myrran.spell.data.entityparams.SpellDebuffParams;
import main.com.myrran.spell.data.templatedata.SpellSlotTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** @author Ivan Delgado Huerta */
public class CustomSpellSlot
{
    private String id;
    private String name;
    private String type;
    private List<CustomSpellSlotKey>lock = new ArrayList<>();
    private CustomSpellDebuff customSpellDebuff;

    // SETTERS GETTERS:
    //------------------------------------------------------------------------------------------------------------------

    public String getID()                                           { return id; }
    public String getName()                                         { return name; }
    public String getSlotType()                                     { return type; }
    public List<CustomSpellSlotKey>getLock()                        { return lock; }
    public CustomSpellDebuff getCustomSpellDebuff()                 { return customSpellDebuff; }

    public CustomSpellSlot setId(String id)                         { this.id = id; return this; }
    public CustomSpellSlot setName(String name)                     { this.name = name; return this; }
    public CustomSpellSlot setType(String type)                     { this.type = type; return this; }
    public CustomSpellSlot setLock(CustomSpellSlotKey...integers)   { lock.addAll(Arrays.asList(integers)); return this; }
    public CustomSpellSlot setCustomSpellDebuff(CustomSpellDebuff effect){ this.customSpellDebuff = effect; return this; }

    // TEMPLATE TO CUSTOM:
    //------------------------------------------------------------------------------------------------------------------

    public void setSpellSlotTemplate(SpellSlotTemplate data)
    {
        this.id = data.getId();
        this.name = data.getName();
        this.type = data.getSlotType();
        this.lock = new ArrayList<>(data.getLock());
    }

    // CUSTOM TO ENTITY DATA:
    //------------------------------------------------------------------------------------------------------------------

    public SpellDebuffParams getSpellEffectData()
    {
        SpellDebuffParams data = customSpellDebuff.getSpellEffectData();
        data.setSlotType(type);

        return data;
    }

    // MAIN:
    //------------------------------------------------------------------------------------------------------------------

    public int getTotalCost()
    {   return customSpellDebuff.getTotalCost(); }

}