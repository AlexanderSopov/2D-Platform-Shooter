package edu.chl.Game.view;




/**
 * GameThread is responsible for creating the underlying thread.
 * To make sure the game is running parallel methods beneath the hood.
 * 
 * @author Mansoor Beck
 * @version 1.0
 */
public class GameThread implements Runnable {

	private boolean isRunning = true;
	
	public GameThread() {
		
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Check whetever the game is running or not.
	 * @return isRunning - True if game is running, false otherwise.
	 */
	public boolean isGameRunning() {
		return this.isRunning;
	}
	
	/**
	 * 
	 * @param b 
	 */
	public void setGameRunning(boolean b) {
		this.isRunning = b;
	}
	public static void main(String[] args) {
		
		GameThread startGame = new GameThread();
	}
	
	
}
