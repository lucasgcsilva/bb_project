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
	private Image graphics;
	private float[] scale = new float[2];
	
	
	
	public OnlineGame(Main main) throws SlickException{
		super("BattleStadium Online");
		this.main = main;
		scale[0] = (float) main.fWidth/640;
		scale[1] = (float) main.fHeight/512;
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.scale(scale[0], scale[1]);
		g.drawImage(graphics, 0f, 0f);
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}

}
