package edu.chl.Game.entity;

import java.util.LinkedList;

import edu.chl.Game.object.Id;
import edu.chl.Game.tile.Tile;

public class UpdateMovement {

	private UnitProperties unitProperties;
	private UnitState unitState;
        private Entity en;

	public UpdateMovement(Entity en) {
                this.en = en;
		this.unitProperties = en.getUnitProperties();
		this.unitState = en.getUnitState();
	}

	public void updateCoordinates() {
		en.setX(en.getX() + en.getVelX());
		en.setY(en.getY() + en.getVelY());
	}

	public void toggleAnimate() {
		if (en.getVelX() != 0) {
			unitState.setAnimate(true);
		} else {
			unitState.setAnimate(false);
		}

	}

}
