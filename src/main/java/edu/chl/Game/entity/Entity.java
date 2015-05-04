package edu.chl.Game.entity;


import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.object.GameObject;
import edu.chl.Game.object.Id;

public abstract class Entity extends GameObject {
	
	private EntityProperties entityProperties;
	private EntityState entityState;
	private RenderClass renderClass;

	public Entity(int x, int y, int width, int height, boolean solid, Id id, GameHandler handler) {
		super(x, y, width, height, solid, id, handler);

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
	

	public void remove() {
		getUnitProperties().getHandler().removeEntity(this);
	}

	public RenderClass getRenderClass() {
		return renderClass;
	}



}
