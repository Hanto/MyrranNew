package com.myrran.view.ui.formview2;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.spell.generators.custom.CustomSpellDebuff;
import com.myrran.spell.generators.custom.debuffslot.CustomDebuffSlot;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.widgets.WidgetGroup;
import com.myrran.view.ui.widgets.WidgetImage;
import com.myrran.view.ui.widgets.WidgetText;
import com.myrran.view.ui.window.MoveWidgetListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** @author Ivan Delgado Huerta */
public class DebuffSlotIcon extends Table implements PropertyChangeListener, Disposable
{
    private CustomDebuffSlot model;
    private CustomSpellController controller;

    private WidgetGroup icon;
    private WidgetImage debuffIcon;
    private WidgetText cost;
    private WidgetText debuffName;
    private WidgetText slotType;
    private WidgetText lock;

    private static final BitmapFont font10 = Atlas.get().getFont("10");
    private static final BitmapFont font14 = Atlas.get().getFont("14");
    private static final Color magenta = new Color(170/255f, 70/255f, 255/255f, 1f);

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public DebuffSlotIcon(CustomSpellController customSpellController)
    {
        controller  = customSpellController;
        icon        = new WidgetGroup();
        debuffIcon  = new WidgetImage();
        cost        = new WidgetText(font14, magenta, Color.BLACK, 1);
        debuffName  = new WidgetText(font10, Color.ORANGE, Color.BLACK, 2);
        slotType    = new WidgetText(font10, Color.WHITE, Color.BLACK, 1);
        lock        = new WidgetText(font10, Color.WHITE, Color.BLACK, 1);

        createLayout();
    }

    @Override public void dispose()
    {
        if(model != null)
            model.removeObserver(this);
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomDebuffSlot debuffSlot)
    {
        dispose();

        if (debuffSlot == null)
            removeModel();
        else
        {
            model = debuffSlot;
            model.addObserver(this);
            update();
        }
    }

    private void removeModel()
    {
        model = null;
        debuffIcon.setTexureRegion((Atlas.get().getTexture("TexturasIconos/IconoVacio")));
        cost.setText(null);
        debuffName.setText(null);
        slotType.setText(null);
        lock.setText(null);
    }

    private void update()
    {
        CustomSpellDebuff debuff = model.getCustomSpellDebuff();
        debuffIcon.setTexureRegion((Atlas.get().getTexture("TexturasIconos/FireBall")));
        cost.setText(debuff != null ? debuff.getTotalCost().toString() : null);
        debuffName.setText(model.getCustomSpellDebuff() != null ? model.getCustomSpellDebuff().getName() : null);
        slotType.setText(model.getSlotType());
        lock.setText(model.getLock().toString().toLowerCase());
    }

    // CREATE LAYOUT:
    //--------------------------------------------------------------------------------------------------------

    private void createLayout()
    {
        int vPad = -4;

        Table textTable = new Table().top().left();
        textTable.add(debuffName).left()  .padTop(vPad).padBottom(vPad).row();
        textTable.add(slotType).left()    .padTop(vPad).padBottom(vPad).row();
        textTable.add(lock).left()        .padTop(vPad).padBottom(vPad).row();

        icon.addActor(debuffIcon);
        icon.addActor(cost);
        cost.setPosition(2, 0);

        top().left();
        add(icon).top().left();
        add(textTable).top().left();
        icon.addListener(new MoveWidgetListener(icon));
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   update(); }
}
