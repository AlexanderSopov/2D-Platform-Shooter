package edu.chl.Game.entity;


import edu.chl.Game.Vector.Vector2D;
import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.object.GameObject;
import edu.chl.Game.object.Id;

public abstract class Entity extends GameObject {
	
	private EntityProperties entityProperties;
	private EntityState entityState;
	private RenderClass renderClass;
	private static Vector2D gravity = new Vector2D(0,1);
	public Entity(double d, double e, int width, int height, boolean solid, Id id, GameHandler handler) {
		super(d, e, width, height, solid, id, handler,10);

		renderClass = new RenderClass();
		entityState = new EntityState(FacingDirection.FacingRight);
		entityProperties = new EntityProperties();
	}

	public EntityProperties getEntityProperties(){
		return entityProperties;
	}
	
	public EntityState getEntityState(){
		return entityState;
	}
	
	public void update(){
		UnitProperties up = getUnitProperties();
		up.setVelocity(up.getVelocity().addWith(gravity));
		up.setPosition(up.getPosition().addWith(up.getVelocity()));
	}
	

	public void remove() {
		getUnitProperties().getHandler().removeEntity(this);
	}

	public RenderClass getRenderClass() {
		return renderClass;
	}

	public boolean touchesGround() {
		return true;
	}



}
