package edu.chl.Game.model.gameobject.entity;

import java.awt.Rectangle;
import java.awt.Graphics;

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
	private ScoreInterface scoreInterface;
	private boolean isRecievingDamage;
	private double latestDamageTaken;
	
	private FrameIterator frameIterator_moving;
	private FrameIterator frameIterator_attack;
	private FrameIterator frameIterator_takeDamage;

	public Entity(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler) {
		super(x, y, width, height, solid, id, handler);

		renderClass = new EntityRender();
		renderClass1 = new EntityRender();
		this.updateMovement = new UpdateMovement(this);
		entityState = new EntityState(FacingDirection.FacingRight);
		entityProperties = new EntityProperties();
		collisionDetection = new CollisionDetection(this);
		scoreInterface = new ScoreInterface(this);

	}

	public EntityProperties getEntityProperties() {
		return entityProperties;
	}

	public EntityState getEntityState() {
		return entityState;
	}

	public CollisionDetection getCollisionDetection() {
		return collisionDetection;
	}

	@Override
	public void remove() {
		getHandler().removeEntity(this);
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
	
	public ScoreInterface getScoreInterface() {
		return scoreInterface;
	}
	
	public void takeDamage(double damage) {
		if (!isRecievingDamage()) {
			setRecievingDamage(true);
		}
		setHealthPoints(getHealthPoints() - damage);
		latestDamageTaken = damage;
		if (getHealthPoints() <= 0.0) {
			this.remove();
		}
	}
	
	public void checkIfHurt(Graphics g){
		if(isRecievingDamage){
			displayScore(g);
		}
	}

	public void displayScore(Graphics g) {
		scoreInterface.renderScore(this, g, latestDamageTaken);
	}
	
	public FrameIterator getFrameIterator_moving() {
		return frameIterator_moving;
	}

	public FrameIterator getFrameIterator_attack() {
		return frameIterator_attack;
	}
	
	public FrameIterator getFrameIterator_takeDamage() {
		return frameIterator_takeDamage;
	}
	
	public void setFrameIterator_moving(FrameIterator fi) {
		this.frameIterator_moving = fi;
	}

	public void setFrameIterator_attack(FrameIterator fi) {
		this.frameIterator_attack = fi;
	}
	
	public void setFrameIterator_takeDamage(FrameIterator fi) {
		this.frameIterator_takeDamage = fi;
	}

}
