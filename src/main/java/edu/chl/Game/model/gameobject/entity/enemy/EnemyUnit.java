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

/**
 * 
 * Abstract class modeling an enemy unit on the playingfield. EnemyUnit enables graphical components like sprites and sheets
 * and methods cencerning combat.
 * 
 * @author gulledompi
 *
 */

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
		super.render(g);
		ur.renderGraphics(this, g);
		runScoreDisplay(g);
		displayHealthBar(g);
		super.render(g);
	}

	@Override
	public void update() {
		super.update();
		if (getUnitState().isAnimate()) {
			iterateMoving();
		}
		exerciseAI();
		if (isAttacking) {
			iterateAttack();
		}
		
	}

	
	/**
	 * Activates the behaviour connected to this unit.
	 */
	public void exerciseAI() {
		aI.exerciseBehaviour();
	}

	/**
	 * Checks if this unit is performing an attack.
	 * 
	 * @return boolean isAttacking.
	 */
	public boolean isAttacking() {
		return isAttacking;
	}

	/**
	 * Setter method if this unit is performing an attack.
	 * 
	 * @param b sets the boolean.
	 */
	public void setAttacking(boolean b) {
		isAttacking = b;
	}
	
	/**
	 * Increases the frameiterator to display the second image for the moving unit. 
	 */
	public void iterateMoving() {
		getFrameIterator_moving().updateFrameCounter();
	}

	/**
	 * Increases the frameiterator to display the second image for the attacking unit. 
	 */
	public void iterateAttack() {
		getFrameIterator_attack().updateFrameCounter();
		if(!getFrameIterator_attack().isActive()){
			isAttacking = false;
		}
	}

	
	// Getters And Setters
	
	/**
	 * Getter method for the adjusted y coordinate in the attack animation.
	 * 
	 * @return integer value of adjusted y.
	 */
	public int getAdjustedY(){
		int change = (altHeight - getHeight());		
		return getY() - change;
	}
	
	/**
	 * Getter method for the right adjusted x coordinate in the attack animation.
	 * 
	 * @return integer value of adjusted x.
	 */
	public int getAdjustedX_right(){
		int offset = getUnitMeasurement().getOffsetRight();
		return getX() - offset;
	}

	/**
	 * Getter method for the left adjusted x coordinate in the attack animation.
	 * 
	 * @return integer value of adjusted x.
	 */
	public int getAdjustedX_left(){
		int offset = getUnitMeasurement().getOffsetLeft();
		return getX() - offset;
	}
	
	/**
	 * Getter method for the spritesheet with the images displaying the unit moving.
	 * 
	 * @return reference to the SpriteSheet.
	 */
	public SpriteSheet getSheetMovingAnimation() {
		return sheetMovingAnimation;
	}

	/**
	 * Getter method for the spritesheet with the images displaying the unit attacking.
	 * 
	 * @return reference to the SpriteSheet.
	 */
	public SpriteSheet getSheetAttackAnimation() {
		return sheetAttackAnimation;
	}

	/**
	 * Setter method for the spritesheet with the images displaying the unit moving.
	 * 
	 * @param ss sets the SpriteSheet.
	 */
	public void setSheetMovingAnimation(SpriteSheet ss) {
		this.sheetMovingAnimation = ss;
	}

	/**
	 * Setter method for the spritesheet with the images displaying the unit attacking.
	 * 
	 * @param ss sets the SpriteSheet.
	 */
	public void setSheetAttackAnimation(SpriteSheet ss) {
		this.sheetAttackAnimation = ss;
	}


	/**
	 * Getter method for the array with the moving 
	 * 
	 * @param ss sets the SpriteSheet.
	 */
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
