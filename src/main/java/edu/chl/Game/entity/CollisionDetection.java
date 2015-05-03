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

		for (Tile t : unitProperties.getHandler().getTileList()) {
			if (t.getUnitState().isSolid()) {
				if (t.getUnitState().getId() == Id.wall) {
					if (calculateBounds.getBoundsTop()
							.intersects(t.getBounds())) {
						unitProperties.setVelY(0);
						if (entityState.isJumping()) {
							entityState.setJumping(false);
							entityProp.setGravity(0.8);
							entityState.setFalling(true);
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
