package org.bb.game;

public class GameConfiguration {
	private GameConfiguration instance = new GameConfiguration();
	
	private GameConfiguration(){
		
	}
	
	public GameConfiguration getGameConfiguration(){
		return this.instance;
	}
}
