package com.myrran.view.ui.widgets;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/** @author Ivan Delgado Huerta */
public class ImageView extends Image
{
    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public ImageView() {}
    public ImageView(TextureRegion region)
    {   setTexureRegion(region); }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override public float getMinWidth()                { return getWidth(); }
    @Override public float getPrefWidth()               { return getWidth(); }
    @Override public float getMaxWidth()                { return getWidth(); }
    @Override public float getMinHeight()               { return getHeight(); }
    @Override public float getPrefHeight()              { return getHeight(); }
    @Override public float getMaxHeight()               { return getHeight(); }

    @Override public void setDrawable (Drawable drawable)
    {
        super.setDrawable(drawable);

        if (drawable != null)
            setBounds(0, 0, drawable.getMinWidth(), drawable.getMinHeight());
        else
            setBounds(0, 0, 0, 0);
    }

    public void setTexureRegion(TextureRegion region)
    {
        TextureRegionDrawable drawableRegion = new TextureRegionDrawable(region);
        setDrawable(drawableRegion);
    }
}
