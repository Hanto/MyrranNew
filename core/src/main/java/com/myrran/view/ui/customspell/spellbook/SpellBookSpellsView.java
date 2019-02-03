package com.myrran.view.ui.customspell.spellbook;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomSpellBook;
import com.myrran.model.spell.generators.CustomSpellForm;
import com.myrran.view.ui.spellbook.customform.CustomSpellView;
import com.myrran.view.ui.listeners.TouchDownListener;

import java.util.Comparator;

/** @author Ivan Delgado Huerta */
public class SpellBookSpellsView extends SortableTable<CustomSpellForm>
{
    private CustomSpellBook model;
    private CustomSpellController controller;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SpellBookSpellsView(CustomSpellController spellController)
    {
        controller = spellController;

        addSortOption("name", Comparator.comparing(CustomSpellForm::getName));
        addSortOption("type", Comparator.comparing(CustomSpellForm::getTemplateID));
        addSortOption("dslots", Comparator.comparing(form -> form.getDebuffSlots().getCustomDebuffSlots().size()));
        addSortOption("cost", Comparator.comparing(CustomSpellForm::getTotalCost));

        build("Spells", true, 500, 400);
        addListener(new TouchDownListener(e -> this.toFront()));
    }

    // CREATE / UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomSpellBook customSpellBook)
    {
        dispose();

        if (customSpellBook == null)
            removeModel();
        else
        {
            model = customSpellBook;
            setModel(model.getCustomSpellForms());
        }
    }

    private void removeModel() {}
    private void update() {}

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
