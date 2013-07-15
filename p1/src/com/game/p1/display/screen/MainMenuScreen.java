package com.game.p1.display.screen;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.game.framework.display.DisplayScreen;
import com.game.p1.utils.Assets;
import com.game.p1.utils.Config;

public class MainMenuScreen extends DisplayScreen{

	public MainMenuScreen() {
		super(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		
		Assets assets = Assets.getInstance();
		TextureAtlas atlas = assets.get("gfx/assets.pack");
		Skin skin = assets.get("data/skins/uiskin.json");
		
		TextButton client = new TextButton("Client", skin);
		TextButton server = new TextButton("Server",skin);
		
		Table table = new Table(skin);
		table.setWidth(300);
		table.setHeight(150);
		addActor(table);
		
		table.add(client);
		table.row();
		table.add(server);
	}

}
