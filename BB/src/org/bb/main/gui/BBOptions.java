package org.bb.main.gui;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import org.bb.main.Main;

public class BBOptions extends JPanel{
	
	public Main main;
	protected Timer tmr;
	protected AlphaComposite ac;
	protected float alphaValue = 1.0f;
	
	JButton btnOnline = new JButton(new ImageIcon("resources/images/buttons/btn_on_o.png"));
	JButton btnOffline = new JButton(new ImageIcon("resources/images/buttons/btn_of_o.png"));
	
	int wInit = 0;
	int yInit = (main.height/2)-10;
	int wFinal = wInit+10;
	int yFinal = wFinal+10;
	boolean key = false;
	int a = 255;
	int w, h;
	
	public BBOptions (Main m){
		main = m;
		setSize(main.width, main.height);
		if (Boolean.parseBoolean(main.sp.fullScreen)){
			w = main.fWidth;
			h = main.fHeight;
		}else{
			w = main.width;
			h = main.height;
		}
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				main.stopMusicMenu();
				main.addMenu();
			}
		});
		setLayout(new GridBagLayout());
		addButons();
		setVisible(true);
		tmr = new Timer(20, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				repaint();
			}
		});
		tmr.start();
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		if (key){
			revalidate();

		}else{
			BufferedImage buffImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
			Graphics2D gg = buffImg.createGraphics();
			if (wFinal < w){
				wFinal+=20;
			}
			if (wFinal == w && yFinal < h){
				yInit -= 10;
				yFinal += 20;
				if (alphaValue > 0.20f){
					alphaValue -= 0.05f;
				}
				if (a > 30){
					a -= 10;
				}
			}
			if (wFinal > w){
				wFinal = w;
			}
			if (yFinal > h){
				yFinal = h;
			}
			if (yInit < 0){
				yInit = 0;
			}
			if (alphaValue < 0.20f){
				alphaValue = 0.20f;
			}
			g.setColor(new Color(0, 28, 209, a));
			g.fillRect(wInit, yInit, wFinal, yFinal);
			if (wFinal == w && yFinal == h){
				if (key == false){
					key = true;
					tmr.stop();
				}			
			}
		}
		
	}
	
	private void addButons(){
		System.out.println("Print Buttons");
		btnOnline.setPreferredSize(new Dimension(200, 90));
		btnOffline.setPreferredSize(new Dimension(200, 90));
		
		btnOnline.setOpaque(false);
		btnOnline.setContentAreaFilled(false);
		btnOnline.setBorderPainted(false);
		btnOffline.setOpaque(false);
		btnOffline.setContentAreaFilled(false);
		btnOffline.setBorderPainted(false);
		btnOnline.setRolloverIcon(new ImageIcon("resources/images/buttons/btn_on_e.png"));
		btnOnline.setPressedIcon(new ImageIcon("resources/images/buttons/btn_on_s.png"));
		btnOffline.setRolloverIcon(new ImageIcon("resources/images/buttons/btn_of_e.png"));
		btnOffline.setPressedIcon(new ImageIcon("resources/images/buttons/btn_of_s.png"));
		
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.CENTER;
		Insets insets = new  Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = insets;
		add(btnOnline, gbc);
		gbc.gridx = 1;
		add(btnOffline, gbc);
		validate();
	}
	
}
