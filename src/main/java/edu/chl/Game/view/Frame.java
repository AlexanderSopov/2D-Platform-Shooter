package edu.chl.Game.view;


import java.awt.Dimension;
import java.awt.Canvas;
import javax.swing.JFrame;


/**
 * Frame is responsible for creating the frame.
 * 
 * @author Mansoor Beck
 * @version 1.0
 */

public class Frame extends Canvas {

	
	/**
	 * Auto generated serialVersion.
	 */
	private static final long serialVersionUID = 2301826460122833631L;
	
	/**
	 * The standard width to begin with.
	 */
	private static final int width = 270;
	
	/**
	 * The standard height to begin with.
	 */
	private static final int height = width/14*10;
	
	/**
	 * Scaler to be used to stretch an object
	 * with length of 4.
	 */
	private static final int scale = 4;
	
	public Frame() {
		
		Dimension dimSize = new Dimension(width*scale, height*scale);
		setPreferredSize(dimSize);
		setMinimumSize(dimSize);
		setMaximumSize(dimSize);
	}
	
	/**
	 * This need to be run in order to create a frame.
	 */
	public void gameFrame() {
		
		JFrame frame = new JFrame("Mario Battlefield");
		frame.add(new Frame());
		frame.setVisible(true);
		frame.pack();
		
		
		/**
		 * Resizable from dragging the window is false. 
		 * Resizable should be in the game in resolution.
		 */
		frame.setResizable(true);
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
