package com.game.framework.display.tilemap;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TileMapDisplay extends Actor {
	
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	
	public TileMapDisplay(TiledMap map ,OrthographicCamera camera) {
		// TODO Auto-generated constructor stub
		this(map ,camera , 1f);
		
	}

	public TileMapDisplay(TiledMap map ,OrthographicCamera camera ,float unitScale) {
		this.map = map;
		// TODO Auto-generated constructor stub
		renderer = new OrthogonalTiledMapRenderer(map, unitScale);
		renderer.setView(camera);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		super.draw(batch, parentAlpha);
		renderer.render();
		
	}

}
