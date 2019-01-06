package main.com.myrran.spell.spelleffect.generators;

public enum SpellEffectType
{
    BUFF("Buff"),
    DEBUFF("Debuff"),
    PUREDAMAGE("Pure Damage");

    private String name;
    public String getName()     { return name; }

    private SpellEffectType(String name)
    {   this.name = name; }
}
