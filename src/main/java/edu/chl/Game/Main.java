package edu.chl.Game;


import javax.swing.SwingUtilities;

import edu.chl.Game.object.Id;
import edu.chl.Game.tile.Tile;
import edu.chl.Game.view.GameThread;
import edu.chl.Game.entity.Entity;
import edu.chl.Game.entity.Player;

/*
  Application entry class (if using standard java and Swing)
*/
public final class Main {
	public static GameThread game;
	private Main() {
		/* No instances allowed! */
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
					game = new GameThread();
                });
	}

}
