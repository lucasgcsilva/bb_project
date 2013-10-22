package org.bb.util;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.prefs.Preferences;

import org.bb.main.Main;
import org.bb.main.gui.LoginPanel;
import org.bb.main.gui.Options;
import org.bb.sound.MusicPlayer;
import org.bb.sound.VolumeControl;

public class SavedPreferences {

	private Preferences savedPref = Preferences.systemNodeForPackage(org.bb.util.SavedPreferences.class);
	public String screen;
	public String volume;
	public String fullScreen;
	public String pad;
	public String pad2;
	public String lastNameUsr;
	
	public int id;
	public String usrname;
	public String email;
	public boolean isLogged = false;
	
	public static String PREF_SCREEN = "screen_size";
	public static String PREF_VOLUME = "volume";
	public static String PREF_FULLSCREEN = "full_screen";
	public static String PREF_PAD = "pad";
	public static String PREF_PAD2 = "pad2";
	public static String PREF_LASTNAMEUSR = "last_name_usr";
	public static String PREF_HIGHSCORE = "highscore";
	
	public static String DEF_SCREEN = "1024x728";
	public static String DEF_VOLUME = "0.5f";
	public static String DEF_FULLSCREEN = "false";
	public static String DEF_PAD = Integer.toString(KeyEvent.VK_UP)+"|"+Integer.toString(KeyEvent.VK_DOWN)+"|"
			+Integer.toString(KeyEvent.VK_LEFT)+"|"+Integer.toString(KeyEvent.VK_RIGHT)+"|"+Integer.toString(KeyEvent.VK_SPACE);
	public static String DEF_PAD2 = Integer.toString(KeyEvent.VK_UP)+"|"+Integer.toString(KeyEvent.VK_DOWN)+"|"
			+Integer.toString(KeyEvent.VK_LEFT)+"|"+Integer.toString(KeyEvent.VK_RIGHT)+"|"+Integer.toString(KeyEvent.VK_SPACE);
	public static String DEF_LASTNAMEUSR = "Username";
	
	
	
	public SavedPreferences(Main main){
		System.out.println(DEF_PAD);
		if (new File("prefs.xml").exists()){
			try{
				InputStream stream = new FileInputStream("prefs.xml");
				savedPref.importPreferences(stream);
			}catch(Exception e){
			}
		}
		updateSavedPreferences();
		//Tamanho da tela
		if (screen == Options.SIZE_800x600){
			main.width = 800;
			main.height = 600;
		}else if (screen == Options.SIZE_1024x728){
			main.width = 1024;
			main.height = 728;
		}else if (screen == Options.SIZE_1280x1024){
			main.width = 1280;
			main.height = 1024;
		}
		//Volume
		VolumeControl vc = new VolumeControl();
		vc.setMasterOutputVolume(Float.parseFloat(volume));
		//Fullscreen
		boolean fs = Boolean.parseBoolean(fullScreen);
		if (fs){
			main.setExtendedState(Frame.MAXIMIZED_BOTH);
		}else{
			main.setExtendedState(Frame.NORMAL);
		}
		//Last Name User
		LoginPanel.lastNameUser = lastNameUsr;
		
		System.out.println(usrname+" "+email+" "+fullScreen+" "+lastNameUsr+" "+id+" "+pad);
		
		
	}
	
	public String getSavedPreference (String key, String def){
		return savedPref.get(key, def);
	}
	
	public void setSavedPreference (String key, String name){
		savedPref.put(key, name);
	}
	
	public void exportPref(){
		try{
			OutputStream stream = new FileOutputStream("prefs.xml");
			savedPref.exportNode(stream);
		}catch(Exception e){
		}
		
	}
	
	public void updateSavedPreferences(){
		screen = savedPref.get(PREF_SCREEN, DEF_SCREEN);
		volume = savedPref.get(PREF_VOLUME, DEF_VOLUME);
		fullScreen = savedPref.get(PREF_FULLSCREEN, DEF_FULLSCREEN);
		pad = savedPref.get(PREF_PAD, DEF_PAD);
		pad2 = savedPref.get(PREF_PAD2, DEF_PAD2);
		lastNameUsr = savedPref.get(PREF_LASTNAMEUSR, DEF_LASTNAMEUSR);
	}
	
	public void printPreferences(){
		System.out.println(screen+volume+fullScreen+pad+lastNameUsr);
	}
	
	public void setUserLogged(){
		this.lastNameUsr = usrname;
		setSavedPreference(lastNameUsr, DEF_LASTNAMEUSR);
		this.isLogged = true;
	}
}
