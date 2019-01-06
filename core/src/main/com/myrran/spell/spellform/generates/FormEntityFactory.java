package main.com.myrran.spell.spellform.generates;

public enum FormEntityFactory
{
    BOLT("Bolt")
    {
        @Override public FormEntity getFormEntity()
        {   return new FormEntityBolt(); }
    }
    ,
    BALL("Ball")
    {
        @Override public FormEntity getFormEntity()
        {   return new FormEntityBolt(); }
    };

    // MAIN:
    //------------------------------------------------------------------------------------------------------------------

    private String name;

    public String getName()     { return name; }
    public abstract FormEntity getFormEntity();

    // MAIN:
    //------------------------------------------------------------------------------------------------------------------

    private FormEntityFactory(String name)
    {   this.name = name; }
}