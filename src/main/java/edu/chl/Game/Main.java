package edu.chl.Game;


import javax.swing.SwingUtilities;	
import edu.chl.Game.controller.RefreshTimer;

/*
  Application entry class (if using standard java and Swing)
*/
public final class Main {
	public static RefreshTimer game;
	private Main() {
		/* No instances allowed! */
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
					try {
						//Store.saveToFile();
						game = new RefreshTimer();
					} catch (Exception e) {
						e.printStackTrace();
					}
                });
	}

}
