package com.myrran.view.ui.spellbook;

import com.myrran.model.spell.templates.TemplateSpellDebuff;
import com.myrran.view.ui.sortabletable.SortableOptions;
import com.myrran.view.ui.sortabletable.SortableTableI;

import java.util.Comparator;

/** @author Ivan Delgado Huerta */
public class SortableOptionsDebuff extends SortableOptions<TemplateSpellDebuff>
{
    public SortableOptionsDebuff(SortableTableI<TemplateSpellDebuff> sortedTable)
    {
        super(sortedTable);

        addSortComparator("name", Comparator.comparing(TemplateSpellDebuff::getName));
        addSortComparator("type", Comparator.comparing(TemplateSpellDebuff::getFactory));
        addSortComparator("cost", Comparator.comparing(TemplateSpellDebuff::getBaseCost));
        addSortComparator("available", Comparator.comparing(TemplateSpellDebuff::getAvailable));
        addSortComparator("total", Comparator.comparing(TemplateSpellDebuff::getTotal));

        createLayout();
    }
}
