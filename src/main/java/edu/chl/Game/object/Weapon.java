package edu.chl.Game.object;

import java.awt.image.BufferedImage;

import edu.chl.Game.handler.GameHandler;

public abstract class Weapon extends Item{

	Weapon(int x, int y, int width, int height, Id id, GameHandler handeler,
			String info, BufferedImage buffImage) {
		super(x, y, width, height, id, handeler, info, buffImage);
		
	}

	@Override
	public abstract void effect();

}
