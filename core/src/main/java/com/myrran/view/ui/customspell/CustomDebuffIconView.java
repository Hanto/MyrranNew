package com.myrran.view.ui.customspell;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.controller.DadDebuffTarget;
import com.myrran.model.spell.generators.CustomDebuffSlot;
import com.myrran.model.spell.generators.CustomSpellDebuff;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.listeners.TouchDownRightListener;
import com.myrran.view.ui.widgets.WidgetGroup;
import com.myrran.view.ui.widgets.WidgetImage;
import com.myrran.view.ui.widgets.WidgetText;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** @author Ivan Delgado Huerta */
public class CustomDebuffIconView extends WidgetGroup implements PropertyChangeListener, Disposable
{
    private CustomDebuffSlot modelSlot;
    private CustomSpellDebuff modelDebuff;
    private CustomSpellController controller;

    private WidgetGroup icon;
    private WidgetImage debuffIcon;
    private WidgetText cost;
    private WidgetText debuffName;
    private WidgetText slotType;
    private WidgetText lock;
    private DadDebuffTarget dadTarget;

    private static final BitmapFont font10 = Atlas.get().getFont("10");
    private static final BitmapFont font14 = Atlas.get().getFont("14");
    private static final Color magenta = new Color(170/255f, 70/255f, 255/255f, 1f);

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomDebuffIconView(CustomSpellController customSpellController)
    {
        controller  = customSpellController;
        icon        = new WidgetGroup();
        debuffIcon  = new WidgetImage();
        cost        = new WidgetText(font10, magenta, Color.BLACK, 1);
        debuffName  = new WidgetText(font10, Color.ORANGE, Color.BLACK, 1);
        slotType    = new WidgetText(font10, Color.WHITE, Color.BLACK, 1);
        lock        = new WidgetText(font10, Color.WHITE, Color.BLACK, 1);
        dadTarget   = new DadDebuffTarget(this, controller);

        controller.getDadDebuff().addTarget(dadTarget);
        addListener(new TouchDownRightListener(event ->
            controller.removeCustomSpellDebuff(modelSlot)));

        createLayout();
    }

    @Override public void dispose()
    {
        disposeObservers();
        controller.getDadDebuff().removeTarget(dadTarget);
    }

    private void disposeObservers()
    {
        if (modelSlot != null)
            modelSlot.removeObserver(this);

        if (modelDebuff != null)
            modelDebuff.removeObserver(this);
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomDebuffSlot customDebuffSlot)
    {
        disposeObservers();

        if (customDebuffSlot == null)
            removeModel();
        else
        {
            modelSlot = customDebuffSlot;
            modelDebuff = modelSlot.getCustomSpellDebuff();
            dadTarget.setModel(modelSlot);
            modelSlot.addObserver(this);
            modelDebuff.addObserver(this);
            update();
        }
    }

    private void removeModel()
    {
        modelSlot = null;
        debuffIcon.setTexureRegion((Atlas.get().getTexture("TexturasIconos/IconoVacio2")));
        cost.setText(null);
        debuffName.setText(null);
        slotType.setText(null);
        lock.setText(null);
    }

    private void update()
    {
        slotType.setText(modelSlot.getSlotType());
        lock.setText(modelSlot.getLock().toString());

        if (modelSlot.hasData())
        {
            debuffIcon.setTexureRegion((Atlas.get().getTexture("TexturasIconos/FireBall2")));
            cost.setText(modelDebuff.getTotalCost().toString());
            debuffName.setText(modelDebuff.getName());
            debuffName.setTextColor(Color.ORANGE);
        }
        else
        {
            debuffIcon.setTexureRegion((Atlas.get().getTexture("TexturasIconos/IconoVacio2")));
            cost.setText(null);
            debuffName.setText(modelSlot.getLock().toString().toLowerCase());
            debuffName.setTextColor(Color.LIGHT_GRAY);
        }
    }

    // CREATE LAYOUT:
    //--------------------------------------------------------------------------------------------------------

    private void createLayout()
    {
        int vPad = -4;

        Table textTable = new Table().bottom().left();
        textTable.setWidth(64-5);
        textTable.add(debuffName).left()  .padTop(vPad).padBottom(vPad).row();
        textTable.add(slotType).left()    .padTop(vPad).padBottom(vPad+1);
        textTable.add(cost).expand().fillX().right().padTop(vPad).padBottom(vPad-6).padRight(2);
        //textTable.add(lock).left()        .padTop(-2).padBottom(vPad).width(50).row();

        addActor(debuffIcon);
        addActor(textTable);
        textTable.setPosition(5, 8);

        /*top().left();
        add(icon).top().left();
        add(textTable).top().left();*/
    }

    public void setLockColor(Color color)
    {   debuffName.setTextColor(color); }

    public void setDefaultLocColor()
    {   debuffName.setTextColor(modelDebuff.hasData() ? Color.ORANGE : Color.LIGHT_GRAY); }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   update(); }
}
