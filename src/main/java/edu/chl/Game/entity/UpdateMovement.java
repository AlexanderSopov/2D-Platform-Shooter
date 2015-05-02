package edu.chl.Game.entity;

import java.util.LinkedList;

import edu.chl.Game.object.Id;
import edu.chl.Game.tile.Tile;

public class UpdateMovement {

	private UnitProperties unitProperties;
	private UnitState unitState;

	public UpdateMovement(UnitProperties unitProperties, UnitState unitState) {
		this.unitProperties = unitProperties;
		this.unitState = unitState;
	}

	public void updateCoordinates() {

		unitProperties.setX(unitProperties.getX() + unitProperties.getVelX());
		unitProperties.setY(unitProperties.getY() + unitProperties.getVelY());
	}

	public void toggleAnimate() {
		if (unitProperties.getVelX() != 0) {
			unitState.setAnimate(true);
		} else {
			unitState.setAnimate(false);
		}

	}

}
