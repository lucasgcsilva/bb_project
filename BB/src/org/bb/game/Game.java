package org.bb.game;

import java.awt.Graphics;

import org.bb.game.player.Actors;
import org.bb.game.player.Player;
import org.bb.main.Main;
import org.bb.sound.MusicPlayer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;

public class Game extends BasicGame{
	public static enum GameState { PLAYING, PAUSED, FAILED, FINISHED };
	private Player hrac;
    private TextField text;
    private TextField text2;
    private Level level;
    private int playingTime;
    private GameScore score;
    private final String levelName = "level";
    private int dyingTime;
    private Main main;
    private MusicPlayer mus;
	
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
        hrac = level.getPlayer();
        TrueTypeFont font = new TrueTypeFont(new java.awt.Font("Verdana", java.awt.Font.PLAIN, 14), true);
        text = new TextField(gc, font, 60, 200, 500, 25);
        text2 = new TextField(gc, font, 270, 230, 110, 25);
        gc.setMusicOn(true);

    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
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
        if (level.getGameState() == GameState.FAILED) {
            dyingTime--;
            if (dyingTime == 0 || input.isKeyPressed(Input.KEY_ENTER) || input.isButton2Pressed(input.ANY_CONTROLLER)) {
//                gc.exit();
            	level.reloadLevel();
                score.restart();
                init(gc);
                dyingTime = 500;
            }
        }

        if (level.getGameState() == GameState.FINISHED) {
            if (!hrac.isStopTime() && level.getLevelNumber() < 5) {
                playingTime = score.getPlayingTime();
                int minutes = playingTime / 60;
                int seconds = playingTime % 60;
                text.setText("  Playing time: " + minutes + " min " + seconds + "secs" + "   Enemies killed: " + score.getEnemiesKilled() + "   Walls Destroyed: " + score.getWallsDestroyed());
                text2.setText("  Press Enter");
            }
            hrac.setStopTime(true);
            if (input.isKeyPressed(Input.KEY_ENTER)) {
                hrac.setStopTime(false);
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
    }

    @Override
    public void render(GameContainer gc, org.newdawn.slick.Graphics grphcs) throws SlickException {
        level.show();
//        grphcs.scale(2f, 2f);
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
            text.render(gc, grphcs);
            text2.render(gc, grphcs);
        }
        if (level.getGameState() == GameState.FAILED) {
            grphcs.setColor(Color.red);
            grphcs.drawString("You died", 270, 8);
        }
        if (level.getGameState() == GameState.PAUSED) {
            int minutes = playingTime / 60;
            int seconds = playingTime % 60;
            grphcs.drawString("Actual playing time: " + minutes + " min " + seconds + " secs", 180, 8);

        }
    }

	
}
