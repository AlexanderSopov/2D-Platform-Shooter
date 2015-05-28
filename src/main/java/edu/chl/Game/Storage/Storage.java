package edu.chl.Game.Storage;

import java.util.LinkedList;
import java.util.List;

public interface Storage {
	
	public void save(List list);
	
	public LinkedList load();
	
	public Object getObject(String str);
	
}
