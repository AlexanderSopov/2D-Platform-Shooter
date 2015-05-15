package edu.chl.Game.model.gameobject.entity;

import java.awt.Rectangle;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.GameObject;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.entityTools.*;
import edu.chl.Game.model.physics.CollisionDetection;
import edu.chl.Game.model.physics.Gravity;
import edu.chl.Game.view.graphics.EntityRender;

public abstract class Entity extends GameObject {

	private EntityProperties entityProperties;
	private EntityState entityState;
	private EntityRender renderClass;
	private EntityRender renderClass1;
	private CollisionDetection collisionDetection;
	private FrameIterator frameIterator;

	public Entity(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler) {
		super(x, y, width, height, solid, id, handler);

		renderClass = new EntityRender();
		renderClass1 = new EntityRender();
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
    	updateCoordinates();
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
		setPosition(getPosition().addWith(getVelocity()));
	}
}
