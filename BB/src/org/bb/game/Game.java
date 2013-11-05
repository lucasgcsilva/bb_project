package org.bb.game;

import java.awt.Graphics;

import org.bb.game.player.Actors;
import org.bb.game.player.Player;
import org.bb.main.Main;
import org.bb.sound.MusicPlayer;
import org.bb.util.Info;
import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;

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
    
	
	public Game (Main main){
		super ("BattleStadium");
		this.main = main;
		score = GameScore.getScore();
		dyingTime = 500;
		mus = new MusicPlayer("resources/musics/archievments.wav", true);
		mus.start();
		
	}
	
	@Override
    public void init(GameContainer gc) throws SlickException {
        level = Level.getLevel();
        level.loadLevel(levelName + level.getLevelNumber());
        level.setGameContainer(gc);
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
        SpriteSheet sheet = new SpriteSheet(Info.ShiroBomb, 32, 32);
        p1Life = new Animation(Anim.getSpriteSheetAnimation(sheet, 8, 0), 200);
        p1Life.setCurrentFrame(0);
        p1Life.stop();
        p1Death = new Animation(Anim.getSpriteSheetAnimation(sheet, 5, 5), 300);
        p1Death.setCurrentFrame(0);
        p1Death.stop();
        
        sheet = new SpriteSheet(Info.KuroBomb, 32, 32);
        p2Life = new Animation(Anim.getSpriteSheetAnimation(sheet, 8, 0), 200);
        p2Life.setCurrentFrame(0);
        p2Life.stop();
        p2Death = new Animation(Anim.getSpriteSheetAnimation(sheet, 5, 5), 300);
        p2Death.setCurrentFrame(0);
        p2Death.stop();
        
        sheet = new SpriteSheet(Info.AkaBomb, 32, 32);
        p3Life = new Animation(Anim.getSpriteSheetAnimation(sheet, 8, 0), 200);
        p3Life.setCurrentFrame(0);
        p3Life.stop();
        p3Death = new Animation(Anim.getSpriteSheetAnimation(sheet, 5, 5), 300);
        p3Death.setCurrentFrame(0);
        p3Death.stop(); 
        
        sheet = new SpriteSheet(Info.AoBomb, 32, 32);
        p4Life = new Animation(Anim.getSpriteSheetAnimation(sheet, 8, 0), 200);
        p4Life.setCurrentFrame(0);
        p4Life.stop();
        p4Death = new Animation(Anim.getSpriteSheetAnimation(sheet, 5, 5), 300);
        p4Death.setCurrentFrame(0);
        p4Death.stop(); 
        
        sheet = new SpriteSheet(Info.MidoriBomb, 32, 32);
        p5Life = new Animation(Anim.getSpriteSheetAnimation(sheet, 8, 0), 200);
        p5Life.setCurrentFrame(0);
        p5Life.stop();
        p5Death = new Animation(Anim.getSpriteSheetAnimation(sheet, 5, 5), 300);
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
        if(level.remainPlayers <=1){
        	level.setGameState(GameState.FAILED);
        }
        System.out.println(level.getGameState());
        if (level.getGameState() == GameState.FAILED) {
            dyingTime--;
        	if(player1.isAlive && !player2.isAlive && !player3.isAlive && !player4.isAlive && !player5.isAlive){
        		player1.setCelebrate();
        	}else if(!player1.isAlive && player2.isAlive && !player3.isAlive && !player4.isAlive && !player5.isAlive){
        		player2.setCelebrate();
        	}else if(!player1.isAlive && !player2.isAlive && player3.isAlive && !player4.isAlive && !player5.isAlive){
        		player3.setCelebrate();
        	}
        	else if(!player1.isAlive && !player2.isAlive && !player3.isAlive && player4.isAlive && !player5.isAlive){
        		player4.setCelebrate();
        	}else if(!player1.isAlive && !player2.isAlive && !player3.isAlive && !player4.isAlive && player5.isAlive){
        		player5.setCelebrate();
        	}
            if (dyingTime == 0 || input.isKeyPressed(Input.KEY_ENTER) || input.isButton2Pressed(input.ANY_CONTROLLER)) {
            	if(player1.isAlive && !player2.isAlive && !player3.isAlive && !player4.isAlive && !player5.isAlive){
            		p1trofeu++;
            		System.out.println("p1 win"+p1trofeu);
            	}else if(!player1.isAlive && player2.isAlive && !player3.isAlive && !player4.isAlive && !player5.isAlive){
            		p2trofeu++;
            		System.out.println("p2 win"+p2trofeu);
            	}else if(!player1.isAlive && !player2.isAlive && player3.isAlive && !player4.isAlive && !player5.isAlive){
            		p3trofeu++;
            		System.out.println("p3 win"+p3trofeu);
            	}
            	else if(!player1.isAlive && !player2.isAlive && !player3.isAlive && player4.isAlive && !player5.isAlive){
            		p4trofeu++;
            		System.out.println("p4 win"+p4trofeu);
            	}else if(!player1.isAlive && !player2.isAlive && !player3.isAlive && !player4.isAlive && player5.isAlive){
            		p5trofeu++;
            		System.out.println("p5 win"+p5trofeu);
            	}
//                gc.exit();
            	level.reloadLevel();
                score.restart();
                init(gc);
                dyingTime = 500;
            }
        }

        if (level.getGameState() == GameState.FINISHED) {
            if (!player1.isStopTime() && level.getLevelNumber() < 5) {
                playingTime = score.getPlayingTime();
                int minutes = playingTime / 60;
                int seconds = playingTime % 60;
            }
            player1.setStopTime(true);
            if (input.isKeyPressed(Input.KEY_ENTER)) {
                player1.setStopTime(false);
                level.incLevel();
                if (level.getLevelNumber() == 5) {
                    System.exit(0);
                }
                level.reloadLevel();
                score.restart();
                init(gc);
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
        playingTime = score.getPlayingTime();
        int minutes = playingTime / 60;
        int seconds = playingTime % 60;
        time.setText(String.valueOf(minutes)+":"+String.format("%02d", seconds));
    }

    @Override
    public void render(GameContainer gc, org.newdawn.slick.Graphics grphcs) throws SlickException {
    	
    	if (gc.isFullscreen()){
    		grphcs.translate((main.fWidth-1024)/2, 20);
    		grphcs.scale(1.4f, 1.4f);
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
        

        if (level.getGameState() == GameState.FINISHED && level.getLevelNumber() < 4) {
            grphcs.setColor(Color.green);
            grphcs.drawString("Level " + level.getLevelNumber() + " completed", 240, 8);
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
        	p1Life.draw(level.getMap().gePlayer1()[0], level.getMap().gePlayer1()[1]);
        }else{
        	p1Death.draw(level.getMap().gePlayer1()[0], level.getMap().gePlayer1()[1]);
        }
        
        if (player2.isAlive){
        	p2Life.draw(level.getMap().gePlayer2()[0], level.getMap().gePlayer2()[1]);
        }else{
        	p2Death.draw(level.getMap().gePlayer2()[0], level.getMap().gePlayer2()[1]);
        }
        
        if (player3.isAlive){
        	p3Life.draw(level.getMap().gePlayer3()[0], level.getMap().gePlayer3()[1]);
        }else{
        	p3Death.draw(level.getMap().gePlayer3()[0], level.getMap().gePlayer3()[1]);
        }
        
        if (player4.isAlive){
        	p4Life.draw(level.getMap().gePlayer4()[0], level.getMap().gePlayer4()[1]);
        }else{
        	p4Death.draw(level.getMap().gePlayer4()[0], level.getMap().gePlayer4()[1]);
        }
        
        if (player5.isAlive){
        	p5Life.draw(level.getMap().gePlayer5()[0], level.getMap().gePlayer5()[1]);
        }else{
        	p5Death.draw(level.getMap().gePlayer5()[0], level.getMap().gePlayer5()[1]);
        }
    }

}
