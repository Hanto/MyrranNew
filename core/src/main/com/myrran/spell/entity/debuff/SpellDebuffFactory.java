package main.com.myrran.spell.entity.debuff;

/** @author Ivan Delgado Huerta */
public enum SpellDebuffFactory
{
    BUFF("Buff")
    {
        @Override public SpellDebuff getEffectEntity()
        {   return new SpellDebuffDOT(); }
    },
    DOT("DOT")
    {
        @Override public SpellDebuff getEffectEntity()
        {   return new SpellDebuffDOT(); }
    },
    PUREDAMAGE("Pure Damage")
    {
        @Override public SpellDebuff getEffectEntity()
        {   return new SpellDebuffDOT(); }
    };

    // MAIN:
    //------------------------------------------------------------------------------------------------------------------

    private String name;
    public String getName()     { return name; }
    public abstract SpellDebuff getEffectEntity();

    // MAIN:
    //------------------------------------------------------------------------------------------------------------------

    SpellDebuffFactory(String name)
    {   this.name = name; }
}
