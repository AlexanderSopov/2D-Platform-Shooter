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

	private int altWidth;
	private int altHeight;
	private AttackTimer attackTimer;
	private AI aI;
	private double attackDamage;
	private boolean isAttacking = false;
	private FrameIterator frameIterator_moving;
	private FrameIterator frameIterator_attack;

	public EnemyUnit(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler) {

		super(x, y, width, height, solid, id, handler);

		this.load = new LoadingSprites();
		this.ur = new UnitGraphicsRender();
		this.initiateUnit();
		this.aI = new AI(this, attackTimer);

	}

	@Override
	public void render(Graphics g) {
		ur.renderGraphics(this, g);
	}

	@Override
	public void update() {
		getUpdateMovement().updateCoordinates();
		getUpdateMovement().toggleAnimate();
		getUpdateMovement().updateFacing();
		getCollisionDetection().checkForCollision();
		if (getUnitState().isAnimate()) {
			iterateMoving();
		}
		if (isAttacking) {
			iterateAttack();
		}
		exerciseAI();
		aI.attack();
	}

	public void iterateMoving() {
		frameIterator_moving.updateFrameCounter();
	}

	public void iterateAttack() {
		frameIterator_attack.updateFrameCounter();
		if(!frameIterator_attack.isActive()){
			isAttacking = false;
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

	public AttackTimer getAttackTimer() {
		return attackTimer;
	}

	public void setAttackTimer(AttackTimer attackTimer) {
		this.attackTimer = attackTimer;
	}

	public void setFrameIterator_moving(FrameIterator fi) {
		this.frameIterator_moving = fi;
	}

	public void setFrameIterator_attack(FrameIterator fi) {
		this.frameIterator_attack = fi;
	}
	
	public FrameIterator getFrameIterator_moving() {
		return frameIterator_moving;
	}

	public FrameIterator getFrameIterator_attack() {
		return frameIterator_attack;
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
