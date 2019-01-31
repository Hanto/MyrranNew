package com.myrran.view.ui.customspell;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.widgets.WidgetGroup;
import com.myrran.view.ui.widgets.WidgetImage;
import com.myrran.view.ui.widgets.WidgetText;

/** @author Ivan Delgado Huerta */
public class CustomIconView extends WidgetGroup
{
    private WidgetImage background;
    private WidgetText name1;
    private WidgetText name2;
    private WidgetText corner;

    private static final BitmapFont font10 = Atlas.get().getFont("10");
    private static final Color magenta = new Color(170/255f, 70/255f, 255/255f, 1f);

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomIconView()
    {
        background  = new WidgetImage();
        name1       = new WidgetText(font10, Color.ORANGE, Color.BLACK, 0);
        name2       = new WidgetText(font10, Color.WHITE, Color.BLACK, 0);
        corner      = new WidgetText(font10, magenta, Color.BLACK, 0);

        createLayout();
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public void setBackground(TextureRegion texture)    { background.setTexureRegion(texture); }
    public void setName1(String text)                   { name1.setText(text); }
    public void setName2(String text)                   { name2.setText(text); }
    public void setCorner(String text)                  { corner.setText(text); }
    public void setName1Color(Color color)              { name1.setTextColor(color); }
    public void setName2Color(Color color)              { name2.setTextColor(color); }
    public void setCornerColor(Color color)             { corner.setTextColor(color); }

    public void removeAll()
    {
        background.setTexureRegion(null);
        name1.setText(null);
        name2.setText(null);
        corner.setText(null);
    }

    // CREATE LAYOUT:
    //--------------------------------------------------------------------------------------------------------

    private void createLayout()
    {
        float pad = -4;

        Table table = new Table().bottom().left().padLeft(5).padBottom(8);
        table.setWidth(64);
        table.add(name1).left().padTop(pad).padBottom(pad+1).row();
        table.add(name2).left().padTop(pad).padBottom(pad+1);
        table.add(corner).expand().fillX().right().padTop(pad).padBottom(pad-6).padRight(2);

        addActor(background);
        addActor(table);
    }
}
