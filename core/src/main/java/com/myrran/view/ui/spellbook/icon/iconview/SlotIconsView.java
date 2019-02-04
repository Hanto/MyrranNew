package com.myrran.view.ui.spellbook.icon.iconview;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.model.spell.templates.TemplateSpellSlot;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class SlotIconsView extends Table implements Disposable
{
    private Collection<TemplateSpellSlot> model;
    private List<SlotIconView> views;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    @Override public void dispose()
    {
        if (views != null)
            views.forEach(AbstractIconView::dispose);
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(Collection<TemplateSpellSlot> models)
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
            .map(SlotIconView::new)
            .collect(Collectors.toList());

        views.forEach(view -> add(view).bottom().left());
    }
}
