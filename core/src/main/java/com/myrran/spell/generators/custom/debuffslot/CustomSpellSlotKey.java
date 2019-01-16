package com.myrran.spell.generators.custom.debuffslot;

/** @author Ivan Delgado Huerta */
public enum CustomSpellSlotKey
{
    DEBUFF("Debuff"),
    BUFF("Buff"),
    PUREDAMAGE("Pure Damage");

    private String name;
    public String getName()     { return name; }

    CustomSpellSlotKey(String name)
    {   this.name = name; }
}