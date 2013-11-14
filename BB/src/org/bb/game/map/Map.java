package org.bb.game.map;

import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.SheetCollate;

import org.bb.game.GameConfiguration;
import org.bb.game.Level;
import org.bb.game.items.BombsUp;
import org.bb.game.items.KickUp;
import org.bb.game.items.RangeUp;
import org.bb.game.items.SpeedUp;
import org.bb.game.player.Player;
import org.bb.util.Info;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.tiled.TiledMap;

public class Map {
	private TiledMap mapa;
	private Level level = Level.getLevel();
	private List<Walls> Walls;
	private int[][] wallMap;
	private int playerCount = 1;
	
	private int[] timePos = new int[2] ;
	private int[] p1tPos = new int[2] ;
	private int[] p2tPos = new int[2] ;
	private int[] p3tPos = new int[2] ;
	private int[] p4tPos = new int[2] ;
	private int[] p5tPos = new int[2] ;
	private int[] player1 = new int[2] ;
	private int[] player2 = new int[2] ;
	private int[] player3 = new int[2] ;
	private int[] player4 = new int[2] ;
	private int[] player5 = new int[2] ;
	private SpriteSheet wallSheet;
	private GameConfiguration gc = GameConfiguration.getGameConfiguration();
	
	public Map(Level level){
		this.level = level;
		playerCount = 1;
		try {
			wallSheet = new SpriteSheet(Info.wallPath, 32, 32);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadMap(String level) {
        try {
            mapa = new TiledMap(gc.getMapPath());
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
        Boolean keyboard = true, control = false;
        int numController = 0;
        for (int i = 0; i < mapa.getObjectCount(0); i++) {
            switch (mapa.getObjectType(0, i)) {
                case "player":
                	if (gc.getIsControls(playerCount))
                		numController++;
                	Player hrac = new Player(playerCount, gc.getImgPlayerPath(playerCount), gc.getIsControls(playerCount), numController, gc.getIsKeyboards(playerCount));
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
        for (int i = 0; i < mapa.getObjectCount(2); i++) {
            switch (mapa.getObjectType(2, i)) {
                case "time":
                    timePos = new int[] {mapa.getObjectX(2, i), mapa.getObjectY(2, i)};
                    break;
                case "p1trofeu":
                	p1tPos = new int[] {mapa.getObjectX(2, i), mapa.getObjectY(2, i)};
                	break;
                case "p2trofeu":
                	p2tPos = new int[] {mapa.getObjectX(2, i), mapa.getObjectY(2, i)};
                	break;
                case "p3trofeu":
                	p3tPos = new int[] {mapa.getObjectX(2, i), mapa.getObjectY(2, i)};
                	break;
                case "p4trofeu":
                	p4tPos = new int[] {mapa.getObjectX(2, i), mapa.getObjectY(2, i)};
                	break;
                case "p5trofeu":
                	p5tPos = new int[] {mapa.getObjectX(2, i), mapa.getObjectY(2, i)};
                	break;
                case "player1":
                	player1 = new int[] {mapa.getObjectX(2, i), mapa.getObjectY(2, i)};
                	break;
                case "player2":
                	player2 = new int[] {mapa.getObjectX(2, i), mapa.getObjectY(2, i)};
                	break;
                case "player3":
                	player3 = new int[] {mapa.getObjectX(2, i), mapa.getObjectY(2, i)};
                	break;
                case "player4":
                	player4 = new int[] {mapa.getObjectX(2, i), mapa.getObjectY(2, i)};
                	break;
                case "player5":
                	player5 = new int[] {mapa.getObjectX(2, i), mapa.getObjectY(2, i)};
                	break;
            }
        }
        playerCount = 1;
    }
    
    public int[] gePlayer1(){
    	return this.player1;
    }
    
    public int[] gePlayer2(){
    	return this.player2;
    }
    
    public int[] gePlayer3(){
    	return this.player3;
    }
    
    public int[] gePlayer4(){
    	return this.player4;
    }
    
    public int[] gePlayer5(){
    	return this.player5;
    }
    
    public int[] getp1tPos(){
    	return this.p1tPos;
    }
    
    public int[] getp2tPos(){
    	return this.p2tPos;
    }
    
    public int[] getp3tPos(){
    	return this.p3tPos;
    }
    
    public int[] getp4tPos(){
    	return this.p4tPos;
    }
    
    public int[] getp5tPos(){
    	return this.p5tPos;
    }
    
    public int[] getTimePos(){
    	return this.timePos;
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
