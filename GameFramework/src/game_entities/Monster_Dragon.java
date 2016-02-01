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

public class Monster_Dragon extends GameMovable implements Drawable, GameEntity,Overlappable, Cloneable{

	public static final int RENDERING_SIZE = 40;
	protected final SpriteManager spriteManager;
	protected boolean movable = true;
	
	public Monster_Dragon(Canvas canvas){
			spriteManager = new SpriteManagerDefaultImpl("images/monsters.gif",
					canvas, RENDERING_SIZE, 4);
			spriteManager.setTypes(
					"down", "left",
					"right","up");
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
			spriteType += "down";
			spriteManager.reset();
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
	/********************added fonctions ****************************/
	public SpriteManager getSpriteManager(){
		return spriteManager;
	}
	
	public Monster_Dragon clone() throws CloneNotSupportedException {
        return (Monster_Dragon)super.clone();
    }
}
