package edu.chl.Game.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javafx.scene.paint.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class StartMenu extends JPanel {
	
	Frame frame;
	JButton startButton;
	JButton profileButton;
	JButton exitButton;
	
	public StartMenu(Frame frame){
		this.frame = frame;
		frame.add(this);
		//setBackground(Color.BLACK);
		
		startButton = new JButton("Start");
		profileButton = new JButton("Profiles");
		exitButton = new JButton("Exit");
		
		setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		add(startButton, gridBagConstraints);
		gridBagConstraints.gridy = 1;
		add(profileButton, gridBagConstraints);
		gridBagConstraints.gridy = 2;
		add(exitButton, gridBagConstraints);
		
	}
}
