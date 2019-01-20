package com.myrran.view.ui.formview2;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.spell.generators.custom.debuffslot.CustomDebuffSlot;
import com.myrran.spell.generators.custom.debuffslot.CustomDebuffSlots;

import java.util.ArrayList;
import java.util.List;

/** @author Ivan Delgado Huerta */
public class DebuffSlotsView2 implements Disposable
{
    private CustomDebuffSlots model;

    private Table table;
    private List<DebuffSlotView2> slotList;

    public Table getTable()                 { return table; }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public DebuffSlotsView2()
    {
        table           = new Table().top().left();
        slotList        = new ArrayList<>();
    }

    @Override public void dispose()
    {   slotList.forEach(DebuffSlotView2::dispose); }

    // CREATE / UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomDebuffSlots customDebuffSlots)
    {
        dispose();
        model = customDebuffSlots;

        update();
    }

    public void removeModel()
    {
        dispose();

        model = null;
        table.clear();
        slotList.clear();
    }

    public void update()
    {
        table.clear();
        slotList.clear();

        model.values().stream()
            .map(this::getView)
            .forEach(view -> slotList.add(view));

        slotList.forEach(this::tableAddRow);
    }

    // MISC:
    //--------------------------------------------------------------------------------------------------------

    private DebuffSlotView2 getView(CustomDebuffSlot customDebuffSlot)
    {
        DebuffSlotView2 view = new DebuffSlotView2();
        view.setModel(customDebuffSlot);
        return view;
    }

    private void tableAddRow(DebuffSlotView2 view)
    {
        //table.add(view);
    }
}
