package edu.chl.Game.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.chl.Game.controller.State;
import edu.chl.Game.view.graphics.WorldMapAnimator;

/**
 * Temporary class for the map
 * @author Martin Tran
 * @version 1.0
 */

public class WorldMapView {
	
	public static State mapState;
	
	private final int BUILDING_WIDTH = 70;
	private final int BUILDING_HEIGHT = 70;
	private final int MENU_SIZE = 500;
	
	//Contains the levels
	public Rectangle[] mapLevels = new Rectangle[5];
	
	//Shop & Character Overview buttons & UI
	public Rectangle shopButton = new Rectangle(Frame.WIDTH - 100, Frame.HEIGHT-60, 70, 50);
	public Rectangle characterButton = new Rectangle(Frame.WIDTH - 170, Frame.HEIGHT-60, 70, 50);
	public Rectangle menuUI = new Rectangle(Frame.WIDTH/2 - MENU_SIZE/2, Frame.HEIGHT/2 - MENU_SIZE/2, MENU_SIZE, MENU_SIZE);
	public Rectangle menuCloseButton = new Rectangle(menuUI.x, menuUI.y, 20, 20);
	
	private Font fntBig;
	private Font fntSmall;
	private Image background;
	private Image shopButtonImg;
	private Image charButtonImg;
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
		
		fntBig = new Font("arial", Font.BOLD, 50);
		fntSmall = new Font("arial", Font.ITALIC, 20);
		
		try {
			background = ImageIO.read(getClass().getResource("/worldMap/background.jpg"));
			shopButtonImg = ImageIO.read(getClass().getResource("/worldMap/shopButton.png"));
			charButtonImg = ImageIO.read(getClass().getResource("/worldMap/charButton.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mapLevels[0] = new Rectangle(Frame.WIDTH / 8, Frame.HEIGHT/4, BUILDING_WIDTH, BUILDING_HEIGHT);
		mapLevels[1] = new Rectangle(Frame.WIDTH / 4, Frame.HEIGHT/2, BUILDING_WIDTH, BUILDING_HEIGHT);
		mapLevels[2] = new Rectangle(Frame.WIDTH / 3+50 + 80, Frame.HEIGHT/4 + 140, BUILDING_WIDTH, BUILDING_HEIGHT);
		mapLevels[3] = new Rectangle(Frame.WIDTH / 2 + 150, Frame.HEIGHT/4, BUILDING_WIDTH, BUILDING_HEIGHT);
		mapLevels[4] = new Rectangle(Frame.WIDTH / 2 + 250, Frame.HEIGHT/2, BUILDING_WIDTH, BUILDING_HEIGHT);
	}
	
	public void render(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		
		//Set background
		g.drawImage(background, 0, 0, Frame.WIDTH, Frame.HEIGHT, null);
		
		//Set title;
		g.setFont(fntBig);
		g.setColor(Color.white);
		g.drawString("WorldMap", Frame.WIDTH/3 + 50, Frame.HEIGHT/7);
		
		//Set the image buttons
		g.drawImage(shopButtonImg, shopButton.x, shopButton.y, shopButton.width, shopButton.height, null);
		g.drawImage(charButtonImg, characterButton.x, characterButton.y, characterButton.width, characterButton.height, null);
		g2.draw(shopButton);
		g2.draw(characterButton);
			
		//Draws buildings and thier label
		g.setFont(fntSmall);
		drawBuilding(g2, mapLevels[0], "Level 1", 0);
		drawBuilding(g2, mapLevels[1], "Level 2", 1);
		drawBuilding(g2, mapLevels[2], "Level 3", 2);
		drawBuilding(g2, mapLevels[3], "Level 4", 3);
		drawBuilding(g2, mapLevels[4], "Level 5", 0);	
		
		//Draws a line that connects the buildings
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
		
		if(mapState == State.MAP_SHOP){
			renderShopMenu(g, g2);
		}else if(mapState == State.MAP_CHAR){
			renderCharMenu(g, g2);
		}
	}//end render
	
	private void drawBuilding(Graphics2D g2, Rectangle r, String Name, int type){
			g2.draw(r);
			g2.drawString(Name, r.x, r.y);
			buildings.renderBuilding(g2, r.x, r.y, r.width, r.height, type);
	}
	
    private void drawArrow(Graphics2D g2, Rectangle r1, Rectangle r2, String type) {
    	if(type.equals("DownToUp")){
    		g2.drawLine(r1.x+r1.width/2, r1.y+r1.height, r2.x+r2.width/2, r2.y);
    	}else if(type.equals("UpToDown")){
    		g2.drawLine(r1.x+r1.width/2, r1.y, r2.x+r2.width/2, r2.y+r2.height);
    	}else if(type.equals("SideToSide")){
    		g2.drawLine(r1.x+r1.width, r1.y+r1.height/2, r2.x, r2.y+r2.height/2);
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
    
    private void renderShopMenu(Graphics g, Graphics2D g2){
    	//Draws shopmenu window
    	g.setColor(Color.BLACK);
    	g2.fill(menuUI);
    	g.setColor(Color.RED);
    	g2.fill(menuCloseButton);
    	
    	//Draws title
    	g.setFont(fntBig);
		g.setColor(Color.white);
		g.drawString("Shop", Frame.WIDTH/2 - 50, Frame.HEIGHT/2 - MENU_SIZE/2 + 50);
    }
    
    private void renderCharMenu(Graphics g, Graphics2D g2){
    	//Draws charmenu window
    	g.setColor(Color.BLACK);
    	g2.fill(menuUI);
    	g.setColor(Color.RED);
    	g2.fill(menuCloseButton);
    	
    	//Draws title
    	g.setFont(fntBig);
		g.setColor(Color.white);
		g.drawString("Character", Frame.WIDTH/2 - 110, Frame.HEIGHT/2 - MENU_SIZE/2 + 50); 	
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
