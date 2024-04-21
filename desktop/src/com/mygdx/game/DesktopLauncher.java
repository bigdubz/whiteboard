package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(165);
		config.setWindowedMode(Lwjgl3ApplicationConfiguration.getDisplayMode().width - 120,
				Lwjgl3ApplicationConfiguration.getDisplayMode().height - 120);
		config.setResizable(true);
		config.useVsync(true);
		config.setTitle("Whiteboard");
		config.setBackBufferConfig(8,8,8,8,16,0,8);
		new Lwjgl3Application(new Whiteboard(), config);
	}
}
