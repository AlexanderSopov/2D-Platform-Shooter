package edu.chl.Game.entity;

import com.sun.javafx.css.CalculatedValue;

import edu.chl.Game.object.Id;
import edu.chl.Game.tile.Tile;

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

	public void checkForCollision() {

		for (Tile t : unitProperties.getHandler().getTileList()) {						// looks through the tilelist
			if (t.getUnitState().isSolid()) {											// if the tile is solid
				if (t.getUnitState().getId() == Id.wall) {								// if the tile is a wall
					if (calculateBounds.getBoundsTop().intersects(t.getBounds())) {		// if the player touches the wall from below						
						unitProperties.setVelY(0);										// sets the Y-velocity to 0
						if (entityState.isJumping()) {									// if the player is jumping
							entityState.setJumping(false);								// set jumping to false
							entityProp.setGravity(0.8);									// set gravity to 0.8
							entityState.setFalling(true);								// set falling to true
						}
					} else if (calculateBounds.getBoundsBottom().intersects(
							t.getBounds())) {
						unitProperties.setVelY(0);
						if (entityState.isFalling()) {
							entityState.setFalling(false);
						}

						if (!entityState.isFalling()
								&& !entityState.isJumping()) {
							entityProp.setGravity(0.8);
							entityState.setFalling(true);
						}
					} else if (calculateBounds.getBoundsLeft().intersects(
							t.getBounds())) {
						unitProperties.setVelX(0);
						unitProperties.setX((t.getUnitProperties().getX() + t
								.getUnitProperties().getWidth()));
					} else if (calculateBounds.getBoundsRight().intersects(
							t.getBounds())) {
						unitProperties.setVelX(0);
						unitProperties.setX((t.getUnitProperties().getX() - t
								.getUnitProperties().getWidth()));
					}
				}
			}
		}
	}

}
