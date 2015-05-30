package edu.chl.Game.view.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;

import edu.chl.Game.controller.network.request.*;


/**
 * 							What need to be implemented
 * Send Message: Socket, Target Port, IP-adress
 * 						Connect - Send - Disconnect
 * Recieve Message: ServerSocket - Receive Port 
 * 
 * @author Mansoor
 * @version 1.0
 */
public class Client {

	private ServerSocket server;
	private String playerName;
	private Request request;
	
	private LinkedList<Integer> portList = new LinkedList<Integer>();
	
	public Client(String name) {
		request = new Request();
		
		try {
			server = new ServerSocket(3724);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void addPortList() {
		portList.add(3724);
		portList.add(6112);
		portList.add(6881);
		portList.add(6882);
		portList.add(6883);
	}
	
}
