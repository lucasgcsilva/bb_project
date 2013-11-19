package org.bb.online.test;

import org.bb.game.Game;
import org.bb.main.Main;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class NewOnlineGame {
	public NewOnlineGame(Main main) throws SlickException{
		boolean fs = Boolean.parseBoolean(main.sp.fullScreen);
		int width, height;
		if (fs){
			width = main.fWidth;
			height = main.fHeight;
		}else{
			width = main.width;
			height = main.height;
		}
		AppGameContainer app = new AppGameContainer(new OnlineGame(main));
		app.setDisplayMode(width, height, fs);
		app.setVSync(true);
		app.setShowFPS(false);
		app.start();
	}
}
