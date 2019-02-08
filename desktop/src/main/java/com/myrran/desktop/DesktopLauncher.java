package com.myrran.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.myrran.view.ZMain;
import com.myrran.data.GraphicSettings;
import com.myrran.data.Settings;


public class DesktopLauncher
{
	public static void main (String[] arg) throws Exception
	{
		if (false)
		{	TexturePacker.process("images", "atlas", "atlas.atlas"); }

		//XMLloader.marshal(new GraphicSettings());
		GraphicSettings settings = Settings.get().getGraphicSettings();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width 		= settings.horizontalRes;
		config.height 		= settings.verticalRes;
		config.title 		= settings.tittle;
		config.fullscreen 	= settings.fullScreen;
		config.vSyncEnabled	= settings.vsync;
		config.foregroundFPS= 5000;
		config.addIcon("textures/icons/IceBall.png", Files.FileType.Internal);

		new LwjglApplication(new ZMain(), config);
	}
}
