package edu.chl.Test.PhysicsTest;


import edu.chl.Game.GameObject.Circle;



public class Main  {
	public static GameThread run;
	public static Circle c1;
	public static Circle c2;
	public static Circle c3;
	
	
	public static void main(String[] args) {
		GameThread run = new GameThread();
		run.start();
		c1 = new Circle(0 ,20, 50, new Float(0.5), 10);
		c2 = new Circle(750, 200, 150, new Float(0.80),500);
		c3 = new Circle(950, 1200, 150, new Float(0.85), 700);

		run.addObserver(c1);
		run.addObserver(c2);
		run.addObserver(c3);
		run.addObserver(new Handler());
	}
	
	
}
