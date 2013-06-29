package com.game.p1.display.objects.player;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.p1.actions.MoveAction;
import com.game.p1.utils.Assets;

public class PlayerPrefab {
	
	private static PlayerPrefab instance;
	
	private PlayerPrefab() {
	}
	
	public static PlayerPrefab getInstance() {
		if(instance==null) {
			instance = new PlayerPrefab();
		}
		return instance;
	}
	
	public PlayerDisplay createPlayer() {
		Assets assets = Assets.getInstance();
		TextureRegion[] playerWalkAnimations = assets.getAtlasAnimation("gfx/assets.pack", "a", 1, 12);
		TextureRegion[] walkDown = new TextureRegion[]{playerWalkAnimations[3],playerWalkAnimations[4],playerWalkAnimations[5]};
		TextureRegion[] walkUp = new TextureRegion[]{playerWalkAnimations[0],playerWalkAnimations[1],playerWalkAnimations[2]};
		TextureRegion[] walkLeft = new TextureRegion[]{playerWalkAnimations[6],playerWalkAnimations[7],playerWalkAnimations[8]};
		TextureRegion[] walkRight = new TextureRegion[]{playerWalkAnimations[9],playerWalkAnimations[10],playerWalkAnimations[11]};
		
		PlayerModel model = new PlayerModel();
		model.setWalkDownTextures(walkDown);
		model.setWalkUpTextures(walkUp);
		model.setWalkLeftTextures(walkLeft);
		model.setWalkRightTextures(walkRight);
		model.setDefaultTextures(walkDown);
		
		MoveAction moveAction = new MoveAction(150);
		
		PlayerDisplay player = new PlayerDisplay(model);
		player.addAction(moveAction);
		
		return player;
	}

}
