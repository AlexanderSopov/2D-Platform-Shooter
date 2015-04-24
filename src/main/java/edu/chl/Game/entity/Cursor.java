package edu.chl.Game.entity;

import java.awt.Color;
import java.awt.Graphics;


import edu.chl.Game.object.*;
import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.handler.MouseInput;
import edu.chl.Game.view.Frame;
import java.awt.MouseInfo;
import java.util.LinkedList;



public class Cursor extends Entity{
	
	
	private State state;
        private Entity en;
        private int counter;
        
        private LinkedList<Bullet> bulletList = new LinkedList<Bullet>();

  
	
	
	
	private enum State{
		AIM,
		DEFULT,
		RELODING;	
	}
	
	
	
	public Cursor(Entity en,GameHandler handler) {
		
		super(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y, 0, 0, false, Id.cursor , handler);
		this.state = State.AIM;
                this.en = en;
                this.counter = 0;
	}

	@Override
	public void render(Graphics g) {
		switch(this.state){
			case AIM:
                       
                                
                                
                                g.setColor(Color.red);
                                g.drawOval(x-15, y-15, 30, 30);
                                g.fillRect(x, y, 1, 1);
                                g.drawLine(x, y, en.getX()+32, en.getY()+32);
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
                
                for(Bullet b: getBulletList()){
			b.render(g);
		}
	}

	@Override
	public void update() {	
                        
                        
			x = MouseInput.getMousePosX()-(-en.getX() + Frame.WIDTH/2);
			y = MouseInput.getMousePosY()-(-en.getY() + Frame.HEIGHT/2 + 100);
                        /*
                        if(MouseInput.isPressed()){
                            this.counter++; 
                           
                            if(this.counter >= 60){
                                System.out.println("Ready");
                                Bullet b = new Bullet(en.getX()+32,en.getY()+32,2,2,true, Id.bullet, handler, getX(), getY(), 10);
                                handler.addEntity(b);
                                this.counter = 0;
                            }
                            
                        }*/
                        
                        for(Bullet b: getBulletList()){
                            b.update();
                           /*
                            if(b.getX()<=0 || b.getY()<= 0 || b.getX() > Frame.WIDTH -(-en.getX() + Frame.WIDTH/2) || b.getY() > Frame.HEIGHT-(-en.getY() + Frame.HEIGHT/2 + 100)){
                                removeBullet(b);
			
                            }*/
                        }
                      
	}
        
        public void shoot(){
            Bullet b = new Bullet(en.getX()+32,en.getY()+32,10,10,true, Id.bullet, handler, getX(), getY(), 3);
            addBullet(b);
            
            //System.out.println("shoot"+ handler.getEntityList().size());
        }
        
        
        public void addBullet(Bullet b){
		bulletList.add(b);
	}
	
	public void removeBullet(Bullet b){
		bulletList.remove(b);
	}
        
        public LinkedList<Bullet> getBulletList(){
		return bulletList;
	}
	
}
