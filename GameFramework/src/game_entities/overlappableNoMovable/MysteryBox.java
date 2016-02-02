package game_entities.overlappableNoMovable;

import java.awt.Canvas;

public class MysteryBox extends AbstractOverlappables {

	public MysteryBox(Canvas defaultCanvas, int xx, int yy) {
		super(defaultCanvas, xx, yy);
	}

	@Override
	public String image() {
		return "images/mysteryBox.png";
	}
}