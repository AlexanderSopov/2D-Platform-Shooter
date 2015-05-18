package edu.chl.Game.model.gameobject.entity.enemy;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import edu.chl.Game.view.graphics.*;
import edu.chl.Game.Main;
import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.controller.RefreshTimer;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.*;
import edu.chl.Game.model.gameobject.entity.entityTools.*;
import edu.chl.Game.model.gameobject.tile.Tile;

public abstract class EnemyUnit extends Unit {

	private SpriteSheet sheetMovingAnimation;
	private SpriteSheet sheetAttackAnimation;
	private Sprite[] arrayMovingAnimation;
	private Sprite[] arrayAttackAnimation;
	private LoadingSprites load;
	private UnitGraphicsRender ur;

	private int altWidth;
	private int altHeight;
	private AI aI;
	private boolean isAttacking = false;

	public EnemyUnit(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler) {

		super(x, y, width, height, solid, id, handler);
		this.load = new LoadingSprites();
		this.ur = new UnitGraphicsRender();
		this.initiateUnit();
		this.aI = new AI(this);

	}

	@Override
	public void render(Graphics g) {
		ur.renderGraphics(this, g);
		runScoreDisplay(g);
		displayHealthBar(g);
		super.render(g);
	}

	@Override
	public void update() {
		//getUpdateMovement().updateCoordinates_enemy();
		//getUpdateMovement().toggleAnimate();
		//getUpdateMovement().updateFacing();
		//getCollisionDetection().checkForCollision();
		//gravitationalProperties.fallingMechanics();
		if (getUnitState().isAnimate()) {
			iterateMoving();
		}
		exerciseAI();
		if (isAttacking) {
			iterateAttack();
		}
		
	}

	public void exerciseAI() {
		aI.exerciseBehaviour();
	}

	public boolean isAttacking() {
		return isAttacking;
	}

	public void setAttacking(boolean b) {
		isAttacking = b;
	}
	
	public void iterateMoving() {
		getFrameIterator_moving().updateFrameCounter();
	}

	public void iterateAttack() {
		getFrameIterator_attack().updateFrameCounter();
		if(!getFrameIterator_attack().isActive()){
			isAttacking = false;
		}
	}

	
	// Getters And Setters
	
	public int getAdjustedY(){
		int change = (altHeight - getHeight());		
		return getY() - change;
	}
	
	public int getAdjustedX_right(){
		int offset = getUnitMeasurement().getOffsetRight();
		return getX() - offset;
	}

	public int getAdjustedX_left(){
		int offset = getUnitMeasurement().getOffsetLeft();
		return getX() - offset;
	}
	
	public SpriteSheet getSheetMovingAnimation() {
		return sheetMovingAnimation;
	}

	public SpriteSheet getSheetAttackAnimation() {
		return sheetAttackAnimation;
	}

	public void setSheetMovingAnimation(SpriteSheet ss) {
		this.sheetMovingAnimation = ss;
	}

	public void setSheetAttackAnimation(SpriteSheet ss) {
		this.sheetAttackAnimation = ss;
	}


	public Sprite[] getArrayMovingAnimation() {
		return arrayMovingAnimation;

	}

	public Sprite[] getArrayAttackAnimation() {
		return arrayAttackAnimation;
	}

	public void setArrayMovingAnimation(Sprite[] sa) {
		this.arrayMovingAnimation = sa;
	}

	public void setArrayAttackAnimation(Sprite[] sa) {
		this.arrayAttackAnimation = sa;
	}

	public LoadingSprites getLoad() {
		return load;
	}

	public int getAltWidth(){
		return altWidth;
	}
	
	public int getAltHeight(){
		return altHeight;
	}
	
	public void setAltWidth(int altWidth){
		this.altWidth = altWidth;
	}
	
	public void setAltHeight(int altHeight){
		this.altHeight = altHeight;
	}

}
