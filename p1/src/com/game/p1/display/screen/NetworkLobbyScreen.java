package com.game.p1.display.screen;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.game.framework.display.DisplayScreen;
import com.game.framework.display.ui.TextArea;
import com.game.framework.net.ConnectionCallback;
import com.game.framework.net.NetworkClient;
import com.game.framework.net.NetworkServer;
import com.game.framework.utils.L;
import com.game.p1.net.Data;
import com.game.p1.net.MessageData;
import com.game.p1.net.PlayerData;
import com.game.p1.utils.Assets;
import com.game.p1.utils.Config;

public class NetworkLobbyScreen extends DisplayScreen {

	private Group mainLobbyGroup;
	private Group serverLobbyGroup;

	private List serverInstanceList;
	private List playerInstanceList;
	private TextArea textArea;
	
	private boolean isServer;

	public NetworkLobbyScreen() {
		super(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		Assets assets = Assets.getInstance();
		Skin skin = assets.get("data/skins/uiskin.json");
		isDebug = true;

		createMainLobby(skin, assets);
		createServerLobbyGroup(skin, assets);
		searchForServer(assets);
	}

	private void createServerLobbyGroup(Skin skin, Assets assets) {
		serverLobbyGroup = new Group();
		serverLobbyGroup.setVisible(false);

		createChatBox(skin);
		createPlayerList(skin);

		addActor(serverLobbyGroup);
	}

	private void createPlayerList(Skin skin) {
		Group listGroup = new Group();
		playerInstanceList = new List(new String[0], skin);

		Table table = new Table(skin);
		table.debug();
		table.debugTable();
		table.setPosition(350, 100);
		table.setSize(400, 350);
		table.add(playerInstanceList).expand().fill();

		listGroup.addActor(table);
		serverLobbyGroup.addActor(listGroup);

	}

	private void createChatBox(Skin skin) {
		Group chatBoxGroup = new Group();

		textArea = new TextArea("", skin);
		textArea.setColor(1f, 1f, 1f, .5f);
		textArea.setDisabled(true);

		final TextField messageField = new TextField("", skin);
		messageField.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				// TODO Auto-generated method stub
				if (keycode == Keys.ENTER) {
					textArea.append(messageField.getText());
					send(messageField.getText());
					messageField.setText("");
				}
				return super.keyDown(event, keycode);
			}
		});

		TextButton sendButton = new TextButton("SEND", skin);
		sendButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				textArea.append(messageField.getText());
				messageField.setText("");
				super.clicked(event, x, y);
			}
		});

		Table table = new Table(skin);
		table.setWidth(300);
		table.setHeight(height);
		chatBoxGroup.addActor(table);

		table.add(textArea).width(300)
				.height(table.getHeight() - sendButton.getHeight()).colspan(2);
		table.row();
		table.add(messageField).width(table.getWidth() - sendButton.getWidth());
		table.add(sendButton).fill();

		serverLobbyGroup.addActor(chatBoxGroup);
	}

	protected void send(String message) {
		MessageData messageData = new MessageData();
		
	}

	private void createMainLobby(Skin skin, Assets assets) {
		mainLobbyGroup = new Group();
		createMenu(skin);
		createServerList(skin);

		addActor(mainLobbyGroup);
	}

	/*
	 * search for active serverInstance in the list of ips written in the
	 * config.txt
	 */
	private void searchForServer(Assets assets) {
		String ips[] = assets.getText("config.txt", "#ips");
		final ArrayList<String> listOfServers = new ArrayList<String>();

		for (int i = 0; i < ips.length; i++) {
			final String ip = ips[i];
			final NetworkClient client = new NetworkClient(
					new ConnectionCallback() {
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
							serverInstanceList.setItems(listOfServers
									.toArray(new String[listOfServers.size()]));
							L.wtf("server " + ip + " detected...");
						}
					});
			client.connectTo(13456, ip);
			//client.dispose();
		}
	}

	private void createServerList(Skin skin) {
		Group listGroup = new Group();
		serverInstanceList = new List(new String[0], skin);

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

		TextButton createBtn = new TextButton("Create", skin);
		TextButton joinBtn = new TextButton("Join", skin);
		TextButton exitBtn = new TextButton("Exit", skin);

		createBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// L.wtf("Server started...");
				final ArrayList<String> listOfPlayers = new ArrayList<String>();
				final HashMap<String,Socket> clientSockets = new HashMap<String, Socket>();
				NetworkServer server = new NetworkServer(
						new ConnectionCallback() {
							private Socket socket;
							private String clientName;

							@Override
							public void onUpdate(byte[] data) {
								
								// TODO Auto-generated method stub
								try {
									String json = new String(data);
									if(json.isEmpty()) return;
									JSONObject object = new JSONObject(json);
									Data gameData = new Data(object);
									if (gameData.getType().equals(
											PlayerData.KEY)) {
										PlayerData playerData = new PlayerData(
												object);
										L.wtf("Player " + playerData.getName()
												+ " connected...");
										this.clientName = playerData.getName();
										listOfPlayers.add(clientName);
										playerInstanceList.setItems(listOfPlayers
												.toArray(new String[listOfPlayers
														.size()]));
										clientSockets.put(clientName, this.socket);
										
									} else if(gameData.getType().equals(
											PlayerData.KEY)) {
										
									}
									// L.wtf(json);
									

								} catch (Exception e) {
									e.printStackTrace();
								}
							}

							@Override
							public void onError() {
								// TODO Auto-generated method stub
								L.wtf(clientName+" connection error...");
							}

							@Override
							public void onEnd() {
								// TODO Auto-generated method stub
								for (int i=listOfPlayers.size()-1;i>=0;i--) {
									if(listOfPlayers.get(i).equals(clientName)){
										listOfPlayers.remove(i);
									}
								}
								clientSockets.remove(clientName);
								L.wtf(clientName+" connection end...");
							}

							@Override
							public void onConnect(Socket socket) {
								// TODO Auto-generated method stub
								L.wtf("New client connected...");
								this.socket = socket;
							}
						});
				server.startServer();
				mainLobbyGroup.setVisible(false);
				serverLobbyGroup.setVisible(true);
				isServer = true;
				super.clicked(event, x, y);
			}
		});
		joinBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				NetworkClient client = new NetworkClient(
						new ConnectionCallback() {

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
								L.wtf("successfully connected to server...");
								PlayerData playerData = new PlayerData();
								playerData.setName("pogs");
								try {
									socket.getOutputStream().write(
											playerData.getBytes());
								} catch (Exception e) {
									e.printStackTrace();
								}
								mainLobbyGroup.setVisible(false);
								serverLobbyGroup.setVisible(true);
								isServer = false;
							}
						});
				if (serverInstanceList.getItems().length > 0)
					client.connectTo(13456, serverInstanceList.getSelection());
				super.clicked(event, x, y);
			}
		});
		exitBtn.addListener(new ClickListener() {
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
