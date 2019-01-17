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
import com.myrran.view.ui.ScrollingCombatText;
import com.myrran.view.ui.SpellFormView;
import com.myrran.view.ui.TextView;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ZMain extends ApplicationAdapter
{
	SpriteBatch batch;
	Texture img;

	BitmapFont font;
	TextView text;
	Stage uiStage;
	ScrollingCombatText sct;

	ShapeRenderer shapeRenderer;
	TextView sctView;
	CustomSpellBook book;
	SpellFormView spellFormView;

	@Override
	public void create ()
	{
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		font = new BitmapFont(Gdx.files.internal("fonts/" + "20.fnt"), false);
		text = new TextView("Hola Mundo", font, Color.WHITE, Color.BLACK, 2);
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
		try
		{
			book = unmarshal(CustomSpellBook.class);
			spellFormView = new SpellFormView(book.getCustomSpellForm("Bolt_00"));
			book.getCustomSpellForm("Bolt_00").getSpellStats().getCustomSpellStat("Speed").setNumUpgrades(40);
		}
		catch (Exception e) { System.out.println(e); }

		spellFormView.setPosition(100, 100);
		uiStage.addActor(spellFormView);

		Gdx.input.setInputProcessor(uiStage);
	}

	@Override
	public void render ()
	{
		Gdx.gl.glClearColor(1, 1, 1, 1);
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
	
	@Override
	public void dispose ()
	{
		batch.dispose();
		img.dispose();

		font.dispose();
		shapeRenderer.dispose();
		spellFormView.dispose();
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
