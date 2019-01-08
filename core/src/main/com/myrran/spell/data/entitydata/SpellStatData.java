package main.com.myrran.spell.data.entitydata;

/** @author Ivan Delgado Huerta */
public class SpellStatData
{
    private String id;
    private String name;
    private Float total;

    // SETTERS GETTERS:
    //------------------------------------------------------------------------------------------------------------------

    public String getID()                               { return id;}
    public String getName()                             { return name; }
    public Float getTotal()                             { return total; }

    public SpellStatData setID(String id)               { this.id = id; return this; }
    public SpellStatData setName(String name)           { this.name = name; return this; }
    public SpellStatData setTotal(float total)          { this.total = total; return this; }
}