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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import org.bb.main.Main;

public class Cadastro extends JPanel{
	
	public Main main;
	protected Timer tmr;
	protected AlphaComposite ac;
	protected float alphaValue = 1.0f;
	
	protected JLabel lblUsername = new JLabel("Nome do Usuario :");
	protected JLabel lblSenha = new JLabel("Senha do Usuario :");
	protected JLabel lblConfirmSenha = new JLabel("Confirmacao da Senha :");
	protected JLabel lblEmail = new JLabel("E-mail do Usuario :");
	protected JTextField txtUsername = new JTextField("Oh Long Johnson!");
	protected JTextField txtSenha = new JTextField("");
	protected JTextField txtConfirmSenha = new JTextField("");
	protected JTextField txtEmail = new JTextField("");
	protected JButton btnEnviar = new JButton("Enviar");
	protected JButton btnCancelar = new JButton("Cancelar");
	
	int wInit = 0;
	int yInit = (main.height/2)-10;
	int wFinal = wInit+10;
	int yFinal = wFinal+10;
	boolean key = false;
	int a = 255;
	int w, h;
	
	public Cadastro (Main m){
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
		lblUsername.setForeground(Color.black);
		lblUsername.setFont(main.getFonte(main.fonteBomberman, 30));
		lblSenha.setForeground(Color.black);
		lblSenha.setFont(main.getFonte(main.fonteBomberman, 30));
		lblConfirmSenha.setForeground(Color.black);
		lblConfirmSenha.setFont(main.getFonte(main.fonteBomberman, 30));
		lblEmail.setForeground(Color.black);
		lblEmail.setFont(main.getFonte(main.fonteBomberman, 30));
//		addComponents();
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
		g.setColor(Color.white);
		Graphics2D g2d = (Graphics2D)g.create();  
        g2d.drawImage(new ImageIcon("resources/images/bg.jpg").getImage(), 0, 0, w, h, null);
        g.setColor(new Color(255,255,255,200) );
        g.fillRect(0, 0, w, h);
		if (a > 50){
			a-=10;
		}else if (a < 50 && a > 20){
			addComponents();
		}else{
			
		}
//		if (key){
//			revalidate();
//
//		}else{
//			BufferedImage buffImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
//			Graphics2D gg = buffImg.createGraphics();
//			if (wFinal < w){
//				wFinal+=20;
//			}
//			if (wFinal == w && yFinal < h){
//				yInit -= 10;
//				yFinal += 20;
//				if (alphaValue > 0.20f){
//					alphaValue -= 0.05f;
//				}
//				if (a > 30){
//					a -= 10;
//				}
//			}
//			if (wFinal > w){
//				wFinal = w;
//			}
//			if (yFinal > h){
//				yFinal = h;
//			}
//			if (yInit < 0){
//				yInit = 0;
//			}
//			if (alphaValue < 0.20f){
//				alphaValue = 0.20f;
//			}
//			g.setColor(new Color(255, 255, 0, a));
//			g.fillRect(wInit, yInit, wFinal, yFinal);
//			if (wFinal == w && yFinal == h){
//				if (key == false){
//					key = true;
//					addComponents();
//					tmr.stop();
//				}			
//			}
//		}
		
	}
	
	private void addComponents(){
		a = 10;
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.CENTER;
		Insets insets = new  Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = insets;
		add(lblUsername, gbc);
		gbc.gridx = 1;
		add(txtUsername, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(lblSenha, gbc);
		gbc.gridx = 1;
		add(txtSenha, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(lblConfirmSenha, gbc);
		gbc.gridx = 1;
		add(txtConfirmSenha, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(lblEmail, gbc);
		gbc.gridx = 1;
		add(txtEmail, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		add(btnCancelar, gbc);
		gbc.gridx = 1;
		add(btnEnviar, gbc);
		
		validate();
	}
}
