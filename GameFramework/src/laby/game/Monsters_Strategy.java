package laby.game;

import java.awt.Point;

import gameframework.base.MoveStrategy;
import gameframework.base.SpeedVector;
import gameframework.base.SpeedVectorDefaultImpl;

public class Monsters_Strategy implements MoveStrategy{
	SpeedVector currentMove = new SpeedVectorDefaultImpl(new Point(0, 0));
	private int count = 0 ;
	
	@Override
	public SpeedVector getSpeedVector() {
		// TODO Auto-generated method stub
		if ( count < 8){
			currentMove.setDirection(new Point(0, 1));
		}
		else if (count < 15){
			currentMove.setDirection(new Point(0, -1));
		}
		else{
			count =0;
		}
		return currentMove;
	}

}
