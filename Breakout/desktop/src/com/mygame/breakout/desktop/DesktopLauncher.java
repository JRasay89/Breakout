package com.mygame.breakout.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygame.breakout.Breakout;
import com.mygame.core.Constant;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Breakout";
		config.width = Constant.WIDTH;
		config.height = Constant.HEIGHT;
		new LwjglApplication(new Breakout(), config);
	}
}
