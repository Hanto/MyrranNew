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
import com.myrran.spell.generators.custom.CustomSpellBook;
import com.myrran.spell.generators.custom.CustomSpellForm;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.ScrollingCombatText;
import com.myrran.view.ui.customspell.SpellFormView;
import com.myrran.view.ui.formview2.DebuffSlotIcon;
import com.myrran.view.ui.formview2.SpellDebuffDetails;
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
	private ScrollingCombatText sct;

	private ShapeRenderer shapeRenderer;
	private WidgetText sctView;
	private CustomSpellBook book;
	private SpellFormView spellFormView;

	private static Logger LOG = LogManager.getFormatterLogger(Atlas.class);

	@Override
	public void create ()
	{
		try
		{
			batch = new SpriteBatch();
			img = new Texture("badlogic.jpg");

			font = new BitmapFont(Gdx.files.internal("fonts/" + "20.fnt"), false);
			text = new WidgetText("Hola Mundo", font, Color.WHITE, Color.BLACK, 2);
			uiStage = new Stage();
			uiStage.addActor(text);
			text.setText("Hola Johana como te llamas");
			text.setPosition(0, 0);
			text.setTextColor(Color.ORANGE);
			shapeRenderer = new ShapeRenderer();
			sct = new ScrollingCombatText(font);
			sct.setDuration(10)
				.setMoveY(300)
				.setMoveX(50)
				.setInterpolationY(Interpolation.smooth2)
				.setInterpolationX(Interpolation.smooth2);

			sctView = sct.sct("Johancia");
			uiStage.addActor(sctView);
			sctView.setPosition(100, 100);

			book = unmarshal(CustomSpellBook.class);
			CustomSpellForm spell = book.getCustomSpellForm("Bolt_00");
			spellFormView = new SpellFormView(book.getCustomSpellForm("Bolt_00"));
			book.getCustomSpellForm("Bolt_00").getSpellStats().getCustomSpellStat("Speed").setNumUpgrades(40);

			spellFormView.setPosition(100, 20);
			uiStage.addActor(spellFormView);

			Gdx.input.setInputProcessor(uiStage);

			SpellDebuffDetails debuffView = new SpellDebuffDetails();
			debuffView.setModel(spell.getCustomDebufflot("Spot 1"));
			uiStage.addActor(debuffView);
			debuffView.setPosition(100, 400);


			DebuffSlotIcon debuffSlotView = new DebuffSlotIcon();
			debuffSlotView.setModel(spell.getDebuffSlots().getCustomDebufflot("Spot 1"));
			debuffSlotView.setPosition(100, 250);;
			uiStage.addActor(debuffSlotView);

			sctView = sct.sct("HoLaaaaa");
			uiStage.addActor(sctView);

			//spell.setNumUpgrades("Spot 1", "Speed", 30);
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

			//shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
			//shapeRenderer.setColor(Color.BLACK);
			//shapeRenderer.rect(sctView.getX(), sctView.getY(), sctView.getWidth(), sctView.getHeight());
			//shapeRenderer.end();

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
		shapeRenderer.dispose();
		spellFormView.dispose();
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
