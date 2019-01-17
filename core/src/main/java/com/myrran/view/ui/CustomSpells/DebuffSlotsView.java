package com.myrran.view.ui.CustomSpells;

import com.badlogic.gdx.utils.Disposable;
import com.myrran.spell.generators.custom.debuffslot.CustomDebuffSlot;
import com.myrran.spell.generators.custom.debuffslot.CustomDebuffSlots;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class DebuffSlotsView implements Disposable
{
    List<DebuffSlotView> slots = new ArrayList<>();

    public List<DebuffSlotView>getSlots()          { return slots; }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public DebuffSlotsView(CustomDebuffSlots debuffSlots)
    {   createView(debuffSlots.values()); }

    @Override public void dispose()
    {   slots.forEach(DebuffSlotView::dispose); }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public void createView(Collection<CustomDebuffSlot> debuffSlots)
    {
        slots = debuffSlots.stream()
            .map(DebuffSlotView::new)
            .collect(Collectors.toList());
    }
}
