package edu.chl.Game.controller;



import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import edu.chl.Game.model.sound.Music;
import edu.chl.Game.view.SubMenuView;


public class MouseInput implements MouseMotionListener, MouseListener {

	private static int mousePosX, mousePosY;
	private static boolean onCanvas = false;
	private static boolean pressed = false;

	
	private Cursor blankCursor;
    private GameHandler handler ;
    private SubMenuView subMenuView;
    
	public MouseInput(GameHandler handler, SubMenuView subMenuView){
		this.handler = handler;
		this.subMenuView = subMenuView;
		
		// Transparent 16 x 16 pixel cursor image.
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

				// Create a new blank cursor.
		blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		if (onCanvas) {
			setMousePosX(e.getX());
			setMousePosY(e.getY());
		}
		
		//Changes the font in the buttons in submenu
		if(ifClicked(subMenuView.soundButton, e)){
			subMenuView.setFont(1, "bold");
		}else if(ifClicked(subMenuView.backButton, e)){
			subMenuView.setFont(2, "bold");
		}
		
		if(!ifClicked(subMenuView.soundButton, e)){
			subMenuView.setFont(1, "italic");
		}else if(!ifClicked(subMenuView.backButton, e)){
			subMenuView.setFont(2, "italic");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {	
		if(RefreshTimer.state == State.GAME){
			handler.getPlayer().shoot();	
		}
		
		if(ifClicked(subMenuView.button, e)){
			if(subMenuView.getState() == null){
				subMenuView.setState(State.SUB_MENU);
			}else{
				subMenuView.setState(null);
			}
		}
		
		if(subMenuView.getState() == State.SUB_MENU){
			if(ifClicked(subMenuView.soundButton, e)){
				if(subMenuView.getSoundState()){
					Music.stopWorldOneMapOne();
					subMenuView.setSoundState(false);
				}else{
					Music.playWorldOneMapOne();
					subMenuView.setSoundState(true);
				}
			}else if(ifClicked(subMenuView.backButton, e)){
				if(RefreshTimer.state == State.MAP){
					RefreshTimer.state = State.MAIN_MENU;
				}else if(RefreshTimer.state == State.GAME){
					RefreshTimer.state = State.MAP;
				}
				subMenuView.setState(null);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {	
		pressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		pressed = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(RefreshTimer.state == State.MAIN_MENU){
			e.getComponent().setCursor(Cursor.getDefaultCursor());
		}else{
			e.getComponent().setCursor(blankCursor);
			onCanvas = true;
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		onCanvas = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	

	// Getters and Setters

	/**
	 * @return the mousePosX
	 */
	public static int getMousePosX() {
		return mousePosX;
	}

	/**
	 * @param mousePosX
	 *            the mousePosX to set
	 */
	private void setMousePosX(int mousePosX) {
		mousePosX = mousePosX;
	}

	/**
	 * @return the mousePosY
	 */
	public static int getMousePosY() {
		return mousePosY;
	}

	/**
	 * @param mousePosY
	 *            the mousePosY to set
	 */
	private void setMousePosY(int mousePosY) {
		mousePosY = mousePosY;
	}

	/**
	 * @return the pressed
	 */
	public static boolean isPressed() {
		return pressed;
	}
	
	/**
	 * Checks which Rectangle is clicked
	 * @param r Rectangle that acts like a button
	 * @param e MouseEvent
	 * @return If the mouse was inside the boundaries of the Rectangle
	 */
	protected boolean ifClicked(Rectangle r, MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		return mx > r.getX() && mx < r.getMaxX() && my > r.getY() && my < r.getMaxY();
	}
}
