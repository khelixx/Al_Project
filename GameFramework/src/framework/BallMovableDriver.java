package framework;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import gameframework.base.Movable;
import gameframework.base.SpeedVector;
import gameframework.base.SpeedVectorDefaultImpl;
import gameframework.game.GameMovableDriverDefaultImpl;

public class BallMovableDriver extends GameMovableDriverDefaultImpl {

	private List<SpeedVector> listPossibleVector = new ArrayList<SpeedVector>();

	public BallMovableDriver() {
		listPossibleVector.add(new SpeedVectorDefaultImpl(new Point(1, -1)));
		listPossibleVector.add(new SpeedVectorDefaultImpl(new Point(1, 1)));
		listPossibleVector.add(new SpeedVectorDefaultImpl(new Point(-1, -1)));
		listPossibleVector.add(new SpeedVectorDefaultImpl(new Point(-1, 1)));
	}

	@Override
	public SpeedVector getSpeedVector(Movable m) {

		SpeedVector possibleSpeedVector;
		SpeedVector finalVector = null;
		boolean check_validation = false;
		possibleSpeedVector = moveStrategy.getSpeedVector();
		if (moveBlockerChecker.moveValidation(m, possibleSpeedVector)) {
			finalVector = possibleSpeedVector;
		} else {
			while (!check_validation) {
				Random randx = new Random();
				int ball_direction = randx.nextInt(4);
				if (listPossibleVector.get(ball_direction).getDirection() != possibleSpeedVector.getDirection()) {
					if (moveBlockerChecker.moveValidation(m, listPossibleVector.get(ball_direction))) {
						moveStrategy.getSpeedVector()
								.setDirection(listPossibleVector.get(ball_direction).getDirection());
						check_validation = true;
						finalVector = moveStrategy.getSpeedVector();
					}
				}
			}
		}

		return finalVector;
	}
}