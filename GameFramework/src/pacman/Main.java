package pacman;

import gameframework.game.GameDefaultImpl;
import gameframework.game.GameLevel;
import labyrinth.First_level;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		GameDefaultImpl g = new GameDefaultImpl();
		ArrayList<GameLevel> levels = new ArrayList<GameLevel>();

		levels.add(new First_level(g)); // only one level is available at this time
		
		g.setLevels(levels);
		g.start();
	}
}
