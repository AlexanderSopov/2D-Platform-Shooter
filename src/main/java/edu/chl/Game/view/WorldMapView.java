package edu.chl.Game.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.chl.Game.view.graphics.WorldMapAnimator;

/**
 * Temporary class for the map
 * @author Martin Tran
 * @version 1.0
 */

public class WorldMapView {
	
	//Contains the levels
	public Rectangle[] mapLevels = new Rectangle[5];
	
	//Shop and Character Overview buttons
	public Rectangle shop = new Rectangle(Frame.WIDTH - 100, Frame.HEIGHT-60, 70, 50);
	public Rectangle character = new Rectangle(Frame.WIDTH - 170, Frame.HEIGHT-60, 70, 50);
	
	private Font fnt;
	private Image background;
	private WorldMapAnimator movingChar;
	private WorldMapAnimator buildings;
	private int pos = 0;
	private int prevPos = 0;
	private int velX = 0;
	private int velY = 0;
	private boolean isMoving;
	
	public WorldMapView(){
		movingChar = new WorldMapAnimator("character");
		buildings = new WorldMapAnimator("buildings");
		isMoving = false;
		
		try {
			background = ImageIO.read(getClass().getResource("/worldMap/background.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mapLevels[0] = new Rectangle(Frame.WIDTH / 8, Frame.HEIGHT/4, 70, 60);
		mapLevels[1] = new Rectangle(Frame.WIDTH / 4, Frame.HEIGHT/2, 70, 60);
		mapLevels[2] = new Rectangle(Frame.WIDTH / 3+50 + 80, Frame.HEIGHT/4 + 140, 70, 60);
		mapLevels[3] = new Rectangle(Frame.WIDTH / 2 + 150, Frame.HEIGHT/4, 70, 60);
		mapLevels[4] = new Rectangle(Frame.WIDTH / 2 + 250, Frame.HEIGHT/2, 70, 60);
	}
	
	public void render(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		
		//Set background
		g.drawImage(background, 0, 0, Frame.WIDTH, Frame.HEIGHT, null);
		
		//Set title;
		fnt = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("WorldMap", Frame.WIDTH/3 + 50, Frame.HEIGHT/7);
		
		//Set the rectangles
		fnt = new Font("arial", Font.BOLD, 20);
		g.setFont(fnt);
		
		//Draws the rects and arrows that connects the rects
		drawObjects(g2, mapLevels[0], "Level 1", "Stage", 0);
		drawObjects(g2, mapLevels[1], "Level 2", "Stage", 1);
		drawObjects(g2, mapLevels[2], "Level 3", "Stage", 2);
		drawObjects(g2, mapLevels[3], "Level 4", "Stage", 3);
		drawObjects(g2, mapLevels[4], "Level 5", "Stage", 0);
		drawObjects(g2, shop, "Shop", "Button", 0);
		drawObjects(g2, character, "Char", "Button", 0);	
		
		drawArrow(g2, mapLevels[0], mapLevels[1], "DownToUp");
		drawArrow(g2, mapLevels[1], mapLevels[2], "SideToSide");
		drawArrow(g2, mapLevels[2], mapLevels[3], "UpToDown");
		drawArrow(g2, mapLevels[3], mapLevels[4], "DownToUp");
		
		//Render och moves the character
		if(isMoving){
			moveCharacter(g);
		}else if(!isMoving){
			if(!(pos - prevPos > 1 || pos - prevPos < -1)){
				prevPos = pos;
			}else{
				pos = prevPos;
			}
			movingChar.renderCharacter(g, (int)mapLevels[pos].getCenterX()-15, (int)mapLevels[pos].getCenterY(), 32, 32);
		}
	}//end render
	
	private void drawObjects(Graphics2D g, Rectangle r, String Name, String obj, int type){
		if(obj.equals("Stage")){
			g.draw(r);
			g.drawString(Name, r.x, r.y);
			buildings.renderBuilding(g, r.x, r.y, r.width, r.height, type);
		}else if(obj.equals("Button")){
			g.draw(r);
			g.drawString(Name, r.x+10, r.y+30);
		}
	}
	
    private void drawArrow(Graphics2D g, Rectangle r1, Rectangle r2, String type) {
    	if(type.equals("DownToUp")){
    		g.drawLine(r1.x+r1.width/2, r1.y+r1.height, r2.x+r2.width/2, r2.y);
    	}else if(type.equals("UpToDown")){
    		g.drawLine(r1.x+r1.width/2, r1.y, r2.x+r2.width/2, r2.y+r2.height);
    	}else if(type.equals("SideToSide")){
    		g.drawLine(r1.x+r1.width, r1.y+r1.height/2, r2.x, r2.y+r2.height/2);
    	}
    }
    
    private void moveCharacter(Graphics g){
    	int tempX = 0;
		int tempY = 0;
    	if((pos - prevPos) == 1){
			tempX = (int)mapLevels[prevPos].getCenterX()-15 + velX;
			tempY = (int)mapLevels[prevPos].getCenterY() + velY;
			
	    	if(tempX < (int)mapLevels[pos].getCenterX()-15 && tempY < (int)mapLevels[pos].getCenterY()){
	    		//Moves from left to right, top to bot
	    		velX+= (mapLevels[pos].getCenterX() - mapLevels[prevPos].getCenterX())/40;
	    		velY+= (mapLevels[pos].getCenterY() - mapLevels[prevPos].getCenterY())/40;
	    	}else if(tempX < (int)mapLevels[pos].getCenterX()-15 && tempY > (int)mapLevels[pos].getCenterY()){
	    		//Moves from left to right, bot to top
	    		velX+= (mapLevels[pos].getCenterX() - mapLevels[prevPos].getCenterX())/40;
	    		velY-= (mapLevels[prevPos].getCenterY() - mapLevels[pos].getCenterY())/40;
	    	}else{
	    		velX = 0;
	    		velY = 0;
	    	}
	    	movingChar.renderCharacter(g, tempX, tempY, 32, 32);
	    	
    	}else if(pos - prevPos == -1){
    		tempX = (int)mapLevels[prevPos].getCenterX()-15 + velX;
			tempY = (int)mapLevels[prevPos].getCenterY() + velY;
			
	    	if(tempX > (int)mapLevels[pos].getCenterX()-15 && tempY < (int)mapLevels[pos].getCenterY()){
	    		//Moves from right to left, top to bot
	    		velX-= (mapLevels[prevPos].getCenterX() - mapLevels[pos].getCenterX())/40;
	    		velY-= (mapLevels[prevPos].getCenterY() - mapLevels[pos].getCenterY())/40;
	    	}else if(tempX > (int)mapLevels[pos].getCenterX()-15 && tempY > (int)mapLevels[pos].getCenterY()){
	    		//Moves from right to left, bot to top
	    		velX-= (mapLevels[prevPos].getCenterX() - mapLevels[pos].getCenterX())/40;
	    		velY+= (mapLevels[pos].getCenterY() - mapLevels[prevPos].getCenterY())/40;
	    	}else{
	    		velX = 0;
	    		velY = 0;
	    	}
	    	movingChar.renderCharacter(g, tempX-10, tempY, 32, 32);
    	}
		
		if(velX == 0 && velY == 0){
			isMoving = false;
		}

    }
    
    public void setPos(int i){
    	if(i < mapLevels.length)
    		pos = i;
    	else
    		System.out.println("Out of boundry");
    }
    
    public int getPos(){
    	return pos;
    }
    
    public void setIsMoving(){
    	isMoving = true;
    }
}
