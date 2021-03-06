package com.myrran.view.ui.spellbook.icon;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.myrran.view.Atlas;
import com.myrran.view.ui.widgets.WidgetGroup;
import com.myrran.view.ui.widgets.WidgetImage;
import com.myrran.view.ui.widgets.WidgetText;

/** @author Ivan Delgado Huerta */
public abstract class AbstractSpellIcon extends WidgetGroup
{
    protected WidgetImage background;
    protected WidgetText name1;
    protected WidgetText name2;
    protected WidgetText corner;

    // SETTERS - GETTERS:
    //--------------------------------------------------------------------------------------------------------

    public void setBackground(TextureRegion texture)    { background.setTexureRegion(texture); }
    public void setName1(String text)                   { name1.setText(text); }
    public void setName2(String text)                   { name2.setText(text); }
    public void setCorner(String text)                  { corner.setText(text); }
    public void setName1Color(Color color)              { name1.setTextColor(color); }
    public void setName2Color(Color color)              { name2.setTextColor(color); }
    public void setCornerColor(Color color)             { corner.setTextColor(color); }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public AbstractSpellIcon()
    {
        BitmapFont font10 = Atlas.get().getFont("10");
        Color magenta = new Color(170/255f, 70/255f, 255/255f, 1f);

        background  = new WidgetImage();
        name1       = new WidgetText(font10, Color.ORANGE, Color.BLACK, 0);
        name2       = new WidgetText(font10, Color.WHITE, Color.BLACK, 0);
        corner      = new WidgetText(font10, magenta, Color.BLACK, 0);

        createLayout();
    }

    // CREATE LAYOUT:
    //--------------------------------------------------------------------------------------------------------

    private void createLayout()
    {
        float pad = -4;

        Table tableRow2 = new Table().bottom().left();
        tableRow2.add(name2).left().padTop(pad).padBottom(pad+1);
        tableRow2.add(corner).expand().fillX().right().padTop(pad).padBottom(pad-6).padRight(2);

        Table table = new Table().bottom().left().padLeft(5).padBottom(8);
        table.setWidth(64);
        table.add(name1).left().padTop(pad).padBottom(pad+1).row();
        table.add(tableRow2).expand().fillX();

        addActor(background);
        addActor(table);
    }
}
