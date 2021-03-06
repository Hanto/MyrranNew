package com.myrran.view.ui.widgets;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/** @author Ivan Delgado Huerta */
public class WidgetImage extends Image
{
    // Original image doesn't have a default size that wraps the texture region
    // so you always have to manually specify it.

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public WidgetImage() {}
    public WidgetImage(TextureRegion region)
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
        invalidateHierarchy();
    }
}
