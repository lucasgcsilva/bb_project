package org.bb.game.items;

import org.bb.game.Anim;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

public class SpeedUp extends Items{
    
    
    public SpeedUp() throws SlickException{
        animation = new Animation(Anim.getAnimation("resources/items/speed", 1),20);
    }
    
}
