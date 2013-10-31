package org.bb.game.map;

import org.bb.game.Level;
import org.newdawn.slick.SlickException;
import org.bb.game.*;
import org.newdawn.slick.Animation;

public class Wall extends Walls{
	Level level = Level.getLevel();
    public Wall() throws SlickException{
        switch(level.getLevelNumber()){
            case 1: animation = new Animation(Anim.getAnimation("resources/map/wall", 1),200);
                break;
            case 2: animation = new Animation(Anim.getAnimation("resources/map/wallb", 1),200);
                break;
            case 3: animation = new Animation(Anim.getAnimation("resources/map/wallc", 1),200);
                break;
            default: animation = new Animation(Anim.getAnimation("resources/map/wall", 1),200);
        }
    }
}
