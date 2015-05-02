package edu.chl.Game.entity;

import java.util.LinkedList;

import edu.chl.Game.object.Id;
import edu.chl.Game.tile.Tile;

public class UpdateMovement {

	private UnitProperties unitProperties;

	public UpdateMovement(UnitProperties unitProperties) {
		this.unitProperties = unitProperties;
	}

	public void updateCoordinates() {

		unitProperties.setX(unitProperties.getX() + unitProperties.getVelX());
		unitProperties.setY(unitProperties.getY() + unitProperties.getVelY());
	}

}
