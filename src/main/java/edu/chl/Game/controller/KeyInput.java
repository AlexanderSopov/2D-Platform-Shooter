package edu.chl.Game.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.Entity;
import edu.chl.Game.model.gameobject.entity.FacingDirection;

import edu.chl.Game.model.gameobject.entity.items.Inventory;
import edu.chl.Game.view.State;


public class KeyInput implements KeyListener {

	private GameHandler handler;

	public KeyInput(GameHandler handler) {
		this.handler = handler;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (RefreshTimer.state == State.GAME) {
			for (Entity en : handler.getEntityList()) {
				if (en.getUnitState().getId() == Id.player) {
					switch (key) {
					case KeyEvent.VK_W:
					case 32:
						en.isTryingToJump(true);
						break;
					case KeyEvent.VK_A:
						en.setVelX(-5);
						
						en.getEntityState().setFacingDirection(
								FacingDirection.FacingLeft);
						break;
					case KeyEvent.VK_D:
						en.setVelX(5);
						
						en.getEntityState().setFacingDirection(
								FacingDirection.FacingRight);
						break;

					}
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (RefreshTimer.state == State.GAME) {
			for (Entity en : handler.getEntityList()) {
				if (en.getUnitState().getId() == Id.player) {
					switch (e.getKeyCode()) {
					case KeyEvent.VK_W:
					case 32:
						en.isTryingToJump(false);
						en.isBumpingGround(false);
						break;
					case KeyEvent.VK_A:
						en.setVelX(0);
						break;
					case KeyEvent.VK_D:
						en.setVelX(0);
						break;
					}
				}
			}
		}
	}

}
