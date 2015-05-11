package edu.chl.Game.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.chl.Game.controller.KeyInput;
import edu.chl.Game.controller.MouseInput;


/**
 * Temporary class for the menus
 * @author Martin Tran
 * @version 1.0
 */
public class StartMenu{

	Container container;
	
	private Frame frame;
	private boolean inMenu;
	private boolean inOption;
	private boolean inCredit;
	
	public StartMenu(Frame frame){
		this.frame = frame;
		inMenu = false;
		container = frame.getContentPane();
		
		frame.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
	}
	
	public void setMenu(){
		JLabel nameLabel = new JLabel("JumpShooter");
		nameLabel.setAlignmentX(container.CENTER_ALIGNMENT);
		nameLabel.setFont(new Font("arial", Font.BOLD, 50));
		nameLabel.setForeground(Color.WHITE);
		container.add(nameLabel);
		
		addButton("Start");
		addButton("New");
		addButton("Option");
		addButton("Credit");
		addButton("Exit");
		
		container.setBackground(Color.BLACK);
		resetState();
		inMenu = true;
	}
	
	public void setOption(){
		JLabel nameLabel = new JLabel("Options");
		nameLabel.setAlignmentX(container.CENTER_ALIGNMENT);
		nameLabel.setFont(new Font("arial", Font.BOLD, 50));
		nameLabel.setForeground(Color.WHITE);
		container.add(nameLabel);
		
		addButton("Graphics");
		addButton("Sound");
		addButton("Difficult");
		addButton("Back");
		
		container.setBackground(Color.BLUE);
		resetState();
		inOption = true;
	}
	
	public void setCredit(){
		JLabel nameLabel1 = new JLabel("Name1");
		nameLabel1.setAlignmentX(container.CENTER_ALIGNMENT);
		nameLabel1.setFont(new Font("arial", Font.BOLD, 50));
		nameLabel1.setForeground(Color.WHITE);
		container.add(nameLabel1);
		
		JLabel nameLabel2 = new JLabel("Name2");
		nameLabel2.setAlignmentX(container.CENTER_ALIGNMENT);
		nameLabel2.setFont(new Font("arial", Font.BOLD, 50));
		nameLabel2.setForeground(Color.WHITE);
		container.add(nameLabel2);
		
		JLabel nameLabel3 = new JLabel("Name3");
		nameLabel3.setAlignmentX(container.CENTER_ALIGNMENT);
		nameLabel3.setFont(new Font("arial", Font.BOLD, 50));
		nameLabel3.setForeground(Color.WHITE);
		container.add(nameLabel3);
		
		container.setBackground(Color.DARK_GRAY);
		resetState();
		inCredit = true;
	}
	
	public void render(Graphics g){
		
	}
	
	private void addButton(String text){
		JButton button = new JButton(text);
		button.setAlignmentX(container.CENTER_ALIGNMENT);
		button.addMouseListener(new MouseInput(frame));
		button.setMaximumSize(new Dimension(180,50));
		container.add(button);
	}
	
	private void resetState(){
		inMenu = false;
		inOption = false;
		inCredit = false;
	}
	
	public boolean inMenu(){
		return inMenu;
	}
	
	public boolean inOption(){
		return inOption;
	}
	
	public boolean inCredit(){
		return inCredit;
	}
}
