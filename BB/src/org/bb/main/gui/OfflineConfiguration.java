package org.bb.main.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.bb.main.Main;
import org.bb.game.*;
import org.newdawn.slick.SlickException;

public class OfflineConfiguration extends JPanel{
	private Image background = new ImageIcon("resources/images/bg3.jpg").getImage();
	private Main main;
	private GameConfiguration gc = GameConfiguration.getGameConfiguration();
	private JLabel lblMap = new JLabel("Mapa:");
	private JLabel lblPlayers = new JLabel ("Jogadores:");
	private JLabel lblTime = new JLabel("Tempo de jogo:");
	private JLabel lblTrofeus = new JLabel("Quantidade de Trofeus");
	private JButton btnStart = new JButton("Jogar!");
	private JButton btnVoltar = new JButton ("Voltar");
	private JRadioButton rb2m = new JRadioButton("2:00", true);
	private JRadioButton rb3m = new JRadioButton("3:00", false);
	private JRadioButton rb5m = new JRadioButton("5:00", false);
	private ButtonGroup btngTime;
	
	public OfflineConfiguration (Main m){
		this.main = m;
		setSize(main.width, main.height);
		
		//Setando Labels
		lblMap.setForeground(Color.white);
		lblMap.setFont(main.getFonte(main.fonteBomberman, 30));
		lblPlayers.setForeground(Color.white);
		lblPlayers.setFont(main.getFonte(main.fonteBomberman, 30));
		lblTime.setForeground(Color.white);
		lblTime.setFont(main.getFonte(main.fonteBomberman, 30));
		lblTrofeus.setForeground(Color.white);
		lblTrofeus.setFont(main.getFonte(main.fonteBomberman, 30));
		btnStart.setForeground(Color.black);
		btnStart.setFont(main.getFonte(main.fonteBomberman, 30));
		btnVoltar.setForeground(Color.black);
		btnVoltar.setFont(main.getFonte(main.fonteBomberman, 30));
		rb2m.setForeground(Color.black);
		rb2m.setFont(main.getFonte(main.fonteBomberman, 30));
		rb3m.setForeground(Color.black);
		rb3m.setFont(main.getFonte(main.fonteBomberman, 30));
		rb5m.setForeground(Color.black);
		rb5m.setFont(main.getFonte(main.fonteBomberman, 30));
		
		//Setando Button Group
		btngTime = new ButtonGroup();
		btngTime.add(rb2m);
		btngTime.add(rb3m);
		btngTime.add(rb5m);		
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.CENTER;
		Insets insets = new  Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = insets;
		add(lblMap, gbc);
		
		gbc.gridx = 4;
		add(lblPlayers, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		add(lblTrofeus, gbc);
		
		gbc.gridx = 4;
		add(lblTime, gbc);
		gbc.gridy = 5;
		add(rb2m, gbc);
		gbc.gridy = 6;
		add(rb3m, gbc);
		gbc.gridy = 7;
		add(rb5m, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 8;
		add(btnStart, gbc);
		
		gbc.gridx = 4;
		add(btnVoltar, gbc);
		
		
		btnVoltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				main.addMenu();
			}
		});
		
		btnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (rb2m.isSelected()){
					gc.setTime(120);
				}else if (rb3m.isSelected()){
					gc.setTime(180);
				}else if (rb5m.isSelected()){
					gc.setTime(300);
				}
				try {
					main.stopMusicMenu();
					main.setVisible(false);
					NewGame newOfflineGame = new NewGame(main);
					System.out.println("New Game created!");
				} catch (SlickException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();

				}
			}
		});
		
		
	}
	
	protected void paintComponent(Graphics g)  {          
	    super.paintComponent(g);  
	    Graphics2D g2d = (Graphics2D)g.create();  
	    g2d.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);  
	    g2d.dispose();          
	}
}
