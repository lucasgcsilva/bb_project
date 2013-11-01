package org.bb.game.map;

import java.util.ArrayList;
import java.util.List;

import org.bb.game.Level;
import org.bb.game.items.BombsUp;
import org.bb.game.items.KickUp;
import org.bb.game.items.RangeUp;
import org.bb.game.items.SpeedUp;
import org.bb.game.player.Player;
import org.bb.util.Info;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map {
	private TiledMap mapa;
	private Level level = Level.getLevel();
	private List<Walls> Walls;
	private int[][] wallMap;
	private int playerCount = 1;
	
	public Map(Level level){
		this.level = level;
		playerCount = 1;
	}
	
	public void loadMap(String level) {
        try {
            mapa = new TiledMap("resources/maps/" + level + ".tmx");
            Walls = new ArrayList<Walls>();
            wallMap = new int[mapa.getWidth()][];
            for (int i = 0; i < mapa.getWidth(); i++) {
                wallMap[i] = new int[mapa.getHeight()];
            }
            processMap();
        } catch (SlickException ex) {
            ex.printStackTrace();
        }
    }

    private void processMap() throws SlickException {
        for (int x = 0; x < mapa.getWidth(); x++) {
            for (int y = 0; y < mapa.getHeight(); y++) {
                wallMap[x][y] = 0;
            }
        }
        for (int i = 0; i < mapa.getObjectCount(0); i++) {
            switch (mapa.getObjectType(0, i)) {
                case "player":
                	String tileBomb;
                	if (playerCount == 1){
                		tileBomb = Info.ShiroBomb;
                	}else {
                		tileBomb = Info.KuroBomb;
                	}
                    Player hrac = new Player(playerCount, tileBomb);
                    hrac.setPosition(mapa.getObjectX(0, i), mapa.getObjectY(0, i));
                    level.addToLevel(hrac);
                    level.setPlayer(hrac, playerCount);
                    playerCount++;
                    break;
                case "bomb":
                    BombsUp bomba = new BombsUp();
                    bomba.setPosition(mapa.getObjectX(0, i), mapa.getObjectY(0, i));
                    level.addToLevel(bomba);
                    break;
                case "speed":
                    SpeedUp speed = new SpeedUp();
                    speed.setPosition(mapa.getObjectX(0, i), mapa.getObjectY(0, i));
                    level.addToLevel(speed);
                    break;
                case "kick":
                    KickUp kick = new KickUp();
                    kick.setPosition(mapa.getObjectX(0, i), mapa.getObjectY(0, i));
                    level.addToLevel(kick);
                    break;
                case "range":
                    RangeUp range = new RangeUp();
                    range.setPosition(mapa.getObjectX(0, i), mapa.getObjectY(0, i));
                    level.addToLevel(range);
                    break;
            }
        } 
        int wallX;
        int wallY;
        for (int i = 0; i < mapa.getObjectCount(1); i++) {
            wallX = mapa.getObjectX(1, i);
            wallY = mapa.getObjectY(1, i);
            wallMap[wallX / 32][wallY / 32] = 1;
            switch (mapa.getObjectType(1, i)) {
                case "wall":
                    Wall stena = new Wall();
                    Walls.add(stena);
                    stena.setPosition(wallX, wallY);
                    break;

                case "block":
                    Block blok = new Block();
                    Walls.add(blok);
                    blok.setPosition(wallX, wallY);
                    break;
            }
        }
        playerCount = 1;
    }

    public TiledMap getTiledMap() {
        return this.mapa;
    }

    public List<Walls> getWalls() {
        return Walls;
    }

    public int[][] getWallMap() {
        return wallMap;
    }
}
