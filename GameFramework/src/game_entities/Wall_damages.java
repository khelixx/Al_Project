package game_entities;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.game.GameEntity;
import gameframework.game.MoveBlocker;

public class Wall_damages extends Wall {
	
	public Wall_damages (Canvas defaultCanvas, int xx, int yy) {
		super(defaultCanvas,xx,yy,"images/block.gif");
	}


}
