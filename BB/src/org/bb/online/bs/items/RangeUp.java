package org.bb.online.bs.items;

import org.bb.online.bs.Anim;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class RangeUp extends Items{
    
    public RangeUp() throws SlickException{
    	SpriteSheet range = new SpriteSheet("resources/items/customItens.png", 32, 32);
        animation = new Animation(Anim.getSpriteSheetAnimation(range, 4, 1),100);
    }
    
}
