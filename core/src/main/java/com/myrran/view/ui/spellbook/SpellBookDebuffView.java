package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.custom.CustomSpellBook;
import com.myrran.model.spell.templates.TemplateSpellDebuff;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.listeners.ActorMoveListener;
import com.myrran.view.ui.widgets.WidgetText;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.myrran.view.ui.spellbook.TemplateDebuffOptions.SortDebuffsBy;

/** @author Ivan Delgado Huerta */
public class SpellBookDebuffView extends Table implements PropertyChangeListener, Disposable
{
    private CustomSpellBook model;
    private CustomSpellController controller;

    private WidgetText name;
    private TemplateDebuffOptions options;
    private List<TemplateDebuffView> list;
    private static final BitmapFont font20 = Atlas.get().getFont("20");

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SpellBookDebuffView(CustomSpellController spellController)
    {
        controller  = spellController;
        name        = new WidgetText("Debuff SpellBook", font20, Color.WHITE, Color.BLACK, 2);
        options     = new TemplateDebuffOptions(this);

        name.addListener(new ActorMoveListener(this));
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
            createLayout(SortDebuffsBy.NAME, options.getShowDetails(), false);
        }
    }

    private void removeModel()
    { }

    private void update()
    { }

    public void createLayout(SortDebuffsBy sortBy, boolean showDetails, boolean reverseOrder)
    {
        clear();

        list = model.getDebuffsTemplatesLearned().stream()
            .filter(template -> template.getTotal() > 0)
            .sorted(sortBy.comparator)
            .map(this::getDebuffIcon)
            .collect(Collectors.toList());

        list.forEach(view -> view.showDetails(showDetails));

        if (reverseOrder)
            Collections.reverse(list);

        add(name).left().padBottom(-8).padTop(-8).row();
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
