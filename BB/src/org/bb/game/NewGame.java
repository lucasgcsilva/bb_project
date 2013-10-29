package org.bb.game;

import org.bb.main.Main;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class NewGame{
	public NewGame(Main main) throws SlickException{
		AppGameContainer app = new AppGameContainer(new Game(), main.width, main.height, false);
		app.setVSync(true);
		app.start();
	}
}
