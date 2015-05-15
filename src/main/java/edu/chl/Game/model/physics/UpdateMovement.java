package edu.chl.Game.model.physics;
import edu.chl.Game.model.gameobject.entity.Entity;
import edu.chl.Game.model.gameobject.entity.UnitState;

public class UpdateMovement {
	private UnitState unitState;
    private Entity en;

	public UpdateMovement(Entity en) {
        this.en = en;
		this.unitState = en.getUnitState();
	}





}
