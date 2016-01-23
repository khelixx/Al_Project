package laby.game;

import game_entities.*;
import gameframework.base.ObservableValue;
import gameframework.game.GameUniverse;
import gameframework.game.IllegalMoveException;
import gameframework.game.MoveBlockerRulesApplierDefaultImpl;

public class PlayerMoveBlocker extends MoveBlockerRulesApplierDefaultImpl {

	private final ObservableValue<Integer> life;
	GameUniverse universe;
	
	public PlayerMoveBlocker(ObservableValue<Integer> life, GameUniverse universe){
		this.universe = universe;
		this.life = life;
	}
	public void moveBlockerRule(Player play, Wall_damages w) throws IllegalMoveException {
		this.life.setValue(life.getValue() - 1);
		universe.removeGameEntity(w);
		play.getSpriteManager().setType("static");
	}

}
