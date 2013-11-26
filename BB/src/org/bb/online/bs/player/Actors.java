package org.bb.online.bs.player;

import org.bb.online.bs.Level;
import org.bb.online.bs.MapObjects;
import org.bb.online.bs.map.Walls;

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

