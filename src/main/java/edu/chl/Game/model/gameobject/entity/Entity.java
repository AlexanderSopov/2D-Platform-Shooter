package edu.chl.Game.model.gameobject.entity;

import java.awt.Rectangle;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.GameObject;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.UnitTools.*;

public abstract class Entity extends GameObject {

	private EntityProperties entityProperties;
	private EntityState entityState;
	private RenderClass renderClass;
	private RenderClass renderClass1;
	private CollisionDetection collisionDetection;
	private FrameIterator frameIterator;
        private UpdateMovement updateMovement;

	public Entity(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler) {
		super(x, y, width, height, solid, id, handler);

		renderClass = new RenderClass();
		renderClass1 = new RenderClass();
                this.updateMovement = new UpdateMovement(this);
		entityState = new EntityState(FacingDirection.FacingRight);
		entityProperties = new EntityProperties();
		collisionDetection = new CollisionDetection(this);

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

	public void remove() {
		getHandler().removeEntity(this);
	}

	public RenderClass getRenderClass() {
		return renderClass;
	}

	public RenderClass getRenderClass1() {
		return renderClass1;
	}

	public FrameIterator getFrameIterator() {
		return frameIterator;
	}
        
        public UpdateMovement getUpdateMovement() {
		return this.updateMovement;
	}

}
