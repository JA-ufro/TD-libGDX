package com.ufro.poke;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.ufro.poke.pantallas.Pantalla;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public DesktopLauncher(){
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("PokeTower");
		config.setWindowedMode(1280,720);
		config.useVsync(true);
		config.setResizable(false);
		new Lwjgl3Application(new Pantalla(), config);
	}
}
