package com.myrran.view.ui.formview2;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.spell.generators.custom.debuffslot.CustomDebuffSlot;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.widgets.ImageView;
import com.myrran.view.ui.widgets.TextView;

/** @author Ivan Delgado Huerta */
public class DebuffSlotView2 extends Table implements Disposable
{
    private CustomDebuffSlot model;

    private ImageView debuffIcon;
    private TextView debuffName;
    private TextView slotType;
    private TextView lock;

    private static final BitmapFont font10 = Atlas.get().getFont("10");
    private static final BitmapFont font20 = Atlas.get().getFont("20");
    private static final BitmapFont font14 = Atlas.get().getFont("14");
    private static final Color magenta = new Color(170/255f, 70/255f, 255/255f, 1f);

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public DebuffSlotView2()
    {
        slotType    = new TextView(font10, Color.WHITE, Color.BLACK, 1);
        lock        = new TextView(font10, Color.WHITE, Color.BLACK, 1);
        debuffName  = new TextView(font10, Color.ORANGE, Color.BLACK, 2);
        debuffIcon  = new ImageView();

        add(debuffIcon).left();

        int vPad = -4;

        Table textTable = new Table().top().left();
        textTable.add(debuffName).left()  .padTop(vPad).padBottom(vPad).row();
        textTable.add(slotType).left()    .padTop(vPad).padBottom(vPad).row();
        textTable.add(lock).left()        .padTop(vPad).padBottom(vPad).row();

        add(textTable);
    }

    @Override public void dispose()
    {   }

    // CREATE / UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomDebuffSlot debuffSlot)
    {
        dispose();

        if (debuffSlot == null)
            removeModel();
        else
        {
            model = debuffSlot;
            update();
        }
    }

    private void removeModel()
    {
        dispose();

        model = null;
        debuffName.setText("");
        slotType.setText("");
        lock.setText("");
    }

    public void update()
    {
        debuffName.setText(model.getCustomSpellDebuff() != null ? model.getCustomSpellDebuff().getName() : "-");
        slotType.setText(model.getSlotType());
        lock.setText(model.getLock().toString().toLowerCase());
        debuffIcon.setTexureRegion((Atlas.get().getTexture("TexturasIconos/FireBall")));
    }

}
