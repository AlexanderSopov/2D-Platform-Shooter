/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.Game.entity;

import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.object.Id;
import edu.chl.Game.view.Frame;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Rasmus
 */
public class Bullet extends Entity{
    
    private int targetPosX,targetPosY, speed;
    private double angle;
    

    public Bullet(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler,int targetPosX, int targetPosY, int speed) {
        super(x, y, width, height, solid, id, handler);
        

		this.setTargetPosX(targetPosX);
		this.setTargetPosY(targetPosY);
		this.speed = speed;
		angle = Math.toDegrees(Math.atan2(targetPosY-y, targetPosX-x));
		/*
		if(angle<0){
			angle += 360; 
		}*/
                System.out.println("a:"+ angle);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
	g.fillRect(x-(width/2), y-(height/2), width, height);
		
    }

    @Override
    public void update() {
       
       x += (speed*Math.cos(angle*(Math.PI/180.0)));
       y += (speed*Math.sin(angle*(Math.PI/180.0)));
		
		if(x<=0 || y<= 0 || x> Frame.WIDTH || y > Frame.HEIGHT){
			//this.remove();
			
		}
		/*
		for(Entity en: Main.handler.entityList){
			if(!en.isSolid())break;
			if(en.getId() == Id.dumb){
				if(this.getBounds().intersects(en.getBounds())){
					
					en.setDamage(10);
					
				}
				
			}
		}*/
    }
    
        public void moveToTarget(){
		
	}
    
    	public int getTargetPosX() {
		return targetPosX;
	}

	private void setTargetPosX(int targetPosX) {
		this.targetPosX = targetPosX;
	}

	public int getTargetPosY() {
		return targetPosY;
	}

	private void setTargetPosY(int targetPosY) {
		this.targetPosY = targetPosY;
	}

	public int getSpeed() {
		return speed;
	}
    
}
