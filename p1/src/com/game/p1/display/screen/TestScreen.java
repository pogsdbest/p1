package com.game.p1.display.screen;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.game.framework.display.DisplayCamera;
import com.game.framework.display.DisplayObject;
import com.game.framework.display.DisplayScreen;
import com.game.framework.display.tilemap.TileMapDisplay;
import com.game.framework.listeners.ActorDragListener;
import com.game.framework.listeners.ActorKeyboardListener;
import com.game.framework.utils.L;
import com.game.p1.utils.Assets;
import com.game.p1.utils.Config;

public class TestScreen extends DisplayScreen {

	private OrthogonalTiledMapRenderer renderer;
	private DisplayObject character;
	private boolean goLeft;
	private boolean goRight;
	private boolean goDown;
	private boolean goUp;

	public TestScreen() {
		super(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		// TODO Auto-generated constructor stub
		isDebug = true;
		
		Assets assets = Assets.getInstance();
		TiledMap map = assets.get("data/maps/map1.tmx");
		//draw by layer
		//renderer = new OrthogonalTiledMapRenderer(map, 1);
		
		//draw by actor
		
		
		
		TextureAtlas atlas = assets.get("data/assets.pack");
		TextureAtlas atlas2 = assets.get("gfx/assets.pack");
		
		/*
		TextureRegion bgTexture = atlas2.findRegion("bg");
		DisplayObject bg = new DisplayObject(bgTexture);
		addActor(bg);
		bg.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				character.setPosition(x, y);
				super.clicked(event, x, y);
			}
		});
		*/
		
		
		TileMapDisplay mapDisplay = new TileMapDisplay(map,camera);
		addActor(mapDisplay);
		
		TextureRegion characterTexture = atlas.findRegion("downhuman");
		character = new DisplayObject(characterTexture);
		character.setPosition(0, 0);
		addActor(character);
		addListener(new ActorKeyboardListener(){
			
		});
		
		DisplayCamera displayCamera = new DisplayCamera(camera);
		displayCamera.setTarget(character);
		addActor(displayCamera);
		
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
		font.draw(batch, "x : "+character.getX(), 10, 30);
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

}
