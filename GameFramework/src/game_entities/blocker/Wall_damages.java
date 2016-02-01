package game_entities.blocker;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.game.GameEntity;
import gameframework.game.MoveBlocker;

public class Wall_damages extends Wall {
	
	public Wall_damages(Canvas defaultCanvas, int x, int y) {
		super(defaultCanvas, x,y);
		// TODO Auto-generated constructor stub
	}

	public String Image(){
		return "images/block.gif";
	}

}
