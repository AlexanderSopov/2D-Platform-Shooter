package edu.chl.Game.view.graphics;

import java.awt.Graphics;

import edu.chl.Game.model.gameobject.entity.*;
import edu.chl.Game.model.gameobject.entity.enemy.*;

public class UnitGraphicsRender {

	public void renderGraphics(EnemyUnit en, Graphics g) {
		chooseRenderStyle(en, g);
	}

	public void chooseRenderStyle(EnemyUnit en, Graphics g) {
		if (en.isAttacking()) {
			renderAttacking(en, g, (en.getUnitMeasurement().getNumberOfSprite_attack()/2));
		} else {
			if (en.getUnitState().isAnimate()) {
				renderMoving(en, g, (en.getUnitMeasurement().getNumberOfSprite_move()/2));
			} else {
				renderStandingStill(en, g, (en.getUnitMeasurement().getNumberOfSprite_move()/2));
			}
		}
	}

	public void renderMoving(EnemyUnit en, Graphics g, int changeRow) {
		if (en.getEntityState().getFacingDirection() == FacingDirection.FacingRight) {
			try {
				en.getRenderClass().renderAnimateRight(g, en.getArrayMovingAnimation(), en.getFrameIterator_moving().getFrame(), en.getX(),en.getY(), en.getWidth(), en.getHeight());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (en.getEntityState().getFacingDirection() == FacingDirection.FacingLeft) {
			try {
				en.getRenderClass().renderAnimateLeft(g, en.getArrayMovingAnimation(), en.getFrameIterator_moving().getFrame(), en.getX(), en.getY(), en.getWidth(), en.getHeight(), changeRow);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void renderStandingStill(EnemyUnit en, Graphics g , int changeRow) {
		if (en.getEntityState().getFacingDirection() == FacingDirection.FacingRight) {
			try {
				en.getRenderClass().renderNotAnimateRight(g,
						en.getArrayMovingAnimation(), en.getX(), en.getY(),
						en.getWidth(), en.getHeight());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (en.getEntityState().getFacingDirection() == FacingDirection.FacingLeft) {
			try {
				en.getRenderClass().renderNotAnimateLeft(g,
						en.getArrayMovingAnimation(), en.getX(), en.getY(),
						en.getWidth(), en.getHeight(), changeRow);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void renderAttacking(EnemyUnit en, Graphics g, int changeRow) {
		if (en.getEntityState().getFacingDirection() == FacingDirection.FacingRight) {
			try {
				en.getRenderClass().renderAnimateRight(g, en.getArrayAttackAnimation(), en.getFrameIterator_attack().getFrame(), en.getAdjustedX_right(), en.getAdjustedY(), en.getAltWidth(), en.getAltHeight());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (en.getEntityState().getFacingDirection() == FacingDirection.FacingLeft) {
			try {
				en.getRenderClass().renderAnimateLeft(g, en.getArrayAttackAnimation(), en.getFrameIterator_attack().getFrame(), en.getAdjustedX_left(), en.getAdjustedY(), en.getAltWidth(), en.getAltHeight(), changeRow);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
