package edu.chl.Game.controller;

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
	private boolean shoot = false;
	
	private Frame frame;
	private WorldMapView mapView;
	private Cursor blankCursor;//hide 
    private GameCursor c;
    
    private GameHandler handler;
    
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
			if(c != null){
				c.getHandler().getPlayer().shoot();
			}
		}else if(RefreshTimer.state == State.MAP){
			int mx = e.getX();
			int my = e.getY();
			
			if(mx > mapView.mapLevels[0].getX() && mx < mapView.mapLevels[0].getMaxX() && my > mapView.mapLevels[0].getY() && my < mapView.mapLevels[0].getMaxY()){
				mapView.setIsMoving();
				mapView.setPos(0);
				setLevel(e, 0);
			}else if(mx > mapView.mapLevels[1].getX() && mx < mapView.mapLevels[1].getMaxX() && my > mapView.mapLevels[1].getY() && my < mapView.mapLevels[1].getMaxY()){
				mapView.setIsMoving();
				mapView.setPos(1);
				setLevel(e, 1);
			}else if(mx > mapView.mapLevels[2].getX() && mx < mapView.mapLevels[2].getMaxX() && my > mapView.mapLevels[2].getY() && my < mapView.mapLevels[2].getMaxY()){
				mapView.setIsMoving();
				mapView.setPos(2);
				setLevel(e, 2);
			}else if(mx > mapView.mapLevels[3].getX() && mx < mapView.mapLevels[3].getMaxX() && my > mapView.mapLevels[3].getY() && my < mapView.mapLevels[3].getMaxY()){
				mapView.setIsMoving();
				mapView.setPos(3);
				setLevel(e, 3);
			}else if(mx > mapView.mapLevels[4].getX() && mx < mapView.mapLevels[4].getMaxX() && my > mapView.mapLevels[4].getY() && my < mapView.mapLevels[4].getMaxY()){
				mapView.setIsMoving();
				mapView.setPos(4);
				setLevel(e, 4);
			}else if(mx > mapView.shop.getX() && mx < mapView.shop.getMaxX() && my > mapView.shop.getY() && my < mapView.shop.getMaxY()){
				System.out.println("Shop");
			}else if(mx > mapView.character.getX() && mx < mapView.character.getMaxX() && my > mapView.character.getY() && my < mapView.character.getMaxY()){
				System.out.println("Character");
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

		handler.getMenuWindow().pressButton(e.getX(), e.getY());
		
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
	
	private void setLevel(MouseEvent e, int i){
		if(e.getClickCount() == 2 && !e.isConsumed()){
			e.consume();
			System.out.println(RefreshTimer.levels[i]);
			RefreshTimer.selectedMap = RefreshTimer.levels[i];
			RefreshTimer.state = State.GAME;
		}
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
	
	public int getMousePos_X() {
		return mousePosX;
	}
	
	public void setHandler(GameHandler handler){
		this.handler = handler;
	}
	

}
