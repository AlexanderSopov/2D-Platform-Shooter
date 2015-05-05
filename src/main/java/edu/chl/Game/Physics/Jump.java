package edu.chl.Game.Physics;

import edu.chl.Game.Vector.Vector2D;
import edu.chl.Game.entity.Entity;
import edu.chl.Game.entity.UnitProperties;
import edu.chl.Game.handler.KeyInput;

public class Jump extends Thread implements Runnable {
	private KeyInput keyInput;
	private Entity en;
	int jumps;
	
	public Jump(Entity o, KeyInput k){
		en = o;
		keyInput = k;
	}
	@Override
	public void run(){
		jumps = 1;
		UnitProperties up = en.getUnitProperties();
	
		while(keyInput.isWPressed())
			if(en.isTouchingGround()){
				if(jumps < 4){
					up.setVelocity(up.getVelocity().addWith(
							new Vector2D(0,-7.5/jumps)));
					jumps++;
					try {
						sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					jumps = 1;
					en.setTouchesGround(false);
				}
			}
		System.out.println("W was released");
		}
	
	private void waitMs(int i) {
		long timeSnap1 = System.nanoTime();
		double nanosec = 1000000000.0;
		long timeSnap2 = System.nanoTime();
		double delta = (timeSnap2 - timeSnap1)/nanosec;
		while(delta < i/1000){
			timeSnap1 = timeSnap2;
			timeSnap2 = System.nanoTime();
			delta += (timeSnap2 - timeSnap1 )/nanosec;
		}
		System.out.println("Delta = " + delta);
	}
}
