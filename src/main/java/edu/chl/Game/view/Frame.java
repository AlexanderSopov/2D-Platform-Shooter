package edu.chl.Game.view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class Frame extends JFrame {
	
	public final static int WIDTH = 1000;
	public final static int HEIGHT = 600;
	
	public Frame(){
        super("Mario");
        
        final GridLayout layout = new GridLayout(0, 3);
        setLayout(layout);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
	}
	
}
