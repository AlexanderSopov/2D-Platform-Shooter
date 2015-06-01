package edu.chl.Test.PhysicsTest;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import edu.chl.Test.GameObject.Box;
import edu.chl.Test.GameObject.Circle;
import edu.chl.Test.Vector.Vector2D;

public class Controller implements KeyListener {


	public Controller() {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
			case KeyEvent.VK_R:
				reset();
			break;
			case KeyEvent.VK_P:
				heavyMetalBallOfDoomNGlory();
				break;
			case KeyEvent.VK_D:
				eraseMe();
				break;
		}
	}



	private void eraseMe() {
		
	}

	private void heavyMetalBallOfDoomNGlory() {
		Main.c1.setMass(1000);
		resetPosition();
		resetVelocity();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	private void reset() {
		resetMass();
		resetPosition();
		resetVelocity();
	}

	private void resetMass() {
		Main.c1.setMass(75);
		Main.c2.setMass(750);
		Main.c3.setMass(750);
	}

	private void resetVelocity() {
		Main.c1.setVelocity(5.5,-1.25);
		Main.c2.setVelocity(0,0);
		Main.c3.setVelocity(-1.25, -12.5);
	}

	private void resetPosition() {
		Main.c1.setLocation(0,-30);
		Main.c2.setLocation(750,150);
		Main.c3.setLocation(950,1150);
	}

}

