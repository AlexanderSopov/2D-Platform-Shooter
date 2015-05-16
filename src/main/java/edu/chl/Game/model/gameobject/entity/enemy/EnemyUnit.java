package edu.chl.Game.model.gameobject.entity.enemy;

import java.awt.Graphics;
import java.util.Random;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import sun.swing.text.html.FrameEditorPaneTag;

import com.sun.prism.impl.BaseMesh.FaceMembers;

import edu.chl.Game.Main;
import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.controller.RefreshTimer;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.*;
import edu.chl.Game.model.gameobject.entity.entityTools.*;
import edu.chl.Game.model.gameobject.tile.Tile;
import edu.chl.Game.view.graphics.*;

public abstract class EnemyUnit extends Entity {

	private SpriteSheet sheetMovingAnimation;
	private SpriteSheet sheetAttackAnimation;
	private Sprite[] arrayMovingAnimation;
	private Sprite[] arrayAttackAnimation;
	private LoadingSprites load;
	private UnitGraphicsRender ur;

	private GravitationalProperties gravitationalProperties;
	private int altWidth;
	private int altHeight;
	private AI aI;
	private double attackDamage;
	private boolean isAttacking = false;

	public EnemyUnit(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler) {

		super(x, y, width, height, solid, id, handler);
		this.load = new LoadingSprites();
		this.ur = new UnitGraphicsRender();
		this.initiateUnit();
		this.aI = new AI(this);
		this.gravitationalProperties = new GravitationalProperties(this);

	}

	@Override
	public void render(Graphics g) {
		ur.renderGraphics(this, g);
		runScoreDisplay(g);
	}

	@Override
	public void update() {
		getUpdateMovement().updateCoordinates_enemy();
		getUpdateMovement().toggleAnimate();
		getUpdateMovement().updateFacing();
		getCollisionDetection().checkForCollision();
		gravitationalProperties.fallingMechanics();
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
		int hightAdd = (altHeight - getHeight());		
		return getY() - hightAdd;
	}
	
	public int getAdjustedX(){	
		return getX() - 53;
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

	public double getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(double attackDamage) {
		this.attackDamage = attackDamage;
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
	
	public abstract void initiateSpriteSheets();
	public abstract void initiateSpriteArrays();
	public abstract void loadSprites();
	public abstract void initiateProperties();
	public abstract void initiateUnit();
	public abstract void setAlternativeMeasurement();

}
