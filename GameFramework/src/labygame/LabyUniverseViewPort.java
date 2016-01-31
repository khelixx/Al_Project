package labygame;

import java.awt.Canvas;
import gameframework.game.GameUniverse;
import gameframework.game.GameUniverseViewPortDefaultImpl;

public class LabyUniverseViewPort extends GameUniverseViewPortDefaultImpl {

	public LabyUniverseViewPort(Canvas canvas, GameUniverse universe) {
		super(canvas, universe);
	}

	protected String backgroundImage() {
		return "images/background.gif";
	}
}
