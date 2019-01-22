package com.myrran.model.spell.entities.subform;

/** @author Ivan Delgado Huerta */
public enum SpellSubformFactory
{
    AOE("AOE")
    {
        @Override public SpellSubform getEffectEntity()
        {   return new SpellSubformAOE(); }
    };

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    private String name;
    public String getName()     { return name; }
    public abstract SpellSubform getEffectEntity();

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    SpellSubformFactory(String name)
    {   this.name = name; }
}
