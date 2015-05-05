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
    
    double targetPosX;
	double targetPosY;
	private int speed;
    private float angle;
    private double firstAngle;
    private boolean passedTarget = false;
    double length;
    double xVelocity, yVelocity;
    double nx,ny;
    

    public Bullet(double d, double e, int width, int height, boolean solid, Id id,
			GameHandler handler,double f, double g, int speed, double angle) {
        super(d, e, width, height, solid, id, handler);
        
                
		this.setTargetPosX(f);
                
		this.setTargetPosY(g);
		this.speed = 25;
               
                ny = g-e;
                nx = f-d;
                 
                length = (float)Math.sqrt((nx * nx) + (ny* ny));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
	g.fillOval((int)(getX()-(getWidth()/2)),(int)(getY()-(getHeight()/2)), (int)getWidth(), (int)getHeight());
        //g.setColor(Color.GREEN);
       // g.fillOval(this.targetPosX-(width/2), this.targetPosY-(height/2), width, height);
        //g.drawString(""+ angle, x, y);
	
    }

    @Override
    public void update() {
   
        xVelocity = nx / length * speed;
        yVelocity = ny / length * speed;

	setX(getX() + xVelocity);
        setY(getY() + yVelocity);

	
    }
    
        public void moveToTarget(){
		
	}
    
    	public double getTargetPosX() {
		return targetPosX;
	}

	private void setTargetPosX(double f) {
		this.targetPosX = f;
	}

	public double getTargetPosY() {
		return targetPosY;
	}

	private void setTargetPosY(double g) {
		this.targetPosY = g;
	}

	public int getSpeed() {
		return speed;
	}
        
}
