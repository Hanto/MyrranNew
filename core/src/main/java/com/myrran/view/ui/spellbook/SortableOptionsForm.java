package com.myrran.view.ui.spellbook;

import com.myrran.model.spell.templates.TemplateSpellForm;
import com.myrran.view.ui.sortabletable.SortableOptions;
import com.myrran.view.ui.sortabletable.SortableTableI;

import java.util.Comparator;

/** @author Ivan Delgado Huerta */
public class SortableOptionsForm extends SortableOptions<TemplateSpellForm>
{
    public SortableOptionsForm(SortableTableI<TemplateSpellForm> sortedTable)
    {
        super(sortedTable);

        addSortComparator("name", Comparator.comparing(TemplateSpellForm::getName));
        addSortComparator("type", Comparator.comparing(TemplateSpellForm::getFactory));
        addSortComparator("slots", Comparator.comparing(form -> form.getSpellSlots().size()));
        addSortComparator("available", Comparator.comparing(TemplateSpellForm::getAvailable));
        addSortComparator("total", Comparator.comparing(TemplateSpellForm::getTotal));

        createLayout();
    }
}
