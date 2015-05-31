package edu.chl.Game.controller;



import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import edu.chl.Game.model.gameobject.entity.player.GameCursor.CursorState;
import edu.chl.Game.model.sound.Music;
import edu.chl.Game.view.SubMenuView;
import edu.chl.Game.view.WorldMapView;


public class MouseInput implements MouseMotionListener, MouseListener {

	private static int mousePosX, mousePosY;
	private static boolean onCanvas = false;
	private static boolean pressed = false;
	
	private Cursor blankCursor;//hide 
    private GameHandler handler ;
    private SubMenuView subMenuView;
	private WorldMapView mapView;
    
	public MouseInput(WorldMapView mapView, GameHandler handler, SubMenuView subMenuView){
		this.handler = handler;
		this.subMenuView = subMenuView;
		this.mapView = mapView;
		
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
		//Insde the Game
		if(RefreshTimer.state == State.GAME){
			handler.getPlayer().shoot();	
		}
		
		//Set the state to submenu when clicked on
		if(ifClicked(subMenuView.button, e)){
			if(subMenuView.getState() == null){
				subMenuView.setState(State.SUB_MENU);
			}else{
				subMenuView.setState(null);
			}
		}
		
		//Inside the submenu
		if(subMenuView.getState() == State.SUB_MENU){
			//SoundController
			if(ifClicked(subMenuView.soundButton, e)){
				if(subMenuView.getSoundState()){
					System.out.println("Sound off");
					subMenuView.setSoundState(false);
				}else{
					System.out.println("Sound on");
					subMenuView.setSoundState(true);
				}
			}else if(ifClicked(subMenuView.backButton, e)){
				//"Go back" feature
				Music.stopMusic();
				if(RefreshTimer.state == State.MAP){
					e.getComponent().setCursor(Cursor.getDefaultCursor());
					RefreshTimer.state = State.MAIN_MENU;
				}else if(RefreshTimer.state == State.GAME){
					handler.getGameCursor().changeState(CursorState.DEFULT);
					RefreshTimer.state = State.MAP;
				}
				subMenuView.setState(null);
			}
		}
		
		//Inside WorldMap
		if(RefreshTimer.state == State.MAP && !mapView.ifMoving()){		
			if(ifClicked(mapView.mapLevels[0], e)){
				mapView.setIsMoving();
				mapView.setPos(0);
				setLevel(e, 0);
			}else if(ifClicked(mapView.mapLevels[1], e)){
				mapView.setIsMoving();
				mapView.setPos(1);
				setLevel(e, 1);
			}else if(ifClicked(mapView.mapLevels[2], e)){
				mapView.setIsMoving();
				mapView.setPos(2);
				setLevel(e, 2);
			}else if(ifClicked(mapView.mapLevels[3], e)){
				mapView.setIsMoving();
				mapView.setPos(3);
				setLevel(e, 3);
			}else if(ifClicked(mapView.mapLevels[4], e)){
				mapView.setIsMoving();
				mapView.setPos(4);
				setLevel(e, 4);
			}else if(ifClicked(mapView.shopButton, e)){
				WorldMapView.mapState = State.MAP_SHOP;
			}else if(ifClicked(mapView.characterButton, e)){
				WorldMapView.mapState = State.MAP_CHAR;
			}
			
			//Inside WorldMap
			if(WorldMapView.mapState == State.MAP_SHOP){
				System.out.println("Shop");
			}else if(WorldMapView.mapState == State.MAP_CHAR){
				System.out.println("Character");
			}
			
			//Inside WorldMap, in the other submenus
			if(ifClicked(mapView.menuCloseButton, e)){
				WorldMapView.mapState = null;
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
	
	//Checks which rectangle(button) is clicked
	private boolean ifClicked(Rectangle r, MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		return mx > r.getX() && mx < r.getMaxX() && my > r.getY() && my < r.getMaxY();
	}
	
	//Set the level depending on which building was double clicked
	private void setLevel(MouseEvent e, int i){
		if(e.getClickCount() == 2 && !e.isConsumed()){
			e.consume();
			Music.stopMusic();
			MapFactory.levelImage = null;
			RefreshTimer.selectedMap = RefreshTimer.levels[i];
			RefreshTimer.state = State.GAME;
			subMenuView.setState(null);
			handler.getGameCursor().changeState(CursorState.AIM);
		}
	}
}
