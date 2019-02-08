package com.myrran.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class GraphicSettings
{
    public int horizontalRes    = 600;
    public int verticalRes      = 400;
    public String tittle        = "application";
    public boolean fullScreen   = false;
    public boolean vsync        = false;
}
