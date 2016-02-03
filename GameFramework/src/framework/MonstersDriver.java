package framework;

import java.awt.Point;
import gameframework.base.Movable;
import gameframework.base.SpeedVector;
import gameframework.game.GameMovableDriverDefaultImpl;

public class MonstersDriver extends GameMovableDriverDefaultImpl {

	@Override
	public SpeedVector getSpeedVector(Movable m) {
		// Point position = limit_horizontal_mouvement(m);

		SpeedVector possibleSpeedVector;
		SpeedVector finalVector = null;
		possibleSpeedVector = moveStrategy.getSpeedVector();
		if (moveBlockerChecker.moveValidation(m, possibleSpeedVector)) {
			finalVector = possibleSpeedVector;
		} else {
			int x = (int) moveStrategy.getSpeedVector().getDirection().getX();
			int y = (int) moveStrategy.getSpeedVector().getDirection().getY();
			moveStrategy.getSpeedVector().setDirection(new Point(-(x), (-y)));
			finalVector = moveStrategy.getSpeedVector();
		}

		return finalVector;
	}
}
