package org.bb.game.player;

import org.bb.game.Level;
import org.bb.game.MapObjects;
import org.bb.game.map.Walls;

public abstract class Actors extends MapObjects {

    public enum Direction{
        NORTH,
        WEST,
        EAST, 
        SOUTH
    }
    
    private Level level = Level.getLevel();
    
    public abstract void act();
    
    public boolean intersectWithWall() {
        for(Walls w: level.getMap().getWalls()){
            if(w.intersects(this)){
                return true;
            }
            
        }
        return false;
    }
}

