package edu.chl.Game.model.physics;

import com.sun.javafx.css.CalculatedValue;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.Entity;
import edu.chl.Game.model.gameobject.entity.EntityProperties;
import edu.chl.Game.model.gameobject.entity.EntityState;
import edu.chl.Game.model.gameobject.entity.UnitState;
import edu.chl.Game.model.gameobject.tile.*;

public class CollisionDetection {

	private UnitState unitState;
	private EntityState entityState;
	private EntityProperties entityProp;
	private CalculateBounds calculateBounds;
	private Entity en;

	public CollisionDetection(Entity en) {
		this.unitState = en.getUnitState();
		this.entityProp = en.getEntityProperties();
		this.entityState = en.getEntityState();
		this.en = en;
	}

	public void checkForCollision() {
		iterateThroughTileList();
	}

	public void iterateThroughTileList() {
		for (Tile t : en.getHandler().getTileList()) {
			checkIfSolid(t);
		}

	}

	public void checkIfSolid(Tile t) {
		if (t.getUnitState().isSolid()) {
			checkIfWall(t);
		}
	}

	public void checkIfWall(Tile t) {
		if (t.getUnitState().getId() == Id.wall) {
			checkCollition(t);
		}
	}

	public void checkCollition(Tile t) {

		checkCollideWithFlor(t);
		checkCollideWithRightWall(t);
		checkCollideWithLeftWall(t);
		checkCollideWithRoof(t);
	}

	public void checkCollideWithFlor(Tile t) {
		if (t.getTileState() == TileState.wall
				|| t.getTileState() == TileState.floor) {
			if (calculateBounds.getBoundsBottom().intersects(t.getBounds())) {
				en.setY(t.getY() - en.getHeight());
				en.setVelY(0);
				if (considerId()) {
					entityState.setFalling(false);
				}
				entityState.setContactWithGround(true);
			}
		}
	}


	public void checkCollideWithRoof(Tile t) {
		if (calculateBounds.getBoundsTop().intersects(t.getBounds())) {
			en.setVelY(0);
			if (entityState.isJumping()) {
				entityState.setJumping(false);
				entityProp.setGravity(0.8);
				entityState.setFalling(true);
			}
		}
	}

	public void checkCollideWithRightWall(Tile t) {
		if (calculateBounds.getBoundsRight().intersects(t.getBounds())) {
			en.setVelX(0);
			en.setX((t.getX() - en.getWidth()));
		}
	}

	public void checkCollideWithLeftWall(Tile t) {
		if (calculateBounds.getBoundsLeft().intersects(t.getBounds())) {
			en.setVelX(0);
			en.setX((t.getX() + t.getWidth()));
		}
	}

	public boolean considerId() {
		return(en.getId() == Id.player);
	}
}
