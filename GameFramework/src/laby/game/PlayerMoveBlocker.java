package laby.game;

import game_entities.*;
import gameframework.base.ObservableValue;
import gameframework.game.IllegalMoveException;
import gameframework.game.MoveBlockerRulesApplierDefaultImpl;

public class PlayerMoveBlocker extends MoveBlockerRulesApplierDefaultImpl {

	private final ObservableValue<Integer> life;
	
	public PlayerMoveBlocker(ObservableValue<Integer> life){
		this.life = life;
	}
	public void moveBlockerRule(Player play, Wall_damages w) throws IllegalMoveException {
		this.life.setValue(life.getValue() - 1);
		play.getSpriteManager().setType("static");
	}

}
