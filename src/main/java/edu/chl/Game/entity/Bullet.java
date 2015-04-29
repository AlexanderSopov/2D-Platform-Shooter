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
    private double firstAngle;
    private boolean passedTarget = false;
    

    public Bullet(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler,int targetPosX, int targetPosY, int speed) {
        super(x, y, width, height, solid, id, handler);
        

		this.setTargetPosX(targetPosX);
		this.setTargetPosY(targetPosY);
		this.speed = speed;
                System.out.println("");
                 firstAngle = Math.toDegrees(Math.atan2(targetPosY-y, targetPosX-x));
		
		
                
		
                //System.out.println("a:"+ angle);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
	g.fillOval(x-(width/2), y-(height/2), width, height);
        //g.drawString(""+ angle, x, y);
		
    }

    @Override
    public void update() {
       
 
        
       if(!this.passedTarget){
        angle = Math.toDegrees(Math.atan2(targetPosY-y, targetPosX-x));
       }	
       
        System.out.println(angle);
        
        double length = Math.sqrt((targetPosX - x)*(targetPosX - x) + (targetPosY - y)*(targetPosY - y)); //calculates the distance between the two points

        double speed_X = (targetPosX - x) /length * speed;

        double speed_Y = (targetPosY - y) /length * speed;
        
        x += speed_X;
        y += speed_Y;
		
       /* 
       x += (float)(Math.cos(Math.toRadians(angle)))*speed;
       y += (float)(Math.sin(Math.toRadians(angle)))*speed;
	*/	
		if(x<=0 || y<= 0 || x> Frame.WIDTH || y > Frame.HEIGHT){
			//this.remove();
			
		}
	
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
