package edu.chl.Game.model.gameobject.entity.items;

import java.awt.image.BufferedImage;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.Id;

public class W1 extends Item{

	public W1(int x, int y, int width, int height, Id id,  GameHandler handeler) {
		super(x, y, width, height, id, handeler);
		
	}

	@Override
	public void update() {
		//System.out.println("Item Update");
		
	}

	@Override
	public void effect() {
		
		
	}

	@Override
	public double getHealth() {
		
		return 100;
	}

	@Override
	public double getArmor() {
		
		return 100;
	}


	@Override
	public String getInfo() {
		
		return "NAN";
	}


	@Override
	public Type getType() {
		
		return Type.WEAPON;
	}

	@Override
	public void remove() {
		
		
	}

	@Override
	public String getPath() {
		
		return "/we00.png";
	}

}
