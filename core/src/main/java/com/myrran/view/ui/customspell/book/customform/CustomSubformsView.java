package com.myrran.view.ui.customspell.book.customform;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomSubformSlot;
import com.myrran.model.spell.generators.CustomSubformSlots;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class CustomSubformsView extends Table implements Disposable
{
    private CustomSpellController controller;
    private List<CustomSubformView> views;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomSubformsView(CustomSpellController spellController)
    {   controller = spellController; }

    @Override public void dispose()
    {
        if (views != null)
            views.forEach(CustomSubformView::dispose);
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomSubformSlots model)
    {
        dispose();

        if (model == null)
            clear();
        else
        {
            clear();
            views = model.getCustomSubformSlots().stream()
                .sorted(Comparator.comparing(CustomSubformSlot::getID))
                .map(this::addSubformView)
                .collect(Collectors.toList());
            views.forEach(view -> add(view).left().row());
        }
    }

    private CustomSubformView addSubformView(CustomSubformSlot slot)
    {
        CustomSubformView view = new CustomSubformView(controller);
        view.setModel(slot);
        return view;
    }
}
