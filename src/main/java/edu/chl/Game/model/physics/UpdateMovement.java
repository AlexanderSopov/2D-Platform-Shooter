package edu.chl.Game.model.physics;

import java.util.LinkedList;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.Entity;
import edu.chl.Game.model.gameobject.entity.FacingDirection;
import edu.chl.Game.model.gameobject.entity.UnitState;
import edu.chl.Game.model.gameobject.tile.Tile;

public class UpdateMovement {
	
	private UnitState unitState;
    private Entity en;

	public UpdateMovement(Entity en) {
        this.en = en;
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
	
	public void updateFacing(){

			if(en.getVelX()<0){
				en.getEntityState().setFacingDirection(FacingDirection.FacingLeft);
			} else {
				en.getEntityState().setFacingDirection(FacingDirection.FacingRight);
			}
	}

}
