package labyrinth;

import gameframework.game.GameDefaultImpl;
import gameframework.game.GameLevel;
import labygame.LabyGame;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		LabyGame g = new LabyGame();
		ArrayList<GameLevel> levels = new ArrayList<GameLevel>();

		levels.add(new FirstLevel(g)); // only one level is available at this time
		
		g.setLevels(levels);
		g.start();
	}
}
