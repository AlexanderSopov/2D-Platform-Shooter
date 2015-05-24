package edu.chl.Game.Storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Store {
	
	public static void saveToFile(){

		Writer w = new Writer("fun", "hej på dig");
		try {
			Reader r = new Reader("fun/sven.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("done");
	}
	
}
