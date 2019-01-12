package com.myrran.spell.data.entityparams;

/** @author Ivan Delgado Huerta */
public class SpellStatParams
{
    private String id;
    private String name;
    private Float total;

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    public String getID()                               { return id;}
    public String getName()                             { return name; }
    public Float getTotal()                             { return total; }

    public SpellStatParams setID(String id)             { this.id = id; return this; }
    public SpellStatParams setName(String name)         { this.name = name; return this; }
    public SpellStatParams setTotal(float total)        { this.total = total; return this; }
}
