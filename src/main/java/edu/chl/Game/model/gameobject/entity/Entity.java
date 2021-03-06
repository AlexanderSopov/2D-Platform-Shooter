package edu.chl.Game.model.gameobject.entity;

import java.util.LinkedList;
import java.awt.Graphics;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.GameObject;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.entityTools.*;
import edu.chl.Game.model.gameobject.entity.player.Player;
import edu.chl.Game.model.gameobject.item.Hat;
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

	public void runScoreDisplay(Graphics g){
		if(!scoreProcess.listIsEmpty()){
			updateScoreDisplay(g);
		}
	}
	
	public void updateScoreDisplay(Graphics g){
		scoreProcess.updateScoreDispay(g);
	}
	
	public void addScoreInterface(int value, ScoreType type){
		scoreProcess.addScoreInterface(this, value, type);
	}
	
	// --- Damage System ---
	
	public void takeDamage(int value) {
		unitValues.setHealthPoints((unitValues.getHealthPoints() - value));
		checkIfDead();
		addScoreInterface(value, ScoreType.damage);
	}
	
	public void checkIfDead(){
		if (unitValues.getHealthPoints() <= 0) {
			getHandler().addItem(new Hat(getX(), getY()+ getHeight() - 64, 64,64,null, getHandler()));
			die();
		}
	}
	
	public void die(){
		if(isEnemy()){
			reward();
		}
		if(getId() == Id.monster){
			getHandler().registerDead(getX(), getY(), getEntityState().getFacingDirection());
		}
		this.remove();

	}
	
	private boolean isEnemy(){
		return getId()==Id.monster;
	}
	
	private void reward(){
		int experienceReward = calculateExperience();
		getPlayer().gainExperience(experienceReward);
	}
	
	private Player getPlayer(){
		return getHandler().getPlayer();
	}
	
	private int calculateExperience(){
		return getUnitValues().getLevel() * 10;
	}
	
	@Override
	public void remove() {
		getHandler().removeEntity(this);
	}
	
	
	// --- HUD ---
	
	public void displayHealthBar(Graphics g){
		healthBar.displayHealthBar(g);
	}
	
	
	
	
	
	// --- Setters And Getters ---
	
	public EntityProperties getEntityProperties() {
		return entityProperties;
	}
	public EntityState getEntityState() {
		return entityState;
	}

	public boolean isRecievingDamage() {
		return isRecievingDamage;
	}
	public void setRecievingDamage(boolean b) {
		this.isRecievingDamage = b;

	}
	public EntityRender getRenderClass() {
		return renderClass;
	}
	public EntityRender getRenderClass1() {
		return renderClass1;
	}

	public FrameIterator getFrameIterator() {
		return frameIterator;
	}
    
        
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
	public FrameIterator getFrameIterator_moving() {
		return frameIterator_moving;
	}
	public FrameIterator getFrameIterator_attack() {
		return frameIterator_attack;
	}
	public FrameIterator getFrameIterator_hurting() {
		return frameIterator_hurt;
	}
	public UnitValues getUnitValues(){
		return unitValues;
	}
	public UnitMeasurement getUnitMeasurement(){
		return um;
	}
	public String getUnitTitle(){
		return unitTitle;
	}
	public void setFrameIterator_moving(FrameIterator fi) {
		this.frameIterator_moving = fi;
	}
	public void setFrameIterator_attack(FrameIterator fi) {
		this.frameIterator_attack = fi;
	}
	public void setFrameIterator_hurt(FrameIterator fi) {
		this.frameIterator_attack = fi;
	}
	public void setUnitValues(int level, int maxHp, int maxEp, int arm, int aD, int aR){
		this.unitValues = new UnitValues(level, maxHp, maxEp, arm, aD, aR);
	}
	public void setUnitTitle(String str){
		this.unitTitle = str;
	}

	public void isBumpingGround(boolean b) {
		isBumpingGround = b;
	}

	public void isTryingToJump(boolean b) {
		isTryingToJump = b;
		
	}
}
