package com.myrran.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** @author Ivan Delgado Huerta */
public class Settings 
{
    private GraphicSettings graphicSettings;

    private static final Logger LOG = LogManager.getFormatterLogger(Settings.class);

    // SETTERS / GETTERS:
    //--------------------------------------------------------------------------------------------------------

    public GraphicSettings getGraphicSettings()         { return graphicSettings; }

    // CONSTRUCTOR (SINGLETON):
    //--------------------------------------------------------------------------------------------------------

    private static class Singleton
    {   private static final Settings get = new Settings(); }

    public static Settings get()
    {   return Settings.Singleton.get; }

    private Settings()
    {   loadData(); }

    // LOADER:
    //--------------------------------------------------------------------------------------------------------

    public void loadData()
    {
        try
        {
            graphicSettings = XMLloader.unmarshal(GraphicSettings.class);
        }
        catch (Exception e)
        {   LOG.error("Unable to load seetings: ", e);}
    }
}
