package edu.chl.Game.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.chl.Game.controller.State;

/**
 * Class for Submenu.
 * Used to controll music and has a navigation functionality.
 * @author Martin Tran
 *
 */
public class SubMenuView {
	
	//Private variables
	private State state;
	private Font fnt1;
	private Font fnt2;
	private Image openedImage;
	private Image closedImage;
	private int pad = 10;
	private Boolean soundState = true;
	
	//Public variables
	public Rectangle button = new Rectangle(0, 20, 50, 50);	
	public Rectangle background = new Rectangle(button.x, button.y + button.height, 150, 100);
	public Rectangle soundButton = new Rectangle(background.x + pad, background.y +pad, 130, 30);
	public Rectangle backButton = new Rectangle(background.x + pad, background.y + soundButton.height + 3*pad, 130, 30);
	
	/**
	 * Constructor for SubMenuView
	 * Sets the font and images
	 */
	public SubMenuView(){
		fnt1 = new Font("arial", Font.ITALIC, 25);
		fnt2 = new Font("arial", Font.ITALIC, 25);
		
		try {
			openedImage = ImageIO.read(getClass().getResource("/subMenu/subMenuOpened.png"));
			closedImage = ImageIO.read(getClass().getResource("/subMenu/subMenuClosed.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Draw the button that opens the submenu.
	 * @param g The graphic context.
	 */
	public void render(Graphics2D g){
		if(state != null){
			g.drawImage(openedImage, button.x, button.y, button.width, button.height, null);
			renderSubMenu(g);
		}else{
			g.drawImage(closedImage, button.x, button.y, button.width, button.height, null);
		}
	}
	
	//Draws the submenu UI
	private void renderSubMenu(Graphics2D g){
		g.setColor(Color.BLACK);
		g.fillRect(background.x, background.y, background.width, background.height);
		
		g.setColor(Color.WHITE);
		g.setFont(fnt1);
		if(soundState){
			g.drawString("Sound:On", soundButton.x, (int)soundButton.getMaxY());
		}else{
			g.drawString("Sound:Off", soundButton.x, (int)soundButton.getMaxY());
		}
		g.setFont(fnt2);
		g.drawString("Go Back", backButton.x, (int)backButton.getMaxY());
	}
	
	/**
	 * Sets the font to italic and bold.
	 * Called from mouseInput to make a sort of a animation.
	 * @param type What type of font will be choosen
	 * @param font The font that will be choosen
	 */
	public void setFont(int type, String font){
		if(type == 1){
			if(font.equals("italic")){
				fnt1 = new Font("arial", Font.ITALIC, 25);
			}else if(font.equals("bold")){
				fnt1 = new Font("arial", Font.BOLD, 25);
			}
		}else if(type == 2){
			if(font.equals("italic")){
				fnt2 = new Font("arial", Font.ITALIC, 25);
			}else if(font.equals("bold")){
				fnt2 = new Font("arial", Font.BOLD, 25);
			}
		}
	}
	
	/**
	 * Sets the current submenu state beetwen Submenu and non-submenu
	 * @param state The state that will be choosen
	 */
	public void setState(State state){
		this.state = state;
	}
	
	public void setSoundState(Boolean arg){
		this.soundState = arg;
	}
	
	/**
	 * Get the current state
	 * @return The current state
	 */
	public State getState(){
		return state;
	}
	
	public boolean getSoundState(){
		return soundState;
	}
	
	/**
	 * The font for First choosable option
	 * @return fnt1
	 */
	public Font getFont1(){
		return fnt1;
	}
	
	/**
	 * The font for the Second choosable option
	 * @return fnt 2
	 */
	public Font getFont2(){
		return fnt2;
	}
}
