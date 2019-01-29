package com.myrran.model.spell.parameters;

import com.myrran.misc.dataestructures.maplist.MapListI;
import com.myrran.model.spell.entities.subform.SpellSubformFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** @author Ivan Delgado Huerta */
@XmlAccessorType(XmlAccessType.FIELD)
public class SpellSubformParams
{
    private String slotType;
    private SpellSubformFactory factory;
    private Map<String, SpellStatParams> stats;
    private MapListI<String, SpellDebuffParams> debuffParams;

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    public String getSlotType()                                         { return slotType; }
    public SpellSubformFactory getFactory()                             { return factory; }
    public Map<String, SpellStatParams> getFormStatParams()             { return stats; }
    public MapListI<String, SpellDebuffParams> getDebuffParams()        { return debuffParams; }

    public SpellSubformParams setSlotType(String slotType)
    {   this.slotType = slotType; return this; }

    public SpellSubformParams setFactory(SpellSubformFactory factory)
    {   this.factory = factory; return this; }

    public SpellSubformParams setSpellDebuffParams(MapListI<String, SpellDebuffParams> params)
    { this.debuffParams = params; return this; }

    public SpellSubformParams setSpellStatParams(Map<String, SpellStatParams> stats)
    {   this.stats = stats; return this; }
}
