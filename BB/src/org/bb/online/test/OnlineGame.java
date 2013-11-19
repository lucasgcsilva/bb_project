package org.bb.online.test;

import org.bb.game.player.Player;
import org.bb.main.Main;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class OnlineGame extends BasicGame {
	private Main main;
	private Player[] players = new Player[5];
	private int numPlayer;
	private Image graphics = null;
	private ClientThread client;
	
	
	
	public OnlineGame(Main main) throws SlickException{
		super("BattleStadium Online");
		this.main = main;
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		if (graphics != null){
			g.drawImage(graphics, 0f, 0f);
		}else{
			g.drawString("Deu Erro Bruninho,  :( ", 300f, 500f);
		}
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		client = new ClientThread();
		client.start();
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		graphics = client.getImage();
	}

}
