package edu.chl.Game.model.gameobject.entity.player;

import java.awt.Color;
import java.awt.Graphics;

import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.Entity;
import edu.chl.Game.view.Camera;
import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.controller.MouseInput;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * GameCursor the Entity which displayed as the cursor in the game.
 * 
 * 
 * @author Rasmus
 *
 */

public class GameCursor extends Entity {

	private BufferedImage cursorImage;

	private CursorState state;
	
	
	/**
	 * 
	 *  States for the Cursor which effect how the Cursor renders 
	 *
	 */
	public enum CursorState {
		AIM, DEFULT, RELODING;
	}

	/**
	 * 
	 * This constructor initialise the default cursor and the cursor image.
	 * 
	 * 
	 * @param handler
	 */
	public GameCursor(GameHandler handler) {

		super(0, 0, 0, 0, false, Id.cursor, handler);

		this.state = CursorState.DEFULT;

		try {
			cursorImage = ImageIO.read(getClass().getResource("/cursor.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void render(Graphics g) {
		switch (state) {

		case AIM:


			// Drawing the aim symbol

			g.setColor(Color.red);
			g.drawOval(getX() - 15, getY() - 15, 30, 30);
			g.fillRect(getX(), getY(), 1, 1);
			break;


		case DEFULT:

			// Drawing the image of the default cursor
			g.drawImage(cursorImage, getX() - cursorImage.getWidth() / 40,
					getY(), cursorImage.getWidth() / 10,
					cursorImage.getHeight() / 10, null);

			break;
			
		default:


		}

	}

	@Override
	public void update() {
		
		//Correct the position of the cursor according to the camera.
		setX(MouseInput.getMousePosX() - Camera.getX());
		setY(MouseInput.getMousePosY() - Camera.getY());
		

	}

	/**
	 * Change the state to parameter state
	 * 
	 * @param state
	 *            - the state of the cursor
	 */
	public void changeState(CursorState state) {
		this.state = state;
	}

}
