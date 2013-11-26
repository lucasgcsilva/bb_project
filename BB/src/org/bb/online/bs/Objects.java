package org.bb.online.bs;

import org.newdawn.slick.Animation;

public interface Objects {
	
	public int getX();
	
	public int getY();
	
	public int getWidth();
	
	public int getHeight();
	
	public Animation getAnimation();
	
	public void setAnimation(Animation animation);
	
	public void setPosition(int x, int y);
}
