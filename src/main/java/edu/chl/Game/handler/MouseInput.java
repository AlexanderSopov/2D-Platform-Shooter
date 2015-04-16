package edu.chl.Game.handler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseMotionListener, MouseListener{
	
	private static int mousePosX, mousePosY;
	private static boolean onCanvas = false;
	private static boolean pressed = false;
	
	

	@Override
	public void mouseMoved(MouseEvent e) {
		
		if(onCanvas){
			setMousePosX(e.getX());
			setMousePosY(e.getY());
		}

		
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
		
		onCanvas = true;
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		onCanvas = false;
		
	}




	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	//Getters and Setters

	/**
	 * @return the mousePosX
	 */
	public int getMousePosX() {
		return mousePosX;
	}


	/**
	 * @param mousePosX the mousePosX to set
	 */
	private void setMousePosX(int mousePosX) {
		this.mousePosX = mousePosX;
	}


	/**
	 * @return the mousePosY
	 */
	public int getMousePosY() {
		return mousePosY;
	}


	/**
	 * @param mousePosY the mousePosY to set
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
