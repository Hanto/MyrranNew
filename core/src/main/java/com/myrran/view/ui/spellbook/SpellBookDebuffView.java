package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.custom.CustomSpellBook;
import com.myrran.model.spell.templates.TemplateSpellDebuff;
import com.myrran.view.ui.Atlas;

import static com.myrran.view.ui.spellbook.TemplateDebuffOptions.SortDebuffsBy;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class SpellBookDebuffView extends Table implements PropertyChangeListener, Disposable
{
    private CustomSpellBook model;
    private CustomSpellController controller;

    private TemplateDebuffOptions options;
    private List<TemplateDebuffView> list;
    private static final BitmapFont font20 = Atlas.get().getFont("20");

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SpellBookDebuffView(CustomSpellController spellController)
    {
        controller  = spellController;
        options     = new TemplateDebuffOptions(this);
        top().left();
    }

    @Override public void dispose()
    { }

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
            createLayout(SortDebuffsBy.NAME, options.getShowDetails());
        }
    }

    private void removeModel()
    { }

    private void update()
    { }

    public void createLayout(SortDebuffsBy sortBy, boolean showDetails)
    {
        clear();

        list = model.getDebuffsTemplatesLearned().stream()
            .filter(template -> template.getTotal() > 0)
            .sorted(sortBy.comparator)
            .map(this::getDebuffIcon)
            .collect(Collectors.toList());

        list.forEach(view -> view.showDetails(showDetails));

        add(options).left().row();

        for (TemplateDebuffView icon: list)
            add(icon).left().row();
    }

    private TemplateDebuffView getDebuffIcon(TemplateSpellDebuff templateDebuff)
    {
        TemplateDebuffView icon = new TemplateDebuffView(controller);
        icon.setModel(templateDebuff);
        return icon;
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent propertyChangeEvent)
    { }
}
