package edu.chl.Game.GameObject;


import edu.chl.Game.Vector.Vector2D;

public class StaticBox extends Box {
	
	public StaticBox(int x, int y, int width, int height, double r, int mass) {
		super(x, y, width, height, r, mass);
	}


	
	@Override
	public void update(){
		setLocation(getLocation().addWith(new Vector2D(0,0)));
	}
	
	public Vector2D getVelocity(){
		return new Vector2D(0,0);
	}

}
