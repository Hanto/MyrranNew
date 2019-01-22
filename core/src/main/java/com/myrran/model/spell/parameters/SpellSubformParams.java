package com.myrran.model.spell.parameters;

import com.myrran.model.spell.entities.subform.SpellSubformFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.HashMap;
import java.util.Map;

/** @author Ivan Delgado Huerta */
@XmlAccessorType(XmlAccessType.FIELD)
public class SpellSubformParams
{
    private SpellSubformFactory factory;
    private Map<String, SpellStatParams> stats = new HashMap<>();

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    public SpellSubformFactory getFactory()                             { return factory; }
    public SpellStatParams getStat(String stat)                         { return stats.get(stat); }
    public SpellSubformParams setFactory(SpellSubformFactory factory)   { this.factory = factory; return this; }

    public SpellSubformParams setSpellStatParams(Map<String, SpellStatParams> stats)
    {   this.stats = stats; return this; }
}
