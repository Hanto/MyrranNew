package com.myrran.view.ui.templatespell;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.templates.TemplateSpellBook;
import com.myrran.model.spell.templates.TemplateSpellDebuff;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class TemplateBookView extends Table implements PropertyChangeListener, Disposable
{
    private TemplateSpellBook model;
    private CustomSpellController controller;

    private Collection<TemplateSpellDebuff>templateDebuffList;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public TemplateBookView(CustomSpellController controller)
    {   this.controller = controller; }

    @Override public void dispose()
    {
        if (model != null)
            model.removeObserver(this);
    }

    // CREATE / UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(TemplateSpellBook templateSpellBook)
    {
        dispose();

        if (templateSpellBook == null)
            removeModel();
        else
        {
            model = templateSpellBook;
            model.addObserver(this);
            createLayout();
        }
    }

    private void removeModel()
    {

    }

    private void update()
    {

    }

    private void createLayout()
    {
        templateDebuffList = model.getSpellDebuffTemplates().values().stream()
            .sorted(Comparator.comparing(TemplateSpellDebuff::getName))
            .collect(Collectors.toList());
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent propertyChangeEvent)
    { }
}
