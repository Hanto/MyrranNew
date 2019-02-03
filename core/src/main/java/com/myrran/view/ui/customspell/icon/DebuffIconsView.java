package com.myrran.view.ui.customspell.icon;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.model.spell.templates.TemplateSpellSlot;

import java.util.List;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class DebuffIconsView extends Table implements Disposable
{
    private List<TemplateSpellSlot> model;
    private List<DebuffIconView> views;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    @Override public void dispose()
    {
        if (views != null)
            views.forEach(AbstractSpellIconView::dispose);
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(List<TemplateSpellSlot> models)
    {
        dispose();

        if (models == null)
            clear();
        else
        {
            model = models;
            update();
        }
    }

    private void update()
    {
        clear();
        top().left();
        views = model.stream()
            .map(DebuffIconView::new)
            .collect(Collectors.toList());

        views.forEach(view -> add(view).bottom().left());
    }
}
