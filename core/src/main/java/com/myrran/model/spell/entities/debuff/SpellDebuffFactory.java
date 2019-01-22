package com.myrran.model.spell.entities.debuff;

/** @author Ivan Delgado Huerta */
public enum SpellDebuffFactory
{
    BUFF("Buff")
    {
        @Override public SpellDebuffDeco getEffectEntity()
        {   return new SpellDebuffDOT(); }
    },
    DOT("DOT")
    {
        @Override public SpellDebuffDeco getEffectEntity()
        {   return new SpellDebuffDOT(); }
    },
    PUREDAMAGE("Pure Damage")
    {
        @Override public SpellDebuffDeco getEffectEntity()
        {   return new SpellDebuffDOT(); }
    };

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    private String name;
    public String getName()     { return name; }
    public abstract SpellDebuffDeco getEffectEntity();

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    SpellDebuffFactory(String name)
    {   this.name = name; }
}
