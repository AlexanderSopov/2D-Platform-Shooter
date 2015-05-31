package edu.chl.Game.view.network;

import java.io.IOException;		
import java.net.ServerSocket;
import java.util.LinkedList;

import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import edu.chl.Game.controller.network.request.*;

/**
 * 				Class under development<p>
 * 
 * 							What need to be implemented
 * To Send Message following needed: Socket, Target Port, Target IP-address
 * 						
 * Receive Message: ServerSocket - Receive Port 
 * 
 * Once connected to the Multiplayer section from Meny
 * Connected as long communication bandwith exist or exit Multiplayer section
 * 
 * @author Mansoor
 * @version 1.0
 */
public class Client {
	
	
	/**
	 * Player name that being used for shown
	 * in Chat, Battle and Co-op
	 */
	private String playerName;
	
	/**
	 * Able to access methods from Request class.
	 * Methods needed for test communication
	 */
	private Request request;
	
	
	/**
	 * List of players connected with names
	 */
	private JList playerList;
	
	
	/**
	 * Main Chat field for players
	 */
	private JTextPane playersChat;
	
	
	/**
	 * Chat input from player
	 */
	private JTextField playerChatInput;
	
	
	/**
	 * List of Ports that are being tested
	 */
	private LinkedList<Integer> portList = new LinkedList<Integer>();
	
	private Server server;
	
	
	public Client() {
		server = new Server(6112);
		server.startServer();
	}
	public void addPortList() {
		portList.add(3724);
		portList.add(6112);
		portList.add(6881);
		portList.add(6882);
		portList.add(6883);
	}
	
	
	/**
	 * Retrieve all the data from the GUI
	 * such as chat and input from the player
	 */
	private void getViewData() {
		
		
	}
}
