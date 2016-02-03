package game_entities;

import java.awt.Canvas;

public class Life extends AbstractOverlappables {

	public Life(Canvas defaultCanvas, int xx, int yy) {
		super(defaultCanvas, xx, yy);
	}

	@Override
	public String image() {
		return "images/life.gif";
	}

}
