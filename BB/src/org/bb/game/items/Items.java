package org.bb.game.items;

import org.bb.game.MapObjects;
import java.awt.geom.Rectangle2D;

public abstract class Items extends MapObjects {
    
    private boolean visible = false;
    
    public boolean isVisible(){
        return visible;
    }
    
    public void setVisible(boolean visible){
        this.visible = visible;
    }
    
    @Override
    public boolean intersects(MapObjects actor) {    
        Rectangle2D predmet = new Rectangle2D.Float(actor.getX()+6, actor.getY()+6, 18, 18);
        Rectangle2D objekt = new Rectangle2D.Float(this.getX()+2, this.getY()+2, this.animation.getWidth()-4, this.animation.getHeight()-4);
        return objekt.intersects(predmet);                    
    }
}

