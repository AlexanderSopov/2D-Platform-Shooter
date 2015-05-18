package edu.chl.Game;


import javax.swing.SwingUtilities;	
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.Entity;
import edu.chl.Game.model.gameobject.entity.player.Player;
import edu.chl.Game.model.gameobject.tile.Tile;
import edu.chl.Game.sound.Music;
import edu.chl.Game.controller.RefreshTimer;

/*
  Application entry class (if using standard java and Swing)
*/
public final class Main {
	public static RefreshTimer game;
	private static Music music;
	
	private Main() {
		/* No instances allowed! */
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
					try {
						game = new RefreshTimer();
						//music = new Music();
						//music.playIntro();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                });
	}

}
