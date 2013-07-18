package com.game.p1.display.screen;

import java.util.ArrayList;

import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.game.framework.display.DisplayScreen;
import com.game.framework.net.ConnectionCallback;
import com.game.framework.net.NetworkClient;
import com.game.framework.net.NetworkServer;
import com.game.framework.utils.L;
import com.game.p1.utils.Assets;
import com.game.p1.utils.Config;

public class NetworkLobbyScreen extends DisplayScreen implements ConnectionCallback {
	
	private Group menuGroup;
	private Group listGroup;
	private List list;
	
	private String[] ips;
	
	public NetworkLobbyScreen() {
		super(Config.SCREEN_WIDTH,Config.SCREEN_HEIGHT);
		Assets assets = Assets.getInstance();
		Skin skin = assets.get("data/skins/uiskin.json");
		isDebug = true;
		
		createMenu(skin);
		createServerList(skin);
		
		searchForServer(assets);
	}
	
	private void searchForServer(Assets assets) {
		this.ips = assets.getText("config.txt", "#ips");
		ArrayList<String> listOfServers = new ArrayList<String>();
		
		for(int i=0;i<ips.length;i++) {
			NetworkClient client = new NetworkClient(this);
			client.connectTo(8888, ips[i]);
			if(client.isConnected()){
				listOfServers.add(ips[i]);
				list.setItems(listOfServers.toArray(new String[listOfServers.size()]));
			} else {
				client.dispose();
			}
		}
	}

	private void createServerList(Skin skin) {
		listGroup = new Group();
		list = new List(new String[0],skin);
		
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
		
		final ConnectionCallback callback = this;
		
		createBtn.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				//L.wtf("Server started...");
				NetworkServer server = new NetworkServer(callback);
				server.startServer();
				super.clicked(event, x, y);
			}
		});
		joinBtn.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				
				super.clicked(event, x, y);
			}
		});
		exitBtn.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				
				super.clicked(event, x, y);
			}
		});
		
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

	@Override
	public void onConnect(Socket socket) {
	}

	@Override
	public void onError() {
	}

	@Override
	public void onEnd() {
	}

	@Override
	public void onUpdate(byte[] data) {
	}
	
	@Override
	public void dispose() {
		
		super.dispose();
	}
}
