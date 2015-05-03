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
    private float angle;
    private double firstAngle;
    private boolean passedTarget = false;
    float length;
    float xVelocity, yVelocity;
    float nx,ny;
    

    public Bullet(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler,int targetPosX, int targetPosY, int speed, double angle) {
        super(x, y, width, height, solid, id, handler);
        
                
		this.setTargetPosX(targetPosX);
                
		this.setTargetPosY(targetPosY);
		this.speed = 25;
               
                ny = targetPosY-y;
                nx = targetPosX-x;
                 
                length = (float)Math.sqrt((nx * nx) + (ny* ny));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
	g.fillOval(x-(width/2), y-(height/2), width, height);
        //g.setColor(Color.GREEN);
       // g.fillOval(this.targetPosX-(width/2), this.targetPosY-(height/2), width, height);
        //g.drawString(""+ angle, x, y);
	
    }

    @Override
    public void update() {
   
        xVelocity = nx / length * speed;
        yVelocity = ny / length * speed;

	x += xVelocity;
        y += yVelocity;

	
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
