package edu.chl.Game.view;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.SwingUtilities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl.LwjglCanvas;

public class GameGDX extends Game {
	
	private Frame frame;
	private GameGDX game;
	private LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
	
	public GameGDX(Frame frame){
		this.frame = frame;
		cfg.title = Frame.title + "frame 2";
		cfg.vSyncEnabled = true;
		cfg.useGL30 = true;
		cfg.width = Frame.WIDTH;
		cfg.height = Frame.HEIGHT;
		game = this;
		setFrame();
	}
	
	@Override
	public void create() {
		setScreen(new MainMenu());
	}

	private void setFrame(){
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				Container container = frame.getContentPane();
				container.setLayout(new BorderLayout());
				
				LwjglAWTCanvas canvas = new LwjglAWTCanvas(game);
				container.add(canvas.getCanvas(), BorderLayout.CENTER);
			}
			
		});
	}
}
