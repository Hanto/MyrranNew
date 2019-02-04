package com.myrran.model.spell.generators;

/** @author Ivan Delgado Huerta */
public enum CustomSpellSlotKey
{
    DOT("DOT"),
    DFF("Debuff"),
    BUFF("Buff"),
    DD("Pure Damage"),

    CAOE("Circle Area of Effect"),
    GAOE("Ground Area of Effect");

    private String name;
    public String getName()     { return name; }

    CustomSpellSlotKey(String name)
    {   this.name = name; }
}