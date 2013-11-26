package org.bb.online.bs.map;

import org.bb.online.bs.Anim;
import org.bb.online.bs.Level;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.bb.online.bs.*;
import org.bb.util.Info;
import org.newdawn.slick.Animation;

public class Wall extends Walls{
	Level level = Level.getLevel();
	private SpriteSheet wallSheet;
	
    public Wall() throws SlickException{
    	try {
    		wallSheet = new SpriteSheet(Info.wallPath, 32, 32);
    	} catch (SlickException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
        animation = new Animation(Anim.getSpriteSheetAnimation(wallSheet, 2, level.getLevelNumber()-1), 200);
        animation.setCurrentFrame(1);
        animation.stop();
    }
}
