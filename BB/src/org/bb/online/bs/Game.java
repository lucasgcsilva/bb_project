package org.bb.online.bs;

import java.awt.Component;
import org.bb.online.bs.player.Actors;
import org.bb.online.bs.player.Player;
import org.bb.online.bs.player.PlayerInfo;
import org.bb.online.bs.player.PlayerInfo.PlayerData;
import org.bb.online.test.ClientThread;
import org.bb.online.test.ServerThread;
import org.bb.main.Main;
import org.bb.sound.MusicPlayer;
import org.bb.util.VideoPlayer;
import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;

public class Game extends BasicGame{
	public static enum GameState { PLAYING, PAUSED, FAILED, FINISHED };
//	private Player player1;
//	private Player player2;
//	private Player player3;
//	private Player player4;
//	private Player player5;
	private Player[] player = new Player[5];
    private Level level;
    private int playingTime;
    private GameScore score;
    private final String levelName = "level";
    private int dyingTime;
    private Main main;
    private MusicPlayer mus;
    private int numMaxTrofeus = 3;
    private PlayerInfo playerInfo = PlayerInfo.getInstance();
    private int ptrofeu[] = {0,0,0,0,0};
//    private int p2trofeu = 0;
//    private int p3trofeu = 0;
//    private int p4trofeu = 0;
//    private int p5trofeu = 0;
    
    private TextField time;
//    private TextField p1tPos;
//    private TextField p2tPos;
//    private TextField p3tPos;
//    private TextField p4tPos;
//    private TextField p5tPos;
    private TextField[] ptPos = new TextField[5];
    private Animation p1Life;
    private Animation p1Death;
    private Animation p2Life;
    private Animation p2Death;
    private Animation p3Life;
    private Animation p3Death;
    private Animation p4Life;
    private Animation p4Death;
    private Animation p5Life;
    private Animation p5Death;
    private Animation hurry;
    private VideoPlayer vp;
    private Component video;
    private boolean isHurryUp = false;
    private float[] fsScale = new float[2];
    private GameConfiguration gc = GameConfiguration.getGameConfiguration();
    private Animation finish;
    private Animation celebrate;
    private int timeGame = 65;    
    private int temp = 40;
    private ClientThread client;
    private ServerThread server;
    
	public Game (Main main) throws SlickException{
		super ("BattleStadium");
		this.main = main;
		score = GameScore.getScore();
		dyingTime = 500;
	}
	
	@SuppressWarnings("deprecation")
	@Override
    public void init(GameContainer gc) throws SlickException {
		if (this.gc.getTypeConn() == this.gc.TYPE_CLIENT){
			client = new ClientThread();
			client.start();
		}else if (this.gc.getTypeConn() == this.gc.TYPE_SERVER){
			server = new ServerThread();
			server.start();
		}
		celebrate = null;
		SpriteSheet victory = new SpriteSheet("resources/images/backVictory01.png", 256, 224);
        finish = new Animation(Anim.getSpriteSheetAnimation(victory, 2, 0), 100);
		isHurryUp = false;
		hurry = new Animation(Anim.getSpriteSheetAnimation(new SpriteSheet(new Image("resources/images/hurryup.png"), 107, 18), 6, 0), 100);
    	hurry.start();
    	mus = new MusicPlayer("resources/musics/archievments.wav", true);
		mus.start();
        level = Level.getLevel();
        level.loadLevel(levelName + level.getLevelNumber());
        level.setGameContainer(gc);
        fsScale[0] = (float) main.fWidth/640;
        fsScale[1] = (float) main.fHeight/512;
        player[0] = level.getPlayer1();
        player[1] = level.getPlayer2();
        player[2] = level.getPlayer3();
        player[3] = level.getPlayer4();
        player[4] = level.getPlayer5();
        TrueTypeFont font = new TrueTypeFont(new java.awt.Font("Verdana", java.awt.Font.PLAIN, 16), true);
        time = new TextField(gc, font, level.getMap().getTimePos()[0], level.getMap().getTimePos()[1], 64, 25);
        ptPos[0] = new TextField(gc, font, level.getMap().getp1tPos()[0], level.getMap().getp1tPos()[1], 32, 25);
        ptPos[1] = new TextField(gc, font, level.getMap().getp2tPos()[0], level.getMap().getp2tPos()[1], 32, 25);
        ptPos[2] = new TextField(gc, font, level.getMap().getp3tPos()[0], level.getMap().getp3tPos()[1], 32, 25);
        ptPos[3] = new TextField(gc, font, level.getMap().getp4tPos()[0], level.getMap().getp4tPos()[1], 32, 25);
        ptPos[4] = new TextField(gc, font, level.getMap().getp5tPos()[0], level.getMap().getp5tPos()[1], 32, 25);
        ptPos[0].setText(String.valueOf(ptrofeu[0]));
        ptPos[1].setText(String.valueOf(ptrofeu[1]));
        ptPos[2].setText(String.valueOf(ptrofeu[2]));
        ptPos[3].setText(String.valueOf(ptrofeu[3]));
        ptPos[4].setText(String.valueOf(ptrofeu[4]));
        ptPos[0].setBackgroundColor(null);
        ptPos[1].setBackgroundColor(null);
        ptPos[2].setBackgroundColor(null);
        ptPos[3].setBackgroundColor(null);
        ptPos[4].setBackgroundColor(null);
        ptPos[0].setBorderColor(null);
        ptPos[1].setBorderColor(null);
        ptPos[2].setBorderColor(null);
        ptPos[3].setBorderColor(null);
        ptPos[4].setBorderColor(null);
       
        p1Life = player[0].getLife();
        p1Life.setCurrentFrame(0);
        p1Life.stop();
        p1Death = player[0].getDying();
        p1Death.setCurrentFrame(0);
        p1Death.stop();
        
        p2Life = player[1].getLife();
        p2Life.setCurrentFrame(0);
        p2Life.stop();
        p2Death = player[1].getDying();
        p2Death.setCurrentFrame(0);
        p2Death.stop();
        
        p3Life = player[2].getLife();
        p3Life.setCurrentFrame(0);
        p3Life.stop();
        p3Death = player[2].getDying();
        p3Death.setCurrentFrame(0);
        p3Death.stop(); 
        
        p4Life = player[3].getLife();
        p4Life.setCurrentFrame(0);
        p4Life.stop();
        p4Death = player[3].getDying();
        p4Death.setCurrentFrame(0);
        p4Death.stop(); 
        
        p5Life = player[4].getLife();
        p5Life.setCurrentFrame(0);
        p5Life.stop();
        p5Death = player[4].getDying();
        p5Death.setCurrentFrame(0);
        p5Death.stop(); 
        
        
        gc.setMusicOn(true);
    }

    @SuppressWarnings("deprecation")
	@Override
    public void update(GameContainer gc, int i) throws SlickException {
    	Input input = gc.getInput();
    	if (input.isKeyDown(Input.KEY_ESCAPE)) {
			mus.stopMusic();
			gc.exit();
		}
    	if (this.playerInfo.isStartGame()){

			level.remainPlayers = 0;
			for (int cont = 0; cont < 4; cont++) {
				if (player[cont].isAlive) {
					level.remainPlayers++;
				}
			}
			
			PlayerData[] pi = playerInfo.getPlayersData();
			if (input.isKeyDown(Input.KEY_UP)) {
				pi[this.gc.getNumPlayer() - 1].setKeyUp(true);
				playerInfo.setPlayersData(pi);
			} else if (input.isKeyDown(Input.KEY_DOWN)) {
				pi[this.gc.getNumPlayer() - 1].setKeyDown(true);
				playerInfo.setPlayersData(pi);
			} else if (input.isKeyDown(Input.KEY_LEFT)) {
				pi[this.gc.getNumPlayer() - 1].setKeyLeft(true);
				playerInfo.setPlayersData(pi);
			} else if (input.isKeyDown(Input.KEY_RIGHT)) {
				pi[this.gc.getNumPlayer() - 1].setKeyRight(true);
				playerInfo.setPlayersData(pi);
			} else if (input.isKeyDown(Input.KEY_SPACE)) {
				pi[this.gc.getNumPlayer() - 1].setKeyBomb(true);
				playerInfo.setPlayersData(pi);
			} else {
				pi[this.gc.getNumPlayer() - 1].setKeyUp(false);
				pi[this.gc.getNumPlayer() - 1].setKeyDown(false);
				pi[this.gc.getNumPlayer() - 1].setKeyLeft(false);
				pi[this.gc.getNumPlayer() - 1].setKeyRight(false);
				pi[this.gc.getNumPlayer() - 1].setKeyBomb(false);
				playerInfo.setPlayersData(pi);
			}
			
			if (input.isKeyPressed(Input.KEY_P)) {
				if (level.getGameState() != GameState.PAUSED) {
					level.setGameState(GameState.PAUSED);
					playingTime = score.getPlayingTime();
				} else {
					level.setGameState(GameState.PLAYING);
				}
			}
			if (level.remainPlayers <= 1) {
				level.setGameState(GameState.FAILED);
			}
			if (level.getGameState() == GameState.FAILED) {
				dyingTime--;
				int numPlayerWin = -1;
				for (int cont = 0; cont < 4; cont++) {
					if (player[cont].isAlive && level.remainPlayers == 1) {
						player[cont].setCelebrate();
						numPlayerWin = cont;
					}
				}
				// if(player1.isAlive && level.remainPlayers == 1){
				// player1.setCelebrate();
				// }else if(player2.isAlive && level.remainPlayers == 1){
				// player2.setCelebrate();
				// }else if(player3.isAlive && level.remainPlayers == 1){
				// player3.setCelebrate();
				// }
				// else if(player4.isAlive && level.remainPlayers == 1){
				// player4.setCelebrate();
				// }else if(player5.isAlive && level.remainPlayers == 1){
				// player5.setCelebrate();
				// }
				if (dyingTime == 0 || input.isKeyPressed(Input.KEY_ENTER)
						|| input.isButton2Pressed(input.ANY_CONTROLLER)) {
					if (numPlayerWin >= 0) {
						ptrofeu[numPlayerWin]++;
						System.out
								.println("p" + ptrofeu[numPlayerWin] + " win");
					}

					// if(player1.isAlive && level.remainPlayers == 1){
					// p1trofeu++;
					// System.out.println("p1 win"+p1trofeu);
					// }else if(player2.isAlive && level.remainPlayers == 1){
					// p2trofeu++;
					// System.out.println("p2 win"+p2trofeu);
					// }else if(player3.isAlive && level.remainPlayers == 1){
					// p3trofeu++;
					// System.out.println("p3 win"+p3trofeu);
					// }
					// else if(player4.isAlive && level.remainPlayers == 1){
					// p4trofeu++;
					// System.out.println("p4 win"+p4trofeu);
					// }else if(player5.isAlive && level.remainPlayers == 1){
					// p5trofeu++;
					// System.out.println("p5 win"+p5trofeu);
					// }
					// gc.exit();
					mus.stopMusic();
					level.reloadLevel();
					score.restart();
					init(gc);
					dyingTime = 500;
				}
			}
			if (level.getGameState() == GameState.FINISHED) {
				mus.stopMusic();
				player[0].setStopTime(true);
				player[1].setStopTime(true);
				player[2].setStopTime(true);
				player[3].setStopTime(true);
				player[4].setStopTime(true);
				if (input.isKeyPressed(Input.KEY_ENTER)) {
					player[0].setStopTime(false);
					player[1].setStopTime(false);
					player[2].setStopTime(false);
					player[3].setStopTime(false);
					player[4].setStopTime(false);
					ptrofeu[0] = 0;
					ptrofeu[1] = 0;
					ptrofeu[2] = 0;
					ptrofeu[3] = 0;
					ptrofeu[4] = 0;
					level.reloadLevel();
					score.restart();
					init(gc);
					dyingTime = 500;
				}

			}
			if (level.getGameState() == GameState.PLAYING) {
				for (int x = 0; x < level.getListOfObjects().toArray().length; x++) {
					MapObjects o = (MapObjects) level.getListOfObjects()
							.toArray()[x];
					if (o instanceof Actors) {
						((Actors) o).act();
					}
				}

			}
			if (!player[0].isStopTime()) {
				playingTime = timeGame - score.getPlayingTime();
				int minutes = playingTime / 60;
				int seconds = playingTime % 60;
				time.setText(String.valueOf(minutes) + ":"
						+ String.format("%02d", seconds));
			} else {
				int minutes = 0;
				int seconds = 0;
				time.setText(String.valueOf(minutes) + ":"
						+ String.format("%02d", seconds));
			}
			if (playingTime == 0) {
				for (int cont = 0; cont < 4; cont++) {
					if (player[cont].isAlive) {
						player[cont].setDying();
					}
				}
				// if (player1.isAlive){
				// player1.setDying();
				// }if(player2.isAlive){
				// player2.setDying();
				// }if(player3.isAlive){
				// player3.setDying();
				// }if(player4.isAlive){
				// player4.setDying();
				// }if(player5.isAlive){
				// player5.setDying();
				// }
				if (!player[0].getStopTime()) {
					mus.stopMusic();
					mus = new MusicPlayer("resources/musics/lose.wav", false);
					mus.playSound();
				}
				player[0].setStopTime(true);
				level.setGameState(GameState.FAILED);
			}
			if (playingTime == 60 && !isHurryUp) {
				isHurryUp = true;
				mus.stopMusic();
				mus = new MusicPlayer("resources/musics/level_hurry.wav", true);
				mus.start();
			}
    	}
    }

    @Override
    public void render(GameContainer gc, org.newdawn.slick.Graphics grphcs) throws SlickException {
//    	System.out.println(video);
//    	BufferedImage bi = new BufferedImage(video.getWidth(), video.getHeight(), BufferedImage.TYPE_INT_ARGB);
//    	Graphics2D g2d = bi.createGraphics();
//    	video.paint(g2d);
//    	Texture texture;
//		try {
//			texture = BufferedImageUtil.getTexture("", bi);
//			Image image = new Image(texture.getImageWidth(), texture.getImageHeight());
//	    	image.setTexture(texture);
//	    	grphcs.drawImage(image, 0, 0);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	if (gc.isFullscreen()){
    		grphcs.scale(fsScale[0], fsScale[1]);
    	}
        level.show();
        for (MapObjects o : level.getListOfObjects()) {
            o.getAnimation().draw(o.getX(), o.getY());
        }
        level.showWalls();
        if (level.getLevelNumber() == 4) {
            grphcs.setColor(Color.green);
            grphcs.drawString("Final level", 270, 200);
        }
        
        if (playingTime == 60){
        	grphcs.drawAnimation(hurry, 512/2, 512/2-9);
        }
        
        
        if (level.getGameState() == GameState.FINISHED) {
            grphcs.setColor(Color.green);
            grphcs.setColor(Color.white);
        }
        
        if (level.getGameState() == GameState.FAILED) {
            grphcs.setColor(Color.red);
        }
        if (level.getGameState() == GameState.PAUSED) {
            int minutes = playingTime / 60;
            int seconds = playingTime % 60;
            grphcs.drawString("Actual playing time: " + minutes + " min " + seconds + " secs", 180, 8);

        }
        time.render(gc, grphcs);
        ptPos[0].render(gc, grphcs);
        ptPos[1].render(gc, grphcs);
        ptPos[2].render(gc, grphcs);
        ptPos[3].render(gc, grphcs);
        ptPos[4].render(gc, grphcs);
        if (player[0].isAlive){
        	p1Life.getImage(0).draw(level.getMap().gePlayer1()[0], level.getMap().gePlayer1()[1]);
        }else{
        	p1Death.draw(level.getMap().gePlayer1()[0], level.getMap().gePlayer1()[1]);
        }
        
        if (player[1].isAlive){
        	
        	p2Life.getImage(0).draw(level.getMap().gePlayer2()[0], level.getMap().gePlayer2()[1]);
        }else{
        	p2Death.draw(level.getMap().gePlayer2()[0], level.getMap().gePlayer2()[1]);
        }
        
        if (player[2].isAlive){
        	p3Life.getImage(0).draw(level.getMap().gePlayer3()[0], level.getMap().gePlayer3()[1]);
        }else{
        	p3Death.draw(level.getMap().gePlayer3()[0], level.getMap().gePlayer3()[1]);
        }
        
        if (player[3].isAlive){
        	p4Life.getImage(0).draw(level.getMap().gePlayer4()[0], level.getMap().gePlayer4()[1]);
        }else{
        	p4Death.draw(level.getMap().gePlayer4()[0], level.getMap().gePlayer4()[1]);
        }
        
        if (player[4].isAlive){
        	p5Life.getImage(0).draw(level.getMap().gePlayer5()[0], level.getMap().gePlayer5()[1]);
        }else{
        	p5Death.draw(level.getMap().gePlayer5()[0], level.getMap().gePlayer5()[1]);
        }
        
        if (ptrofeu[0] == numMaxTrofeus){
        	grphcs.setColor(Color.white);	
        	if (finish.isStopped()){
        		finish.start();
        	}
            finish.draw(0f, 0f, 640, 512);
            if (celebrate == null){
            	celebrate = player[0].getCelebrate();
            }
            celebrate.draw((float)(640-32)/2, (float) 512/4);
        	grphcs.drawString("PLAYER 1 É O GRANDE VENCEDOR!", gc.getWidth()/4-300, gc.getHeight()/4);
        	level.setGameState(GameState.FINISHED);
        }
        if (ptrofeu[1] == numMaxTrofeus){
        	grphcs.setColor(Color.black);
        	grphcs.drawString("PLAYER 2 É O GRANDE VENCEDOR!", gc.getWidth()/4-300, gc.getHeight()/4);
        	level.setGameState(GameState.FINISHED);
        }
        if (ptrofeu[2] == numMaxTrofeus){
        	grphcs.setColor(Color.red);
        	grphcs.drawString("PLAYER 3 É O GRANDE VENCEDOR!", gc.getWidth()/4-300, gc.getHeight()/4);
        	level.setGameState(GameState.FINISHED);
        }
        if (ptrofeu[3] == numMaxTrofeus){
        	grphcs.setColor(Color.blue);
        	grphcs.drawString("PLAYER 4 É O GRANDE VENCEDOR!", gc.getWidth()/4-300, gc.getHeight()/4);
        	level.setGameState(GameState.FINISHED);
        }
        if (ptrofeu[4] == numMaxTrofeus){
        	grphcs.setColor(Color.green);
        	grphcs.drawString("PLAYER 5 É O GRANDE VENCEDOR!", gc.getWidth()/4-300, gc.getHeight()/4);
        	level.setGameState(GameState.FINISHED);
        }
        

    }
    

}
