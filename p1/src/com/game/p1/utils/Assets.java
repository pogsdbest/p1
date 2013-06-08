package com.game.p1.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.framework.manager.ResourceManager;
import com.game.framework.utils.L;

public class Assets extends ResourceManager {

	private static Assets instance;

	public static Music currentMusic;
	
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
		//load the atlas
		String packFile = "gfx/assets.pack";
		loadAtlas(packFile);
		isDebug = true;
		loadFont("data/font.fnt","data/font_00.png","uni_05_63");
		
	}
	
	private TextureRegion[] getAtlasAnimation(TextureAtlas atlas,String name,int frameCount , int startIndex , boolean format) {
		TextureRegion[] frames = new TextureRegion[frameCount];
		for(int i=0;i<frameCount ;i++){
			String textureName="";
			if(format)
				textureName = name+String.format("%04d", startIndex);
			else
				textureName = name + startIndex;
			frames[i] = atlas.findRegion(textureName);
			startIndex++;
		}
		return frames;
	}
	
	private TextureRegion[] getAtlasAnimation(TextureAtlas atlas,String name,int frameCount,boolean format) {
		return getAtlasAnimation(atlas,name,frameCount,0,format);
	}
	
	private Sound loadSound(String file) {
		Sound sound = Gdx.audio.newSound(Gdx.files.internal(file));
		
		if(sound == null) {
			L.e(file);
		}
		
		return sound;
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
		
		super.dispose();
	}
	
}
