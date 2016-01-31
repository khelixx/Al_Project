package game_entities.blocker;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.game.GameEntity;
import gameframework.game.MoveBlocker;

public class Wall_laby extends Wall{
	
	public Wall_laby (Canvas defaultCanvas, int xx, int yy) {
		super(defaultCanvas,xx,yy);
	}
	
	public String Image(){
		return "images/block.gif";
	}
}
