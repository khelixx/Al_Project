package game_entities.overlappableNoMovable;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;

public class Teleportation implements Drawable, GameEntity, Overlappable {
	
	protected static DrawableImage image = null;
	public static final int RENDERING_SIZE = 40;
	protected Point position;
	protected Point destination;
	
	public Teleportation(Canvas defaultCanvas, Point pos, Point destination) {
		image = new DrawableImage("images/teleportation.gif", defaultCanvas);
		position = pos;
		this.destination = destination;
	}

	public void draw(Graphics g) {
		g.drawImage(image.getImage(), position.x, position.y , RENDERING_SIZE, RENDERING_SIZE,
				null);
	}

	public Point getDestination() {
		return destination;
	}

	public Point getPosition() {
		return position;
	}

	public Rectangle getBoundingBox() {
		return (new Rectangle((int) position.getX(), (int) position.getY(),
				RENDERING_SIZE, RENDERING_SIZE));
	}

}
