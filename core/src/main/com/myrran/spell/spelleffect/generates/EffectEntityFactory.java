package main.com.myrran.spell.spelleffect.generates;

public enum EffectEntityFactory
{
    BUFF("Buff")
    {
        @Override public EffectEntity getEffectEntity()
        {   return new EffectEntityDOT(); }
    },
    DOT("DOT")
    {
        @Override public EffectEntity getEffectEntity()
        {   return new EffectEntityDOT(); }
    },
    PUREDAMAGE("Pure Damage")
    {
        @Override public EffectEntity getEffectEntity()
        {   return new EffectEntityDOT(); }
    };

    // MAIN:
    //------------------------------------------------------------------------------------------------------------------

    private String name;
    public String getName()     { return name; }
    public abstract EffectEntity getEffectEntity();

    // MAIN:
    //------------------------------------------------------------------------------------------------------------------

    private EffectEntityFactory(String name)
    {   this.name = name; }
}
