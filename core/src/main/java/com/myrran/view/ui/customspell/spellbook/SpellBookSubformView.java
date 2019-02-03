package com.myrran.view.ui.customspell.spellbook;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomSpellBook;
import com.myrran.model.spell.templates.TemplateSpellSubform;
import com.myrran.view.ui.listeners.TouchDownListener;
import com.myrran.view.ui.spellbook.TemplateSubformView;

import java.util.Comparator;

public class SpellBookSubformView extends SortableTable<TemplateSpellSubform>
{
    private CustomSpellBook model;
    private CustomSpellController controller;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SpellBookSubformView(CustomSpellController spellController)
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
            setModel(model.getSubformTemplatesLearned());
        }
    }

    private void removeModel() {}
    private void update() {}

    // HELPER:
    //--------------------------------------------------------------------------------------------------------

    @Override public Actor getActor(TemplateSpellSubform model)
    {
        TemplateSubformView icon = new TemplateSubformView(controller);
        icon.setModel(model);
        return icon;
    }
}
