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
import com.game.p1.utils.Assets;
import com.game.p1.utils.Config;

public class NetworkLobbyScreen extends DisplayScreen {
	
	private Group mainLobbyGroup;
	private Group serverLobbyGroup;
	
	private List serverInstanceList;
	
	public NetworkLobbyScreen() {
		super(Config.SCREEN_WIDTH,Config.SCREEN_HEIGHT);
		Assets assets = Assets.getInstance();
		Skin skin = assets.get("data/skins/uiskin.json");
		isDebug = true;
		
		createMainLobby(skin,assets);
		createServerLobbyGroup(skin,assets);
		searchForServer(assets);
	}
	
	private void createServerLobbyGroup(Skin skin, Assets assets) {
		serverLobbyGroup = new Group();
		createChatBox
	}

	private void createMainLobby(Skin skin, Assets assets) {
		mainLobbyGroup = new Group();
		createMenu(skin);
		createServerList(skin);
		
		addActor(mainLobbyGroup);
	}

	/*
	 * search for active serverInstance in the list of ips written in the config.txt
	 */
	private void searchForServer(Assets assets) {
		String ips[] = assets.getText("config.txt", "#ips");
		final ArrayList<String> listOfServers = new ArrayList<String>();
		
		for(int i=0;i<ips.length;i++) {
			final String ip = ips[i];
			final NetworkClient client = new NetworkClient(new ConnectionCallback() {
				@Override
				public void onUpdate(byte[] data) {
					
				}
				
				@Override
				public void onError() {
					
				}
				
				@Override
				public void onEnd() {
					
				}
				
				@Override
				public void onConnect(Socket socket) {
					listOfServers.add(ip);
					serverInstanceList.setItems(listOfServers.toArray(new String[listOfServers.size()]));
				}
			});
			client.connectTo(8888, ip);
			client.dispose();
		}
	}

	private void createServerList(Skin skin) {
		Group listGroup = new Group();
		serverInstanceList = new List(new String[0],skin);
		
		
		Table table = new Table(skin);
		table.debug();
		table.debugTable();
		table.setPosition(200, 20);
		table.setSize(500, 400);
		table.add(serverInstanceList).expand().fill();
		
		listGroup.addActor(table);
		mainLobbyGroup.addActor(listGroup);
	}

	private void createMenu(Skin skin) {
		Group menuGroup = new Group();
		
		TextButton createBtn = new TextButton("Create",skin);
		TextButton joinBtn = new TextButton("Join",skin);
		TextButton exitBtn = new TextButton("Exit",skin);
		
		createBtn.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				//L.wtf("Server started...");
				NetworkServer server = new NetworkServer(new ConnectionCallback() {
					
					@Override
					public void onUpdate(byte[] data) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onError() {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onEnd() {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onConnect(Socket socket) {
						// TODO Auto-generated method stub
						
					}
				});
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
		mainLobbyGroup.addActor(menuGroup);
	}
	
	@Override
	public void dispose() {
		
		super.dispose();
	}
	
}
