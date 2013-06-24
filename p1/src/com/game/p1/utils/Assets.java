package com.game.p1.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {

	private static Assets instance;
	
	public static Music currentMusic;
	public AssetManager manager;
	
	// singleton
	public static Assets getInstance() {
		if (instance == null) {
			instance = new Assets();
		}
		return instance;
	}

	private Assets() {
		super();
	}
	
	public void initialize() {
		
		//pre load assets
		manager = new AssetManager();
		
		//load texture packs
		manager.load("gfx/assets.pack",TextureAtlas.class);
		manager.load("data/assets.pack",TextureAtlas.class);
		//load fonts
		manager.load("data/fonts/uni05_64.fnt",BitmapFont.class);
		manager.load("data/skins/uiskin.json",Skin.class);
		//load Sounds
		
		//load Musics
		
		//load maps
		manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		manager.load("data/maps/map1.tmx",TiledMap.class);
		
	}
	
	public <T> T get(String name) {
		return manager.get(name);
	}
	
	public void playMusic(Music music)
	{
		if(currentMusic!=null){
			currentMusic.stop();
			currentMusic=null;
		}
		if(Config.soundOn)
		{
			if(!music.isPlaying())
				music.play();
			currentMusic = music;
		}	
	}
	
	public void playSound(Sound sound) {
		if(Config.soundOn) {
			sound.play();
		}
	}
	public void pauseMusic(Music music)
	{
		if(music.isPlaying())
			music.pause();
	}
	public void stopMusic(Music music)
	{
		if(music.isPlaying())
			music.stop();
	}
	
	public void dispose() {
		
		manager.dispose();
	}
	
}
