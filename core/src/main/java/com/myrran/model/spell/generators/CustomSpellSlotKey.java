package com.myrran.model.spell.generators;

/** @author Ivan Delgado Huerta */
public enum CustomSpellSlotKey
{
    DOT("DOT"),
    DEBUFF("Debuff"),
    BUFF("Buff"),
    DD("Pure Damage"),

    ROUNDAOE("Round Area of Effect");

    private String name;
    public String getName()     { return name; }

    CustomSpellSlotKey(String name)
    {   this.name = name; }
}