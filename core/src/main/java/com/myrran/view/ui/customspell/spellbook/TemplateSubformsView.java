package com.myrran.view.ui.customspell.spellbook;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.templates.TemplateSpellSubform;
import com.myrran.view.ui.listeners.TouchDownListener;

import java.util.Comparator;

public class TemplateSubformsView extends SortableTable<TemplateSpellSubform>
{
    private CustomSpellController controller;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public TemplateSubformsView(CustomSpellController spellController)
    {
        controller = spellController;

        addSortOption("name", Comparator.comparing(TemplateSpellSubform::getName));
        addSortOption("type", Comparator.comparing(TemplateSpellSubform::getFactory));
        addSortOption("cost", Comparator.comparing(TemplateSpellSubform::getBaseCost));
        addSortOption("available", Comparator.comparing(TemplateSpellSubform::getAvailable));
        addSortOption("total", Comparator.comparing(TemplateSpellSubform::getTotal));

        build("Subform SpellBook", true);
        addListener(new TouchDownListener(e -> this.toFront()));
    }

    // HELPER:
    //--------------------------------------------------------------------------------------------------------

    @Override public Actor getActor(TemplateSpellSubform model)
    {
        TemplateSubformView icon = new TemplateSubformView(controller);
        icon.setModel(model);
        return icon;
    }
}
