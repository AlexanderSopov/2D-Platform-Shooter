package edu.chl.Game.model.gameobject.item;

import java.awt.Graphics;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.Id;

public class Nothing extends Item{

	public Nothing(int x, int y, int width, int height, Id id,
			GameHandler handeler) {
		super(x, y, width, height, id, handeler);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return "NAN";
	}

	@Override
	public String getPath() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public void equippedRender(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void equippedUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void effect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getArmor() {
		// TODO Auto-generated method stub
		return 0;
	}

}
