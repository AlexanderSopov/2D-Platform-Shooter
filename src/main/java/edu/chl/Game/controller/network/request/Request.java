package edu.chl.Game.controller.network.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;


/**
 * Request Data from any given website or device. 
 * The necessary methods for doing that contains within this class.
 * 
 * @author Mansoor
 * @version 2.0
 */
public class Request {
	
	private Map<String, String> ipv4Address = new HashMap<String, String>();
	private Map<String, String> ipv6Address = new HashMap<String, String>();
	
	/**
	 * Used for primarly for creating an instance of Request
	 * in other classes to use below methods.
	 */
	public Request() {
		
	}
	
	
	/**
	 * Enter an address to check if the address is reachable.
	 * @param address - Address the correct term is for example: google.com
	 * 
	 * @return True if reachable, false otherwise.
	 * @throws IOException - If 
	 * @throws InterruptedException
	 */
	public boolean isReachableAddress(String address) throws IOException, 
						InterruptedException {
		
		int timeout = getCommandProcess(address).waitFor();
		boolean reachable = (timeout==0);
		return reachable;
	}

	
	/**
	 * Get Data from a particular IP-address or DNS (Website)
	 * For example: Address could be "google.se” or "74.125.232.111"
	 * without the quotes.
	 * 
	 * @return 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public String getDataFrom(String address) throws IOException, NullPointerException, InterruptedException {
		
		if(address.isEmpty() || address == null || !isReachableAddress(address)) {
			throw new IllegalArgumentException("Invalid address");
		}
		
		// BufferedReader makes it efficient to read
		BufferedReader googleData = new BufferedReader(
				new InputStreamReader(getCommandProcess(address).getInputStream()));
		System.out.println(googleData.readLine());
		return googleData.readLine();
	}
	
	/**
	 * 
	 * @param address
	 * @return
	 * @throws IOException
	 */
	public Process getCommandProcess(String address) throws IOException {
		
		Process processCommand = Runtime.getRuntime().exec(getThisDeviceWholeCommand() + address);
		return processCommand;
	}
	
	
	/**
	 * Data Latency in terms of milliseconds in response time.
	 * 
	 * @return Latency - String type in time=xxxxx ms 
	 * @throws IOException - Throws if not successfully gets the ping
	 * @throws InterruptedException 
	 * @throws NullPointerException 
	 */
	public String getDataLatencyInMs(String address) throws IOException, 
											NullPointerException, InterruptedException {
		
		String latency = getDataFrom(address).substring(47, 61);
		return latency;
	}
	
	/**
	 * Get Data Latency In Numbers<p>
	 * For example: 25000 is being printed out means
	 * 25.000 ms
	 * 
	 * @return intTime - The latency in numbers
	 * @throws IOException - If reading failure
	 * @throws InterruptedException 
	 * @throws NullPointerException 
	 */
	public int getDataLatencyInNumbers(String address) throws IOException, 
							NullPointerException, InterruptedException {
				
		String stringTime = getDataLatencyInMs(address).substring(5, 11);
		if(stringTime.contains(".")) {
			
			stringTime = stringTime.replace(".", "");
		}
		int intTime = Integer.parseInt(stringTime);
		return intTime;
	}
	
	
	/**
	 * Get this device Operating System.
	 * @return OS - String type which returns the device OS.
	 */
	public String getThisDeviceOS() {
		
		String os = System.getProperty("os.name");
		return os;
	}
	
	
	/**
	 * Different OS have different commands which effects the request for address.
	 * For example: Mac, Ubuntu and Linux are built on top of Unix kernel 
	 * and using the command "-c". While Windows using "-n".<p>
	 * 
	 * This method determines what command to use depending on what Operating System.
	 * @return command - String type which contains the command for this device OS.
	 */
	public String getThisDeviceCommand() {
		String os = getThisDeviceOS();
		String command = "";
		
		if(os.contains("Mac") || os.contains("Ubuntu") || os.contains("Linux")) {
			command = "-c";
		} else if(os.contains("Windows")) {
			command = "-n";
		} 
		return command;
	}
	
	
	/**
	 * Prints out the whole command for current device 
	 * which is needed for Ping a website or device<p>
	 * 
	 * For example in Mac: ping -c 1 google.com
	 * For example in Windows: ping -n 1 google.com
	 * The command is "ping -.. 1"
	 * 
	 * @return wholeCommand - String type
	 */
	public String getThisDeviceWholeCommand() {
		
		String wholeCommand = ("ping " + getThisDeviceCommand() + " 1 ");
		return wholeCommand;
	}
	
	
	/**
	 * Print the Addresses of the this device. 
	 * The addresses consist of IPv4 Address, Loopback and IPv6 Address.
	 * Since IPv4 is running out of addresses and IPv6 is in the game
	 * we need to get IPv6 Addresses as well since there could be a chance
	 * that we encounter a device with IPv6 and we should be able to handle it.<p>
	 * 
	 * @return Addresses of this device such as IPv4, IPv6 and Loopback.
	 */
	public void getThisDeviceAddress() {
		try {
			Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
			while(e.hasMoreElements()) {
				
				NetworkInterface network = (NetworkInterface)e.nextElement();
				Enumeration<InetAddress> ee = network.getInetAddresses();
				while(ee.hasMoreElements()) {
					InetAddress i = ee.nextElement();
					
					if(!i.isLinkLocalAddress()) {
						if(i instanceof Inet4Address) {
							addIPv4AddressList(i.getHostAddress());
							System.out.println("IP Adress 4v: " + i.getHostAddress());
						} else if (i instanceof Inet6Address) {
							addIPv6AddressList(i.getHostAddress());
							System.out.println("IP Adress 6v: " + i.getHostAddress());
						}
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Add IPv4 Addresses to the list.
	 */
	public void addIPv4AddressList(String address) {
		
		if(address.contains("192")) {
			getIPv4AddressList().put("ipv4address: ", address);
		} else if(address.contains("127")) {
			getIPv4AddressList().put("ipv4addressloopback: ", address);
		}
	}
	
	
	/**
	 * Add IPv6 Addresses to the list.
	 */
	public void addIPv6AddressList(String address) {
		
		if(address.contains("6v")) {
			getIPv6AddressList().put("ipv6address", address);
		}
	}
	
	/**
	 * Prints the IPv4 Addresses of this device.
	 */
	public void printIPv4AddressList() {
		
		if(!getIPv4AddressList().isEmpty()) {
			for(Entry<String, String> address: getIPv4AddressList().entrySet()) {
				System.out.println(address.getKey() + address.getValue());
			}
		} else {
			System.out.println("No IPv4 Addresses here!");
		}
	}
	
	
	/**
	 * Prints the IPv6 Addresses of this device.
	 */
	public void printIPv6AddressList() {
		
		if(!getIPv6AddressList().isEmpty()) {
			for(Entry<String, String> address: getIPv6AddressList().entrySet()) {
				System.out.println(address.getKey() + address.getValue());
			}
		} else {
			System.out.println("No IPv6 Addresses here!");
		}
	}
	

	/**
	 * Clear the IPv4 Address List
	 */
	public void clearIPv4AddressList() {
		getIPv4AddressList().clear();
	}
	
	/**
	 * Clear the IPv6 Address List
	 */
	public void clearIPv6AddressList() {
		getIPv6AddressList().clear();
	}
	
	/**
	 * Returns all IPv4 Addresses of this device in a list.
	 * @return A list - Type Map<String, String> 
	 */
	public Map<String, String> getIPv4AddressList() {
		return this.ipv4Address;
	}
	
	
	/**
	 * Returns all IPv6 Addresses of this device in a list.
	 * @return A list - Type Map<String, String>
	 */
	public Map<String, String> getIPv6AddressList() {
		return this.ipv6Address;
	}
	
	
	/**
	 * Returns IPv4 Address LoopBack of this device in a string.
	 * @return IPv4 Address LoopBack in string
	 */
	public String getIPv4AddressLoopBack() {
		return getIPv4AddressList().get("ipv4addressloopback");
	}
	
	/**
	 * Returns IPv4 Address of this device in a string.
	 * @return IPv4 Address in string
	 */
	public String getIPv4Address() {
		return getIPv4AddressList().get("ipv4address");
	}
	
	
	/**
	 * Returns IPv6 Address of this device in a string.
	 * @return IPv6 Address in string
	 */
	public String getIPv6Address() {
		return getIPv6AddressList().get("ipv6address");
	}
}
