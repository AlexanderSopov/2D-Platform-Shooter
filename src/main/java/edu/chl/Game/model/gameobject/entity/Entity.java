package edu.chl.Game.model.gameobject.entity;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.util.ArrayList;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.GameObject;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.entityTools.*;
import edu.chl.Game.model.physics.CollisionDetection;
import edu.chl.Game.model.physics.UpdateMovement;
import edu.chl.Game.view.graphics.*;

public abstract class Entity extends GameObject {

	private EntityProperties entityProperties;
	private EntityState entityState;
	private EntityRender renderClass;
	private EntityRender renderClass1;
	private CollisionDetection collisionDetection;
	private UpdateMovement updateMovement;
	private boolean isRecievingDamage;

	private FrameIterator frameIterator_moving;
	private FrameIterator frameIterator_attack;
	private ScoreProcess scoreProcess;

	public Entity(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler) {
		super(x, y, width, height, solid, id, handler);

		renderClass = new EntityRender();
		renderClass1 = new EntityRender();
		this.updateMovement = new UpdateMovement(this);
		entityState = new EntityState(FacingDirection.FacingRight);
		entityProperties = new EntityProperties();
		collisionDetection = new CollisionDetection(this);
		scoreProcess = new ScoreProcess();

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
	
	public void addScoreInterface(double value){
		scoreProcess.addScoreInterface(this, value);
	}
	
	// --- Damage System ---
	
	public void takeDamage(double value) {
		setHealthPoints(getHealthPoints() - value);
		checkIfDead();
		addScoreInterface(value);
	}
	
	public void checkIfDead(){
		if (getHealthPoints() <= 0.0) {
			die();
		}
	}
	
	public void die(){
		this.remove();
	}
	
	@Override
	public void remove() {
		getHandler().removeEntity(this);
	}
	
	
	
	
	
	// --- Setters And Getters ---
	
	public EntityProperties getEntityProperties() {
		return entityProperties;
	}
	public EntityState getEntityState() {
		return entityState;
	}
	public CollisionDetection getCollisionDetection() {
		return collisionDetection;
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
	public UpdateMovement getUpdateMovement() {
		return this.updateMovement;
	}
	public FrameIterator getFrameIterator_moving() {
		return frameIterator_moving;
	}
	public FrameIterator getFrameIterator_attack() {
		return frameIterator_attack;
	}
	public void setFrameIterator_moving(FrameIterator fi) {
		this.frameIterator_moving = fi;
	}
	public void setFrameIterator_attack(FrameIterator fi) {
		this.frameIterator_attack = fi;
	}
}
