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
	
	public Wall_laby(Canvas defaultCanvas, int x,int y) {
		super(defaultCanvas, x, y);
	}

	public String Image(){
		return "images/block.gif";
	}
}
