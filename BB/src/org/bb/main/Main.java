package org.bb.main;

import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JFrame;
import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import org.bb.main.gui.Archievments;
import org.bb.main.gui.BBOptions;
import org.bb.main.gui.Cadastro;
import org.bb.main.gui.Init;
import org.bb.main.gui.Menu;
import org.bb.main.gui.Options;
import org.bb.main.gui.Score;
import org.bb.util.SavedPreferences;

import java.util.*;

public class Main extends JFrame {
	
	public String fonteBomberman = "resources/fonts/bm.ttf";
	Container container;
	public static int width = 800;
	public static int height = 600;
	public static int fWidth;
	public static int fHeight;
	private Init init;
	private Menu menu;
	private BBOptions bbo;
	private Archievments arch;
	private Options opt;
	private Cadastro cad;
	private Score score;
	public SavedPreferences sp;
	
	public Main(){
		super("BattleStadium Bomberman");
		container = getContentPane();
		fWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		fHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setUndecorated(true);
		sp = new SavedPreferences(this);
		setSize(width, height);
		init = new Init(this);
		container.add(init);
	}
	
    public Font getFonte(String fonte, int tamanho){  
        Font font = null;  
        try{  
            File file = new File(fonte);  
            FileInputStream fis = new FileInputStream( file );  
            font = Font.createFont( Font.TRUETYPE_FONT , fis );  
        }catch( Exception e ){  
            System.out.println( e.getMessage() );  
        }  
        font = font.deriveFont( Font.PLAIN , tamanho );  
        return font;  
    }  
	
    public void windowClosing(){
    	sp.exportPref();
    	exit();
    }
    
	public void addMenu(){
		menu = new Menu(this);
		container.removeAll();
		container.add(menu);
		container.repaint();
		container.validate();
	}
	
	public void addArchievments(){
		menu.stopMusic();
		arch = new Archievments(this);
		container.removeAll();
		container.add(arch);
		container.repaint();
		container.validate();
	}
	
	public void addOptions(){
		menu.stopMusic();
		opt = new Options(this);
		container.removeAll();
		container.add(opt);
		container.repaint();
		container.validate();
	}
	
	public void addScore(){
		menu.stopMusic();
		score = new Score(this);
		container.removeAll();
		container.add(score);
		container.repaint();
		container.validate();
	}
	
	public void addBBOptions(){
		bbo = new BBOptions(this);
		menu.setEnabled(false);
		menu.setEnabledPanels(false);
		menu.removeAll();
		container.add(bbo);
		container.repaint();
		container.validate();
	}
	
	public void addCadastro(){
		menu.stopMusic();
		cad = new Cadastro(this);
		menu.removeAll();
		container.add(cad);
		container.repaint();
		container.validate();
	}
	
	public void exit(){
		System.exit(1);
	}
	
	public void stopMusicMenu(){
		menu.stopMusic();
	}
	
	public static void main (String args[]){
		Main main = new Main();
		main.setVisible(true);
	}
	
	public void setUserConnected (int ID, String usrname, String email){
		sp.id = ID;
		sp.usrname = usrname;
		sp.email = email;
	}
	
}
