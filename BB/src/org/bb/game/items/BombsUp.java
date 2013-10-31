package org.bb.game.items;

import org.bb.game.Anim;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

public class BombsUp extends Items{
    public BombsUp() throws SlickException{
        animation = new Animation(Anim.getAnimation("resources/items/bomb", 1), 20);
    }
    
}
