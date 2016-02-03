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

public class Carapace extends GameMovable implements Drawable, GameEntity, Overlappable {

	protected static DrawableImage image = null;
	public static final int RENDERING_SIZE = 40;

	public Carapace(Canvas defaultCanvas) {
		image = new DrawableImage("images/carapace.gif", defaultCanvas);
	}

	public void draw(Graphics g) {

		g.drawImage(image.getImage(), (int) super.getPosition().getX(), (int) super.getPosition().getY(),
				RENDERING_SIZE, RENDERING_SIZE, null);
	}

	public Rectangle getBoundingBox() {
		return (new Rectangle((int) super.getPosition().getX(), (int) super.getPosition().getY(), RENDERING_SIZE,
				RENDERING_SIZE));
	}

	@Override
	public Point getPosition() {
		return (new Point((int) super.getPosition().getX(), (int) super.getPosition().getY()));
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		// TODO Auto-generated method stub

	}

}
