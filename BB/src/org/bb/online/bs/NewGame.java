package org.bb.online.bs;

import org.bb.main.Main;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class NewGame extends Thread{
	private Main main;
	private AppGameContainer app;
	public NewGame(Main main){
		this.main = main;
		app = null;
	}
	public void run(){
		try{
			boolean fs = Boolean.parseBoolean(main.sp.fullScreen);
			int width, height;
			if (fs){
				width = main.fWidth;
				height = main.fHeight;
			}else{
				width = main.width;
				height = main.height;
			}
			
			
			app = new AppGameContainer(new Game(main));
			app.setDisplayMode(width, height, fs);
			app.setVSync(true);
			app.setShowFPS(false);
			app.setTargetFrameRate(40);
			app.setForceExit(true);
			app.start();		
		}catch(SlickException e){
			e.printStackTrace();
		}
	}
	public void destroyApp(){
		app.destroy();
	}
}
