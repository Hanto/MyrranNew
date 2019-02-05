package com.myrran;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomSpellBook;
import com.myrran.model.spell.generators.CustomSpellForm;
import com.myrran.model.spell.templates.TemplateSpellBook;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.spellbook.*;
import com.myrran.view.ui.widgets.WidgetText;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ZMain extends ApplicationAdapter
{
    private SpriteBatch batch;
    private BitmapFont font;
    private WidgetText text;
    private Stage uiStage;
    private CustomSpellBook book;
    private TemplateSpellBook templateBook;
    private CustomSpellForm spell;
    private CustomSpellController controller;
    private WidgetText fps;

    private static final Logger LOG = LogManager.getFormatterLogger(Atlas.class);

    @Override
    public void create ()
    {
        try
        {
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
            bookDebuff.setPosition(10, 690);

            TemplateSubformsView bookSubform = new TemplateSubformsView(controller);
            bookSubform.setModel(book.getSubformTemplatesLearned());
            uiStage.addActor(bookSubform);
            bookSubform.setPosition(10, 425);

            TemplateFormsView bookForm = new TemplateFormsView(controller);
            bookForm.setModel(book.getFormTemplatesLearned());
            uiStage.addActor(bookForm);
            bookForm.setPosition(10, 200);

            CustomSpellView formView2 = new CustomSpellView(controller);
            uiStage.addActor(formView2);
            formView2.setModel(spell);
            formView2.setPosition(600, 597);

            CustomSpellsView bookSpells = new CustomSpellsView(controller);
            bookSpells.setModel(book.getCustomSpellForms());
            uiStage.addActor(bookSpells);
            bookSpells.setPosition(280, 290);

            uiStage.addActor(fps);

            Gdx.input.setInputProcessor(uiStage);
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

            //batch.begin();
            //batch.draw(img, 0, 0);
            //batch.end();

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
