package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomSpellBook;
import com.myrran.model.spell.generators.CustomSpellForm;
import com.myrran.view.ui.customspell.CustomFormView;
import com.myrran.view.ui.listeners.TouchDownListener;
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

    @Override public Actor getActor(CustomSpellForm model)
    {
        CustomFormView actor = new CustomFormView(controller, false);
        actor.setModel(model);
        return actor;
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent propertyChangeEvent)
    { }
}
