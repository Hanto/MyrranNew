package com.myrran;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.custom.CustomSpellBook;
import com.myrran.model.spell.generators.custom.CustomSpellForm;
import com.myrran.model.spell.templates.SpellBookTemplates;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.ScrollingCombatText;
import com.myrran.view.ui.customspell.SpellFormView;
import com.myrran.view.ui.widgets.WidgetText;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ZMain extends ApplicationAdapter
{
	private SpriteBatch batch;
	private Texture img;

	private BitmapFont font;
	private WidgetText text;
	private Stage uiStage;
	private CustomSpellBook book;
	private SpellBookTemplates templateBook;
	private CustomSpellForm spell;
	private CustomSpellController controller;

	private static Logger LOG = LogManager.getFormatterLogger(Atlas.class);

	@Override
	public void create ()
	{
		try
		{
			uiStage = new Stage();
			batch = new SpriteBatch();

			img = new Texture("badlogic.jpg");
			font = new BitmapFont(Gdx.files.internal("fonts/" + "20.fnt"), false);

			templateBook = unmarshal(SpellBookTemplates.class);
			book = unmarshal(CustomSpellBook.class);
			book.setSpellBookTemplates(templateBook);
			spell = book.getCustomSpellForm("Bolt_00");
			controller = new CustomSpellController(book);

			SpellFormView formView2 = new SpellFormView(controller);
			uiStage.addActor(formView2);
			formView2.setModel(spell);
			formView2.setPosition(100, 200);

			//book.addCustomSpellDebuff("Bolt_00", "Spot 3", "Slow");

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
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			//batch.begin();
			//batch.draw(img, 0, 0);
			//batch.end();

			uiStage.setDebugUnderMouse(true);
			uiStage.setDebugAll(true);

			uiStage.act();
			uiStage.draw();

		}
		catch (Exception e)
		{ 	LOG.error("PUMBA",e); }

	}
	
	@Override
	public void dispose ()
	{
		batch.dispose();
		img.dispose();
		font.dispose();

		Atlas.get().dispose();
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
