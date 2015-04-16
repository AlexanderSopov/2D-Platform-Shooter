package edu.chl.Game.handler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseMotionListener, MouseListener{
	
	private static int mousePosX, mousePosY;
	private static boolean onCanvas = false;
	
	MouseInput(){
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		
		if(onCanvas){
			
			setMousePosX(e.getX());
			setMousePosX(e.getX());
			
		}
		
	}
	

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
		onCanvas = true;
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
		onCanvas = false;
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


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

}
