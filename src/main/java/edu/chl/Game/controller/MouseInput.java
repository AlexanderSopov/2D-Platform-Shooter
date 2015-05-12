package edu.chl.Game.controller;

import edu.chl.Game.handler.State;
import edu.chl.Game.model.gameobject.entity.player.GameCursor;
import edu.chl.Game.view.Frame;
import edu.chl.Game.view.WorldMapView;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

public class MouseInput implements MouseMotionListener, MouseListener {

	private static int mousePosX, mousePosY;
	private static boolean onCanvas = false;
	private static boolean pressed = false;
	
	private Frame frame;
	private WorldMapView mapView;
	private Cursor blankCursor;//hide 
    private GameCursor c;
    
    public MouseInput(Frame frame){
    	this.frame = frame;
    }
	
	public void setCursor(GameCursor c){
		//Put all the pre-load content here

		this.c = c;
		// Transparent 16 x 16 pixel cursor image.
		BufferedImage cursorImg = new BufferedImage(16, 16,
				BufferedImage.TYPE_INT_ARGB);

		// Create a new blank cursor.
		blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg,
				new Point(0, 0), "blank cursor");
	}


	public MouseInput(Frame frame, WorldMapView mapView){
		this.frame = frame;
		this.mapView = mapView;
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		if (RefreshTimer.state == State.GAME){
			if (onCanvas) {
				setMousePosX(e.getX());
				setMousePosY(e.getY());
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(RefreshTimer.state == State.GAME) {
			c.shoot();
			if (c != null) {
				c.shoot();
			}
		}else if(RefreshTimer.state == State.MAP){
			int mx = e.getX();
			int my = e.getY();
			
			if(mx > mapView.level1.getX() && mx < mapView.level1.getMaxX() && my > mapView.level1.getY() && my < mapView.level1.getMaxY()){
				RefreshTimer.selectedMap = "level_1";
				RefreshTimer.state = State.GAME;
			}else if(mx > mapView.level2.getX() && mx < mapView.level2.getMaxX() && my > mapView.level2.getY() && my < mapView.level2.getMaxY()){
				RefreshTimer.selectedMap = "level_2";
				RefreshTimer.state = State.GAME;
			}else if(mx > mapView.level3.getX() && mx < mapView.level3.getMaxX() && my > mapView.level3.getY() && my < mapView.level3.getMaxY()){
				RefreshTimer.selectedMap = "level_3";
				RefreshTimer.state = State.GAME;
			}else if(mx > mapView.level4.getX() && mx < mapView.level4.getMaxX() && my > mapView.level4.getY() && my < mapView.level4.getMaxY()){
				RefreshTimer.selectedMap = "level_4";
				RefreshTimer.state = State.GAME;
			}else if(mx > mapView.level5.getX() && mx < mapView.level5.getMaxX() && my > mapView.level5.getY() && my < mapView.level5.getMaxY()){
				RefreshTimer.selectedMap = "level_5";
				RefreshTimer.state = State.GAME;
			}else if(mx > mapView.shop.getX() && mx < mapView.shop.getMaxX() && my > mapView.shop.getY() && my < mapView.shop.getMaxY()){
				System.out.println("Shop");
				RefreshTimer.state = State.GAME;
			}else if(mx > mapView.character.getX() && mx < mapView.character.getMaxX() && my > mapView.character.getY() && my < mapView.character.getMaxY()){
				System.out.println("Character");
				RefreshTimer.state = State.GAME;
			}
		}
		
		
		if(e.getSource() instanceof JButton){
			String button = ((JButton) e.getSource()).getText();
			if(RefreshTimer.state == State.MENU){
				switch(button){
					case "Start":
						RefreshTimer.state = State.GAME;
						RefreshTimer.selectedMap = "level_1";
						break;
					case "New":
						RefreshTimer.state = State.MAP;
						break;
					case "Option":
						RefreshTimer.state = State.OPTION;
						break;
					case "Credit":
						RefreshTimer.state = State.CREDIT;
						break;
					case "Exit":
						System.exit(0);
						break;
					default:
						break;
				}
			}else if(RefreshTimer.state == State.OPTION){
				switch(button){
				case "Back":
					RefreshTimer.state = State.MENU;
					break;
				default:
					break;
				}
			}
			
			frame.getContentPane().removeAll();
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		if(RefreshTimer.state == State.GAME) {
			pressed = true;
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		if(RefreshTimer.state == State.GAME) {
			pressed = false;
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {

		if(RefreshTimer.state == State.GAME) {	
			e.getComponent().setCursor(blankCursor);
			if (c != null) {
				e.getComponent().setCursor(blankCursor);
			}
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
		this.mousePosX = mousePosX;
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
		this.mousePosY = mousePosY;
	}

	/**
	 * @return the pressed
	 */
	public static boolean isPressed() {
		return pressed;
	}

}
