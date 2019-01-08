package main.com.myrran.spell.entity.form;

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
    //------------------------------------------------------------------------------------------------------------------

    private String name;

    public String getName()     { return name; }
    public abstract SpellForm getFormEntity();

    // MAIN:
    //------------------------------------------------------------------------------------------------------------------

    private SpellFormFactory(String name)
    {   this.name = name; }
}