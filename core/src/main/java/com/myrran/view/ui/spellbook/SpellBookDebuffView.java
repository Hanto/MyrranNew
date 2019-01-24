package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.custom.CustomSpellBook;
import com.myrran.model.spell.templates.TemplateSpellDebuff;
import com.myrran.view.ui.sortabletable.SortableTableView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** @author Ivan Delgado Huerta */
public class SpellBookDebuffView extends SortableTableView<TemplateSpellDebuff>
    implements PropertyChangeListener, Disposable
{
    private CustomSpellBook model;
    private CustomSpellController controller;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SpellBookDebuffView(CustomSpellController spellController)
    {
        controller  = spellController;
        build("Debuff SpellBook", new SortableOptionsDebuff(this), true);
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
            createLayout(model.getDebuffsTemplatesLearned());
        }
    }

    private void removeModel() {}
    private void update() {}

    // HELPER:
    //--------------------------------------------------------------------------------------------------------

    @Override public Actor getIcon(TemplateSpellDebuff template)
    {
        TemplateDebuffView icon = new TemplateDebuffView(controller);
        icon.setModel(template);
        return icon;
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent propertyChangeEvent)
    { }
}
