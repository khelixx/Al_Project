package game_entities.overlappableNoMovable;

import java.awt.Canvas;

public class CheckPoint extends AbstractOverlappables{

	public CheckPoint(Canvas defaultCanvas, int xx, int yy) {
		super(defaultCanvas, xx, yy);
	}

	@Override
	public String image() {
		return "images/checkpoint.png";
	}
}
