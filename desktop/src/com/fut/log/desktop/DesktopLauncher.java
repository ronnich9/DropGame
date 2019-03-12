package com.fut.log.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fut.log.Drop;
import com.fut.log.IActivityRequestHandler;

public class DesktopLauncher {
	public static void main (String[] arg) {
		IActivityRequestHandler handler;

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "CiberPank";
		config.width = 800;
		config.height = 400;
		new LwjglApplication(new Drop(new IActivityRequestHandler() {
			@Override
			public void showAds(boolean show) {
				show = true;
			}
		}), config);
	}
}
