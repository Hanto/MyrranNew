package com.myrran.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.esotericsoftware.spine.SkeletonRenderer;
import com.myrran.controller.CustomSpellController;
import com.myrran.controller.PlayerInputProcessor;
import com.myrran.controller.PlayerInputs;
import com.myrran.model.mob.Player;
import com.myrran.model.spell.generators.CustomSpellBook;
import com.myrran.model.spell.generators.CustomSpellForm;
import com.myrran.model.spell.templates.TemplateSpellBook;
import com.myrran.view.ui.spellbook.CustomSpellsView;
import com.myrran.view.ui.spellbook.TemplateDebuffsView;
import com.myrran.view.ui.spellbook.TemplateFormsView;
import com.myrran.view.ui.spellbook.TemplateSubformsView;
import com.myrran.view.ui.widgets.WidgetText;
import com.myrran.view.mob.PlayerView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ZMain extends ApplicationAdapter
{
    private InputMultiplexer inputMultiplexer;
    private PlayerInputProcessor playerInputProcessor;
    private PlayerInputs playerInputs;
    private Player player;

    private SpriteBatch batch;
    private SkeletonRenderer skeletonRenderer;
    private BitmapFont font;
    private WidgetText text;
    private Stage uiStage;
    private CustomSpellBook book;
    private TemplateSpellBook templateBook;
    private CustomSpellForm spell;
    private CustomSpellController controller;
    private WidgetText fps;

    private PlayerView characterView;

    private static final Logger LOG = LogManager.getFormatterLogger(Atlas.class);

    public ZMain() {}

    @Override
    public void create ()
    {
        try
        {
            inputMultiplexer = new InputMultiplexer();
            playerInputs = new PlayerInputs();
            player = new Player(playerInputs);
            playerInputProcessor = new PlayerInputProcessor(playerInputs);
            skeletonRenderer = new SkeletonRenderer();
            skeletonRenderer.setPremultipliedAlpha(true);
            uiStage = new Stage();
            batch = new SpriteBatch();
            font = new BitmapFont(Gdx.files.internal("fonts/" + "20.fnt"), false);
            fps = new WidgetText(Atlas.get().getFont("14"), Color.WHITE, Color.BLACK, 1);
            templateBook = unmarshal(TemplateSpellBook.class);
            book = unmarshal(CustomSpellBook.class);
            book.setSpellBookTemplates(templateBook);
            spell = book.getCustomSpellForm("SpellForm_00");
            controller = new CustomSpellController(book);

            TemplateDebuffsView bookDebuff = new TemplateDebuffsView(controller);
            bookDebuff.setModel(book.getDebuffsTemplatesLearned());
            uiStage.addActor(bookDebuff);
            bookDebuff.setPosition(3, 765);

            TemplateSubformsView bookSubform = new TemplateSubformsView(controller);
            bookSubform.setModel(book.getSubformTemplatesLearned());
            uiStage.addActor(bookSubform);
            bookSubform.setPosition(272, 765);

            TemplateFormsView bookForm = new TemplateFormsView(controller);
            bookForm.setModel(book.getFormTemplatesLearned());
            uiStage.addActor(bookForm);
            bookForm.setPosition(541, 765);

            CustomSpellsView bookSpells = new CustomSpellsView(controller);
            bookSpells.setModel(book.getCustomSpellForms());
            uiStage.addActor(bookSpells);
            bookSpells.setPosition(810, 765);

            uiStage.addActor(fps);

            inputMultiplexer.addProcessor(uiStage);
            inputMultiplexer.addProcessor(playerInputProcessor);

            Gdx.input.setInputProcessor(inputMultiplexer);

            characterView = new PlayerView(player);
        }
        catch (Exception e)
        { 	LOG.error("PUMBA",e); }
    }

    @Override
    public void render ()
    {
        try
        {
            Gdx.gl.glClearColor(0.05f, 0.05f, 0.05f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            batch.begin();
            characterView.render(skeletonRenderer, batch);
            batch.end();

            //uiStage.setDebugUnderMouse(true);
            //uiStage.setDebugAll(true);

            uiStage.act();
            uiStage.draw();

            fps.setText(Integer.toString(Gdx.graphics.getFramesPerSecond()));
        }
        catch (Exception e)
        { 	LOG.error("PUMBA",e); }

    }

    @Override
    public void dispose ()
    {
        batch.dispose();
        font.dispose();

        Atlas.get().dispose();
    }

    public void resize(int width, int height)
    {
        uiStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }


    @SuppressWarnings("unchecked")
    private <T extends Object>T unmarshal(Class<T> classz) throws Exception
    {
        JAXBContext context = JAXBContext.newInstance(classz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        File file = new File(classz.getSimpleName()+".xml");
        return (T)unmarshaller.unmarshal(file);
    }
}
