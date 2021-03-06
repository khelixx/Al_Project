package game_entities.blocker;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.game.GameEntity;
import gameframework.game.MoveBlocker;

public abstract class WallAbstract implements Drawable, MoveBlocker, GameEntity, Cloneable {

	protected DrawableImage image = null;
	protected int x, y;
	protected Canvas canvas;

	public static final int RENDERING_SIZE = 40;

	public WallAbstract(Canvas defaultCanvas, int xx, int yy) {
		image = new DrawableImage(Image(), defaultCanvas);
		x = xx;
		y = yy;
	}

	public void draw(Graphics g) {
		g.drawImage(image.getImage(), x, y, RENDERING_SIZE, RENDERING_SIZE, null);
	}

	public Point getPos() {
		return (new Point(x, y));
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Rectangle getBoundingBox() {
		return (new Rectangle(x, y, RENDERING_SIZE, RENDERING_SIZE));
	}

	public WallAbstract clone() {
		try {
			WallAbstract copy = (WallAbstract) super.clone();
			return copy;
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	public abstract String Image();
}
