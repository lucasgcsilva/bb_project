package org.bb.sound;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.SourceDataLine;



public class MusicPlayer extends Thread{
	
    private String music;
    private AudioClip sound;
    boolean isLoop = false;
    AePlayWave player;
    
    /**
     * @param filename the name of the file that is going to be played
     */
    public MusicPlayer(String arquivo, final boolean repetir){
    	music = arquivo;
    	isLoop = repetir;
    }
    
    public void run(){
    	tocar();
    }
    
    
    
 // Toca um som  
    public void tocar() {
    	player =  new AePlayWave(music);
    	while (isLoop){
    		if (!player.isAlive()){
    			player =  new AePlayWave(music);
    			player.start();
    		}
    	}
    }  
    
    public void playSound(String arquivo){
    	player =  new AePlayWave(arquivo);
        player.start();
    }
    
    public void stopMusic(){
    	isLoop = false;
    	player.stopWave();
    }
    
    
    
	
}
