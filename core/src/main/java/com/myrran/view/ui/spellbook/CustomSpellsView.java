package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomSpellBook;
import com.myrran.model.spell.generators.CustomSpellForm;
import com.myrran.view.ui.listeners.TouchDownListener;
import com.myrran.view.ui.widgets.SortableTable;

import java.util.Comparator;

/** @author Ivan Delgado Huerta */
public class CustomSpellsView extends SortableTable<CustomSpellForm>
{
    private CustomSpellBook model;
    private CustomSpellController controller;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomSpellsView(CustomSpellController spellController)
    {
        controller = spellController;

        addSortOption("name", Comparator.comparing(CustomSpellForm::getName));
        addSortOption("type", Comparator.comparing(CustomSpellForm::getTemplateID));
        addSortOption("dslots", Comparator.comparing(form -> form.getDebuffSlots().getCustomDebuffSlots().size()));
        addSortOption("cost", Comparator.comparing(CustomSpellForm::getTotalCost));

        build("Spells", 348+8, 400);
        addListener(new TouchDownListener(e -> this.toFront()));
    }

    // HELPER:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public Actor getActor(CustomSpellForm model)
    {
        CustomSpellView actor = new CustomSpellView(controller, false);
        actor.setModel(model);
        return actor;
    }
}
