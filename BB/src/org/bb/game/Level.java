package org.bb.game;

import java.util.ArrayList;
import java.util.List;

import org.bb.game.Game.GameState;
import org.bb.game.map.Map;
import org.bb.game.map.Wall;
import org.bb.game.map.Walls;
import org.bb.game.player.Player;

public class Level {
	private ArrayList<MapObjects> listOfObjects;
	private static Level instance = new Level();
	private Player player;
	private Map mapa = new Map(this);
	private GameState gameState;
	private int levelNumber;
	
	private Level(){
		listOfObjects = new ArrayList<MapObjects>();
        levelNumber = 1;
	}
	
	public static Level getLevel(){
		return instance;
	}
	
    public void loadLevel(String level) {
        mapa.loadMap(level);
        this.gameState=GameState.PLAYING;
    }

    public void show() {
        mapa.getTiledMap().render(0, 0);
    }

  
    public void showWalls() {
        for (Walls w : mapa.getWalls()) {
            if (w instanceof Wall) {
                w.getAnimation().draw(w.getX(), w.getY());
            }
        }
    }

   
    public Player getPlayer() {
        return player;
    }

    
    public void setPlayer(Player player) {
        this.player = player;
    }

   
    public void addToLevel(MapObjects o) {
        listOfObjects.add(o);
    }

  
    public void removeFromLevel(MapObjects o) {
        listOfObjects.remove(o);
    }

    
    public List<MapObjects> getListOfObjects() {
        return listOfObjects;
    }

    
    public Map getMap() {
        return mapa;
    }
    
    
    public void reloadLevel(){
        listOfObjects.clear();
    }

   
    public Game.GameState getGameState() {
        return gameState;
    }

   
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

   
    public int getLevelNumber() {
        return levelNumber;
    }
    
    public void incLevel() {
        this.levelNumber++;
    }
}
