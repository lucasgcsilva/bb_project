package org.bb.game.player;

import org.bb.game.Anim;
import org.bb.game.Level;
import org.bb.game.MapObjects;
import org.bb.game.items.Items;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import java.awt.geom.Rectangle2D;

public class Flame extends Actors {

    private int time;
    private Level level = Level.getLevel();
    private boolean broomsMode = false;
    private int portalX;
    private int portalY;
    private int dragonTimer;

   
    public Flame(Direction direction) throws SlickException {
//        switch (direction){
//            case NORTH: 
//            animation = new Animation(Anim.getAnimation("resources/actors/flame_n", 4),250);
//                break;
//            case SOUTH: 
//                animation = new Animation(Anim.getAnimation("resources/actors/flame_s", 4),250);
//                break;
//            case EAST:
//                animation = new Animation(Anim.getAnimation("resources/actors/flame_e", 4),250);
//                break;
//            case WEST:
//                animation = new Animation(Anim.getAnimation("resources/actors/flame_w", 4),250);
//                break;
//        }
        if (direction == Direction.NORTH || direction == Direction.SOUTH) {
            animation = new Animation(Anim.getAnimation("resources/actors/flame_ns", 1), 20);
        } else {
            animation = new Animation(Anim.getAnimation("resources/actors/flame_we", 1), 20);
        }
        time = 50;
        dragonTimer = 0;
    }

    @Override
    public void act() {
        if (dragonTimer>0){
            dragonTimer--;
        }
        for (int j = 0; j < level.getListOfObjects().toArray().length; j++) {
            MapObjects o = (MapObjects) level.getListOfObjects().toArray()[j];
            if (o.intersects(this) && o instanceof Bombs) {
                ((Bombs) o).setExplodeTime(0);
            }

            if (o.intersects(this) && o instanceof Items && ((Items) o).isVisible()) {
                level.removeFromLevel(o);
            }
        }
        if (time == 0) {
            level.getListOfObjects().remove(this);
            
        } else {
            time--;
        }
    }

    @Override
    public boolean intersects(MapObjects actor) {
        Rectangle2D predmet = new Rectangle2D.Float(actor.getX() + 6, actor.getY() + 6, 18, 18);
        Rectangle2D objekt = new Rectangle2D.Float(this.getX() + 2, this.getY() + 2, this.animation.getWidth() - 4, this.animation.getHeight() - 4);
        return objekt.intersects(predmet);
    }
}
