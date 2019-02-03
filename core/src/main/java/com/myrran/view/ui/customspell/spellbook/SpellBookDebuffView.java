package com.myrran.view.ui.customspell.spellbook;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomSpellBook;
import com.myrran.model.spell.templates.TemplateSpellDebuff;
import com.myrran.view.ui.listeners.TouchDownListener;
import com.myrran.view.ui.customspell.book.TemplateDebuffView;

import java.util.Comparator;

/** @author Ivan Delgado Huerta */
public class SpellBookDebuffView extends SortableTable<TemplateSpellDebuff>
{
    private CustomSpellBook model;
    private CustomSpellController controller;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SpellBookDebuffView(CustomSpellController spellController)
    {
        controller = spellController;

        addSortOption("name", Comparator.comparing(TemplateSpellDebuff::getName));
        addSortOption("type", Comparator.comparing(TemplateSpellDebuff::getFactory));
        addSortOption("cost", Comparator.comparing(TemplateSpellDebuff::getBaseCost));
        addSortOption("available", Comparator.comparing(TemplateSpellDebuff::getAvailable));
        addSortOption("total", Comparator.comparing(TemplateSpellDebuff::getTotal));

        build("Debuff SpellBook", true);
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
            setModel(model.getDebuffsTemplatesLearned());
        }
    }

    private void removeModel() {}
    private void update() {}

    // HELPER:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public Actor getActor(TemplateSpellDebuff model)
    {
        TemplateDebuffView icon = new TemplateDebuffView(controller);
        icon.setModel(model);
        return icon;
    }
}
