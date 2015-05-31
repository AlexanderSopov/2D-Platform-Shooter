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
 * WorldMap class
 * Render and animates the worldmap. 
 * @author Martin Tran
 * @version 1.0
 */

public class WorldMapView {
	
	//States can be Shop, Character overview and WorldMap(null)
	public static State mapState;
	
	private int buildingWidth = 100;
	private int buildingHeight = 100;
	private int menuSize = 500;
	
	//Submenu on the leftop corner that has a soundcontroll & "Go Back" feature
	private SubMenuView subMenuView;
	
	//Contains the levels
	public Rectangle[] mapLevels = new Rectangle[5];
	
	//Shop & Character Overview buttons & UI
	public Rectangle shopButton = new Rectangle(Frame.WIDTH - 100, Frame.HEIGHT-60, 70, 50);
	public Rectangle characterButton = new Rectangle(Frame.WIDTH - 170, Frame.HEIGHT-60, 70, 50);
	public Rectangle menuUI = new Rectangle(Frame.WIDTH/2 - menuSize/2, Frame.HEIGHT/2 - menuSize/2, menuSize, menuSize);
	public Rectangle menuCloseButton = new Rectangle(menuUI.x, menuUI.y, 20, 20);
	public Rectangle menuGrid = new Rectangle(menuUI.width/6, menuUI.width/6);
	
	//Animators and private variables for the moving Character
	private WorldMapAnimator movingCharRight;
	private WorldMapAnimator movingCharLeft;
	private int pos = 0;
	private int prevPos = 0;
	private int velX = 0;
	private int velY = 0;
	private boolean isMoving = false;
	
	//Animator for buildings
	private WorldMapAnimator[] buildings = new WorldMapAnimator[5];
	
	private Font fntBig;
	private Font fntSmall;
	private Image background;
	private Image shopButtonImg;
	private Image charButtonImg;
	
	/**
	 * Consturctor for WorldMapView. Sets the images.
	 * Creates the buildings as Rectangles.
	 * @param subMenuView
	 */
	public WorldMapView(SubMenuView subMenuView){		
		this.subMenuView = subMenuView;
		
		//Sets the fronts
		fntBig = new Font("arial", Font.BOLD, 50);
		fntSmall = new Font("arial", Font.ITALIC, 20);
		
		//Sets the image background & buttons
		try {
			background = ImageIO.read(getClass().getResource("/worldMap/background2.jpg"));
			shopButtonImg = ImageIO.read(getClass().getResource("/worldMap/shopButton.png"));
			charButtonImg = ImageIO.read(getClass().getResource("/worldMap/charButton.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Creaters each levels as a Rectangle
		mapLevels[0] = new Rectangle(Frame.WIDTH / 8, Frame.HEIGHT/4, buildingWidth, buildingHeight);
		mapLevels[1] = new Rectangle(Frame.WIDTH / 4, Frame.HEIGHT/2, buildingWidth, buildingHeight);
		mapLevels[2] = new Rectangle(Frame.WIDTH / 3+50 + 80, Frame.HEIGHT/4 + 140, buildingWidth, buildingHeight);
		mapLevels[3] = new Rectangle(Frame.WIDTH / 2 + 150, Frame.HEIGHT/4, buildingWidth, buildingHeight);
		mapLevels[4] = new Rectangle(Frame.WIDTH / 2 + 250, Frame.HEIGHT/2, 2*buildingWidth, buildingHeight);
		
		//Sets each levels & character with a animation
		movingCharRight = new WorldMapAnimator("/SH_Player.png", 20, 0, 62, 62, 5);
		movingCharLeft = new WorldMapAnimator("/SH_Player.png", 20, 1, 62, 62, 5);
		buildings[0] = new WorldMapAnimator("/worldMap/buildingSheet1.png", 5, 0, 260, 263, 40);
		buildings[1] = new WorldMapAnimator("/worldMap/buildingSheet2.png", 8, 0, 200, 200, 10);
		buildings[2] = new WorldMapAnimator("/worldMap/buildingSheet3.png", 5, 0, 256, 204, 10);
		buildings[3] = new WorldMapAnimator("/worldMap/buildingSheet4.png", 7, 0, 132, 156, 20);
		buildings[4] = new WorldMapAnimator("/worldMap/buildingSheet5.png", 5, 0, 400, 103, 20);
	}
	
	/**
	 * Draws the objects on screen
	 * @param g The graphic context
	 */
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
		drawBuilding(g2, mapLevels[4], "Level 5", 4);	
		
		//Draws a line that connects the buildings
		drawLine(g2, mapLevels[0], mapLevels[1], "DownToUp");
		drawLine(g2, mapLevels[1], mapLevels[2], "SideToSide");
		drawLine(g2, mapLevels[2], mapLevels[3], "UpToDown");
		drawLine(g2, mapLevels[3], mapLevels[4], "DownToUp");
		
		//Render och moves the character
		if(isMoving){
			moveCharacter(g);
		}else if(!isMoving){
			if(!(pos - prevPos > 1 || pos - prevPos < -1)){
				prevPos = pos;
			}else{
				pos = prevPos;
			}
			movingCharRight.renderAnimation(g, (int)mapLevels[pos].getCenterX()-15, (int)mapLevels[pos].getCenterY()+15, 32, 32);
		}
		
		if(mapState == State.MAP_SHOP){
			renderShopMenu(g, g2);
		}else if(mapState == State.MAP_CHAR){
			renderCharMenu(g, g2);
		}
		
		subMenuView.render(g2);
	}//end render
	
	//Draws the building with a label on top
	private void drawBuilding(Graphics2D g2, Rectangle r, String Name, int type){
			g2.drawString(Name, (int)r.getCenterX() - 5*Name.length(), r.y);
			buildings[type].renderAnimation(g2, r.x, r.y, r.width, r.height);	
	}
	
	//Draws a line that connects the building to each other
    private void drawLine(Graphics2D g2, Rectangle r1, Rectangle r2, String type) {
    	int x1 = (int) r1.getCenterX();
    	int y1 = (int) r1.getCenterY();
    	int x2 = (int) r2.getCenterX();
    	int y2 = (int) r2.getCenterY();
    	if(type.equals("DownToUp")){
    		g2.drawLine(x1, r1.y+r1.height, r2.x, y2);
    	}else if(type.equals("UpToDown")){
    		g2.drawLine((int)r1.getMaxX(), y1, x2, (int)r2.getMaxY());
    	}else if(type.equals("SideToSide")){
    		g2.drawLine((int)r1.getMaxX(), y1+20, r2.x, y2+20);
    	}
    }
    
    //Moves the character. Checks where and how it should move and animate
    private void moveCharacter(Graphics g){
    	int tempX = 0;
		int tempY = 0;
    	if((pos - prevPos) == 1){
			tempX = (int)mapLevels[prevPos].getCenterX()-15 + velX;
			tempY = (int)mapLevels[prevPos].getCenterY()+15 + velY;
			
	    	if(tempX < (int)mapLevels[pos].getCenterX()-15 && tempY < (int)mapLevels[pos].getCenterY()+15){
	    		//Moves from left to right, top to bot
	    		velX+= (mapLevels[pos].getCenterX() - mapLevels[prevPos].getCenterX())/40;
	    		velY+= (mapLevels[pos].getCenterY() - mapLevels[prevPos].getCenterY())/40;
	    	}else if(tempX < (int)mapLevels[pos].getCenterX()-15 && tempY > (int)mapLevels[pos].getCenterY()+15){
	    		//Moves from left to right, bot to top
	    		velX+= (mapLevels[pos].getCenterX() - mapLevels[prevPos].getCenterX())/40;
	    		velY-= (mapLevels[prevPos].getCenterY() - mapLevels[pos].getCenterY())/40;
	    	}else{
	    		velX = 0;
	    		velY = 0;
	    	}
	    	movingCharRight.renderAnimation(g, tempX, tempY, 32, 32);
	    	
    	}else if(pos - prevPos == -1){
    		tempX = (int)mapLevels[prevPos].getCenterX()-15 + velX;
			tempY = (int)mapLevels[prevPos].getCenterY()+15 + velY;
			
	    	if(tempX > (int)mapLevels[pos].getCenterX()-15 && tempY < (int)mapLevels[pos].getCenterY()+15){
	    		//Moves from right to left, top to bot
	    		velX-= (mapLevels[prevPos].getCenterX() - mapLevels[pos].getCenterX())/40;
	    		velY-= (mapLevels[prevPos].getCenterY() - mapLevels[pos].getCenterY())/40;
	    	}else if(tempX > (int)mapLevels[pos].getCenterX()-15 && tempY > (int)mapLevels[pos].getCenterY()+15){
	    		//Moves from right to left, bot to top
	    		velX-= (mapLevels[prevPos].getCenterX() - mapLevels[pos].getCenterX())/40;
	    		velY+= (mapLevels[pos].getCenterY() - mapLevels[prevPos].getCenterY())/40;
	    	}else{
	    		velX = 0;
	    		velY = 0;
	    	}
	    	movingCharLeft.renderAnimation(g, tempX-10, tempY, 32, 32);
    	}
		
		if(velX == 0 && velY == 0){
			isMoving = false;
		}

    }
    
    //Draws the ShopMenu
    private void renderShopMenu(Graphics g, Graphics2D g2){
    	renderMenuUI(g, g2, "Shop");
    	
    	//Draws gridpane
    	int centerX = (int)menuUI.getCenterX();
    	int maxX = (int)menuUI.getMaxX();
    	int maxY = (int)menuUI.getMaxY();
    	int startY = menuUI.y + menuUI.height/5;
    	g2.drawLine(centerX, startY, centerX, maxY);
    	g2.drawLine(menuUI.x, startY, maxX, startY);
    	
    	// 6*4
    	for(int i = 0; i < 6; i++){
    		for(int k = 0; k < 4; k++){
    			g2.drawRect(menuUI.x +(menuGrid.width*i), startY + (menuGrid.height*k), menuGrid.width, menuGrid.height);
    			drawItems(g2, menuUI.x +(menuGrid.width*i), startY + (menuGrid.height*k), menuGrid.width, menuGrid.height);
    		}
    	}
    	
		//display cash
		g.setFont(fntSmall);
		g.drawString("Cash: 1005$", maxX - 2*menuGrid.width, maxY - menuGrid.height/3);
    }
    
    //Draws the CharacterOverview
    private void renderCharMenu(Graphics g, Graphics2D g2){
    	renderMenuUI(g, g2, "Character");
    	
    	//Draws gridpane
    	int maxX = (int)menuUI.getMaxX();
    	int maxY = (int)menuUI.getMaxY();
    	int startY = menuUI.y + menuUI.height/5;
    	g2.drawLine(menuUI.x, startY, maxX, startY);
    	g2.drawRect(menuUI.x + menuGrid.width, startY, 4*menuGrid.width, 4*menuGrid.height);
    	
    	// 2*4
    	for(int i = 0; i < 2; i++){
    		for(int k = 0; k < 4; k++){
    			g2.drawRect(menuUI.x +((menuUI.width - menuGrid.width)*i), startY + (menuGrid.height*k), menuGrid.width, menuGrid.height);
    			drawItems(g2, menuUI.x +((menuUI.width - menuGrid.width)), startY + (menuGrid.height), menuGrid.width, menuGrid.height);
    		}
    	}
    	
    	//draws the character inside the big middle grid
    	movingCharRight.renderAnimation(g, menuUI.x + 2*menuGrid.width, startY + menuGrid.height, 2*menuGrid.width, 2*menuGrid.height);
    	
    	//charactar stats
    	g.setFont(fntSmall);
    	g.drawString("HP: 100/100", menuUI.x + menuGrid.width, maxY - menuGrid.height/2);
    	g.drawString("STR: 100", menuUI.x + menuGrid.width, maxY - menuGrid.height/4);
    	g.drawString("Lvl: 5", (int)menuUI.getCenterX() + menuGrid.width, maxY - menuGrid.height/2);
    	g.drawString("XP: 7/100", (int)menuUI.getCenterX() + menuGrid.width, maxY - menuGrid.height/4);
    }
    
    //Draws the base for the UI
    private void renderMenuUI(Graphics g,Graphics2D g2, String title){
    	//Draws window
    	g.setColor(Color.BLACK);
    	g2.fill(menuUI);
    	g.setColor(Color.RED);
    	g2.fill(menuCloseButton);
    	
    	//Draws title
    	g.setFont(fntBig);
		g.setColor(Color.white);
		g.drawString(title, Frame.WIDTH/2 - title.length() * 13, Frame.HEIGHT/2 - menuSize/2 + 50); 
    }
    
    //Draws the item on each grid on the submenus Shop and CharacterOverview
    private void drawItems(Graphics2D g2, int x, int y, int width, int height){
    	//Temporär placeholder
    	g2.drawImage(charButtonImg, x, y, width, height, null);
    	
    	/*Tänkte mej att man typ har en if-sats här så som tex:
    	 * 
    	 * if(character.hasGun2()){
    	 * 	  g2.drawImage(character.getGun2Image(), x, y, width, height, null);
    	 * }
    	 * 
    	 * Blir lite fult med massa hål. men enklaste sättet just nu
    	 */
    }
    
    /**
     * Set the posistion for the Moving Character
     * @param i The new position.
     */
    public void setPos(int i){
    	if(i < mapLevels.length)
    		pos = i;
    	else
    		System.out.println("Out of boundry");
    }
    
    /**
     * Get the currcent position for the Moving Character
     * @return Current position.
     */
    public int getPos(){
    	return pos;
    }
    
    /**
     * Set the Moving character to start moving
     */
    public void setIsMoving(){
    	isMoving = true;
    }
    
    /**
     * Checks if the character is moving
     * @return True if the character is moving and false if the character is standing still
     */
    public boolean ifMoving(){
    	return isMoving;
    }
}
