package com.myrran.view.ui.spellbook.stats.bar;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.myrran.model.spell.generators.SpellStatI;
import com.myrran.view.Atlas;

/** @author Ivan Delgado Huerta */
public class UpgradeBarView extends Actor
{
    private SpellStatI model;

    private TextureRegion fondo;
    private TextureRegion frente;
    private int barUpTo25 = 0;
    private int barUpTo50 = 0;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public UpgradeBarView()
    {   createLayout(); }

    // CREATE / UPDATE::
    //--------------------------------------------------------------------------------------------------------

    public void setModel(SpellStatI stat)
    {
        if (stat == null)
            removeModel();
        else
        {
            model = stat;
            update();
        }
    }

    private void removeModel()
    {}

    public void update()
    {
        int numUpgrades = model.getNumUpgrades();
        barUpTo25 = ((int)getWidth()/25) * (numUpgrades > 25 ? 25 : numUpgrades);
        barUpTo50 = ((int)getWidth()/25) * (numUpgrades > 50 ? 25 : numUpgrades - 25);
    }

    // CREATE LAYOUT:
    //--------------------------------------------------------------------------------------------------------

    private void createLayout()
    {
        fondo   = Atlas.get().getTexture("TexturasMisc/CasillaTalentoFondo");
        frente  = Atlas.get().getTexture("TexturasMisc/CasillaTalento");

        setWidth(fondo.getRegionWidth());
        setHeight(fondo.getRegionHeight());
    }

    // DRAW:
    //--------------------------------------------------------------------------------------------------------

    @Override public void draw (Batch batch, float alpha)
    {
        if (model != null)
        {
            batch.setColor(getColor());

            if (!model.getIsUpgradeable()) batch.setColor(Color.GRAY);
            batch.draw(fondo, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

            batch.setColor(255 / 255f, 180 / 255f, 0 / 255f, 0.75f);
            batch.draw(frente, getX(), getY(), getOriginX(), getOriginY(), barUpTo25, getHeight(), getScaleX(), getScaleY(), getRotation());

            if (model.getNumUpgrades() > 25)
            {
                batch.setColor(255 / 255f, 0 / 255f, 0 / 255f, 0.55f);
                batch.draw(frente, getX(), getY(), getOriginX(), getOriginY(), barUpTo50, getHeight(), getScaleX(), getScaleY(), getRotation());
            }
        }
    }
}
