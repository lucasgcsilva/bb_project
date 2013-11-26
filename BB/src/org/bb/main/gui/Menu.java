package org.bb.main.gui;

import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.bb.main.Main;
import org.bb.sound.MusicPlayer;
import org.newdawn.easyogg.OggClip;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Menu extends JPanel{
	Image background = new ImageIcon("resources/images/bg.jpg").getImage();
	OggClip clip;
	Main main;
	LoginPanel lp;
	Buttons btns;
	MusicPlayer mp;
	JPanel panelAux;
	
	public Menu(Main m){
		setSize(main.width,main.height);
		setLayout(new BorderLayout());
		main = m;
		
		btns =  new Buttons(m);
		lp = new LoginPanel(btns, main);
		panelAux = new JPanel();
		panelAux.setOpaque(false);
		panelAux.setLayout(new BorderLayout());
		panelAux.add(lp, BorderLayout.SOUTH);
		
		add(btns, BorderLayout.EAST);
		add(panelAux, BorderLayout.WEST);
		
		setVisible(true);
//		try {
//			clip = new OggClip("resources/musics/menu");
//			clip.loop();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		mp = new MusicPlayer("resources/musics/menu.wav", true);
		mp.start();
	}
	public void setEnabledPanels(Boolean b){
		btns.setEnabled(b);
		btns.setEnabledButtons(b);
		lp.setEnabled(b);
		lp.setEnabledComponents(b);
		panelAux.setEnabled(b);
		this.setEnabled(b);
	}
	
	public void stopMusic(){
		mp.stopMusic();
	}
	
	protected void paintComponent(Graphics g)  {          
		super.paintComponent(g);  
        Graphics2D g2d = (Graphics2D)g.create();  
        g2d.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);  
        g2d.dispose();          
    } 
	
}
