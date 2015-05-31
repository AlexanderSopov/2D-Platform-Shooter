package edu.chl.Game.view.network;

import java.io.IOException;
import java.net.ServerSocket;



/**
 * Class under development<p>
 * 
 * FIX THREAD, Future Implementation for Multiplayer compatibility
 * 
 * @author Mansoor
 * @version 1.0
 */
public class Server implements Runnable {

	
	/**
	 * Server waits for request
	 */
	private ServerSocket socket;
	
	
	/**
	 * Server state is running or not
	 * If running return true else false.
	 */
	private boolean serverState = false;
	
	
	/**
	 * Port for the Server waits for request
	 * and the Client need to access.
	 */
	private int port;

	public Server(int port) {
		this.port = port;

	}
	
	/**
	 * Starts the Server so the clients and the server
	 * can communicate<p>
	 * 
	 * Server involves Thread
	 */
	public void startServer() {
		
		try {
			socket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
			/**
			 *  Should be running if thrown 
			 *  exception since its server
			 */
			return;
		}
		serverState = true;
		getServerThread().start();
	}
	
	/**
	 * FIX
	 * @return
	 */
	private Thread getServerThread() {
		Thread serverThread = new Thread();
		return serverThread;
	}
	
	
	/**
	 * Stops the Server<p>
	 *
	 * No communication between the client and server
	 * will occur.
	 */
	public void stopServer() {
		
		
	}
	
	
	/**
	 * 
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}