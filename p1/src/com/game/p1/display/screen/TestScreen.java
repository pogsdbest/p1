package com.game.p1.display.screen;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.game.framework.display.DisplayCamera;
import com.game.framework.display.DisplayObject;
import com.game.framework.display.DisplayScreen;
import com.game.framework.utils.L;
import com.game.p1.utils.Assets;
import com.game.p1.utils.Config;

public class TestScreen extends DisplayScreen {

	private OrthogonalTiledMapRenderer renderer;
	private DisplayObject character;

	public TestScreen() {
		super(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		// TODO Auto-generated constructor stub
		isDebug = true;
		
		Assets assets = Assets.getInstance();
		TiledMap map = assets.get("data/maps/map1.tmx");
		//draw by layer
		renderer = new OrthogonalTiledMapRenderer(map, 1);
		renderer.setView(camera.combined,0,0,800,480);
		//draw by actor
		//TileMapDisplay mapDisplay = new TileMapDisplay(map,camera);
		//saddActor(mapDisplay);
		TextureAtlas atlas = assets.get("data/assets.pack");
		TextureAtlas atlas2 = assets.get("gfx/assets.pack");
		
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
		
		TextureRegion characterTexture = atlas.findRegion("downhuman");
		character = new DisplayObject(characterTexture);
		character.setPosition(0, 0);
		addActor(character);
		
		DisplayCamera displayCamera = new DisplayCamera(camera);
		displayCamera.setTarget(character);
		addActor(displayCamera);
	}
	
	@Override
	protected void drawTileMapLayers() {
		// TODO Auto-generated method stub
		//renderer.render(new int[]{0});
		super.drawTileMapLayers();
	}

}
