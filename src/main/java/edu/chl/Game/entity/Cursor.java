package edu.chl.Game.entity;

import java.awt.Color;
import java.awt.Graphics;


import edu.chl.Game.object.*;
import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.handler.MouseInput;
import edu.chl.Game.view.Frame;
import java.awt.MouseInfo;



public class Cursor extends Entity{
	
	
	private State state;
        private Entity en;
	
	
	
	private enum State{
		AIM,
		DEFULT,
		RELODING;	
	}
	
	
	
	public Cursor(Entity en,GameHandler handler) {
		
		super(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y, 0, 0, false, Id.cursor , handler);
		this.state = State.AIM;
                this.en = en;
	}

	@Override
	public void render(Graphics g) {
		switch(this.state){
			case AIM:
                       
                                
                                
                                g.setColor(Color.red);
                                g.drawOval(x-15, y-15, 30, 30);
                                g.fillRect(x, y, 1, 1);
				/*
                                g.fillRect(x-1, y-1, 2, 2);
				int relX = x-1;
				int relY = y-1;
				g.drawLine(relX, relY+4, relX, relY+8);
				g.drawLine(relX+4, relY, relX+8, relY);
				g.drawLine(relX, relY-4, relX, relY-8);
				g.drawLine(relX-4, relY, relX-8, relY);*/
				break;
			
		
		}
	}

	@Override
	public void update() {	
		
			x = MouseInput.getMousePosX()-(-en.getX() + Frame.WIDTH/2);
			y = MouseInput.getMousePosY()-(-en.getY() + Frame.HEIGHT/2 + 100);
                        
                      
	}
	
}
