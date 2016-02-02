package game_entities.blocker;

import java.awt.Canvas;

public class Wall_laby extends WallAbstract {

	public Wall_laby(Canvas defaultCanvas, int x, int y) {
		super(defaultCanvas, x, y);
	}

	public String Image() {
		return "images/block.gif";
	}
}
