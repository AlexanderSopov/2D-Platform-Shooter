package edu.chl.Game.model.gameobject.entity;

import java.util.LinkedList;
import java.awt.Graphics;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.GameObject;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.entityTools.*;
import edu.chl.Game.model.gameobject.entity.player.Player;
import edu.chl.Game.model.gameobject.item.Hat;
import edu.chl.Game.model.gameobject.item.Nothing;
import edu.chl.Game.model.gameobject.item.W1;
import edu.chl.Game.model.physics.Gravity;
import edu.chl.Game.model.physics.collisions.CollisionSolver;
import edu.chl.Game.view.graphics.EntityRender;
import edu.chl.Game.view.graphics.HealthBar;
import edu.chl.Game.view.graphics.ScoreProcess;
import edu.chl.Game.view.graphics.ScoreType;
import edu.chl.Game.model.physics.CollisionDetection;
import edu.chl.Game.view.graphics.*;
import edu.chl.Game.model.physics.*;
import edu.chl.Game.model.sound.Sound;

/**
 * Abstract superclass to the entities of the game. This class manages the "living" units of the game.
 * Entity enables services for rendering graphics of the unit and its surrounding information. 
 * 
 * @author Oliver Tunberg
 */

public abstract class Entity extends GameObject {

	private EntityProperties entityProperties;
	private EntityState entityState;
	private EntityRender renderClass;
	private EntityRender renderClass1;
	private FrameIterator frameIterator;
	private boolean isBumpingGround;
	private boolean isTryingToJump;
	private boolean isRecievingDamage;
	private FrameIterator frameIterator_moving;
	private FrameIterator frameIterator_attack;
	private FrameIterator frameIterator_hurt;
	private ScoreProcess scoreProcess;
	private HealthBar healthBar;
	private UnitValues unitValues;
	private UnitMeasurement um;
	private String unitTitle;
	private CalculateBounds calcBounds;


	public Entity(int x, int y, int width, int height, boolean solid, Id id, GameHandler handler) {
		super(x, y, width, height, solid, id, handler);
		renderClass = new EntityRender();
		renderClass1 = new EntityRender();
		entityState = new EntityState(FacingDirection.FacingRight);
		entityProperties = new EntityProperties();
		isBumpingGround(false);
		isTryingToJump(false);
		entityProperties = new EntityProperties();
		scoreProcess = new ScoreProcess();
		this.healthBar = new HealthBar(this);
		this.um = new UnitMeasurement();



	}
	
	// --- Score System ---

	/**
	 * Checks if the list of score information contains anything to display. In that case, updates that score with 
	 * the graphics from g.
	 * 
	 * @param g Graphics component.
	 */
	public void runScoreDisplay(Graphics g){
		if(!scoreProcess.listIsEmpty()){
			updateScoreDisplay(g);
		}
	}
	
	/**
	 * Updates the score information and draws its graphics.
	 * 
	 * @param g Graphics component
	 */
	public void updateScoreDisplay(Graphics g){
		scoreProcess.updateScoreDispay(g);
	}
	
	
	/**
	 * Adds a value to be displayed by the score information. The score is added to the list in the ScoreProcess.
	 * 
	 * @param value
	 * @param type
	 */
	public void addScoreInterface(int value, ScoreType type){
		scoreProcess.addScoreInterface(this, value, type);
	}
	
	// --- Damage System ---
	
	/**
	 * Method for recieving damage from another unit. Reduces the healthPoints integer value from UnitValues class bythe value
	 * of the parameter.
	 * 
	 * @param value integer value of the attack force recieved.
	 */
	public void takeDamage(int value) {
		unitValues.setHealthPoints((unitValues.getHealthPoints() - value));
		checkIfDead();
		addScoreInterface(value, ScoreType.damage);
	}
	
	/**
	 * Checks if this unit has run out of healthpoints.
	 */
	public void checkIfDead(){
		if (unitValues.getHealthPoints() <= 0) {
			getHandler().addItem(new Hat(getX(), getY()+ getHeight() - 64, 64,64,null, getHandler()));
			die();
		}
	}
	
	/**
	 * Killing this unit by removing it from the list of entities in the Handler.
	 */
	public void die(){
		if(isEnemy()){
			reward();
		}
		if(getId() == Id.monster){
			getHandler().registerDead(getX(), getY(), getEntityState().getFacingDirection());
		}
		this.remove();

	}
	
	/**
	 * Checks if this entity is an enemy.
	 * 
	 * @return true if an enemy.
	 */
	private boolean isEnemy(){
		return getId()==Id.monster;
	}
	
	/**
	 * Rewards the player character with experience based on the level of this unit.
	 */
	private void reward(){
		int experienceReward = calculateExperience();
		getPlayer().gainExperience(experienceReward);
	}
	
	/**
	 * Getter method for the player character in the Handler.
	 * 
	 * @return Player referance.
	 */
	private Player getPlayer(){
		return getHandler().getPlayer();
	}
	
	/**
	 * Calculates the experience this method rewards the player once it has been defeated.
	 * 
	 * @return integer value of the experience rewarded.
	 */
	private int calculateExperience(){
		return getUnitValues().getLevel() * 10;
	}
	
	/**
	 * Removes this unit from the list of entities.
	 */
	@Override
	public void remove() {
		getHandler().removeEntity(this);
	}
	
	
	// --- HUD ---
	
	/**
	 * Displays healthbar over the unit.
	 * 
	 * @param g Graphics component.
	 */
	public void displayHealthBar(Graphics g){
		healthBar.displayHealthBar(g);
	}
	
	
	
	
	
	// --- Setters And Getters ---
	
	/**
	 * Getter method for the EntityProperties instance variable.
	 * 
	 * @return referance to EntityProperties.
	 */
	
	public EntityProperties getEntityProperties() {
		return entityProperties;
	}
	
	/**
	 * Getter method for int EntityState instance variable. 
	 * 
	 * @return reference to EntityState.
	 */
	public EntityState getEntityState() {
		return entityState;
	}

	/**
	 * Returns the boolean checking if this unit is recieving damage. 
	 * 
	 * @return value of isRecievingDamage.
	 */
	public boolean isRecievingDamage() {
		return isRecievingDamage;
	}
	/**
	 * Sets if the unit is recieving damage or not.
	 * 
	 * @param b true if being hit.
	 */
	public void setRecievingDamage(boolean b) {
		this.isRecievingDamage = b;

	}
	/**
	 * Getter method for the EntityRender instance variable.
	 * 
	 * @return reference to EntityRender.
	 */
	
	public EntityRender getRenderClass() {
		return renderClass;
	}
	
	/**
	 * Getter method for the second Entity Render instance variable.
	 * 
	 * @return reference to the second Entity Render.
	 */
	public EntityRender getRenderClass1() {
		return renderClass1;
	}

	/**
	 * Getter method for the head frameiterator instance variable.
	 * 
	 * @return reference to FrameIterator
	 */
	
	public FrameIterator getFrameIterator() {
		return frameIterator;
	}
	
	/**
	 * Getter method for the CalculateBounds instance variable.
	 * 
	 * @return reference to CalculateBounds.
	 */
	public CalculateBounds getCalcBounds(){
		return calcBounds;
	}
    
    /**
     * This method handles the update of this units coordinates between running cycles.   
     */
    @Override
    public void update(){
    	addGravity();
    	toggleAnimate();
    	jump();
    	updateCoordinates();
    	CollisionSolver.solveCollisions(this, getNearbyGameObjects());
    }
    
    private void jump() {
		if(isTryingToJump)
			if(isBumpingGround){
				setVelY(getVelY() -4);
				if(getVelY() < -20)
					isBumpingGround(false);
			}
	}

	private LinkedList<GameObject> getNearbyGameObjects() {
    	LinkedList<GameObject> list = new LinkedList<GameObject>();
    	list.addAll(getHandler().getEntityList());
    	list.addAll(getHandler().getTileList());
    	list.addAll(getHandler().getItemList());
		return list;
	}

	private void addGravity(){
    	setVelocity(getVelocity().addWith(Gravity.GRAVITY));
    }
    
    private void toggleAnimate() {
    	if (getVelX() != 0) {
    		getUnitState().setAnimate(true);
    	} else {
    		getUnitState().setAnimate(false);
    	}

    }
	private void updateCoordinates() {
		if(Math.abs(getVelY())>1)
			setPosition(getPosition().addWith(getVelocity()));
		else
			setX(getX()+getVelX());
	}
	
	/**
	 * Getter method for the frameiterator handling moving animation.
	 * 
	 * @return reference to FrameIterator.
	 */
	public FrameIterator getFrameIterator_moving() {
		return frameIterator_moving;
	}
	
	/**
	 * Getter method for the frameiterator handling attack animation.
	 * 
	 * @return reference to FrameIterator.
	 */
	public FrameIterator getFrameIterator_attack() {
		return frameIterator_attack;
	}
	
	/**
	 * Getter method for the frameiterator handling taking damage animation.
	 * 
	 * @return reference to FrameIterator.
	 */
	public FrameIterator getFrameIterator_hurting() {
		return frameIterator_hurt;
	}
	
	/**
	 * Getter method for the instance variable Unitvalues.
	 * 
	 * @return reference to UnitValues.
	 */
	public UnitValues getUnitValues(){
		return unitValues;
	}
	
	/**
	 * Getter method for the instance variable UnitMeasurement.
	 * 
	 * @return reference to UnitMeasurement.
	 */
	public UnitMeasurement getUnitMeasurement(){
		return um;
	}
	
	/**
	 * Getter method for the name of this unit.
	 * 
	 * @return name as a String.
	 */
	public String getUnitTitle(){
		return unitTitle;
	}
	
	/**
	 * Setter method for the frameiterator handling moving animation.
	 * 
	 * @param fi sets the frameIterator
	 */
	public void setFrameIterator_moving(FrameIterator fi) {
		this.frameIterator_moving = fi;
	}
	
	/**
	 * Setter method for the frameiterator handling attack animation.
	 * 
	 * @param fi sets the frameIterator
	 */
	public void setFrameIterator_attack(FrameIterator fi) {
		this.frameIterator_attack = fi;
	}
	
	/**
	 * Setter method for the frameiterator handling taking damage animation.
	 * 
	 * @param fi sets the frameIterator
	 */
	public void setFrameIterator_hurt(FrameIterator fi) {
		this.frameIterator_attack = fi;
	}
	
	/**
	 * Setter method for the values and properties of this unit. 
	 * 
	 * @param level the level of this unit.
	 * @param maxHp the maximum amount of health is unit can have.
	 * @param maxEp the maximum amount of energy is unit can have.
	 * @param arm the amount of armor this unit will have.
	 * @param aD the amount of damage this unit will be able to make.
	 * @param aR the speed of which this unit will attack.
	 * 
	 * 
	 */
	public void setUnitValues(int level, int maxHp, int maxEp, int arm, int aD, int aR){
		this.unitValues = new UnitValues(level, maxHp, maxEp, arm, aD, aR);
	}
	
	/**
	 * Setter method for the title/name of this unit.
	 * 
	 * @param str sets the name.
	 */
	public void setUnitTitle(String str){
		this.unitTitle = str;
	}

	/**
	 * Setter method if this unit is bumping into the ground.
	 * 
	 * @param b sets the boolean.
	 */
	public void isBumpingGround(boolean b) {
		isBumpingGround = b;
	}

	/**
	 * Setter method if this unit is trying to jump.
	 * 
	 * @param b sets the boolean.
	 */
	public void isTryingToJump(boolean b) {
		isTryingToJump = b;
		
	}
}
