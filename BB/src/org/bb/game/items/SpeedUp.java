package org.bb.game.items;

import org.bb.game.Anim;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class SpeedUp extends Items{
    
    
    public SpeedUp() throws SlickException{
    	SpriteSheet speed = new SpriteSheet("resources/items/customItens.png", 32, 32);
        animation = new Animation(Anim.getSpriteSheetAnimation(speed, 4, 2),20);
    }
    
}
