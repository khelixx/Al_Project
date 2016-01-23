package game_entities;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;

public class EndLevel implements Drawable, GameEntity, Overlappable {
	
	protected static DrawableImage image = null;
	int x, y;
	public static final int RENDERING_SIZE = 40;

	public EndLevel (Canvas defaultCanvas, int xx, int yy) {
		image = new DrawableImage("images/start.gif", defaultCanvas);
		x = xx;
		y = yy;
	}

	public void draw(Graphics g) {
		g.drawImage(image.getImage(), x, y, RENDERING_SIZE, RENDERING_SIZE,
				null);
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
}
