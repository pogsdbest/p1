package com.game.p1.display.screen;


import org.json.JSONObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.game.framework.display.DisplayCamera;
import com.game.framework.display.DisplayObject;
import com.game.framework.display.DisplayScreen;
import com.game.framework.display.tilemap.TileMapDisplay;
import com.game.framework.display.ui.DialogWindow;
import com.game.framework.display.ui.TextArea;
import com.game.framework.listeners.ActorDragListener;
import com.game.framework.net.ClientConnection;
import com.game.framework.net.ConnectionCallback;
import com.game.p1.utils.Assets;
import com.game.p1.utils.Commands;
import com.game.p1.utils.Config;

public class TestScreen extends DisplayScreen implements ConnectionCallback {

	private DisplayObject character;
	private boolean goLeft;
	private boolean goRight;
	private boolean goDown;
	private boolean goUp;
	private ClientConnection cc;
	private TextArea textArea;
	private Skin skin;
	private DisplayCamera displayCamera;

	public TestScreen() {
		super(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		// TODO Auto-generated constructor stub
		isDebug = true;
		
		Assets assets = Assets.getInstance();
		TiledMap map = assets.get("data/maps/map1.tmx");
		TiledMapTileLayer layer0 = (TiledMapTileLayer)map.getLayers().get(0);
		int width = layer0.getWidth();
		int height = layer0.getHeight();
		//draw by actor
		
		TextureAtlas atlas = assets.get("data/assets.pack");
		TextureAtlas atlas2 = assets.get("gfx/assets.pack");
		
		TileMapDisplay mapDisplay = new TileMapDisplay(map,camera);
		addActor(mapDisplay);
		
		TextureRegion characterTexture = atlas.findRegion("downhuman");
		character = new DisplayObject(characterTexture);
		character.setPosition(mapDisplay.getWidth()/2, mapDisplay.getHeight()/2);
		addActor(character);
		addListener(new InputListener(){
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				// TODO Auto-generated method stub
				return super.keyDown(event, keycode);
			}
		});
		
		displayCamera = new DisplayCamera(camera);
		displayCamera.setTarget(character);
		displayCamera.setBoundaries(mapDisplay);
		addActor(displayCamera);
		
		skin = assets.get("data/skins/uiskin.json");
		
		Label label = new Label("Project-P1 v1.0.0", skin); 
		displayCamera.addActor(label);
		label.addListener(new ActorDragListener());
		label.setX(500);
		
		textArea = new TextArea("", skin);
		textArea.setColor(1f, 1f, 1f, .5f);
		textArea.setDisabled(true);
		final TextField messageField = new TextField("-connect 6000 127.0.0.1",skin);
		messageField.addListener(new InputListener(){
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				// TODO Auto-generated method stub
				if(keycode == Keys.ENTER) {
					send(textArea,messageField);
				}
				return super.keyDown(event, keycode);
			}
		});
		TextButton sendButton = new TextButton("SEND", skin);
		sendButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				send(textArea,messageField);
				super.clicked(event, x, y);
			}
		});
		
		Table table = new Table(skin);
		table.setWidth(300);
		table.setHeight(150);
		displayCamera.addActor(table);
		
		table.add(textArea).width(300).height(table.getHeight() - messageField.getHeight()).colspan(2);
		table.row();
		table.add(messageField).width(table.getWidth() - sendButton.getWidth());
		table.add(sendButton).fill();
		
		addListener(new InputListener(){
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				// TODO Auto-generated method stub
				
				return super.keyDown(event, keycode);
			}
		});
		
		cc = new ClientConnection();
		cc.setCallback(this);
	}
	
	protected void send(TextArea textArea, TextField messageField) {
		// TODO Auto-generated method stub
		String message = messageField.getText();
		if(message.length() == 0) return;
		if(message.charAt(0) == '-') {
			String[] commands = message.split(" ");
			String command = commands[0];
			if(command.equals(Commands.CONNECT) && commands.length==3) {
				if(!cc.isConnected())
					cc.connectTo(commands[1], commands[2]);
			}  else if(command.equals(Commands.SEND) && commands.length==2) {
				if(!cc.isConnected()) return;
				try{
					JSONObject object = new JSONObject();
					object.put("key", commands[1]);
					cc.sendData(object);
				} catch(Exception e) {}
			}
		}
		
		textArea.append(message);
		messageField.setText("");
	}
	
	@Override
	protected void drawTileMapLayers() {
		// TODO Auto-generated method stub
		//renderer.setView(camera.combined,0,0,800,480);
		//renderer.render(new int[]{0});
		super.drawTileMapLayers();
	}
	
	@Override
	protected void drawScreen(SpriteBatch batch) {
		// TODO Auto-generated method stub
		super.drawScreen(batch);
	}
	
	@Override
	protected void update(float delta) {
		// TODO Auto-generated method stub
		if(goLeft) {
			character.setX(character.getX() - 2);
		} else if(goRight) {
			character.setX(character.getX() + 2);
		} else if(goDown) {
			character.setY(character.getY() - 2);
		} else if(goUp) {
			character.setY(character.getY() + 2);
		}
		
		super.update(delta);
	}
	
	@Override
	public boolean keyDown(int keyCode) {
		// TODO Auto-generated method stub
		if(keyCode == Keys.LEFT ) {
			goLeft = true;
		} else if(keyCode == Keys.RIGHT ) {
			goRight = true;
		} else if(keyCode == Keys.DOWN ) {
			goDown = true;
		} else if(keyCode == Keys.UP ) {
			goUp = true;
		}
		return super.keyDown(keyCode);
	}
	
	@Override
	public boolean keyUp(int keyCode) {
		// TODO Auto-generated method stub
		if(keyCode == Keys.LEFT ) {
			goLeft = false;
		} else if(keyCode == Keys.RIGHT ) {
			goRight = false;
		} else if(keyCode == Keys.DOWN ) {
			goDown = false;
		} else if(keyCode == Keys.UP ) {
			goUp = false;
		}
		return super.keyUp(keyCode);
	}

	@Override
	public void obtainedData(JSONObject data) {
		// TODO Auto-generated method stub
		try {
			textArea.append(data.getString("message"));
		} catch(Exception e) {}
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		cc.dispose();
		super.dispose();
	}

	@Override
	public void connectionCrash(String err) {
		// TODO Auto-generated method stub
		final DialogWindow window = new DialogWindow(skin,"ERROR REPORT",err,"QUIT");
		window.getButton().addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				window.remove();
				Gdx.app.exit();
				super.clicked(event, x, y);
			}
		});
		displayCamera.addActor(window);
		window.setX((displayCamera.getWidth() - window.getWidth())/2 );
		window.setY((displayCamera.getHeight() - window.getHeight()) /2);
	}

}
