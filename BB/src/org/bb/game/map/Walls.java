package org.bb.game.map;

import org.bb.game.MapObjects;
import java.awt.geom.Rectangle2D;

public class Walls extends MapObjects{
	public Walls(){
	
	}   
	   
	@Override
	public boolean intersects(MapObjects actor) {
	    Rectangle2D predmet = new Rectangle2D.Float(actor.getX()+4, actor.getY()+4, actor.getAnimation().getWidth()-8, actor.getAnimation().getHeight()-8);
	    Rectangle2D objekt = new Rectangle2D.Float(this.getX(), this.getY(), 32, 32);
	    return objekt.intersects(predmet);    
	}
}
