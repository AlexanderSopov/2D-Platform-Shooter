package edu.chl.Game.view.graphics;

import java.awt.Graphics;
import edu.chl.Game.model.gameobject.entity.FacingDirection;
import edu.chl.Game.model.gameobject.entity.enemy.*;

public class UnitGraphicsRender {

	public void renderGraphics(EnemyUnit en, Graphics g) {
		chooseRenderStyle(en, g);
	}

	public void chooseRenderStyle(EnemyUnit en, Graphics g) {
		if (en.isAttacking()) {
			renderAttacking(en, g);
		} else {
			if (en.getUnitState().isAnimate()) {
				renderMoving(en, g);
			} else {
				renderStandingStill(en, g);
			}
		}
	}

	public void renderMoving(EnemyUnit en, Graphics g) {
		if (en.getEntityState().getFacingDirection() == FacingDirection.FacingRight) {
			en.getRenderClass().renderAnimateRight(g,
					en.getArrayMovingAnimation(),
					en.getFrameIterator_moving().getFrame(), en.getX(),
					en.getY(), en.getWidth(), en.getHeight());
		} else if (en.getEntityState().getFacingDirection() == FacingDirection.FacingLeft) {
			en.getRenderClass().renderAnimateLeft(g,
					en.getArrayMovingAnimation(),
					en.getFrameIterator_moving().getFrame(), en.getX(),
					en.getY(), en.getWidth(), en.getHeight(), 8);
		}
	}

	public void renderStandingStill(EnemyUnit en, Graphics g) {
		if (en.getEntityState().getFacingDirection() == FacingDirection.FacingRight) {
			en.getRenderClass().renderNotAnimateRight(g,
					en.getArrayMovingAnimation(), 0, en.getX(), en.getY(),
					en.getWidth(), en.getHeight());
		} else if (en.getEntityState().getFacingDirection() == FacingDirection.FacingLeft) {
			en.getRenderClass().renderNotAnimateLeft(g,
					en.getArrayMovingAnimation(), 10, en.getX(), en.getY(),
					en.getWidth(), en.getHeight(), 8);
		}
	}

	public void renderAttacking(EnemyUnit en, Graphics g) {
		if (en.getEntityState().getFacingDirection() == FacingDirection.FacingRight) {
			en.getRenderClass().renderAnimateRight(g,
					en.getArrayAttackAnimation(),
					en.getFrameIterator_attack().getFrame(), en.getX(),
					en.getAdjustedY(), en.getAltWidth(), en.getAltHeight());

		} else if (en.getEntityState().getFacingDirection() == FacingDirection.FacingLeft) {
			en.getRenderClass().renderAnimateLeft(g,
					en.getArrayAttackAnimation(),
					en.getFrameIterator_attack().getFrame(), en.getAdjustedX(),
					en.getAdjustedY(), en.getAltWidth(), en.getAltHeight(), 10);
		}
	}

}
