package edu.chl.Game.handler;

import edu.chl.Game.entity.FacingDirection;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import edu.chl.Game.entity.Entity;
import edu.chl.Game.object.Id;
import edu.chl.Game.view.GameThread;

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
		if(GameThread.state == State.GAME){
			for (Entity en : handler.getEntityList()) {
				if (en.getUnitState().getId() == Id.player) {
					switch (key) {
					case KeyEvent.VK_W:
					case 32:
						if (!en.isJumping()) {
							en.setJumping(true);
							en.setGravity(8.0);
						}
						break;
					case KeyEvent.VK_A:
						en.getUnitProperties().setVelX(-5);
						// en.facing = 1;
						en.setFacing(1);
						en.setFacingDirection(FacingDirection.FacingLeft);
						break;
					case KeyEvent.VK_D:
						en.getUnitProperties().setVelX(5);
						// en.facing = 0;
						en.setFacing(0);
						en.setFacingDirection(FacingDirection.FacingRight);
						break;
					}
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		for (Entity en : handler.getEntityList()) {
			if (en.getUnitState().getId() == Id.player) {
				en.getUnitProperties().setVelY(0);
				en.getUnitProperties().setVelX(0);
			}
		}
	}

}
