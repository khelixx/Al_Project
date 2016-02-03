package patterns.memento;

import game_entities.movable.Player;

public class Memento {

	private Player memento;

	public Memento(Player state) {
		memento = state;
	}

	public Player  getState() {
		return memento;
	}
}
