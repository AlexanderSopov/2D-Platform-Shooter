package edu.chl.Game.object;

import java.awt.image.BufferedImage;

import edu.chl.Game.handler.GameHandler;

public class WeaponA extends Weapon{
        
        private String name = "WeaponA";
        private String info = "this is an A weapon";
        private BufferedImage buffImage = null;
    
	WeaponA(int x, int y, int width, int height, Id id, GameHandler handeler) {
		super(x, y, width, height, id, handeler,"w", "f", null);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void effect() {
		//
		
	}

}
