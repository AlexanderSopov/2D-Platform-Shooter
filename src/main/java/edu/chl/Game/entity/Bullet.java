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
                //System.out.println("");
                ny = targetPosY-y;
                nx = targetPosX-x;
                 //firstAngle = Math.toDegrees(Math.atan2(ny, nx));
                length = (float)Math.sqrt((nx * nx) + (ny* ny));
                 //this.angle = firstAngle;
                 
                
                 System.out.println(""+ angle);
		/*
		 if(!this.passedTarget){
                    angle = Math.toDegrees(Math.atan2(targetPosY-y, targetPosX-x));
                    
//passedTarget = true;
                 }   	*/
       
                
		
                //System.out.println("a:"+ angle);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
	g.fillOval(x-(width/2), y-(height/2), width, height);
        g.setColor(Color.GREEN);
        g.fillOval(this.targetPosX-(width/2), this.targetPosY-(height/2), width, height);
        g.drawString(""+ angle, x, y);
        g.setColor(Color.red);
        //g.drawLine(x, y, this.targetPosX, this.targetPosY);
		
    }

    @Override
    public void update() {
        //angle = (float) Math.toDegrees(Math.atan2(targetPosY-y, targetPosX-x));
        //System.out.println(angle);
        xVelocity = nx / length * speed;
        yVelocity = ny / length * speed;
       //x += (float)(Math.cos(Math.toRadians(angle))*speed);
       //y += (float)(Math.sin(Math.toRadians(angle))*speed);
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
        
      public static vec2 point(vec2 pivot, vec2 point, float rotation) {
      
      float rot = (float)(1f / 180 * rotation * Math.PI);
      
      float x = point.x - pivot.x;
      float y = point.y - pivot.y;
      
      float newx = (float)(x * Math.cos(rot) - y * Math.sin(rot));
      float newy = (float)(x * Math.sin(rot) + y * Math.cos(rot));
      
      
      newx += pivot.x;
      newy += pivot.y;
      
      return new vec2(newx, newy);
   }

   public static int angle(vec2 pivot, vec2 point) {

      float xdiff = pivot.x - point.x;
      float ydiff = pivot.y - point.y;
      
      float angle = (float) ((Math.atan2(xdiff, ydiff)) * 180 / Math.PI);
      
      return -(int)angle;
   }
    
}
