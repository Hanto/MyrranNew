package com.myrran.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Map;

/** @author Ivan Delgado Huerta */
public class Atlas implements Disposable
{
    private AssetManager assetManager;
    private TextureAtlas textureAtlas;

    private Map<String, TextureRegion> textures = new HashMap<>();
    private Map<String, BitmapFont> fonts = new HashMap<>();
    private Map<String, NinePatch> ninePatches = new HashMap<>();
    private Map<String, SkeletonData> skeletonsData = new HashMap<>();
    private Map<String, AnimationStateData> animationsData = new HashMap<>();

    private static final DecimalFormat df = new DecimalFormat("0.0");
    private static final DecimalFormatSymbols simbolos = df.getDecimalFormatSymbols();

    private static final Logger LOG = LogManager.getFormatterLogger(Atlas.class);

    // SETTERS / GETTERS:
    //--------------------------------------------------------------------------------------------------------

    public SkeletonData getSkeletonData(String name)            { return skeletonsData.get(name); }
    public AnimationStateData getAnimationStateData(String name){ return animationsData.get(name); }
    public NinePatch getNinePatch(String name)                  { return ninePatches.get(name); }
    public TextureRegion getTexture(String name)                { return textures.get(name); }
    public BitmapFont getFont(String name)                      { return fonts.get(name); }
    public Image getImage(String name)                          { return new Image(textures.get(name)); }
    public DecimalFormat getFormater()                          { return df; }

    // CONSTRUCTOR (SINGLETON):
    //--------------------------------------------------------------------------------------------------------

    private static class Singleton
    {   private static final Atlas get = new Atlas(); }

    public static Atlas get()
    {   return Singleton.get; }

    private Atlas()
    {   loadData(); }

    @Override public void dispose()
    {
        LOG.info("Disposing Atlas");
        assetManager.dispose();
        fonts.values().forEach(BitmapFont::dispose);
    }

    // LOADER:
    //--------------------------------------------------------------------------------------------------------

    private void loadData()
    {
        assetManager = new AssetManager();
        loadAtlas("Atlas/Atlas");
        loadAtlas("spine/spineboy");

        assetManager.finishLoading();

        // SPINE:
        loadSpineData("spine/spineboy");

        // TEXTURES:
        textureAtlas = assetManager.get("Atlas/Atlas.atlas", TextureAtlas.class);

        addNinePatch("TexturasIconos/IconoVacioNine");
        addNinePatch("TexturasIconos/IconoVacioNine2");

        addTexture("TexturasMisc/RebindOn");
        addTexture("TexturasMisc/RebindOff");
        addTexture("TexturasIconos/IconoVacio2");
        addTexture("TexturasIconos/FireBall2");
        addTexture("TexturasMisc/RebindOn");
        addTexture("TexturasIconos/IconoVacio");
        addTexture("TexturasIconos/FireBall");
        addTexture("TexturasIconos/IceBall");
        addTexture("TexturasMisc/CasillaTalentoFondo");
        addTexture("TexturasMisc/CasillaTalento");
        addTexture("TexturasMisc/Casillero2");

        addFont("Calibri14");
        addFont("Calibri13");
        addFont("Calibri12");
        addFont("Calibri11");
        addFont("Calibri10");
        addFont("Arial14");
        addFont("Arial13");
        addFont("Arial12");
        addFont("Arial11");
        addFont("Arial10");
        addFont("8");
        addFont("10");
        addFont("11");
        addFont("14");
        addFont("20");

        simbolos.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(simbolos);
    }

    private void loadAtlas(String name)
    {
        LOG.info("Adding textureAtlas: %s", name);
        assetManager.load(name + ".atlas", TextureAtlas.class);
    }

    private void loadSpineData(String name)
    {
        LOG.info("Adding skeleton data: %s", name);
        TextureAtlas spineAtlas = assetManager.get(name+".atlas", TextureAtlas.class);
        SkeletonJson json = new SkeletonJson(spineAtlas); json.setScale(0.08f);
        SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal(name + ".json"));
        AnimationStateData animationData = new AnimationStateData(skeletonData);
        animationData.setDefaultMix(0.1f);

        skeletonsData.put(name, skeletonData);
        animationsData.put(name, animationData);
    }

    private void addNinePatch(String name)
    {
        LOG.info("Adding NinePatch: %s from Atlas", name);
        NinePatch ninePatch = textureAtlas.createPatch(name);
        ninePatches.put(name, ninePatch);
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

    // GETTER:
    //--------------------------------------------------------------------------------------------------------

    public NinePatchDrawable getNinePatchDrawable(String name, float alpha)
    {
        NinePatch ninePatch = getNinePatch(name);
        return new NinePatchDrawable(ninePatch).tint(new Color(0.6f, 0.6f, 0.6f, alpha));
    }

    public NinePatchDrawable getNinePatchDrawable(String name, Color color, float alpha)
    {
        NinePatch ninePatch = getNinePatch(name);
        Color tint = new Color(color.r, color.g, color.b, alpha);
        return new NinePatchDrawable(ninePatch).tint(tint);
    }
}
