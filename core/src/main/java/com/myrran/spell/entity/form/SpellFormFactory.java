package com.myrran.spell.entity.form;

/** @author Ivan Delgado Huerta */
public enum SpellFormFactory
{
    BOLT("Bolt")
    {
        @Override public SpellForm getFormEntity()
        {   return new SpellFormBolt(); }
    }
    ,
    BALL("Ball")
    {
        @Override public SpellForm getFormEntity()
        {   return new SpellFormBolt(); }
    };

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    private String name;

    public String getName()     { return name; }
    public abstract SpellForm getFormEntity();

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    SpellFormFactory(String name)
    {   this.name = name; }
}