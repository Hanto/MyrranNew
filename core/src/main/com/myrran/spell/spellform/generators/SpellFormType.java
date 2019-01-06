package main.com.myrran.spell.spellform.generators;

public enum SpellFormType
{
    BOLT("Bolt");

    private String name;
    public String getName()     { return name; }

    private SpellFormType(String name)
    {   this.name = name; }
}