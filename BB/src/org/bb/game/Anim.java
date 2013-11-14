package org.bb.game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public abstract class Anim {
	
	public static Image[] getAnimation (String pattern, int frames) throws SlickException{
		Image[] images = new Image[frames];
		for (int i=1; i<=frames; i++){
			images[i-1] = new Image(pattern+0+i+".png");
		}
		return images;		
	}
	
	public static Image[] getSpriteSheetAnimation(SpriteSheet sheet, int frames, int row){
		Image[] images = new Image[frames];
		sheet.startUse();
		for (int i=1; i<=frames; i++){
			images[i-1] = sheet.getSubImage(i-1, row);
		}
		sheet.endUse();
		return images;
	}
	
}
