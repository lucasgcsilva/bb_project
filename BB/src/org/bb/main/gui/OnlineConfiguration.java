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
import javax.swing.JTextField;

import org.bb.main.Main;
import org.bb.online.bs.*;
import org.newdawn.slick.SlickException;

public class OnlineConfiguration extends JPanel{
	private Image background = new ImageIcon("resources/images/bg3.jpg").getImage();
	private Main main;
	private GameConfiguration gc = GameConfiguration.getGameConfiguration();
	private JLabel lblMap = new JLabel("Mapa:");
	private JLabel lblPlayers = new JLabel ("Jogadores:");
	private JLabel lblTime = new JLabel("Tempo de jogo:");
	private JLabel lblTrofeus = new JLabel("Quantidade de Trofeus");
	private JButton btnVoltar = new JButton ("Voltar");
	private JRadioButton rb2m = new JRadioButton("2:00", true);
	private JRadioButton rb3m = new JRadioButton("3:00", false);
	private JRadioButton rb5m = new JRadioButton("5:00", false);
	private ButtonGroup btngTime;
	private JRadioButton rb3t = new JRadioButton("3", true);
	private JRadioButton rb5t = new JRadioButton("5", true);
	private ButtonGroup btngTrofeu;
	private JTextField tf = new JTextField("");
	private JButton btnClient = new JButton("Conectar ao IP");
	private JButton btnServer = new JButton("Iniciar como servidor");
	
	public OnlineConfiguration (Main m){
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
		btnVoltar.setForeground(Color.black);
		btnVoltar.setFont(main.getFonte(main.fonteBomberman, 30));
		rb2m.setForeground(Color.black);
		rb2m.setFont(main.getFonte(main.fonteBomberman, 30));
		rb3m.setForeground(Color.black);
		rb3m.setFont(main.getFonte(main.fonteBomberman, 30));
		rb5m.setForeground(Color.black);
		rb5m.setFont(main.getFonte(main.fonteBomberman, 30));
		rb3t.setForeground(Color.black);
		rb3t.setFont(main.getFonte(main.fonteBomberman, 30));
		rb5t.setForeground(Color.black);
		rb5t.setFont(main.getFonte(main.fonteBomberman, 30));
		btnClient.setForeground(Color.black);
		btnClient.setFont(main.getFonte(main.fonteBomberman, 30));
		btnServer.setForeground(Color.black);
		btnServer.setFont(main.getFonte(main.fonteBomberman, 30));
		
		//Setando Button Group
		btngTime = new ButtonGroup();
		btngTime.add(rb2m);
		btngTime.add(rb3m);
		btngTime.add(rb5m);		
		btngTrofeu = new ButtonGroup();
		btngTrofeu.add(rb3t);
		btngTrofeu.add(rb5t);
		
		tf.setText(main.sp.getSavedPreference(main.sp.PREF_IP_ONLINE, main.sp.DEF_IP_ONLINE));
		tf.setColumns(40);
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.CENTER;
		Insets insets = new  Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = insets;
		add(lblMap, gbc);
		
		gbc.gridx = 7;
		add(lblPlayers, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 7;
		add(lblTrofeus, gbc);
		
		gbc.gridx = 7;
		gbc.gridwidth = 1;
		add(lblTime, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridy = 5;
		gbc.gridx = 0;
		add(rb3t, gbc);
		gbc.gridx = 7;
		add(rb2m, gbc);
		gbc.gridy = 6;
		gbc.gridx = 0;
		add(rb5t, gbc);
		gbc.gridx = 7;
		add(rb3m, gbc);
		gbc.gridy = 7;
		add(rb5m, gbc);
				
				
		gbc.gridx = 0;
		gbc.gridwidth = 7;
		gbc.gridy = 8;
		add(tf, gbc);
		gbc.gridheight = 1;
		gbc.gridx = 7;
		gbc.gridy = 8;
		add(btnClient, gbc);
		gbc.gridx = 0;
		gbc.gridy = 9;
		add(btnVoltar, gbc);
		gbc.gridx = 7;
		add(btnServer, gbc);
		
		
		btnVoltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				main.addMenu();
			}
		});
		
		btnClient.addActionListener(new ActionListener() {
			
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
				
				if (rb3t.isSelected()){
					gc.setQtdeTrofeus(3);
				}else if (rb5t.isSelected()){
					gc.setQtdeTrofeus(5);
				}
				main.sp.setSavedPreference(main.sp.PREF_IP_ONLINE, tf.getText());
				gc.setIp(tf.getText());
				gc.setTypeConn(gc.TYPE_CLIENT);
				gc.setNumPlayer(2);
				try {
					main.stopMusicMenu();
					main.setVisible(false);
					
					NewGame newOnineGame = new NewGame(main);
					System.out.println("New Game created!");
				} catch (SlickException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();

				}
			}
		});
		
		btnServer.addActionListener(new ActionListener() {
			
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
				
				if (rb3t.isSelected()){
					gc.setQtdeTrofeus(3);
				}else if (rb5t.isSelected()){
					gc.setQtdeTrofeus(5);
				}
				gc.setTypeConn(gc.TYPE_SERVER);
				gc.setNumPlayer(1);
				try {
					main.stopMusicMenu();
					main.setVisible(false);
					
					NewGame newOnineGame = new NewGame(main);
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
