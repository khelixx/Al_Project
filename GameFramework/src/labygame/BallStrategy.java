package labygame;

import java.awt.Point;

import gameframework.base.MoveStrategy;
import gameframework.base.SpeedVector;
import gameframework.base.SpeedVectorDefaultImpl;

public class BallStrategy implements MoveStrategy {
	
	private SpeedVector speedVector;
	
	public BallStrategy(int vertical, int horizontal, int speed){
		speedVector = new SpeedVectorDefaultImpl(new Point(vertical, horizontal),speed);
	}
	
	@Override
	public SpeedVector getSpeedVector() {
		return speedVector;
	}
	
	public SpeedVector getvector(){
		return speedVector;
	}
}
