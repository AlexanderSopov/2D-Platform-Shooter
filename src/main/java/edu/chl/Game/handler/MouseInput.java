package edu.chl.Game.handler;

import edu.chl.Game.entity.GameCursor;
import edu.chl.Game.view.Frame;
import edu.chl.Game.view.GameThread;

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
	private Cursor blankCursor;//hide 
    private GameCursor c;
	
	MouseInput(GameCursor c){
		//Put all the pre-load content here

		this.c = c;
		// Transparent 16 x 16 pixel cursor image.
		BufferedImage cursorImg = new BufferedImage(16, 16,
				BufferedImage.TYPE_INT_ARGB);

		// Create a new blank cursor.
		blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg,
				new Point(0, 0), "blank cursor");
	}


	public MouseInput(Frame frame) {
		this.frame = frame;
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		if (GameThread.state == State.GAME){
			if (onCanvas) {
				setMousePosX(e.getX());
				setMousePosY(e.getY());
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
                
                System.out.println("hahaha");
                if (c != null) {
                    c.shoot();
                }
		/*
		if(GameThread.state == State.GAME) {
			c.shoot();
			if (c != null) {
				c.shoot();
			}
		}else if(e.getSource() instanceof JButton){
			String button = ((JButton) e.getSource()).getText();
			if(GameThread.state == State.MENU){
				switch(button){
					case "Start":
						GameThread.state = State.GAME;
						break;
					case "New":
						GameThread.state = State.GAME;
						break;
					case "Option":
						GameThread.state = State.OPTION;
						break;
					case "Credit":
						GameThread.state = State.CREDIT;
						break;
					case "Exit":
						System.exit(0);
						break;
					default:
						break;
				}
			}else if(GameThread.state == State.OPTION){
				switch(button){
				case "Back":
					GameThread.state = State.MENU;
					break;
				default:
					break;
				}
			}
			
			frame.getContentPane().removeAll();
		}*/

	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		if(GameThread.state == State.GAME) {
			pressed = true;
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		if(GameThread.state == State.GAME) {
			pressed = false;
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {

		if(GameThread.state == State.GAME) {	
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
