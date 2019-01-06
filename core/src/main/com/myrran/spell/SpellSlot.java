package main.com.myrran.spell;

import main.com.myrran.spell.data.SpellSlotDataTemplate;
import main.com.myrran.spell.spelleffect.generators.SpellEffectI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpellSlot
{
    private String id;
    private String name;
    private String type;
    private List<SpellSlotKey>lock = new ArrayList<>();
    private SpellEffectI spellEffect;

    // GET:
    //------------------------------------------------------------------------------------------------------------------

    public String getId()                                   { return id; }
    public String getName()                                 { return name; }
    public String getSlotType()                             { return type; }
    public List<SpellSlotKey>getLock()                      { return lock; }
    public SpellEffectI getSpellEffect()                    { return spellEffect; }

    // SET:
    //------------------------------------------------------------------------------------------------------------------

    public SpellSlot setId(String id)                       { this.id = id; return this; }
    public SpellSlot setName(String name)                   { this.name = name; return this; }
    public SpellSlot setType(String type)                   { this.type = type; return this; }
    public SpellSlot setLock(SpellSlotKey...integers)       { lock.addAll(Arrays.asList(integers)); return this; }
    public SpellSlot setSpellEffect(SpellEffectI effect)    { this.spellEffect = effect; return this; }

    // MAIN:
    //------------------------------------------------------------------------------------------------------------------

    public void setSpellSlotTemplate(SpellSlotDataTemplate data)
    {
        this.id = data.getId();
        this.name = data.getName();
        this.type = data.getType();
        this.lock = new ArrayList<>(data.getLock());
    }
}