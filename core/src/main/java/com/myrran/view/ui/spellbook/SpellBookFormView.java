package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.custom.CustomSpellBook;
import com.myrran.model.spell.templates.TemplateSpellForm;
import com.myrran.view.ui.sortabletable.SortableTableView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Comparator;

/** @author Ivan Delgado Huerta */
public class SpellBookFormView extends SortableTableView<TemplateSpellForm>
    implements PropertyChangeListener, Disposable
{
    private CustomSpellBook model;
    private CustomSpellController controller;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SpellBookFormView(CustomSpellController spellController)
    {
        controller  = spellController;

        addSortComparator("name", Comparator.comparing(TemplateSpellForm::getName));
        addSortComparator("type", Comparator.comparing(TemplateSpellForm::getFactory));
        addSortComparator("slots", Comparator.comparing(form -> form.getSpellSlots().size()));
        addSortComparator("available", Comparator.comparing(TemplateSpellForm::getAvailable));
        addSortComparator("total", Comparator.comparing(TemplateSpellForm::getTotal));

        build("SpellForm SpellBook", true);
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
            createLayout(model.getFormTemplatesLearned());
        }
    }

    private void removeModel() {}
    private void update() {}

    // HELPER:
    //--------------------------------------------------------------------------------------------------------

    @Override public Actor getIcon(TemplateSpellForm template)
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
