package edu.chl.Game.handler;

import edu.chl.Game.Physics.Jump;
import edu.chl.Game.Vector.Vector2D;
import edu.chl.Game.entity.FacingDirection;
import edu.chl.Game.entity.UnitProperties;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import edu.chl.Game.entity.Entity;
import edu.chl.Game.object.Id;
import edu.chl.Game.view.GameThread;

public class KeyInput implements KeyListener {

	private GameHandler handler;
	private boolean isWPressed;
	private Jump jump;
	private Entity player;

	public KeyInput(GameHandler handler, Entity p) {
		this.handler = handler;
		player = p;
		isWPressed = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (GameThread.state == State.GAME) {
			Entity en = player;
					switch (key) {
					case KeyEvent.VK_W:
					case 32:
						if(!isWPressed){
							isWPressed = true;
							jump = new Jump(player, this);
							jump.start();
						}
						break;
					case KeyEvent.VK_A:
						en.getUnitProperties().setVelocity(new Vector2D(-5,
								en.getUnitProperties().getVelocity().getY()));
						en.getEntityState().setFacingDirection(
								FacingDirection.FacingLeft);
						break;
					case KeyEvent.VK_D:
						en.getUnitProperties().setVelocity(new Vector2D(5,
								en.getUnitProperties().getVelocity().getY()));
						en.getEntityState().setFacingDirection(
								FacingDirection.FacingRight);
						break;

					}
		}
	}



	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (GameThread.state == State.GAME) {
			for (Entity en : handler.getEntityList()) {
				if (en.getUnitState().getId() == Id.player) {
					switch (key) {
					case KeyEvent.VK_W:
					case 32:
						isWPressed = false;
						System.out.println("W RELEASED");
						break;
					case KeyEvent.VK_A:
						en.getUnitProperties().setVelocity(
								new Vector2D(0, 
										en.getUnitProperties().getVelocity().getY()));
						break;
					case KeyEvent.VK_D:
						en.getUnitProperties().setVelocity(
								new Vector2D(0, 
										en.getUnitProperties().getVelocity().getY()));
						break;
					}
				}
			}
		}
	}
	
	public boolean isWPressed(){
		return isWPressed;
	}

}
