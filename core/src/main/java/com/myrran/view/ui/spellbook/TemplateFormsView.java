package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomSpellBook;
import com.myrran.model.spell.templates.TemplateSpellForm;
import com.myrran.view.ui.listeners.TouchDownListener;
import com.myrran.view.ui.widgets.SortableTable;

import java.util.Comparator;

/** @author Ivan Delgado Huerta */
public class TemplateFormsView extends SortableTable<TemplateSpellForm>
{
    private CustomSpellController controller;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public TemplateFormsView(CustomSpellController spellController)
    {
        controller  = spellController;

        addSortOption("name", Comparator.comparing(TemplateSpellForm::getName));
        addSortOption("type", Comparator.comparing(TemplateSpellForm::getFactory));
        addSortOption("slots", Comparator.comparing(form -> form.getSpellSlots().size()));
        addSortOption("available", Comparator.comparing(TemplateSpellForm::getAvailable));
        addSortOption("total", Comparator.comparing(TemplateSpellForm::getTotal));

        build("SpellForm SpellBook", true);
        addListener(new TouchDownListener(e -> this.toFront()));
    }

    // HELPER:
    //--------------------------------------------------------------------------------------------------------

    @Override public Actor getActor(TemplateSpellForm model)
    {
        TemplateFormView icon = new TemplateFormView(controller);
        icon.setModel(model);
        return icon;
    }
}
