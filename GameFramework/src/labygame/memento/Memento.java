package labygame.memento;

import gameframework.game.GameUniverse;



public class Memento {
	private GameUniverse u;

	public Memento(GameUniverse u) {
		super();
		this.u = u;
	}

	public GameUniverse getU() {
		return u;
	}

	public void setU(GameUniverse u) {
		this.u = u;
	}


	

}
