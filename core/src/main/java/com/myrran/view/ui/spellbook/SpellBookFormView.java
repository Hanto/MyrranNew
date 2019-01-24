package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.custom.CustomSpellBook;
import com.myrran.model.spell.templates.TemplateSpellForm;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.listeners.ActorMoveListener;
import com.myrran.view.ui.sortabletable.SortableTableI;
import com.myrran.view.ui.widgets.WidgetText;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class SpellBookFormView extends Table implements PropertyChangeListener, Disposable,
    SortableTableI<TemplateSpellForm>
{
    private CustomSpellBook model;
    private CustomSpellController controller;

    private WidgetText name;
    private SortableOptionsForm options;
    private List<TemplateFormView> list;
    private static final BitmapFont font20 = Atlas.get().getFont("20");

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SpellBookFormView(CustomSpellController spellController)
    {
        controller  = spellController;
        name        = new WidgetText("SpellForm SpellBook", font20, Color.WHITE, Color.BLACK, 2);
        options     = new SortableOptionsForm(this);
        name.addListener(new ActorMoveListener(this));
        top().left();
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
            createLayout(options.getActualSortBy().comparator, options.getShowDetails(), options.getReverseOrder());
        }
    }

    private void removeModel() {}
    private void update() {}

    @Override
    public void createLayout(Comparator<TemplateSpellForm> sortBy, boolean showDetails, boolean reverseOrder)
    {
        clear();

        list = model.getFormTemplatesLearned().stream()
            .filter(template -> template.getTotal() > 0)
            .sorted(sortBy)
            .map(this::getDebuffIcon)
            .collect(Collectors.toList());

        list.forEach(view -> view.showDetails(showDetails));

        if (reverseOrder)
            Collections.reverse(list);

        add(name).left().padBottom(-8).padTop(-8).row();
        add(options).left().row();

        for (TemplateFormView icon: list)
            add(icon).left().row();
    }

    // HELPER:
    //--------------------------------------------------------------------------------------------------------

    private TemplateFormView getDebuffIcon(TemplateSpellForm template)
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
