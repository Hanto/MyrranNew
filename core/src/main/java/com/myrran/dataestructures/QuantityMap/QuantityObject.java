package com.myrran.dataestructures.QuantityMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/** @author Ivan Delgado Huerta */
@XmlAccessorType(XmlAccessType.FIELD)
public class QuantityObject
{
    @XmlAttribute(name = "available")
    private Integer available = 0;
    @XmlAttribute(name = "total")
    private Integer total = 0;

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    public Integer getTotal()                       { return total; }
    public void setAvailable(Integer available)     { this.available = available; }
    public void setTotal(Integer total)             { this.total = total; }
    public Integer getAvailable()                   { return available; }
}
