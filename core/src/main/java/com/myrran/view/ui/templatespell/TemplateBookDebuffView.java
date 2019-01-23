package com.myrran.view.ui.templatespell;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.templates.TemplateSpellBook;
import com.myrran.model.spell.templates.TemplateSpellDebuff;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.widgets.WidgetText;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

/** @author Ivan Delgado Huerta */
public class TemplateBookDebuffView extends Table implements PropertyChangeListener, Disposable
{
    private TemplateSpellBook model;
    private CustomSpellController controller;

    List<TemplateDebuffIcon> list;
    private static final BitmapFont font20 = Atlas.get().getFont("20");

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public TemplateBookDebuffView(CustomSpellController controller)
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
            createLayout(SortDebuffsBy.NAME);
        }
    }

    private void removeModel()
    {

    }

    private void update()
    {

    }

    private void createLayout(SortDebuffsBy sortBy)
    {
        clear();
        list = model.getSpellDebuffTemplates().values().stream()
            .sorted(sortBy.comparator)
            .map(this::getDebuffIcon)
            .collect(Collectors.toList());

        list.forEach(this::add);
    }

    private TemplateDebuffIcon getDebuffIcon(TemplateSpellDebuff templateDebuff)
    {
        TemplateDebuffIcon icon = new TemplateDebuffIcon(controller);
        icon.setModel(templateDebuff);
        return icon;
    }

    // DEBUFF SORTING:
    //--------------------------------------------------------------------------------------------------------

    public enum SortDebuffsBy
    {
        NAME(Comparator.comparing(TemplateSpellDebuff::getName)),
        TYPE(Comparator.comparing(TemplateSpellDebuff::getFactory)),
        BASECOST(Comparator.comparing(TemplateSpellDebuff::getBaseCost));

        Comparator<TemplateSpellDebuff> comparator;
        private SortDebuffsBy(Comparator<TemplateSpellDebuff> comparator)
        {   this.comparator = comparator; }
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent propertyChangeEvent)
    { }
}
