package com.myrran.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.myrran.ZMain;
import com.myrran.data.GraphicSettings;
import com.myrran.data.Settings;
import com.myrran.data.XMLloader;


public class DesktopLauncher
{
	public static void main (String[] arg) throws Exception
	{
		//XMLloader.marshal(new GraphicSettings());
		GraphicSettings settings = Settings.get().getGraphicSettings();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width 		= settings.horizontalRes;
		config.height 		= settings.verticalRes;
		config.title 		= settings.tittle;
		config.fullscreen 	= settings.fullScreen;
		config.vSyncEnabled	= settings.vsync;

		new LwjglApplication(new ZMain(), config);
	}
}
