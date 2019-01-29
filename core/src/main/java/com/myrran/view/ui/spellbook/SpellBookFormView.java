package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomSpellBook;
import com.myrran.model.spell.templates.TemplateSpellForm;
import com.myrran.view.ui.listeners.TouchDownListener;
import com.myrran.view.ui.widgets.WidgetSortableTable;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Comparator;

/** @author Ivan Delgado Huerta */
public class SpellBookFormView extends WidgetSortableTable<TemplateSpellForm>
    implements PropertyChangeListener, Disposable
{
    private CustomSpellBook model;
    private CustomSpellController controller;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SpellBookFormView(CustomSpellController spellController)
    {
        controller  = spellController;

        addSortOption("name", Comparator.comparing(TemplateSpellForm::getName));
        addSortOption("type", Comparator.comparing(TemplateSpellForm::getFactory));
        addSortOption("slots", Comparator.comparing(form -> form.getSpellDebuffs().size()));
        addSortOption("available", Comparator.comparing(TemplateSpellForm::getAvailable));
        addSortOption("total", Comparator.comparing(TemplateSpellForm::getTotal));

        build("SpellForm SpellBook", true);
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
            setModel(model.getFormTemplatesLearned());
        }
    }

    private void removeModel() {}
    private void update() {}

    // HELPER:
    //--------------------------------------------------------------------------------------------------------

    @Override public Actor getActor(TemplateSpellForm template)
    {
        TemplateFormView icon = new TemplateFormView(controller);
        icon.setModel(template);
        return icon;
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent propertyChangeEvent)
    { }
}
