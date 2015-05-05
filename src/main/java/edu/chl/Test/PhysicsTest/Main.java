package edu.chl.Test.PhysicsTest;

/**
*
* @author Alexander Sopov
*/

import edu.chl.Test.GameObject.Box;
import edu.chl.Test.GameObject.Circle;
import edu.chl.Test.GameObject.StaticBox;



public class Main  {
	public static GameThread run;
	public static Circle c1;
	public static Circle c2;
	public static Circle c3;
	public static Box b1;
	public static StaticBox b2;
	
	
	public static void main(String[] args) {
		GameThread run = new GameThread();
		run.start();

		c1 = new Circle(0 ,-30, 50, new Float(0.5), 10);
		c2 = new Circle(750, 150, 150, new Float(0.80),500);
		c3 = new Circle(950, 1150, 150, new Float(0.85), 700);

/*
		c1 = new Circle(50 ,150, 50, 0.5, 10);
		c2 = new Circle(800, 400, 150, 0.7,500);
		c3 = new Circle(800, 1500, 150, 0.85, 300);
		b1 = new Box(950,400,150, 80,  0.4, 50);
		b2 = new StaticBox(50, 600, 1500, 50, 0.4, 0);
		run.addObserver(b1);
		run.addObserver(b2);*/	

		run.addObserver(c1);
		run.addObserver(c2);
		run.addObserver(c3);
		run.addObserver(new Handler());
	}
	
	
}
