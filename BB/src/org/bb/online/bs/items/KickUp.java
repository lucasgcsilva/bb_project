package org.bb.online.bs.items;

import org.bb.online.bs.Anim;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

public class KickUp extends Items {
    
    public KickUp() throws SlickException{
        animation = new Animation(Anim.getAnimation("resources/items/kick", 1),20);
    }
}