package edu.chl.Game.handler;

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
	private double timePressed;
	private long lastTimeSnap;
	private boolean isJumping;
	private static final double ns = 1000000000.0;
	private int jumps = 1;

	public KeyInput(GameHandler handler) {
		this.handler = handler;
		lastTimeSnap = 0;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (GameThread.state == State.GAME) {
			for (Entity en : handler.getEntityList()) {
				if (en.getUnitState().getId() == Id.player) {
					switch (key) {
					case KeyEvent.VK_W:
					case 32:
						if(en.isTouchingGround()){
							jump(en);
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
		}
	}



	private void jump(Entity en) {
		while(timePressed < 1/60){
			long currentTime = System.nanoTime();
			timePressed += ((currentTime - lastTimeSnap) /ns);
			lastTimeSnap = currentTime;
		}
		timePressed = 0;
		addJumpVelocity(en);
	
	}

	private void addJumpVelocity(Entity en) {
		UnitProperties ep = en.getUnitProperties();
		Vector2D v = ep.getVelocity();
		if(v.getY() > -25){
			jumps = ((jumps+1)%9)+1;
			ep.setVelocity(ep.getVelocity().addWith(new Vector2D(0,-18/jumps)));
		}
		else
			en.setTouchesGround(false);
		jumps = 1;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (GameThread.state == State.GAME) {
			for (Entity en : handler.getEntityList()) {
				if (en.getUnitState().getId() == Id.player) {
					switch (key) {
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

}
