package com.myrran.view.ui.customspell.debuff;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.spell.generators.custom.debuffslot.CustomDebuffSlots;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class DebuffSlotsView implements Disposable
{
    private CustomDebuffSlots model;

    private Table tableDebuffIcons;
    private List<DebuffSlotView> slots = new ArrayList<>();

    public Table getDebuffIconTable()               { return tableDebuffIcons; }
    public List<DebuffSlotView>getSlots()           { return slots; }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public DebuffSlotsView(CustomDebuffSlots debuffSlots)
    {
        this.model = debuffSlots;

        createView();
    }

    @Override public void dispose()
    {
        slots.stream()
            .filter(Objects::nonNull)
            .forEach(DebuffSlotView::dispose);
    }

    // CREATE / UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void createView()
    {
        tableDebuffIcons = new Table().top().left();

        slots = model.values().stream()
            .map(DebuffSlotView::new)
            .collect(Collectors.toList());

        slots.stream()
            .map(DebuffSlotView::getDebufIcon)
            .forEach(icon -> tableDebuffIcons.add(icon).row());
    }

    public void updateView()
    {   slots.forEach(DebuffSlotView::updateView); }

    public void setModel(CustomDebuffSlots debuffSlots)
    {
        dispose();
        model = debuffSlots;
        createView();
    }
}
