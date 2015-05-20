package edu.chl.Game.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class Frame extends JFrame{
	
	public final static int WIDTH = 1000;
	public final static int HEIGHT = 600;
	public final static String title = "Ghost Town";
	
	public Frame(){
        super(title + " 2.1");
        
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
	}
}
