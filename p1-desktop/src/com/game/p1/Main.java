package com.game.p1;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "p1";
		cfg.useGL20 = false;
		cfg.width = 800;
		cfg.height = 480;
		
		System.out.println((int)(.99));
		
		new LwjglApplication(new P1Main(), cfg);
	}
}
