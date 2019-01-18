package com.myrran.view.ui.customspellview;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.spell.generators.custom.debuffslot.CustomDebuffSlot;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.TextView;

/** @author Ivan Delgado Huerta */
public class DebuffView extends Group implements Disposable
{
    private CustomDebuffSlot model;

    private Image iconImage;
    private TextView slotName;
    private TextView debuffName;
    private TextView keys;
    private TextView lock;

    public CustomDebuffSlot getModel()              { return model; }

    private static final Color white = Color.WHITE;
    private static final Color orange = Color.ORANGE;
    private static final Color black = Color.BLACK;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public DebuffView(CustomDebuffSlot customDebuffSlot)
    {
        model = customDebuffSlot;

        createView();
        updateView();
    }

    @Override public void dispose()
    {   }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    private void createView()
    {
        BitmapFont font10 = Atlas.get().getFont("10");

        slotName    = new TextView(font10, white, black, 1);
        lock        = new TextView(font10, white, black, 1);
        debuffName  = new TextView(font10, orange, black, 1);

        updateIcon();
        addActor(debuffName);
        addActor(slotName);
        addActor(lock);

        setBounds(0, 0, iconImage.getWidth(), iconImage.getHeight());
        debuffName.setPosition(getWidth(), 20);
        slotName.setPosition(getWidth(), 10);
        lock.setPosition(getWidth(), 0);
    }

    private void updateView()
    {
        updateIcon();
        debuffName.setText(model.getCustomSpellDebuff() != null ? model.getCustomSpellDebuff().getName() : "-");
        slotName.setText(model.getName());
        lock.setText(model.getLock().toString().toLowerCase());
    }

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
