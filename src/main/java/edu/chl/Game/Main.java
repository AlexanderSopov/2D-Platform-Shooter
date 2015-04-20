package edu.chl.Game;

import javax.swing.SwingUtilities;

import edu.chl.Game.view.GameThread;

/*
  Application entry class (if using standard java and Swing)
*/
public final class Main {
	private Main() {
		/* No instances allowed! */
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
					GameThread game = new GameThread();
					game.start();
                });
	}
}
