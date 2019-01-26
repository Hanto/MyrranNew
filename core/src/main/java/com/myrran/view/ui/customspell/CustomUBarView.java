package com.myrran.view.ui.customspell;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.myrran.model.spell.generators.CustomSpellStat;
import com.myrran.view.ui.Atlas;

/** @author Ivan Delgado Huerta */
public class CustomUBarView extends Actor
{
    private CustomSpellStat model;

    private TextureRegion fondo;
    private TextureRegion frente;
    private int barUpTo25 = 0;
    private int barUpTo50 = 0;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomUBarView(CustomSpellStat customSpellStat)
    {
        model = customSpellStat;

        createView();
        updateView();
    }

    // CREATE / UPDATE::
    //--------------------------------------------------------------------------------------------------------

    private void createView()
    {
        fondo   = Atlas.get().getTexture("TexturasMisc/CasillaTalentoFondo");
        frente  = Atlas.get().getTexture("TexturasMisc/CasillaTalento");

        setWidth(fondo.getRegionWidth());
        setHeight(fondo.getRegionHeight());
    }

    public void updateView()
    {
        int numUpgrades = model.getNumUpgrades();
        barUpTo25 = ((int)getWidth()/25) * (numUpgrades > 25 ? 25 : numUpgrades);
        barUpTo50 = ((int)getWidth()/25) * (numUpgrades > 50 ? 25 : numUpgrades - 25);
    }

    // DRAW:
    //--------------------------------------------------------------------------------------------------------

    @Override public void draw (Batch batch, float alpha)
    {
        batch.setColor(getColor());

        if (!model.getIsUpgradeable()) batch.setColor(Color.GRAY);
        batch.draw(fondo, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

        batch.setColor(255 / 255f, 180 / 255f, 0 / 255f, 0.75f);
        batch.draw(frente, getX(), getY(), getOriginX(), getOriginY(), barUpTo25, getHeight(), getScaleX(), getScaleY(), getRotation());

        if (model.getNumUpgrades() > 25)
        {
            batch.setColor(255/255f, 0/255f, 0/255f, 0.55f);
            batch.draw(frente, getX(), getY(), getOriginX(), getOriginY(), barUpTo50, getHeight(), getScaleX(), getScaleY(), getRotation());
        }
    }
}
