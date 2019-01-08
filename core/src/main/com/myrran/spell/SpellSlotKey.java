package main.com.myrran.spell;

/** @author Ivan Delgado Huerta */
public enum SpellSlotKey
{
    DEBUFF("Debuff"),
    BUFF("Buff"),
    PUREDAMAGE("Pure Damage");

    private String name;
    public String getName()     { return name; }

    private SpellSlotKey(String name)
    {   this.name = name; }
}