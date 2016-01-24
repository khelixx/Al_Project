package laby.game;

import java.awt.Point;

import gameframework.base.MoveStrategy;
import gameframework.base.SpeedVector;
import gameframework.base.SpeedVectorDefaultImpl;

public class Monsters_Strategy implements MoveStrategy{
	SpeedVector currentMove = new SpeedVectorDefaultImpl(new Point(0, 0));
	private boolean check = true ;
	
	@Override
	public SpeedVector getSpeedVector() {
		// TODO Auto-generated method stub
		if (check){
			currentMove.setDirection(new Point(0, 1));
		}
		else{
			currentMove.setDirection(new Point(0, -1));
		}
		return currentMove;
	}

}
