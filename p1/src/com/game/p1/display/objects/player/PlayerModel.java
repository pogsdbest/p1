package com.game.p1.display.objects.player;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerModel {
	
	//texture animations
	private TextureRegion[] walkLeftTextures;
	private TextureRegion[] walkRightTextures;
	private TextureRegion[] walkUpTextures;
	private TextureRegion[] walkDownTextures;
	private TextureRegion[] defaultTextures;
	
	/*******SETTERS AND GETTERS***********/
	public TextureRegion[] getWalkLeftTextures() {
		return walkLeftTextures;
	}
	public void setWalkLeftTextures(TextureRegion[] walkLeftTextures) {
		this.walkLeftTextures = walkLeftTextures;
	}
	public TextureRegion[] getWalkRightTextures() {
		return walkRightTextures;
	}
	public void setWalkRightTextures(TextureRegion[] walkRightTextures) {
		this.walkRightTextures = walkRightTextures;
	}
	public TextureRegion[] getWalkUpTextures() {
		return walkUpTextures;
	}
	public void setWalkUpTextures(TextureRegion[] walkUpTextures) {
		this.walkUpTextures = walkUpTextures;
	}
	public TextureRegion[] getWalkDownTextures() {
		return walkDownTextures;
	}
	public void setWalkDownTextures(TextureRegion[] walkDownTextures) {
		this.walkDownTextures = walkDownTextures;
	}
	public TextureRegion[] getDefaultTextures() {
		return defaultTextures;
	}
	public void setDefaultTextures(TextureRegion[] defaultTextures) {
		this.defaultTextures = defaultTextures;
	}
	
	
}
