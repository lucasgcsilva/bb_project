package org.bb.main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.bb.main.Main;
import org.bb.sound.MusicPlayer;

public class Score extends JPanel {
	Main main;
	Image background = new ImageIcon("resources/images/bg2.jpg").getImage();
	MusicPlayer player;
	JLabel lblScore = new JLabel("PONTUACAO");
	JButton btnVoltar = new JButton("Â«VOLTAR");
	
	public Score(Main m){
		main = m;
		setSize(main.width, main.height);
		setLayout(new BorderLayout());
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setForeground(Color.red);
		lblScore.setFont(main.getFonte(main.fonteBomberman, 40));
		add(lblScore, BorderLayout.NORTH);
		btnVoltar.setForeground(Color.red);
		btnVoltar.setFont(main.getFonte(main.fonteBomberman, 40));
		btnVoltar.setHorizontalAlignment(SwingConstants.CENTER);
		btnVoltar.setBackground(Color.BLUE);
		printScore();
		setVisible(true);
		player = new MusicPlayer("resources/musics/archievments.wav", true);//TODO: Alterar música
		player.start();
	}
	
	public void printScore(){
		JPanel pnl = new JPanel(new GridLayout(5, 5, 30, 10));
		pnl.setOpaque(false);
		pnl.setSize(main.width, main.height);
		btnVoltar.setBackground(Color.yellow);
		btnVoltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				player.stopMusic();
				main.addMenu();
			}
		});
		add(btnVoltar, BorderLayout.SOUTH);
	}
	
	protected void paintComponent(Graphics g)  {          
	    super.paintComponent(g);  
	    Graphics2D g2d = (Graphics2D)g.create();  
	    g2d.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);  
	    g2d.dispose();          
	} 
}
