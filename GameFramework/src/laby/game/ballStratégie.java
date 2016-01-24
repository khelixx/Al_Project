package laby.game;

import java.awt.Point;

import gameframework.base.MoveStrategy;
import gameframework.base.MoveStrategyKeyboard;
import gameframework.base.SpeedVector;
import gameframework.base.SpeedVectorDefaultImpl;

public class ballStratégie implements MoveStrategy {

	SpeedVector currentMove = new SpeedVectorDefaultImpl(new Point(0, 0));

	@Override
	public SpeedVector getSpeedVector() {
		currentMove.setDirection(new Point(1,-1));		
		return currentMove;
	}
}
