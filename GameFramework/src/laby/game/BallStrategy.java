package laby.game;

import java.awt.Point;

import gameframework.base.MoveStrategy;
import gameframework.base.SpeedVector;
import gameframework.base.SpeedVectorDefaultImpl;

public class BallStrategy implements MoveStrategy {
	
	SpeedVector speedVector = new SpeedVectorDefaultImpl(new Point(1, -1),10);
	
	@Override
	public SpeedVector getSpeedVector() {
		return speedVector;
	}
	
	public SpeedVector getvector(){
		return speedVector;
	}
}
