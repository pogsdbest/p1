package com.game.p1.display.screen;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.game.framework.display.DisplayScreen;
import com.game.p1.utils.Assets;
import com.game.p1.utils.Config;

public class NetworkLobbyScreen extends DisplayScreen {
	
	private Group menuGroup;
	private Group listGroup;
	
	public NetworkLobbyScreen() {
		super(Config.SCREEN_WIDTH,Config.SCREEN_HEIGHT);
		Assets assets = Assets.getInstance();
		Skin skin = assets.get("data/skins/uiskin.json");
		isDebug = true;
		
		createMenu(skin);
		createServerList(skin);
	}
	
	private void createServerList(Skin skin) {
		listGroup = new Group();
		String servers[] = new String[]{"Server1","Server2"};
		List list = new List(servers,skin);
		
		
		Table table = new Table(skin);
		table.debug();
		table.debugTable();
		table.setPosition(200, 20);
		table.setSize(500, 400);
		table.add(list).expand().fill();
		
		listGroup.addActor(table);
		addActor(listGroup);
	}

	private void createMenu(Skin skin) {
		this.menuGroup = new Group();
		
		TextButton createBtn = new TextButton("Create",skin);
		TextButton joinBtn = new TextButton("Join",skin);
		TextButton exitBtn = new TextButton("Exit",skin);
		
		Table table = new Table(skin);
		table.debug();
		table.debugTable();
		table.setPosition(20, 20);
		table.setSize(150, 200);
		table.defaults().width(150);
		table.add(createBtn);
		table.row();
		table.add(joinBtn);
		table.row();
		table.add(exitBtn);
		
		menuGroup.addActor(table);
		addActor(menuGroup);
	}
	
	
}
