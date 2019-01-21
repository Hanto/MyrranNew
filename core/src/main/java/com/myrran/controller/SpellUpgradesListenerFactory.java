package com.myrran.controller;

import static com.myrran.controller.SpellUpgradesListener.StatsType;

/** @author Ivan Delgado Huerta */
public class SpellUpgradesListenerFactory
{
    private CustomSpellController controller;
    private SpellUpgradesListener.StatsType type;
    private String formID;
    private String slotID;

    public SpellUpgradesListenerFactory(CustomSpellController controller, StatsType type)
    {
        this.controller = controller;
        this.type = type;
    }

    public SpellUpgradesListenerFactory set(String formID, String slotID)
    {
        this.formID = formID;
        this.slotID = slotID;
        return this;
    }

    public SpellUpgradesListener getListener(String statID)
    {   return new SpellUpgradesListener(controller, type, formID, slotID, statID); }
}
