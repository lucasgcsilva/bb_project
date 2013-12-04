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
import org.newdawn.slick.Input;

public class SavedPreferences {

	private Preferences savedPref = Preferences.systemNodeForPackage(org.bb.util.SavedPreferences.class);
	public String screen;
	public String volume;
	public String fullScreen;
	public static String pad1;
	public static String pad2;
	public String lastNameUsr;
	
	public int id;
	public String usrname;
	public String email;
	public boolean isLogged = false;
	public String ipTomcat;
	public String ipOnline;
	
	private Input input = new Input(480);;
	public static String PREF_SCREEN = "screen_size";
	public static String PREF_VOLUME = "volume";
	public static String PREF_FULLSCREEN = "full_screen";
	public static String PREF_LASTNAMEUSR = "last_name_usr";
	public static String PREF_HIGHSCORE = "highscore";
	public static String PREF_IP_TOMCAT = "ip_tomcat";
	public static String PREF_IP_ONLINE = "ip_online";
	
	public static String DEF_SCREEN = "1024x728";
	public static String DEF_VOLUME = "0.5f";
	public static String DEF_FULLSCREEN = "true";
	public static String DEF_PAD1 = Integer.toString(Input.KEY_UP)+"|"+Integer.toString(Input.KEY_DOWN)+"|"
			+Integer.toString(Input.KEY_LEFT)+"|"+Integer.toString(Input.KEY_RIGHT)+"|"+Integer.toString(Input.KEY_SPACE);
	public static String DEF_PAD2 = Integer.toString(Input.KEY_W)+"|"+Integer.toString(Input.KEY_S)+"|"
			+Integer.toString(Input.KEY_A)+"|"+Integer.toString(Input.KEY_D)+"|"+Integer.toString(Input.KEY_Z);
	public static String DEF_LASTNAMEUSR = "Username";
	public static String PREF_PAD = DEF_PAD1;
	public static String PREF_PAD2 = DEF_PAD2;
	public static String DEF_IP_TOMCAT = "200.236.3.203";
	public static String DEF_IP_ONLINE = "0.0.0.0";
	
	
	
	public SavedPreferences(Main main){
		System.out.println(DEF_PAD1);
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
		
		System.out.println(usrname+" "+email+" "+fullScreen+" "+lastNameUsr+" "+id+" "+pad1);
		
		
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
		pad1 = savedPref.get(PREF_PAD, DEF_PAD1);
		pad2 = savedPref.get(PREF_PAD2, DEF_PAD2);
		lastNameUsr = savedPref.get(PREF_LASTNAMEUSR, DEF_LASTNAMEUSR);
		ipTomcat = savedPref.get(PREF_IP_TOMCAT, DEF_IP_TOMCAT);
		ipOnline = savedPref.get(PREF_IP_ONLINE, DEF_IP_ONLINE);
	}
	
	public void resetDefault(){
		savedPref.put(PREF_SCREEN, DEF_SCREEN);
		savedPref.put(PREF_VOLUME, DEF_VOLUME);
		savedPref.put(PREF_FULLSCREEN, DEF_FULLSCREEN);
		savedPref.put(PREF_PAD, DEF_PAD1);
		savedPref.put(PREF_PAD2, DEF_PAD2);
		savedPref.put(PREF_LASTNAMEUSR, DEF_LASTNAMEUSR);
		savedPref.put(PREF_IP_TOMCAT, DEF_IP_TOMCAT);
		savedPref.put(PREF_IP_ONLINE, DEF_IP_ONLINE);
	}
	public void printPreferences(){
		System.out.println(screen+volume+fullScreen+pad1+lastNameUsr);
	}
	
	public void setUserLogged(){
		this.lastNameUsr = usrname;
		setSavedPreference(PREF_LASTNAMEUSR, usrname);
		this.isLogged = true;
	}
}
