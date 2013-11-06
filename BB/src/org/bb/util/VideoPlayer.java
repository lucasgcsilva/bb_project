package org.bb.util;
import java.awt.Component;
import java.io.File;
import java.net.URL;

import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;

public class VideoPlayer {
	Component video;
	Player mediaPlayer;
	public VideoPlayer(String path) {
		try {
			mediaPlayer=Manager.createPlayer(new MediaLocator("file:///"+path));
			this.video = mediaPlayer.getVisualComponent();
			mediaPlayer.start();
			
		} catch (Exception e) {
			e.printStackTrace();
 
		}
	 
	}
	
	public Component getVideo(){
		return this.video;
	}
}