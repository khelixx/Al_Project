package game_entities;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gameframework.base.Drawable;
import gameframework.base.Overlappable;
import gameframework.base.SpeedVectorDefaultImpl;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;
import gameframework.game.SpriteManager;
import gameframework.game.SpriteManagerDefaultImpl;

public class Player extends GameMovable implements Drawable, GameEntity,Overlappable  {

	public static final int RENDERING_SIZE = 40;
	protected final SpriteManager spriteManager;
	protected boolean movable = true;
	private int invicibleTimer;
	private int cursor = 0;
	private List <String> type = Arrays.asList("right", "down", "left","up");
	
	public Player(Canvas canvas){
			spriteManager = new SpriteManagerDefaultImpl("images/zelda.gif",
					canvas, RENDERING_SIZE, 5);
			spriteManager.setTypes(
					"left", "right",
					"up","down","static");
	}
	
	public void setInvulnerable() {
		invicibleTimer = 15;
	}

	public boolean isVulnerable() {
		return (invicibleTimer <= 0);
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
		
		if (!isVulnerable()) {
			spriteType += spin();
			cursor ++;
		}
		else{
			getDriver().getSpeedVector(this).setSpeed(10);
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
		}
		spriteManager.setType(spriteType);
		spriteManager.draw(g, getPosition());
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		if (movable) {
			if (!isVulnerable()) {
				invicibleTimer--;
			}
			else{
				spriteManager.increment();
			}
		}
	}
	/********************added fonctions ****************************/
	public SpriteManager getSpriteManager(){
		return spriteManager;
	}
	
	public String spin(){
		spriteManager.reset();
		getDriver().getSpeedVector(this).setSpeed(0);
		return type.get(cursor % type.size());
	}
}
