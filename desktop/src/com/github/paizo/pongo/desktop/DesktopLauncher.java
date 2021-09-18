package com.github.paizo.pongo.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.github.paizo.pongo.Boot;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setIdleFPS(60);
//		config.setForegroundFPS(60);
		config.useVsync(true);
		config.setTitle("Pongo");

		config.setWindowedMode(640, 480);
//		config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());

		new Lwjgl3Application(new Boot(), config);
	}
}
