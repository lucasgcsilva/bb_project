package org.bb.main.gui;

import java.applet.AudioClip;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.bb.main.Main;
import org.bb.sound.MusicPlayer;


public class Init extends JPanel implements ActionListener {
	
	protected Timer tmr;
	
	protected int contTimer=0;
	float alphaValue = 0.0f;
	public AlphaComposite ac;
	boolean key;
	Main main;
	MusicPlayer mp;
	int w, h;
	
	public Init(Main m){
		tmr = new Timer(200, this);
		tmr.start();
		setSize(main.width, main.height);
		setVisible(true);
		main = m;
		key = false;
		if (Boolean.parseBoolean(main.sp.fullScreen)){
			w = main.fWidth;
			h = main.fHeight;
		}else{
			w = main.width;
			h = main.height;
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		BufferedImage buffImg = new BufferedImage(main.width, main.height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D gg = buffImg.createGraphics();
		System.out.println(contTimer);
		contTimer++;
		ac = AlphaComposite.getInstance(AlphaComposite.CLEAR, alphaValue);
		gg.setComposite(ac);
		if (contTimer <= 20){
			ImageIcon img = new ImageIcon("resources/images/hudson_logo.png");
			g.drawImage(img.getImage(), 0, 0, w, h, this );
			g.setFont(new Font ("Arial", Font.PLAIN, 36));
			g.setColor(Color.blue);
			FontMetrics fm = new FontMetrics(g.getFont()){};
			int strW = (int) fm.getStringBounds("Este é um jogo gratuito feito por fãs.", null).getWidth();
			g.drawString("Este é um jogo gratuito feito por fãs", (w/2)-(strW/2), (h/3)*2);
			strW = (int) fm.getStringBounds("Todos os Direitos da Marca Bomberman e Personagens", null).getWidth();
			int strH = (int) fm.getStringBounds("Todos os Direitos da Marca Bomberman e Personagens", null).getHeight();
			g.drawString("Todos os direitos da marca Bomberman e personagens", (w/2)-(strW/2), (h/3)*2+strH);
			strW = (int) fm.getStringBounds("são reservados a Hudson Software.", null).getWidth();
			strH = (int) fm.getStringBounds("são reservados a Hudson Software.", null).getHeight();
			g.drawString("são reservados a Hudson Software.", (w/2)-(strW/2), (h/3)*2+(strH*2));
			
			if (contTimer == 8){
				mp = new MusicPlayer("resources/musics/hudson_intro.wav", false);
				mp.playSound("resources/musics/hudson_intro.wav");
			}
		}else if (contTimer == 21){
			alphaValue = 0;
		}else if (contTimer > 21 && contTimer <= 45){
			g.create();
			buffImg.flush();
			ImageIcon img = new ImageIcon("resources/images/l2games_logo.png");
			g.drawImage(img.getImage(), 0, 0, w, h, this );
			if (contTimer == 30){
				MusicPlayer mp = new MusicPlayer("resources/musics/l2games_intro.wav", false);
				mp.playSound("resources/musics/l2games_intro.wav");
			}
		}else if (contTimer > 45 && key == false){
			System.out.println("Enter here!");
			main.addMenu();
			key = true;
		}
		if (alphaValue < 0.9f){
			alphaValue += 0.02f;
		}
	}
	
	public void stopMusicMenu(){
		mp.stopMusic();
	}
	
	public void actionPerformed(ActionEvent actionEvent){
		repaint();
	}
	
}
