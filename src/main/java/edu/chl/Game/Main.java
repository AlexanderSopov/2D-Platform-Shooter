package edu.chl.Game;

import javax.swing.SwingUtilities;

import edu.chl.Game.view.GameThread;

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
                });
	}
}
