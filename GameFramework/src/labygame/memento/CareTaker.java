package labygame.memento;

import gameframework.game.GameUniverse;

import labygame.PlayerOverlapRules;

public class CareTaker {
	private static Memento m = new Memento(null);
	public static void save(PlayerOverlapRules overlapRules){
		GameUniverse u = overlapRules.getUniverse();
		m.setU(u);
	}
	public void restore(PlayerOverlapRules overlapRules){
		overlapRules.setUniverse(m.getU());
	}
	

}
