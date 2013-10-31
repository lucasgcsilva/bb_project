package org.bb.game.items;

import org.bb.game.Anim;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

public class RangeUp extends Items{
    
    public RangeUp() throws SlickException{
        animation = new Animation(Anim.getAnimation("resources/items/range", 1),20);
    }
    
}
