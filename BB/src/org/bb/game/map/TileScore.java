package org.bb.game.map;

import org.bb.game.items.BombsUp;
import org.bb.game.items.KickUp;
import org.bb.game.items.RangeUp;
import org.bb.game.items.SpeedUp;
import org.bb.game.player.Player;
import org.bb.util.Info;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class TileScore {
	
	TiledMap mapa;
	
	public TileScore(){
		
	}
	
	public void loadTileScore(){
		try{
			mapa = new TiledMap("resources/maps/tileScore.tmx");
			processMap();
		}catch(SlickException e){
			e.printStackTrace();
		}
		
	}
	
	private void processMap() throws SlickException{
//		for (int i = 0; i < mapa.getObjectCount(0); i++) {
//            switch (mapa.getObjectType(0, i)) {
//                case "textfield":
//                	String tileBomb;
//                	if (playerCount == 1){
//                		tileBomb = Info.ShiroBomb;
//                	}else {
//                		tileBomb = Info.KuroBomb;
//                	}
//                    Player hrac = new Player(playerCount, tileBomb);
//                    hrac.setPosition(mapa.getObjectX(0, i), mapa.getObjectY(0, i));
//                    level.addToLevel(hrac);
//                    level.setPlayer(hrac, playerCount);
//                    playerCount++;
//                    break;
//                case "bomb":
//                    BombsUp bomba = new BombsUp();
//                    bomba.setPosition(mapa.getObjectX(0, i), mapa.getObjectY(0, i));
//                    level.addToLevel(bomba);
//                    break;
//                case "speed":
//                    SpeedUp speed = new SpeedUp();
//                    speed.setPosition(mapa.getObjectX(0, i), mapa.getObjectY(0, i));
//                    level.addToLevel(speed);
//                    break;
//                case "kick":
//                    KickUp kick = new KickUp();
//                    kick.setPosition(mapa.getObjectX(0, i), mapa.getObjectY(0, i));
//                    level.addToLevel(kick);
//                    break;
//                case "range":
//                    RangeUp range = new RangeUp();
//                    range.setPosition(mapa.getObjectX(0, i), mapa.getObjectY(0, i));
//                    level.addToLevel(range);
//                    break;
//            }
//		} 
		
	}

}
