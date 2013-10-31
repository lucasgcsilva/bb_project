package org.bb.game.player;

import org.bb.game.Anim;
import org.bb.game.Level;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

public class WallInFire extends Actors{ 
    private int time;
    Level level = Level.getLevel();
    
    /**
     * sets animations, animation time
     * @throws SlickException
     */
    public WallInFire() throws SlickException{
        switch(level.getLevelNumber()){
            case 1: animation = new Animation(Anim.getAnimation("resources/map/wallf", 5),180);
                break;
            case 2: animation = new Animation(Anim.getAnimation("resources/map/wallbf", 5),180);
                break;
            case 3: animation = new Animation(Anim.getAnimation("resources/map/wallcf", 5),180);
                break;
            default: animation = new Animation(Anim.getAnimation("resources/map/wallf", 5),180);
                
        }
        time=50;
    }
    
    @Override
    public void act() {
        if(time==0){
            level.removeFromLevel(this);
        }
        else {
            time--;
        }
    }
    
}
