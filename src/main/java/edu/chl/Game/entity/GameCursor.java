package edu.chl.Game.entity;

import java.awt.Color;
import java.awt.Graphics;


import edu.chl.Game.object.*;
import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.handler.MouseInput;
import edu.chl.Game.view.Frame;
import java.awt.Cursor;
import static java.awt.Cursor.CUSTOM_CURSOR;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.util.LinkedList;



public class GameCursor extends Entity{
	
	
	private State state;
        private Entity en;
        private int counter;
        //private final Cursor defukts; 
        
        private LinkedList<Bullet> bulletList = new LinkedList<Bullet>();

  
	
	
	
	private enum State{
		AIM,
		DEFULT,
		RELODING;	
	}
	
	
	
	public GameCursor(Entity en,GameHandler handler) {
		
		super(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y, 0, 0, false, Id.cursor , handler);
                //this.defukts = Toolkit.getDefaultToolkit().createCustomCursor(null, null, null);
		this.state = State.AIM;
                this.en = en;
                this.counter = 0;
	}

	@Override
	public synchronized void  render(Graphics g) {
		switch(this.state){
			case AIM:
                                
                                g.setColor(Color.red);
                                g.drawOval(x-15, y-15, 30, 30);
                                g.fillRect(x, y, 1, 1);
                               
				break;
			
		
		}
                
               for(Bullet b: getBulletList()){
			b.render(g);
		}
	}

	@Override
	public synchronized void update() {	
                        
                        
			//x = MouseInput.getMousePosX()-(-en.getX() + Frame.WIDTH/2);
			//y = MouseInput.getMousePosY()-(-en.getY() + Frame.HEIGHT/2 + 100);
                        x = MouseInfo.getPointerInfo().getLocation().x-(-en.getX() + Frame.WIDTH/2);
			y = MouseInfo.getPointerInfo().getLocation().y-(-en.getY() + Frame.HEIGHT/2+ 100);
                        
                        for(Bullet b: getBulletList()){
                            b.update();
                            
                        }
                        
                        
                      
	}
        
        public void shoot(){
            Bullet b = new Bullet(en.getX() + (en.width/2) ,en.getY()+ (en.height/2),10,10,true, Id.bullet, handler, getX(), getY(), 12);
            addBullet(b);
            
            //System.out.println("shoot"+ handler.getEntityList().size());
        }
        
        
        public synchronized void addBullet(Bullet b){
		bulletList.add(b);
	}
	
	public synchronized void removeBullet(Bullet b){
		bulletList.remove(b);
	}
        
        public LinkedList<Bullet> getBulletList(){
		return bulletList;
	}
	
        private void CustomCursor(){
            
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Image img = toolkit.getImage("Cursor.png");
            Point point = new Point(0,0);
            Cursor ursor = toolkit.createCustomCursor(img, point, "Cursor");
            
        }
        
}
