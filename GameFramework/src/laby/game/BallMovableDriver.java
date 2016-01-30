package laby.game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game_entities.Fireball;
import gameframework.base.Movable;
import gameframework.base.ObservableValue;
import gameframework.base.SpeedVector;
import gameframework.base.SpeedVectorDefaultImpl;
import gameframework.game.GameMovableDriverDefaultImpl;

public class BallMovableDriver extends GameMovableDriverDefaultImpl {

	private List<SpeedVector> listPossibleVector = new ArrayList<SpeedVector>();
	private ObservableValue<Fireball>[] monstersObservable;
	
	public BallMovableDriver(ObservableValue<Fireball>[] monstersObservable) {
		listPossibleVector.add(new SpeedVectorDefaultImpl(new Point(1, -1)));
		listPossibleVector.add(new SpeedVectorDefaultImpl(new Point(1,  1)));
		listPossibleVector.add(new SpeedVectorDefaultImpl(new Point(-1,-1)));
		listPossibleVector.add(new SpeedVectorDefaultImpl(new Point(-1, 1)));
		/*listPossibleVector.add(new SpeedVectorDefaultImpl(new Point(0, 1)));
		listPossibleVector.add(new SpeedVectorDefaultImpl(new Point(0, -1)));*/
		this.monstersObservable = monstersObservable;
	}
	
	@Override
	public SpeedVector getSpeedVector(Movable m) {
		//Point position = limit_horizontal_mouvement(m);
		
		SpeedVector possibleSpeedVector;
		SpeedVector finalVector = null;
		boolean check_validation = false;
		possibleSpeedVector = moveStrategy.getSpeedVector();
		if (moveBlockerChecker.moveValidation(m, possibleSpeedVector)) {
			finalVector = possibleSpeedVector;
		}
		else{
			while(!check_validation){
				Random randx = new Random();
				int ball_direction = randx.nextInt(4);
				if(listPossibleVector.get(ball_direction).getDirection() != possibleSpeedVector.getDirection()){
					if(moveBlockerChecker.moveValidation(m, listPossibleVector.get(ball_direction))) {
						moveStrategy.getSpeedVector().setDirection(listPossibleVector.get(ball_direction).getDirection());
						check_validation = true;	
						finalVector =  moveStrategy.getSpeedVector();
					}
				}
			}
		}
		
		return finalVector;
	}
	
	public Point limit_horizontal_mouvement(Movable m){
		Point position = null;
		
		for (ObservableValue<Fireball> fire : monstersObservable){
			if(fire.getValue().equals(m)){
				position = fire.getValue().getPosition();
			}
		}
		
		return position;
	}
}

/*if (moveStrategy.getSpeedVector().getDirection().getX() == 0){
if(position.getX() < 1500 || position.getX() > 1700 ){
	moveStrategy.getSpeedVector().setDirection(listPossibleVector.get(ball_direction).getDirection());
	check_validation = true;	
	finalVector =  moveStrategy.getSpeedVector();
}
}
else{
moveStrategy.getSpeedVector().setDirection(listPossibleVector.get(ball_direction).getDirection());
check_validation = true;	
finalVector =  moveStrategy.getSpeedVector();
}*/