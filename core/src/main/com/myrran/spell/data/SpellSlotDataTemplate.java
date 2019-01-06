package main.com.myrran.spell.data;

import main.com.myrran.spell.SpellSlotKey;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class SpellSlotDataTemplate
{
    @XmlAttribute
    private String id;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String type;
    private List<SpellSlotKey> lock = new ArrayList<SpellSlotKey>();

    // GET:
    //------------------------------------------------------------------------------------------------------------------

    public String getId()                                       { return id; }
    public String getName()                                     { return name; }
    public String getType()                                     { return type; }
    public List<SpellSlotKey>getLock()                         { return lock; }

    // SET:
    //------------------------------------------------------------------------------------------------------------------

    public SpellSlotDataTemplate setId(String id)                       { this.id = id; return this; }
    public SpellSlotDataTemplate setName(String name)                   { this.name = name; return this; }
    public SpellSlotDataTemplate setType(String type)                   { this.type = type; return this; }
    public SpellSlotDataTemplate setLock(SpellSlotKey...integers)      { lock.addAll(Arrays.asList(integers)); return this; }
}
