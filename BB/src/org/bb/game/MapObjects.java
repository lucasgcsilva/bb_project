package org.bb.game;

import org.newdawn.slick.Animation;
import java.awt.geom.Rectangle2D;

public abstract class MapObjects implements Objects{

	protected int x;
	protected int y;
	protected Animation animation;
	
	public int getX() {
		// TODO Auto-generated method stub
		return this.x;
	}

	public int getY() {
		// TODO Auto-generated method stub
		return this.y;
	}

	public int getWidth() {
		// TODO Auto-generated method stub
		return animation.getWidth();
	}

	public int getHeight() {
		// TODO Auto-generated method stub
		return animation.getHeight();
	}

	public Animation getAnimation() {
		// TODO Auto-generated method stub
		return this.animation;
	}

	public void setAnimation(Animation animation) {
		// TODO Auto-generated method stub
		this.animation = animation;
	}

	public void setPosition(int x, int y) {
		// TODO Auto-generated method stub
		this.x = x;
		this.y = y;
	}
	
	public boolean intersects(MapObjects actor) {      
        Rectangle2D predmet = new Rectangle2D.Float(actor.getX(), actor.getY(), actor.getAnimation().getWidth(), actor.getAnimation().getHeight());
        Rectangle2D objekt = new Rectangle2D.Float(this.getX(), this.getY(), this.animation.getWidth(), this.animation.getHeight());
        return objekt.intersects(predmet);
    }

}
