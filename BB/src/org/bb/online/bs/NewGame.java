package org.bb.online.bs;

import org.bb.main.Main;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class NewGame{
	public NewGame(Main main) throws SlickException{
		boolean fs = Boolean.parseBoolean(main.sp.fullScreen);
		int width, height;
		if (fs){
			width = main.fWidth;
			height = main.fHeight;
		}else{
			width = main.width;
			height = main.height;
		}
		AppGameContainer app = new AppGameContainer(new Game(main));
		app.setDisplayMode(width, height, fs);
		app.setVSync(true);
		app.setShowFPS(false);
		app.setTargetFrameRate(40);
		app.start();
	}
}
