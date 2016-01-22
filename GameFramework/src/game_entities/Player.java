package game_entities;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.base.Drawable;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;
import gameframework.game.SpriteManager;
import gameframework.game.SpriteManagerDefaultImpl;

public class Player extends GameMovable implements Drawable, GameEntity,Overlappable  {

	public static final int RENDERING_SIZE = 40;
	protected final SpriteManager spriteManager;
	protected boolean movable = true;
	
	public Player(Canvas canvas){
			spriteManager = new SpriteManagerDefaultImpl("images/zelda.gif",
					canvas, RENDERING_SIZE, 5);
			spriteManager.setTypes(
					"left", "right",
					"up","down");
	}


	@Override
	public Rectangle getBoundingBox() {
		 return new Rectangle(0, 0, RENDERING_SIZE, RENDERING_SIZE);
	}

	@Override
	public void draw(Graphics g) {
		String spriteType = "";
		Point tmp = getSpeedVector().getDirection();
		movable = true;
		
		if (tmp.getX() == 1) {
			spriteType += "right";
		} else if (tmp.getX() == -1) {
			spriteType += "left";
		} else if (tmp.getY() == 1) {
			spriteType += "down";
		} else if (tmp.getY() == -1) {
			spriteType += "up";
		} else {
			spriteType += "right";
			spriteManager.setIncrement(4);
			movable = false;
		}
		spriteManager.setType(spriteType);
		spriteManager.draw(g, getPosition());
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		if (movable) {
			spriteManager.increment();
		}
		
	}
	
	public SpriteManager getSpriteManager(){
		return spriteManager;
	}

}
