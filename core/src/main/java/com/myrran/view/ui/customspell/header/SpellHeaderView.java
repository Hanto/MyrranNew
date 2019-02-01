package com.myrran.view.ui.customspell.header;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.model.components.observable.ObservableI;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.widgets.WidgetGroup;
import com.myrran.view.ui.widgets.WidgetImage;
import com.myrran.view.ui.widgets.WidgetText;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** @author Ivan Delgado Huerta */
public abstract class SpellHeaderView<T extends ObservableI> extends Table implements PropertyChangeListener, Disposable
{
    protected T model;

    private WidgetGroup icon;
    private WidgetImage iconBackground;
    private WidgetText availableTotal;

    private WidgetText keys;
    private WidgetText name;
    private WidgetText cost;

    private static final BitmapFont font10 = Atlas.get().getFont("10");
    private static final BitmapFont font14 = Atlas.get().getFont("14");
    private static final BitmapFont font20 = Atlas.get().getFont("20");
    private static final Color magenta = new Color(170/255f, 70/255f, 255/255f, 1f);

    // SETTERS - GETTERS:
    //--------------------------------------------------------------------------------------------------------

    public WidgetGroup getIcon()                        { return icon; }
    public WidgetText getIconName()                     { return name; }

    public void setIcon(TextureRegion texture)          { iconBackground.setTexureRegion(texture); }
    public void setAvailableTotal(String text)          { availableTotal.setText(text); }
    public void setKeys(String text)                    { keys.setText(text); }
    public void setIconName(String text)                { name.setText(text); }
    public void setCost(String text)                    { cost.setText(text);}
    public void setrAvailableTotalColor(Color color)    { availableTotal.setTextColor(color); }
    public void setKeysColor(Color color)               { keys.setTextColor(color); }
    public void setNameColor(Color color)               { name.setTextColor(color); }
    public void setCostColor(Color color)               { cost.setTextColor(color); }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SpellHeaderView()
    {
        icon            = new WidgetGroup();
        iconBackground  = new WidgetImage();
        availableTotal  = new WidgetText(font14, Color.ORANGE, Color.BLACK, 1);
        keys            = new WidgetText(font10, Color.WHITE, Color.BLACK, 1);
        name            = new WidgetText(font20, Color.ORANGE, Color.BLACK, 1);
        cost            = new WidgetText(font14, magenta, Color.BLACK, 1);

        createLayout();
    }

    private void disposeObservers()
    {
        if (model != null)
            model.removeObserver(this);
    }

    @Override public void dispose()
    {   disposeObservers(); }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(T spellForm)
    {
        disposeObservers();

        if (spellForm == null)
            removeModel();
        else
        {
            model = spellForm;
            model.addObserver(this);
            update();
        }
    }

    public void removeModel()
    {
        iconBackground.setTexureRegion(null);
        availableTotal.setText(null);
        keys.setText(null);
        name.setText(null);
        cost.setText(null);
    }

    protected abstract void update();

    // CREATE LAYOUT:
    //--------------------------------------------------------------------------------------------------------

    public void createLayout()
    {
        float pad = -4;

        icon.addActor(iconBackground);
        icon.addActor(availableTotal);
        availableTotal.setPosition(2, 0);

        Table table = new Table().bottom().left();
        table.padLeft(3);
        table.add(keys).bottom().padTop(pad).padBottom(pad).left().row();
        table.add(name).bottom().padTop(pad).padBottom(pad).left();
        table.add(cost).bottom().padTop(pad).padBottom(pad+1).left().row();

        top().left();
        add(icon).top().left();
        add(table).left().row();
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   update(); }
}
