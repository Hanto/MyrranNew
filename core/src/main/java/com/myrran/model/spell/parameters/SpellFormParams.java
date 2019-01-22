package com.myrran.model.spell.parameters;

import com.myrran.model.spell.entities.form.SpellFormFactory;

import java.util.HashMap;
import java.util.Map;

/** @author Ivan Delgado Huerta */
public class SpellFormParams
{
    private SpellFormFactory factory;
    private Map<String, SpellStatParams> stats = new HashMap<>();

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    public SpellFormFactory getFactory()                        { return factory; }
    public SpellStatParams getStat(String stat)                 { return stats.get(stat); }
    public SpellFormParams setFactory(SpellFormFactory factory) { this.factory = factory; return this; }

    public SpellFormParams setSpellStatParams(Map<String, SpellStatParams> stats)
    {   this.stats = stats; return this; }
}
