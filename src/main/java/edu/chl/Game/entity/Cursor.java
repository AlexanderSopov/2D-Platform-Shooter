package edu.chl.Game.entity;

import java.awt.Color;
import java.awt.Graphics;


import edu.chl.Game.object.*;
import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.handler.MouseInput;
import java.awt.MouseInfo;



public class Cursor extends Entity{
	
	
	private State state;
	
	
	
	private enum State{
		AIM,
		DEFULT,
		RELODING;	
	}
	
	
	
	public Cursor(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler) {
		
		super(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y, width, height, solid, id, handler);
		
	
	}

	@Override
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

	@Override
	public void update() {	
		
			x = MouseInput.getMousePosX();
			y = MouseInput.getMousePosY();
			
	}
	
}
