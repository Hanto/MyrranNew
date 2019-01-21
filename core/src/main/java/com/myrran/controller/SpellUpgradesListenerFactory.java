package com.myrran.controller;

import static com.myrran.controller.SpellUpgradesListener.StatsType;

/** @author Ivan Delgado Huerta */
public class SpellUpgradesListenerFactory
{
    private CustomSpellController controller;
    private SpellUpgradesListener.StatsType type;
    private String formID;
    private String slotID;

    public SpellUpgradesListenerFactory(CustomSpellController controller, String formID, StatsType type, String slotID)
    {
        this.controller = controller;
        this.type = type;
        this.formID = formID;
        this.slotID = slotID;
    }

    public SpellUpgradesListener getListener(String statID)
    {   return new SpellUpgradesListener(controller, type, formID, slotID, statID); }
}
