package org.bb.online.bs;

import org.bb.main.*;
import org.bb.util.SavedPreferences;

public class GameScore implements Comparable<GameScore> {
	
	private static GameScore instance = new GameScore();
	private int enemiesKilled;
	private int wallsDestroyed;
	private int itemsUsed;
	private int playingTime;
	private long startTime;
	private String name;
	
	private GameScore(){
		enemiesKilled = 0;
		wallsDestroyed = 0;
		itemsUsed = 0;
		startTime = System.currentTimeMillis();
		name = "Bomberman";
	}
	
	public static void reloadScore(){
		instance = new GameScore();
	}
	
	public void loadData(String name, int time, int enemies, int walls, int items){
		this.name = name;
		this.playingTime = time;
		this.enemiesKilled = enemies;
		this.wallsDestroyed = walls;
		this.itemsUsed = items;
	}
	
	public void restart(){
		enemiesKilled = 0;
		wallsDestroyed = 0;
		itemsUsed = 0;
		startTime = System.currentTimeMillis();
	}
	
	public static GameScore getScore(){
		return instance;
	}
	
	public void incWallsDestroyed(){
		wallsDestroyed++;
	}
	
	public void incEnemiesKilled(){
		enemiesKilled++;
	}
	
	public void incItemsUsed(){
		itemsUsed++;
	}
	
	public int getPlayingTime(){
		return (int) (System.currentTimeMillis() - startTime) / 1000;
	}

	public int PlayingTime(){
		return playingTime;
	}
	
	public int getEnemiesKilled(){
		return enemiesKilled;
	}
	
	public int getWallsDestroyed(){
		return wallsDestroyed;
	}
	
	public int getItemsUsed(){
		return itemsUsed;
	}
	
	public String getName(){
		return name;
	}
	
	@Override
	public int compareTo(GameScore o) {
		// TODO Auto-generated method stub
		if (o.PlayingTime() > this.PlayingTime()){
			return -1;
		}else if(o.PlayingTime() < this.PlayingTime()){
			return 1;
		}else{
			if(o.getEnemiesKilled() > this.getEnemiesKilled()){
				return -1;
			}else if (o.getEnemiesKilled() < this.getEnemiesKilled()){
				return 1;
			}else{
				return 0;
			}
		}
	}

}
