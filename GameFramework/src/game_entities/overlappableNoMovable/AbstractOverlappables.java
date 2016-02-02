package game_entities.overlappableNoMovable;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;

public abstract class AbstractOverlappables implements Drawable, GameEntity, Overlappable {

	protected DrawableImage image;
	int x, y;
	public static final int RENDERING_SIZE = 40;

	public AbstractOverlappables(Canvas defaultCanvas, int xx, int yy) {
		image = new DrawableImage(image(), defaultCanvas);
		x = xx;
		y = yy;
	}

	public void draw(Graphics g) {
		g.drawImage(image.getImage(), x, y, RENDERING_SIZE, RENDERING_SIZE, null);
	}

	public Point getPos() {
		return (new Point(x, y));
	}

	public Rectangle getBoundingBox() {
		return (new Rectangle(x, y, RENDERING_SIZE, RENDERING_SIZE));
	}

	@Override
	public Point getPosition() {
		return (new Point(x, y));
	}

	public abstract String image();
}
