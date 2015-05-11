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
	
	private SpriteSheet sheetRoaringBrute;
	private SpriteSheet sheetAttackAnimation;
	private Sprite[] movingAnimationArray;
	private Sprite[] attackAnimationArray;
	private LoadingSprites loadMoving;
	private LoadingSprites loadAttacking;

	
	public RoaringBrute(int x, int y, int width, int height, boolean solid, Id id, GameHandler handler, OpponentUnitProperties op, FrameValues frameValues, SpriteSheet spriteSheet_walking) {
		super(x, y, width, height, solid, id, handler, op, frameValues, spriteSheet_walking);
		
		this.sheetRoaringBrute = new SpriteSheet("/b00.png");
		this.sheetAttackAnimation = new SpriteSheet("/b03.png");
		/*
		this.loadMoving = new LoadingSprites(sheetRoaringBrute, movingAnimationArray, 16, getWidth(), getHeight());
		this.loadMoving.loadSprites();
		this.loadAttacking = new LoadingSprites(sheetAttackAnimation, attackAnimationArray, 16, 148, 126);
		this.loadAttacking.loadSprites();
		*/
	}
	
	/*
	@Override
	public void render(Graphics g) {
		if (getEntityState().getFacingDirection() == FacingDirection.FacingRight) {	
					getRenderClass().renderAnimateRight(g, movingAnimationArray,
					getEntityProperties().getFrame(),
					getX(), getY(),
					getWidth(),
					getHeight());
		} else if (getEntityState().getFacingDirection() == FacingDirection.FacingLeft) {
					getRenderClass().renderAnimateLeft(g, movingAnimationArray,
					getEntityProperties().getFrame(),
					getX(), getY(),
					getWidth(),
					getHeight());
		}
	}
	*/


}
