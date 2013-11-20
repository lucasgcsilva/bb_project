package org.bb.game.items;

import org.bb.game.Anim;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class BombsUp extends Items{
    public BombsUp() throws SlickException{
    	SpriteSheet bomb = new SpriteSheet("resources/items/customItens.png", 32, 32);
        animation = new Animation(Anim.getSpriteSheetAnimation(bomb, 4, 0), 100);
    }
    
}
