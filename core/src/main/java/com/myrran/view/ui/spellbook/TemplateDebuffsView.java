package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.templates.TemplateSpellDebuff;
import com.myrran.view.ui.listeners.TouchDownListener;
import com.myrran.view.ui.widgets.SortableTable;

import java.util.Comparator;

/** @author Ivan Delgado Huerta */
public class TemplateDebuffsView extends SortableTable<TemplateSpellDebuff>
{
    private CustomSpellController controller;
    protected static final int MINWIDTH = 245;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public TemplateDebuffsView(CustomSpellController spellController)
    {
        controller = spellController;

        addSortOption("name", Comparator.comparing(TemplateSpellDebuff::getName));
        addSortOption("type", Comparator.comparing(TemplateSpellDebuff::getFactory));
        addSortOption("cost", Comparator.comparing(TemplateSpellDebuff::getBaseCost));
        addSortOption("available", Comparator.comparing(TemplateSpellDebuff::getAvailable));
        addSortOption("total", Comparator.comparing(TemplateSpellDebuff::getTotal));

        build("Debuff SpellBook");
        addListener(new TouchDownListener(e -> this.toFront()));
    }

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
