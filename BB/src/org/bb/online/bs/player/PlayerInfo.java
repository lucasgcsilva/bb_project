package org.bb.online.bs.player;

import java.io.Serializable;

public class PlayerInfo implements Serializable{
	
	public class PlayerData{
    	private boolean keyUp;
    	private boolean keyDown;
    	private boolean keyLeft;
    	private boolean keyRight;
    	private boolean keyBomb;
    	
    	public boolean isKeyUp() {
			return keyUp;
		}

		public void setKeyUp(boolean keyUp) {
			this.keyUp = keyUp;
		}

		public boolean isKeyDown() {
			return keyDown;
		}

		public void setKeyDown(boolean keyDown) {
			this.keyDown = keyDown;
		}

		public boolean isKeyLeft() {
			return keyLeft;
		}

		public void setKeyLeft(boolean keyLeft) {
			this.keyLeft = keyLeft;
		}

		public boolean isKeyRight() {
			return keyRight;
		}

		public void setKeyRight(boolean keyRight) {
			this.keyRight = keyRight;
		}
		
		public boolean isKeyBomb() {
			return keyBomb;
		}

		public void setKeyBomb(boolean keyBomb) {
			this.keyBomb = keyBomb;
		}

		public PlayerData(){
    		keyUp = false;
    		keyDown = false;
    		keyLeft = false;
    		keyRight = false;
    		keyBomb = false;
    	}
    }
	
	private static final long serialVersionUID = -2775712605115141465L;
	private int time;
	private int numPlayer;
	private static PlayerInfo instance = new PlayerInfo();
	
	
	
	public static PlayerInfo getInstance() {
		return instance;
	}

	public void setInstance(PlayerInfo instance) {
		this.instance = instance;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getNumPlayer() {
		return numPlayer;
	}

	public void setNumPlayer(int numPlayer) {
		this.numPlayer = numPlayer;
	}

	public PlayerData[] getPlayersData() {
		return playersData;
	}

	public void setPlayersData(PlayerData[] playersData) {
		this.playersData = playersData;
	}

	private PlayerData[] playersData = new PlayerData[5];
	
	private PlayerInfo(){
		time = 0;
		numPlayer = 0;
		for (int i = 0; i < playersData.length; i++){
			playersData[i] = new PlayerData();
		}
	}
	

}
