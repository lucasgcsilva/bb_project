package org.bb.game;

import java.awt.Graphics;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame{
	public static enum GameState { PLAYING, PAUSED, FAILED, FINISHED };
	private int dyingTime;
	
	public Game (){
		super ("BattleStadium");
		dyingTime = 150;
	}
	
	@Override
	public void init(GameContainer gc){
		
	}
	
	@Override
	public void update(GameContainer gc, int i) throws SlickException{
		
	}

	@Override
	public void render(GameContainer gc, org.newdawn.slick.Graphics grphcs) throws SlickException {
		
		
	}

	
}
