package edu.chl.Game;

import java.util.Observer;

import javax.swing.SwingUtilities;

import edu.chl.Game.object.GameObject;
import edu.chl.Game.object.Id;
import edu.chl.Game.view.GameThread;

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
					game.start();
					game.addObserver(new Player(1*64,1*64, 64, 64, true, Id.player, game.handler));
                });
	}

}
