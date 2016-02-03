package labyrinth;

import gameframework.game.GameLevel;


import java.util.ArrayList;

import framework.LabyGame;

public class Main {
	public static void main(String[] args) {
		
		LabyGame g = new LabyGame();
		
		ArrayList<GameLevel> levels = new ArrayList<GameLevel>();
		FirstLevel l1 = new FirstLevel(g);
		
		levels.add(l1); // only one level is available at this time
		
		g.setLevels(levels);
		g.start();
	}
}
