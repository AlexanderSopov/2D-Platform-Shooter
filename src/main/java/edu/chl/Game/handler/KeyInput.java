package edu.chl.Game.handler;

import edu.chl.Game.entity.FacingDirection;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import edu.chl.Game.entity.Entity;
import edu.chl.Game.object.Id;

public class KeyInput implements KeyListener{
	
	private GameHandler handler;
	
	public KeyInput(GameHandler handler){
		this.handler = handler;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for(Entity en: handler.getEntityList()){
			if(en.getId() == Id.player){
				switch(key){
				case KeyEvent.VK_W: case 32:
					if(!en.jumping){
						en.jumping = true;
						en.gravity = 8.0;
					}
					break;
				case KeyEvent.VK_A:
					en.setVelX(-5);
					en.facing = 1;
					en.setFacingDirection(FacingDirection.FacingLeft);
					break;
				case KeyEvent.VK_D:
					en.setVelX(5);
					en.facing = 0;
					en.setFacingDirection(FacingDirection.FacingRight);
					break;
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		for(Entity en: handler.getEntityList()){
			if(en.getId() == Id.player){
				en.setVelY(0);
				en.setVelX(0);
			}
		}
	}

}
