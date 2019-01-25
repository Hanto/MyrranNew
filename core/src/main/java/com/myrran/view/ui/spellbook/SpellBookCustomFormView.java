package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.custom.CustomSpellBook;
import com.myrran.model.spell.generators.custom.CustomSpellForm;
import com.myrran.view.ui.customspell.CustomFormView;
import com.myrran.view.ui.widgets.WidgetSortableTable;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Comparator;

/** @author Ivan Delgado Huerta */
public class SpellBookCustomFormView extends WidgetSortableTable<CustomSpellForm>
    implements PropertyChangeListener, Disposable
{
    private CustomSpellBook model;
    private CustomSpellController controller;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SpellBookCustomFormView(CustomSpellController spellController)
    {
        controller = spellController;

        addSortOption("name", Comparator.comparing(CustomSpellForm::getName));
        addSortOption("type", Comparator.comparing(CustomSpellForm::getTemplateID));
        addSortOption("dslots", Comparator.comparing(form -> form.getDebuffSlots().values().size()));
        addSortOption("cost", Comparator.comparing(CustomSpellForm::getTotalCost));

        build("Spells", true);
    }

    @Override public void dispose() {}

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
            createLayout(model.getCustomSpellForms());
        }
    }

    private void removeModel() {}
    private void update() {}

    // HELPER:
    //--------------------------------------------------------------------------------------------------------

    @Override public Actor getActor(CustomSpellForm model)
    {
        CustomFormView icon = new CustomFormView(controller, false);
        icon.setModel(model);
        return icon;
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent propertyChangeEvent)
    { }
}
