package edu.chl.Game.model.gameobject.entity.enemy;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import edu.chl.Game.Main;
import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.controller.RefreshTimer;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.*;
import edu.chl.Game.model.gameobject.entity.entityTools.*;
import edu.chl.Game.view.graphics.Sprite;
import edu.chl.Game.view.graphics.SpriteSheet;

public class RoaringBrute extends EnemyUnit {
	
	private SpriteSheet attackAnimationSheet;
	private Sprite[] attackAnimationArray;
	private LoadingSprites load;

	public RoaringBrute(int x, int y, int width, int height, boolean solid, Id id, GameHandler handler, OpponentUnitProperties op, FrameValues frameValues, SpriteSheet spriteSheet_walking) {
		super(x, y, width, height, solid, id, handler, op, frameValues, spriteSheet_walking);
		
		attackAnimationSheet = new SpriteSheet("/b03");
		//load = new LoadingSprites(attackAnimationSheet, attackAnimationArray, 8, width, height);
		
	}
	


}
