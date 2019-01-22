package com.myrran.view.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Disposable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Map;

/** @author Ivan Delgado Huerta */
public class Atlas implements Disposable
{
    private TextureAtlas textureAtlas;
    private Map<String, TextureRegion> textures = new HashMap<>();
    private Map<String, BitmapFont> fonts = new HashMap<>();
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static final DecimalFormatSymbols simbolos = df.getDecimalFormatSymbols();

    private static final Logger LOG = LogManager.getFormatterLogger(Atlas.class);

    // SETTERS / GETTERS:
    //--------------------------------------------------------------------------------------------------------

    public TextureRegion getTexture(String name)    { return textures.get(name); }
    public BitmapFont getFont(String name)          { return fonts.get(name); }
    public Image getImage(String name)              { return new Image(textures.get(name)); }
    public DecimalFormat getFormater()              { return df; }

    // CONSTRUCTOR (SINGLETON):
    //--------------------------------------------------------------------------------------------------------

    private static class Singleton
    {   private static final Atlas get = new Atlas(); }

    public static Atlas get()
    {   return Singleton.get; }

    private Atlas()
    {
        loadData();
    }

    @Override public void dispose()
    {
        LOG.info("Disposing Atlas");
        textureAtlas.dispose();
        fonts.values().forEach(BitmapFont::dispose);
    }

    // LOADER:
    //--------------------------------------------------------------------------------------------------------

    private void loadData()
    {
        textureAtlas = new TextureAtlas(Gdx.files.internal("Atlas/Atlas.Atlas"));

        addTexture("TexturasMisc/RebindOn");
        addTexture("TexturasIconos/IconoVacio");
        addTexture("TexturasIconos/FireBall");
        addTexture("TexturasIconos/IceBall");
        addTexture("TexturasMisc/CasillaTalentoFondo");
        addTexture("TexturasMisc/CasillaTalento");
        addTexture("TexturasMisc/Casillero2");
        addFont("8");
        addFont("10");
        addFont("11");
        addFont("14");
        addFont("20");

        simbolos.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(simbolos);
    }

    private void addTexture(String name)
    {
        LOG.info("Adding texture: %s from Atlas", name);
        TextureRegion texture = new TextureRegion(textureAtlas.findRegion(name));
        textures.put(name, texture);
    }

    private void addFont(String name)
    {
        LOG.info("Adding font: %s from Atlas", name);
        BitmapFont font = new BitmapFont(Gdx.files.internal(String.format("fonts/%s.fnt", name)), false);
        fonts.put(name, font);
    }
}
