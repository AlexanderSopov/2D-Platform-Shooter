package edu.chl.Game.view;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.SwingUtilities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import edu.chl.Game.view.screens.IntroSplash;

/**
 * Main frame for LibGDX applications
 * Contains method to swap from Swing to LibGDX
 * @author Martin Tran
 *
 */
public class FrameGDX extends Game {
	
	private Frame frame;
	private Container container;
	private LwjglAWTCanvas canvas;
	private LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
	
	public FrameGDX(Frame frame){
		this.frame = frame;
		cfg.title = Frame.title + "frame 2";
		cfg.vSyncEnabled = true;
		cfg.useGL30 = true;
		cfg.width = Frame.WIDTH;
		cfg.height = Frame.HEIGHT;
	
		setFrame(this);
	}
	
	@Override
	public void create() {
		setScreen(new IntroSplash());
	}

	private void setFrame(FrameGDX game){
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				container = frame.getContentPane();
				container.setLayout(new BorderLayout());
				
				canvas = new LwjglAWTCanvas(game);
				container.add(canvas.getCanvas(), BorderLayout.CENTER);
			}
		});
	}
	
	public void dispose(){
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				container.remove(canvas.getCanvas());
			}	
		});
	}

}
