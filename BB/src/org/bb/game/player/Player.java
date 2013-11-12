package org.bb.game.player;

import java.applet.AudioClip;
import java.util.Timer;
import java.util.TimerTask;

import org.bb.game.Anim;
import org.bb.game.Game.GameState;
import org.bb.game.GameScore;
import org.bb.game.Level;
import org.bb.game.MapObjects;
import org.bb.game.items.BombsUp;
import org.bb.game.items.KickUp;
import org.bb.game.items.RangeUp;
import org.bb.game.items.SpeedUp;
import org.bb.sound.AePlayWave;
import org.bb.sound.MusicPlayer;
import org.bb.util.SavedPreferences;
import org.newdawn.slick.Animation;
import org.newdawn.slick.ControllerListener;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Player extends Actors {

    private Animation leftAnimation;
    private Animation rightAnimation;
    private Animation downAnimation;
    private Animation upAnimation;
    private Animation speedLeftAnimation;
    private Animation speedRightAnimation;
    private Animation speedDownAnimation;
    private Animation speedUpAnimation;
    private Animation puttingLeft;
    private Animation puttingRight;
    private Animation puttingDown;
    private Animation puttingUp;
    private Animation celebrating;
    private Animation dying;
    private Animation idle;
    private Direction direction;
    private MusicPlayer die = new MusicPlayer("resources/musics/die_bomb.wav", false);
    private int bombsCount;
    private int range;
    private int speed;
    private int speedTime;
    private int kickTime;
    private boolean puttingBomb = false;
    private int puttingTime = 0;
    private boolean stopTime;
    private boolean ghostMode;
    private boolean isKeyPressed = false;
    private int timeIdle = 500;
    private Level level = Level.getLevel();
    private GameScore stat = GameScore.getScore();
    private Input input = new Input(480);
    private SpriteSheet sheet;
    public int numPlayer;
    public boolean isAlive = true;
    private boolean isController = false;
    private boolean isKeyboard = true;
    private int numController = 0;
    private int KEY_UP;
    private int KEY_DOWN;
    private int KEY_LEFT;
    private int KEY_RIGHT;
    private int KEY_BOMB;
   
    
    public Player(int numPlayer, String tileBomb, boolean isControl, int numControl, boolean isKeyboard) throws SlickException {
    	sheet = new SpriteSheet(tileBomb, 32, 32);
    	this.numPlayer = numPlayer;
        leftAnimation = new Animation(Anim.getSpriteSheetAnimation(sheet, 8, 2), 200);
        rightAnimation = new Animation(Anim.getSpriteSheetAnimation(sheet, 8, 1), 200);
        downAnimation = new Animation(Anim.getSpriteSheetAnimation(sheet, 8, 0), 200);
        upAnimation = new Animation(Anim.getSpriteSheetAnimation(sheet, 8, 3), 200);
        speedLeftAnimation = new Animation(Anim.getSpriteSheetAnimation(sheet, 8, 2), 100);
        speedRightAnimation = new Animation(Anim.getSpriteSheetAnimation(sheet, 8, 1), 100);
        speedDownAnimation = new Animation(Anim.getSpriteSheetAnimation(sheet, 8, 0), 100);
        speedUpAnimation = new Animation(Anim.getSpriteSheetAnimation(sheet, 8, 3), 100);
        puttingLeft = new Animation(Anim.getSpriteSheetAnimation(sheet, 8, 3), 100);
        puttingRight = new Animation(Anim.getSpriteSheetAnimation(sheet, 8, 3), 100);
        puttingUp = new Animation(Anim.getSpriteSheetAnimation(sheet, 8, 3), 100);
        puttingDown = new Animation(Anim.getSpriteSheetAnimation(sheet, 8, 3), 100);
        celebrating = new Animation(Anim.getSpriteSheetAnimation(sheet, 5, 6), 200);
        dying = new Animation(Anim.getSpriteSheetAnimation(sheet, 6, 5), 300);
        idle = new Animation(Anim.getSpriteSheetAnimation(sheet, 9, 4), 200);
        super.animation = this.downAnimation;
        direction = Direction.SOUTH;
        super.animation.stop();
        bombsCount = 1;
        range = 1;
        speed = 1;
        speedTime = 0;
        kickTime = 0;
        stopTime = false;
        ghostMode = false;
        this.isKeyboard = isKeyboard;
    	this.isController = isControl;
    	this.numController = numControl;
    	this.numPlayer = numPlayer;
        getKeyboardConfiguration();
        
    }
    
    public void getKeyboardConfiguration(){
    	String [] keyConf;
    	if (numPlayer == Level.PLAYER_1){
    		 keyConf = SavedPreferences.pad1.split("\\|");
    	}else{
    		 keyConf = SavedPreferences.pad2.split("\\|");
    	}
    	KEY_UP = Integer.parseInt(keyConf[0]);
    	KEY_DOWN = Integer.parseInt(keyConf[1]);
    	KEY_LEFT = Integer.parseInt(keyConf[2]);
    	KEY_RIGHT = Integer.parseInt(keyConf[3]);
    	KEY_BOMB = Integer.parseInt(keyConf[4]);
    }
    
    @Override
    public void act() {
//        if(input.isKeyDown(Input.KEY_G)){
//            if(!ghostMode){
//                ghostMode=true;
//            }
//            else{
//                ghostMode=false;
//            }
//        }
        if (puttingTime > 0) {
            puttingTime--;
            if (puttingTime == 0) {
            	MusicPlayer mus = new MusicPlayer("resources/musics/put.wav", false);
        		mus.playSound();
                Bombs bomba;
                try {
                    bomba = new Bombs(numPlayer);
                    level.addToLevel(bomba);
                    bomba.setPosition((this.getX() + 15) / 32 * 32, (this.getY() + 15) / 32 * 32);
                    level.getMap().getWallMap()[bomba.getX() / 32][bomba.getY() / 32] = 1;
                } catch (SlickException ex) {
                    ex.printStackTrace();
                }
                puttingBomb = false;
            }
        }
        if (speedTime > 0) {
            speedTime--;
            if (speedTime == 0) {
                speed -= 2;
            }
        }
        if (kickTime > 0) {
            kickTime--;
        }
        putBomb();
        walk();
        try {
			input.initControllers();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * method responsible for putting bomb - sets putting animation, timer for animation
     */
    public void putBomb() {
        if (((input.isButton1Pressed(numController) && isController) || (input.isKeyDown(KEY_BOMB) && isKeyboard)) && puttingBomb == false && isAlive) {
            if (bombsCount > 0) {
                bombsCount--;
                puttingBomb = true;
                puttingTime = 30;
                switch (direction) {
                    case NORTH:
                        animation = puttingUp;
                        animation.restart();
                        break;
                    case SOUTH:
                        animation = puttingDown;
                        animation.restart();
                        break;
                    case EAST:
                        animation = puttingRight;
                        animation.restart();
                        break;
                    case WEST:
                        animation = puttingLeft;
                        animation.restart();
                        break;
                }
            }
        }
    }

    /**
     * provides movement for player |
     * sets animation based on direction/speed animation as well
     * checks intersection with other objects in map |
     * if portal - sets game state on FINISHED, celebrating animation |
     * if enemy - sets game state on FAILED, dying animation |
     * if bomb - player kicks it if taken KickUp item, else doesn't move |
     * if an item - removes it from level, sets specific feature of player based on the item |
     * if wall - player doesn't move
     */
    public void walk() {
        int hracX = this.getX();
        int hracY = this.getY();
        isKeyPressed = false;
        if (!puttingBomb && isAlive) {
            if ((input.isControllerLeft(numController) && isController) || (input.isKeyDown(KEY_LEFT) && isKeyboard)) {
                isKeyPressed = true;
            	if (speedTime > 0) {
                    this.animation = this.speedLeftAnimation;
                } else {
                    this.animation = this.leftAnimation;
                }
                direction = Direction.WEST;
                this.animation.start();
                this.x -= speed;
            } else if ((input.isControllerRight(numController) && isController) || (input.isKeyDown(KEY_RIGHT) && isKeyboard)) {
            	isKeyPressed = true;
                if (speedTime > 0) {
                    this.animation = this.speedRightAnimation;
                } else {
                    this.animation = this.rightAnimation;
                }
                direction = Direction.EAST;
                this.animation.start();
                this.x += speed;
            } else if ((input.isControllerDown(numController) && isController) || (input.isKeyDown(KEY_DOWN) && isKeyboard)) {
            	isKeyPressed = true;
                if (speedTime > 0) {
                    this.animation = this.speedDownAnimation;
                } else {
                    this.animation = this.downAnimation;
                }
                direction = Direction.SOUTH;
                this.animation.start();
                this.y += speed;
            } else if ((input.isControllerUp(numController) && isController) || (input.isKeyDown(KEY_UP) && isKeyboard)) {
            	isKeyPressed = true;
                if (speedTime > 0) {
                    this.animation = this.speedUpAnimation;
                } else {
                    this.animation = this.upAnimation;
                }
                direction = Direction.NORTH;
                this.animation.start();
                this.y -= speed;
            } else {
            	if (timeIdle < 10){
            		this.animation = this.idle; 
            		this.animation.start();
            	}else{
            		this.animation.setCurrentFrame(0);
                    this.animation.stop();
            		timeIdle--;
            	}
            }
            
            
        }
        if (isKeyPressed){
        	timeIdle = 500;
        }
        for (int x = 0; x < level.getListOfObjects().toArray().length; x++) {
            MapObjects o = (MapObjects) level.getListOfObjects().toArray()[x];
            if (o.intersects(this)) {
                if (o instanceof Flame) {
                	this.animation.setLooping(false);
                    animation = dying;
                    if (!die.isPlaying()){
                    	die.playSound();             	
                    }
                    isAlive = false;
                }
                if (o instanceof Bombs) {
                    if (!((Bombs) o).canIntersectWithPlayer()) {
                        if (kickTime > 0) {
                            kickBomb((Bombs) o);
                            this.setPosition(hracX, hracY);
                        } else {
                            this.setPosition(hracX, hracY);
                        }
                    }
                }
                if (o instanceof BombsUp) {
                	MusicPlayer explode = new MusicPlayer("resources/musics/item.wav", false);
                	explode.playSound();
                    level.removeFromLevel(o);
                    bombsCount++;
                    stat.incItemsUsed();
                }
                if (o instanceof RangeUp) {
                	MusicPlayer explode = new MusicPlayer("resources/musics/item.wav", false);
                	explode.playSound();
                    level.removeFromLevel(o);
                    range++;
                    stat.incItemsUsed();
                }
                if (o instanceof SpeedUp) {
                	MusicPlayer explode = new MusicPlayer("resources/musics/item.wav", false);
                	explode.playSound();
                    level.removeFromLevel(o);
                    speed += 2;
                    speedTime = 1000;
                    stat.incItemsUsed();
                }
                if (o instanceof KickUp) {
                	MusicPlayer explode = new MusicPlayer("resources/musics/item.wav", false);
                	explode.playSound();
                    level.removeFromLevel(o);
                    kickTime = 2000;
                    stat.incItemsUsed();
                }
            }
        }

        if (this.intersectWithWall()) {
            if(!ghostMode){
            this.setPosition(hracX, hracY);
            }
        }
    }

    public void kickBomb(Bombs bomb) {
        int r=1;
        switch (direction) {          
            case EAST:
                bomb.setX(32 * r);
                while (!bomb.intersectWithWall()) {
                    bomb.setX(-(32 * r));
                    r++;
                    bomb.setX(32 * r);
                }
                bomb.setX(-(32 * r));
                bomb.makeMove(direction, (r-1)*16);
                break;

            case WEST:
                bomb.setX(-(32 * r));
                while (!bomb.intersectWithWall()) {
                    bomb.setX(32 * r);
                    r++;
                    bomb.setX(-(32 * r));
                }
                bomb.setX(32 * r);
                bomb.makeMove(direction, (r-1)*16);
                break;
            case SOUTH:
                bomb.setY(32 * r);
                while (!bomb.intersectWithWall()) {
                    bomb.setY(-(32 * r));
                    r++;
                    bomb.setY(32 * r);
                }
                bomb.setY(-(32 * r));
                bomb.makeMove(direction, (r-1)*16);
                break;
            case NORTH:
                bomb.setY(-(32 * r));
                while (!bomb.intersectWithWall()) {
                    bomb.setY(32 * r);
                    r++;
                    bomb.setY(-(32 * r));
                }
                bomb.setY(32 * r);
                bomb.makeMove(direction, (r-1)*16);
                break;
        }
    }

    public int getRange() {
        return range;
    }

    public void incBomb() {
        bombsCount++;
    }

    public boolean isStopTime() {
        return stopTime;
    }

 
    public void setStopTime(boolean stopTime) {
        this.stopTime = stopTime;
    }
    
    public boolean getStopTime(){
    	return this.stopTime;
    }
    
    public void setCelebrate(){
    	animation = celebrating;
    	animation.start();
    }
    
    public void setDying(){
    	animation = dying;
    	animation.start();
    }
   
}
