package com.myrran.model.spell.parameters;

import com.myrran.misc.dataestructures.maplist.MapListI;
import com.myrran.model.spell.entities.form.SpellFormFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** @author Ivan Delgado Huerta */
public class SpellFormParams
{
    private SpellFormFactory factory;
    private Map<String, SpellStatParams> stats;
    private MapListI<String, SpellDebuffParams> debuffParams;

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    public SpellFormFactory getFactory()                            { return factory; }
    public Map<String, SpellStatParams> getFormStatParams()         { return stats; }
    public MapListI<String, SpellDebuffParams> getDebuffParams()    { return debuffParams; }

    public SpellFormParams setFactory(SpellFormFactory factory)
    {   this.factory = factory; return this; }

    public SpellFormParams setSpellDebuffParams(MapListI<String, SpellDebuffParams> params)
    {   this.debuffParams = params; return this; }

    public SpellFormParams setSpellStatParams(Map<String, SpellStatParams> stats)
    {   this.stats = stats; return this; }
}
