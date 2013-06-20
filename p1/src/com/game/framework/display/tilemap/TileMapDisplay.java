package com.game.framework.display.tilemap;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;

public class TileMapDisplay extends Group {
	
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	
	public TileMapDisplay(TiledMap map ,OrthographicCamera camera) {
		// TODO Auto-generated constructor stub
		this(map ,camera , 1f);
		
	}

	public TileMapDisplay(TiledMap map ,OrthographicCamera camera ,float unitScale) {
		this.map = map;
		this.camera = camera;
		// TODO Auto-generated constructor stub
		renderer = new OrthogonalTiledMapRenderer(map, unitScale);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		batch.end();
		renderer.setView(camera);
		renderer.render();
		batch.begin();
		super.draw(batch, parentAlpha);
	}

}
