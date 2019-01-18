package com.myrran.view.ui.customspell.debuff;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.myrran.spell.generators.custom.debuffslot.CustomDebuffSlot;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.TextView;

/** @author Ivan Delgado Huerta */
public class DebuffIconView extends Group
{
    private CustomDebuffSlot model;

    private Image iconImage;
    private TextView slotType;
    private TextView debuffName;
    private TextView keys;
    private TextView lock;

    public CustomDebuffSlot getModel()              { return model; }

    private static final Color white = Color.WHITE;
    private static final Color orange = Color.ORANGE;
    private static final Color black = Color.BLACK;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public DebuffIconView(CustomDebuffSlot customDebuffSlot)
    {
        model = customDebuffSlot;

        createView();
        updateView();
    }

    // CREATE / UPDATE:
    //--------------------------------------------------------------------------------------------------------

    private void createView()
    {
        BitmapFont font10 = Atlas.get().getFont("10");

        slotType    = new TextView(font10, white, black, 1);
        lock        = new TextView(font10, white, black, 1);
        debuffName  = new TextView(font10, orange, black, 1);

        updateIcon();
        addActor(debuffName);
        addActor(slotType);
        addActor(lock);
        updatePositions();
    }

    private void updatePositions()
    {
        setBounds(0, 0, iconImage.getWidth(), iconImage.getHeight());
        debuffName.setPosition(getWidth(), 20);
        slotType.setPosition(getWidth(), 10);
        lock.setPosition(getWidth(), 0);
    }

    public void updateView()
    {
        updateIcon();
        debuffName.setText(model.getCustomSpellDebuff() != null ? model.getCustomSpellDebuff().getName() : "-");
        slotType.setText(model.getSlotType());
        lock.setText(model.getLock().toString().toLowerCase());
        updatePositions();
    }

    // MISC:
    //--------------------------------------------------------------------------------------------------------

    private void updateIcon()
    {
        if (iconImage != null)
            removeActor(iconImage);

        iconImage = model.getCustomSpellDebuff() == null ?
            Atlas.get().getImage("TexturasIconos/IconoVacio") :
            Atlas.get().getImage("TexturasIconos/FireBall");

        addActor(iconImage);
    }
}
