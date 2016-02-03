package game_entities;

import java.awt.Canvas;

public class EndLevel extends AbstractOverlappables {

	public EndLevel(Canvas defaultCanvas, int xx, int yy) {
		super(defaultCanvas, xx, yy);
	}

	@Override
	public String image() {
		return "images/start.gif";
	}
}
