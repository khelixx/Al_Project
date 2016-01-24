package game_entities;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;

public class Fireball extends GameMovable implements Drawable, GameEntity, Overlappable {
	public static final int RENDERING_SIZE = 40;
	protected static DrawableImage image = null;
	Canvas canvas;
	
	public Fireball(Canvas canvas){
			this.canvas = canvas;
			image = new DrawableImage("images/ball.gif", canvas);
	}


	public void draw(Graphics g) {
		 g.drawImage(image.getImage(), (int) getPosition().getX(),
	                (int) getPosition().getY(), RENDERING_SIZE, RENDERING_SIZE, null);

	}

	public Rectangle getBoundingBox() {
		 return new Rectangle(0, 0, RENDERING_SIZE, RENDERING_SIZE);
	}

	@Override
	public void oneStepMoveAddedBehavior() {
	}
}
