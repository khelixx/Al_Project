package labygame;

import java.awt.Point;
import java.awt.event.KeyEvent;

import gameframework.base.MoveStrategyKeyboard;
import gameframework.base.SpeedVector;
import gameframework.base.SpeedVectorDefaultImpl;

public class KeyboardExtensionStrategy extends MoveStrategyKeyboard{
	
	private Boolean check = false;
	protected SpeedVector speedVector = new SpeedVectorDefaultImpl(new Point(0,
			0));

	public SpeedVector getSpeedVector() {
		return speedVector;
	}

	@Override
	public void keyPressed(KeyEvent event) {
		int keycode = event.getKeyCode();
		switch (keycode) {
		case KeyEvent.VK_SPACE:
			speedVector.setDirection(new Point(0, 0));
			break;
		case KeyEvent.VK_RIGHT:
			speedVector.setDirection(new Point(1, 0));
			break;
		case KeyEvent.VK_LEFT:
			speedVector.setDirection(new Point(-1, 0));
			break;
		case KeyEvent.VK_UP:
			speedVector.setDirection(new Point(0, -1));
			break;
		case KeyEvent.VK_DOWN:
			speedVector.setDirection(new Point(0, 1));
			break;
		}
	}
}

