package edu.chl.Game.entity;

import java.awt.Color;
import java.awt.Graphics;


import edu.chl.Game.object.*;
import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.handler.MouseInput;



public class Cursor extends Entity{
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private int velX, velY;
	
	private Id id;
	
	private State state;
	
	private GameHandler handler;
	
	private enum State{
		AIM,
		DEFULT,
		RELODING;	
	}
	
	
	
	public Cursor(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler) {
		
		//super(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y, width, height, solid, id, handler);
		
	
	}

	
	public void render(Graphics g) {
		switch(this.state){
			case AIM:
				g.setColor(Color.red);
				g.fillRect(x-1, y-1, 2, 2);
				int relX = x-1;
				int relY = y-1;
				g.drawLine(relX, relY+4, relX, relY+8);
				g.drawLine(relX+4, relY, relX+8, relY);
				g.drawLine(relX, relY-4, relX, relY-8);
				g.drawLine(relX-4, relY, relX-8, relY);
				break;
			
		
		}
	}

	
	public void update() {	
		
			x = MouseInput.getMousePosX();
			y = MouseInput.getMousePosY();
			
	}
	
}
