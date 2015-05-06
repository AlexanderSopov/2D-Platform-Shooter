package edu.chl.Game.entity;

import com.sun.javafx.css.CalculatedValue;
import edu.chl.Game.object.Id;
import edu.chl.Game.tile.*;

public class CollisionDetection {

	private UnitProperties unitProperties;
	private UnitState unitState;
	private EntityState entityState;
	private EntityProperties entityProp;
	private CalculateBounds calculateBounds;

	public CollisionDetection(UnitProperties unitProperties, UnitState unitState, CalculateBounds calculateBounds, EntityProperties entityProperties, EntityState entityState) {
		
		this.unitProperties = unitProperties;
		this.unitState = unitState;
		this.calculateBounds = calculateBounds;
		this.entityProp = entityProperties;
		this.entityState = entityState;
	}

	public void checkForCollision(){
		iterateThroughTileList();
	}
	
	public void iterateThroughTileList(){
		for (Tile t : unitProperties.getHandler().getTileList()) {
			checkIfSolid(t);
		}
	}

	public void checkIfSolid(Tile t){
		if (t.getUnitState().isSolid()) {
			checkIfWall(t);
		}
	}
	
	public void checkIfWall(Tile t){
		if (t.getUnitState().getId() == Id.wall) {
			checkCollition(t);
		}
	}
	
	public void checkCollition(Tile t){		
		checkCollideWithFlor(t);
		checkCollideWithRightWall(t);
		checkCollideWithLeftWall(t);
		checkCollideWithRoof(t);
	}
	
	
	public void checkCollideWithFlor(Tile t){
		if(t.getTileState() == TileState.wall || t.getTileState() == TileState.floor){
			if (calculateBounds.getBoundsBottom().intersects(t.getBounds())) {
				System.out.println("<check> collide floor");
				unitProperties.setY(t.getY() - unitProperties.getHeight());
				unitProperties.setVelY(0);
				entityState.setFalling(false);
				entityState.setContactWithGround(true);
			}
		}
		
	}
	
	public void checkCollideWithRoof(Tile t){
		if (calculateBounds.getBoundsTop().intersects(t.getBounds())) {
			unitProperties.setVelY(0);										
			if (entityState.isJumping()) {									
				entityState.setJumping(false);								
				entityProp.setGravity(0.8);									
				entityState.setFalling(true);								
			}
		}
	}
	
	public void checkCollideWithRightWall(Tile t){
		if(t.getTileState() == TileState.wall || t.getTileState() == TileState.rightWall){
			if (calculateBounds.getBoundsRight().intersects(t.getBounds())) {
				System.out.println("<check> collide Right wall");
				unitProperties.setVelX(0);
				unitProperties.setX((t.getUnitProperties().getX() - t.getUnitProperties().getWidth()));
			}
		}
	}
	
	public void checkCollideWithLeftWall(Tile t){
		if(t.getTileState() == TileState.wall || t.getTileState() == TileState.leftWall){
			if (calculateBounds.getBoundsLeft().intersects(t.getBounds())) {
				System.out.println("<check> collide Left wall");
				unitProperties.setVelX(0);
				unitProperties.setX((t.getUnitProperties().getX() + t.getUnitProperties().getWidth()));
			}
		}
	}

}
