package com.myrran.view.ui.customspell;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomSpellSubform;
import com.myrran.model.spell.generators.CustomSubformSlot;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.widgets.WidgetGroup;
import com.myrran.view.ui.widgets.WidgetImage;
import com.myrran.view.ui.widgets.WidgetText;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** @author Ivan Delgado Huerta */
public class CustomSubformIconView extends Table implements PropertyChangeListener, Disposable
{
    private CustomSubformSlot modelSlot;
    private CustomSpellSubform modelSubform;
    private CustomSpellController controller;

    private WidgetGroup icon;
    private WidgetImage subformIcon;
    private WidgetText cost;
    private WidgetText subformName;
    private WidgetText slotType;
    private WidgetText lock;

    private static final BitmapFont font10 = Atlas.get().getFont("10");
    private static final BitmapFont font14 = Atlas.get().getFont("14");
    private static final Color magenta = new Color(170/255f, 70/255f, 255/255f, 1f);

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomSubformIconView(CustomSpellController customSpellController)
    {
        controller = customSpellController;
        icon        = new WidgetGroup();
        subformIcon = new WidgetImage();
        cost        = new WidgetText(font14, magenta, Color.BLACK, 1);
        subformName = new WidgetText(font14, Color.ORANGE, Color.BLACK, 1);
        slotType    = new WidgetText(font10, Color.WHITE, Color.BLACK, 1);
        lock        = new WidgetText(font10, Color.WHITE, Color.BLACK, 1);

        createLayout();
    }

    @Override public void dispose()
    {
        disposeObservers();
    }

    private void disposeObservers()
    {
        if (modelSlot != null)
            modelSlot.removeObserver(this);

        if (modelSubform != null)
            modelSubform.removeObserver(this);
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomSubformSlot customSubformSlot)
    {
        disposeObservers();

        if (customSubformSlot == null)
            removeModel();
        else
        {
            modelSlot = customSubformSlot;
            modelSubform = modelSlot.getCustomSpellSubform();
            modelSlot.addObserver(this);
            modelSubform.addObserver(this);
            update();
        }
    }

    private void removeModel()
    {
        modelSlot = null;
        subformIcon.setTexureRegion((Atlas.get().getTexture("TexturasIconos/IconoVacio")));
        cost.setText(null);
        subformName.setText(null);
        slotType.setText(null);
        lock.setText(null);
    }

    private void update()
    {
        slotType.setText(modelSlot.getSlotType());
        lock.setText(modelSlot.getLock().toString());

        if (modelSlot.hasData())
        {
            subformIcon.setTexureRegion((Atlas.get().getTexture("TexturasIconos/FireBall")));
            cost.setText(modelSubform.getTotalCost().toString());
            subformName.setText(modelSubform.getName());
        }
        else
        {
            subformIcon.setTexureRegion((Atlas.get().getTexture("TexturasIconos/IconoVacio")));
            cost.setText(null);
            subformName.setText(null);
        }
    }

    // CREATE LAYOUT:
    //--------------------------------------------------------------------------------------------------------

    private void createLayout()
    {
        int vPad = -4;

        Table textTable = new Table().top().left();
        textTable.add(slotType).left()    .padTop(vPad).padBottom(vPad).row();
        textTable.add(lock).left()        .padTop(-2).padBottom(vPad).row();
        textTable.add(subformName).left()  .padTop(vPad).padBottom(vPad).row();

        icon.addActor(subformIcon);
        icon.addActor(cost);
        cost.setPosition(2, 0);

        top().left();
        add(icon).top().left();
        add(textTable).top().left();
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   update(); }
}
