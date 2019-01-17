package com.myrran.view.ui.CustomSpells;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.myrran.spell.generators.custom.stats.CustomSpellStat;

/** @author Ivan Delgado Huerta */
public class SpellUpgradesView extends Actor
{
    private CustomSpellStat model;

    private TextureRegion fondo;
    private TextureRegion frente;
    private int barraTalentos00_25 = 0;
    private int barraTalentos25_50 = 0;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SpellUpgradesView(CustomSpellStat customSpellStat)
    {
        model = customSpellStat;
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("atlas/atlas.atlas"));
        fondo = new TextureRegion(atlas.findRegion("TexturasMisc/CasillaTalentoFondo"));
        frente = new TextureRegion(atlas.findRegion("TexturasMisc/CasillaTalento"));

        setWidth(fondo.getRegionWidth());
        setHeight(fondo.getRegionHeight());
        updateView();
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public void updateView()
    {
        int numUpgrades = model.getNumUpgrades();
        barraTalentos00_25 = ((int)getWidth()/25) * (numUpgrades > 25 ? 25 : numUpgrades)+ 1;
        barraTalentos25_50 = ((int)getWidth()/25) * (numUpgrades > 50 ? 25 : numUpgrades - 25);
    }

    @Override public void draw (Batch batch, float alpha)
    {
        batch.setColor(getColor());

        if (!model.getIsUpgradeable()) batch.setColor(Color.GRAY);
        batch.draw(fondo, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

        batch.setColor(255 / 255f, 180 / 255f, 0 / 255f, 0.75f);
        batch.draw(frente, getX(), getY(), getOriginX(), getOriginY(), barraTalentos00_25, getHeight(), getScaleX(), getScaleY(), getRotation());

        if (model.getNumUpgrades() > 25)
        {
            batch.setColor(255/255f, 0/255f, 0/255f, 0.55f);
            batch.draw(frente, getX(), getY(), getOriginX(), getOriginY(), barraTalentos25_50, getHeight(), getScaleX(), getScaleY(), getRotation());
        }
    }
}
