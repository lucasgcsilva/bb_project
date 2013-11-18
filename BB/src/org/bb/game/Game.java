package org.bb.game;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import jmapps.ui.VideoPanel;

import org.bb.game.player.Actors;
import org.bb.game.player.Player;
import org.bb.main.Main;
import org.bb.sound.MusicPlayer;
import org.bb.util.Info;
import org.bb.util.VideoPlayer;
import org.lwjgl.opengl.XRandR.Screen;
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
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.BufferedImageUtil;

public class Game extends BasicGame{
	public static enum GameState { PLAYING, PAUSED, FAILED, FINISHED };
	private Player player1;
	private Player player2;
	private Player player3;
	private Player player4;
	private Player player5;
    private Level level;
    private int playingTime;
    private GameScore score;
    private final String levelName = "level";
    private int dyingTime;
    private Main main;
    private MusicPlayer mus;
    private int numMaxTrofeus = 3;
    
    private int p1trofeu = 0;
    private int p2trofeu = 0;
    private int p3trofeu = 0;
    private int p4trofeu = 0;
    private int p5trofeu = 0;
    
    private TextField time;
    private TextField p1tPos;
    private TextField p2tPos;
    private TextField p3tPos;
    private TextField p4tPos;
    private TextField p5tPos;
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
	
    private Image i = null;
    private int temp = 40;
    
	public Game (Main main) throws SlickException{
		super ("BattleStadium");
		this.main = main;
		score = GameScore.getScore();
		dyingTime = 500;
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
    public void init(GameContainer gc) throws SlickException {
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
        player1 = level.getPlayer1();
        player2 = level.getPlayer2();
        player3 = level.getPlayer3();
        player4 = level.getPlayer4();
        player5 = level.getPlayer5();
        TrueTypeFont font = new TrueTypeFont(new java.awt.Font("Verdana", java.awt.Font.PLAIN, 16), true);
        time = new TextField(gc, font, level.getMap().getTimePos()[0], level.getMap().getTimePos()[1], 64, 25);
        p1tPos = new TextField(gc, font, level.getMap().getp1tPos()[0], level.getMap().getp1tPos()[1], 32, 25);
        p2tPos = new TextField(gc, font, level.getMap().getp2tPos()[0], level.getMap().getp2tPos()[1], 32, 25);
        p3tPos = new TextField(gc, font, level.getMap().getp3tPos()[0], level.getMap().getp3tPos()[1], 32, 25);
        p4tPos = new TextField(gc, font, level.getMap().getp4tPos()[0], level.getMap().getp4tPos()[1], 32, 25);
        p5tPos = new TextField(gc, font, level.getMap().getp5tPos()[0], level.getMap().getp5tPos()[1], 32, 25);
        p1tPos.setText(String.valueOf(p1trofeu));
        p2tPos.setText(String.valueOf(p2trofeu));
        p3tPos.setText(String.valueOf(p3trofeu));
        p4tPos.setText(String.valueOf(p4trofeu));
        p5tPos.setText(String.valueOf(p5trofeu));
        p1tPos.setBackgroundColor(null);
        p2tPos.setBackgroundColor(null);
        p3tPos.setBackgroundColor(null);
        p4tPos.setBackgroundColor(null);
        p5tPos.setBackgroundColor(null);
        p1tPos.setBorderColor(null);
        p2tPos.setBorderColor(null);
        p3tPos.setBorderColor(null);
        p4tPos.setBorderColor(null);
        p5tPos.setBorderColor(null);
       
        p1Life = player1.getLife();
        p1Life.setCurrentFrame(0);
        p1Life.stop();
        p1Death = player1.getDying();
        p1Death.setCurrentFrame(0);
        p1Death.stop();
        
        p2Life = player2.getLife();
        p2Life.setCurrentFrame(0);
        p2Life.stop();
        p2Death = player2.getDying();
        p2Death.setCurrentFrame(0);
        p2Death.stop();
        
        p3Life = player3.getLife();
        p3Life.setCurrentFrame(0);
        p3Life.stop();
        p3Death = player3.getDying();
        p3Death.setCurrentFrame(0);
        p3Death.stop(); 
        
        p4Life = player4.getLife();
        p4Life.setCurrentFrame(0);
        p4Life.stop();
        p4Death = player4.getDying();
        p4Death.setCurrentFrame(0);
        p4Death.stop(); 
        
        p5Life = player5.getLife();
        p5Life.setCurrentFrame(0);
        p5Life.stop();
        p5Death = player5.getDying();
        p5Death.setCurrentFrame(0);
        p5Death.stop(); 
         
        
        gc.setMusicOn(true);
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
    	level.remainPlayers = 0;
    	if(player1.isAlive){
    		level.remainPlayers++;
    	}
    	if(player2.isAlive){
    		level.remainPlayers++;
    	}
    	if(player3.isAlive){
    		level.remainPlayers++;
    	}
    	if(player4.isAlive){
    		level.remainPlayers++;
    	}
    	if(player5.isAlive){
    		level.remainPlayers++;
    	}
        Input input = gc.getInput();
        if (input.isKeyDown(Input.KEY_ESCAPE)) {
        	mus.stopMusic();
            gc.exit();
        }
        
        
        if (input.isKeyPressed(Input.KEY_P)) {
            if (level.getGameState() != GameState.PAUSED) {
                level.setGameState(GameState.PAUSED);
                playingTime = score.getPlayingTime();
            } else {
                level.setGameState(GameState.PLAYING);
            }
        }
        if (input.isKeyPressed(Input.KEY_I)){
          this.i = new Image(main.fWidth, main.fHeight);
          org.newdawn.slick.Graphics g = gc.getGraphics();
          g.copyArea(this.i, 0, 0);
          
          temp = 40;
        }
        if(level.remainPlayers <=1){
        	level.setGameState(GameState.FAILED);
        }
        System.out.println(level.getGameState());
        if (level.getGameState() == GameState.FAILED) {
            dyingTime--;
        	if(player1.isAlive && level.remainPlayers == 1){
        		player1.setCelebrate();
        	}else if(player2.isAlive && level.remainPlayers == 1){
        		player2.setCelebrate();
        	}else if(player3.isAlive && level.remainPlayers == 1){
        		player3.setCelebrate();
        	}
        	else if(player4.isAlive && level.remainPlayers == 1){
        		player4.setCelebrate();
        	}else if(player5.isAlive && level.remainPlayers == 1){
        		player5.setCelebrate();
        	}
            if (dyingTime == 0 || input.isKeyPressed(Input.KEY_ENTER) || input.isButton2Pressed(input.ANY_CONTROLLER)) {
            	if(player1.isAlive && level.remainPlayers == 1){
            		p1trofeu++;
            		System.out.println("p1 win"+p1trofeu);
            	}else if(player2.isAlive && level.remainPlayers == 1){
            		p2trofeu++;
            		System.out.println("p2 win"+p2trofeu);
            	}else if(player3.isAlive && level.remainPlayers == 1){
            		p3trofeu++;
            		System.out.println("p3 win"+p3trofeu);
            	}
            	else if(player4.isAlive && level.remainPlayers == 1){
            		p4trofeu++;
            		System.out.println("p4 win"+p4trofeu);
            	}else if(player5.isAlive && level.remainPlayers == 1){
            		p5trofeu++;
            		System.out.println("p5 win"+p5trofeu);
            	}
//                gc.exit();
            	mus.stopMusic();
            	level.reloadLevel();
                score.restart();
                init(gc);
                dyingTime = 500;
            }
        }
        if (level.getGameState() == GameState.FINISHED) {
        	mus.stopMusic();
            player1.setStopTime(true);
            player2.setStopTime(true);
            player3.setStopTime(true);
            player4.setStopTime(true);
            player5.setStopTime(true);
            if (input.isKeyPressed(Input.KEY_ENTER)) {
                player1.setStopTime(false);
                player2.setStopTime(false);
                player3.setStopTime(false);
                player4.setStopTime(false);
                player5.setStopTime(false);
                p1trofeu = 0;
                p2trofeu = 0;
                p3trofeu = 0;
                p4trofeu = 0;
                p5trofeu = 0;
                level.reloadLevel();
                score.restart();
                init(gc);
                dyingTime = 500;
            }

        }
        if (level.getGameState() == GameState.PLAYING) {
            for (int x = 0; x < level.getListOfObjects().toArray().length; x++) {
                MapObjects o = (MapObjects) level.getListOfObjects().toArray()[x];
                if (o instanceof Actors) {
                    ((Actors) o).act();
                }
            }

        }
        if (!player1.isStopTime()){
        	playingTime = timeGame - score.getPlayingTime();
        	int minutes = playingTime / 60;
        	int seconds = playingTime % 60;
        	time.setText(String.valueOf(minutes)+":"+String.format("%02d", seconds));
        }else{
        	int minutes = 0;
        	int seconds = 0;
        	time.setText(String.valueOf(minutes)+":"+String.format("%02d", seconds));
        }
        if (playingTime == 0){
        	if (player1.isAlive){
        		player1.setDying();
        	}if(player2.isAlive){
        		player2.setDying();
        	}if(player3.isAlive){
        		player3.setDying();
        	}if(player4.isAlive){
        		player4.setDying();
        	}if(player5.isAlive){
        		player5.setDying();
        	}
        	if (!player1.getStopTime()){
        		mus.stopMusic();
        		mus = new MusicPlayer("resources/musics/lose.wav", false);	
        		mus.playSound();
        	}
        	player1.setStopTime(true);
        	level.setGameState(GameState.FAILED);
        }
        if (playingTime == 60 && !isHurryUp){
        	isHurryUp = true;
        	mus.stopMusic();
        	mus = new MusicPlayer("resources/musics/level_hurry.wav", true);
        	mus.start();
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
    		System.out.println(fsScale[0]+" "+fsScale[1]);
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
        p1tPos.render(gc, grphcs);
        p2tPos.render(gc, grphcs);
        p3tPos.render(gc, grphcs);
        p4tPos.render(gc, grphcs);
        p5tPos.render(gc, grphcs);
        if (player1.isAlive){
        	p1Life.getImage(0).draw(level.getMap().gePlayer1()[0], level.getMap().gePlayer1()[1]);
        }else{
        	p1Death.draw(level.getMap().gePlayer1()[0], level.getMap().gePlayer1()[1]);
        }
        
        if (player2.isAlive){
        	
        	p2Life.getImage(0).draw(level.getMap().gePlayer2()[0], level.getMap().gePlayer2()[1]);
        }else{
        	p2Death.draw(level.getMap().gePlayer2()[0], level.getMap().gePlayer2()[1]);
        }
        
        if (player3.isAlive){
        	p3Life.getImage(0).draw(level.getMap().gePlayer3()[0], level.getMap().gePlayer3()[1]);
        }else{
        	p3Death.draw(level.getMap().gePlayer3()[0], level.getMap().gePlayer3()[1]);
        }
        
        if (player4.isAlive){
        	p4Life.getImage(0).draw(level.getMap().gePlayer4()[0], level.getMap().gePlayer4()[1]);
        }else{
        	p4Death.draw(level.getMap().gePlayer4()[0], level.getMap().gePlayer4()[1]);
        }
        
        if (player5.isAlive){
        	p5Life.getImage(0).draw(level.getMap().gePlayer5()[0], level.getMap().gePlayer5()[1]);
        }else{
        	p5Death.draw(level.getMap().gePlayer5()[0], level.getMap().gePlayer5()[1]);
        }
        
        if (p1trofeu == numMaxTrofeus){
        	grphcs.setColor(Color.white);	
        	if (finish.isStopped()){
        		finish.start();
        	}
            finish.draw(0f, 0f, 640, 512);
            if (celebrate == null){
            	celebrate = player1.getCelebrate();
            }
            celebrate.draw((float)(640-32)/2, (float) 512/4);
        	grphcs.drawString("PLAYER 1 É O GRANDE VENCEDOR!!! OH! LONG JOHNSON!", gc.getWidth()/4-300, gc.getHeight()/4);
        	level.setGameState(GameState.FINISHED);
        }
        if (p2trofeu == numMaxTrofeus){
        	grphcs.setColor(Color.black);
        	grphcs.drawString("PLAYER 2 É O GRANDE VENCEDOR!!! OH! LONG JOHNSON!", gc.getWidth()/4-300, gc.getHeight()/4);
        	level.setGameState(GameState.FINISHED);
        }
        if (p3trofeu == numMaxTrofeus){
        	grphcs.setColor(Color.red);
        	grphcs.drawString("PLAYER 3 É O GRANDE VENCEDOR!!! OH! LONG JOHNSON!", gc.getWidth()/4-300, gc.getHeight()/4);
        	level.setGameState(GameState.FINISHED);
        }
        if (p4trofeu == numMaxTrofeus){
        	grphcs.setColor(Color.blue);
        	grphcs.drawString("PLAYER 4 É O GRANDE VENCEDOR!!! OH! LONG JOHNSON!", gc.getWidth()/4-300, gc.getHeight()/4);
        	level.setGameState(GameState.FINISHED);
        }
        if (p5trofeu == numMaxTrofeus){
        	grphcs.setColor(Color.green);
        	grphcs.drawString("PLAYER 5 É O GRANDE VENCEDOR!!! OH! LONG JOHNSON!", gc.getWidth()/4-300, gc.getHeight()/4);
        	level.setGameState(GameState.FINISHED);
        }
        if (this.i != null){
        	if (temp > 0){
        		grphcs.scale(0.99f, 0.99f);
        		grphcs.drawImage(i, 0f, 0f); 
        		temp--;
        	}
        }
    }

}
