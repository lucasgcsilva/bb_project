package org.bb.main.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.bb.main.Main;

public class OfflineConfiguration extends JPanel{
	private Image background = new ImageIcon("resources/images/bg3.jpg").getImage();
	private JLabel lblMap = new JLabel("Mapa:");
	private JLabel lblPlayers = new JLabel ("Jogadores:");
	private JLabel lblTime = new JLabel("Tempo de jogo:");
	private JLabel lblTrofeus = new JLabel("Quantidade de Trofeus");
	private JButton btnStart = new JButton("Começar!");
	
	public OfflineConfiguration (Main main){
		setSize(main.width, main.height);
		
		//Setando Labels
		lblMap.setForeground(Color.white);
		lblMap.setFont(main.getFonte(main.fonteBomberman, 40));
		lblPlayers.setForeground(Color.white);
		lblPlayers.setFont(main.getFonte(main.fonteBomberman, 40));
		lblTime.setForeground(Color.white);
		lblTime.setFont(main.getFonte(main.fonteBomberman, 40));
		lblTrofeus.setForeground(Color.white);
		lblTrofeus.setFont(main.getFonte(main.fonteBomberman, 40));
		btnStart.setForeground(Color.white);
		btnStart.setFont(main.getFonte(main.fonteBomberman, 40));
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.CENTER;
		Insets insets = new  Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = insets;
		
	}
	
	protected void paintComponent(Graphics g)  {          
	    super.paintComponent(g);  
	    Graphics2D g2d = (Graphics2D)g.create();  
	    g2d.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);  
	    g2d.dispose();          
	}
}
