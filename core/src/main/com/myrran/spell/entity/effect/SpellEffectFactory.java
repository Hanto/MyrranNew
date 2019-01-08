package main.com.myrran.spell.entity.effect;

public enum SpellEffectFactory
{
    BUFF("Buff")
    {
        @Override public SpellEffect getEffectEntity()
        {   return new SpellEffectDOT(); }
    },
    DOT("DOT")
    {
        @Override public SpellEffect getEffectEntity()
        {   return new SpellEffectDOT(); }
    },
    PUREDAMAGE("Pure Damage")
    {
        @Override public SpellEffect getEffectEntity()
        {   return new SpellEffectDOT(); }
    };

    // MAIN:
    //------------------------------------------------------------------------------------------------------------------

    private String name;
    public String getName()     { return name; }
    public abstract SpellEffect getEffectEntity();

    // MAIN:
    //------------------------------------------------------------------------------------------------------------------

    private SpellEffectFactory(String name)
    {   this.name = name; }
}
