package edu.chl.Game.entity;

import java.awt.Rectangle;

import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.object.GameObject;
import edu.chl.Game.object.Id;

public abstract class Entity extends GameObject {
	
	private EntityProperties entityProperties;
	private EntityState entityState;
	private RenderClass renderClass;
	private CollisionDetection collisionDetection;

	public Entity(int x, int y, int width, int height, boolean solid, Id id, GameHandler handler) {
		super(x, y, width, height, solid, id, handler);

		renderClass = new RenderClass();
		entityState = new EntityState(FacingDirection.FacingRight);
		entityProperties = new EntityProperties();
		collisionDetection = new CollisionDetection(getUnitProperties(), getUnitState(), getCalculateBounds(), entityProperties, entityState);



	}

	public EntityProperties getEntityProperties(){
		return entityProperties;
	}
	
	public EntityState getEntityState(){
		return entityState;
	}
	
	public CollisionDetection getCollisionDetection() {
		return collisionDetection;
	}

	public void remove() {
		getUnitProperties().getHandler().removeEntity(this);
	}

	public RenderClass getRenderClass() {
		return renderClass;
	}



}
