package main.com.myrran.spell.generators.form;

import main.com.myrran.spell.SpellSlotKey;
import main.com.myrran.spell.data.entitydata.SpellEffectData;
import main.com.myrran.spell.data.templatedata.SpellSlotTemplate;
import main.com.myrran.spell.generators.effect.CustomSpellEffect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** @author Ivan Delgado Huerta */
public class CustomSpellSlot
{
    private String id;
    private String name;
    private String type;
    private List<SpellSlotKey>lock = new ArrayList<>();
    private CustomSpellEffect customSpellEffect;

    // SETTERS GETTERS:
    //------------------------------------------------------------------------------------------------------------------

    public String getID()                                           { return id; }
    public String getName()                                         { return name; }
    public String getSlotType()                                     { return type; }
    public List<SpellSlotKey>getLock()                              { return lock; }
    public CustomSpellEffect getCustomSpellEffect()                 { return customSpellEffect; }

    public CustomSpellSlot setId(String id)                         { this.id = id; return this; }
    public CustomSpellSlot setName(String name)                     { this.name = name; return this; }
    public CustomSpellSlot setType(String type)                     { this.type = type; return this; }
    public CustomSpellSlot setLock(SpellSlotKey...integers)         { lock.addAll(Arrays.asList(integers)); return this; }
    public CustomSpellSlot setCustomSpellEffect(CustomSpellEffect effect){ this.customSpellEffect = effect; return this; }

    // TEMPLATE TO CUSTOM:
    //------------------------------------------------------------------------------------------------------------------

    public CustomSpellSlot(SpellSlotTemplate data)
    {   setSpellSlotTemplate(data);}

    public void setSpellSlotTemplate(SpellSlotTemplate data)
    {
        this.id = data.getId();
        this.name = data.getName();
        this.type = data.getSlotType();
        this.lock = new ArrayList<>(data.getLock());
    }

    // CUSTOM TO ENTITY DATA:
    //------------------------------------------------------------------------------------------------------------------

    public SpellEffectData getSpellEffectData()
    {
        SpellEffectData data = customSpellEffect.getSpellEffectData();
        data.setSlotType(type);

        return data;
    }
}