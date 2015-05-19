package edu.chl.Game.model.sound;

import java.util.HashMap;
import java.util.Map;

public final class StackOverFlow {
	
	private static Map<String, String> map = new HashMap<String, String>();
	
	private StackOverFlow() {
		
		map.put("hello", "hello");
	}
	
	public static String getHello() {
		return map.get("hello");
	}
}
