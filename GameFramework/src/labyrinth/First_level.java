package labyrinth;

import java.awt.Canvas;
import java.awt.Point;

import game_entities.Player;
import game_entities.Start;
import game_entities.Wall_damages;
import game_entities.Wall_laby;
import gameframework.base.MoveStrategyKeyboard;
import gameframework.game.CanvasDefaultImpl;
import gameframework.game.Game;
import gameframework.game.GameLevelDefaultImpl;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.GameUniverseDefaultImpl;
import gameframework.game.MoveBlockerChecker;
import gameframework.game.MoveBlockerCheckerDefaultImpl;
import gameframework.game.OverlapProcessor;
import gameframework.game.OverlapProcessorDefaultImpl;
import laby.game.LabyGame;
import laby.game.LabyUniverseViewPort;
import laby.game.PlayerMoveBlocker;
import laby.game.PlayerOverlapRules;
import pacman.entity.Pacman;
import pacman.entity.Wall;
import pacman.rule.PacmanMoveBlockers;
import pacman.rule.PacmanOverlapRules;

public class First_level extends GameLevelDefaultImpl {
	
	Canvas canvas ;
	


	static int[][] tab = { 
		    { 3, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 2, 1, 0, 0, 0, 0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 0, 3 },};
	
	
	public static final int SPRITE_SIZE = 40;
			
	public First_level(Game g) {
		super(g);
		canvas = g.getCanvas();
	}
	
	@Override
	protected void init() {
		OverlapProcessor overlapProcessor = new OverlapProcessorDefaultImpl();

		MoveBlockerChecker moveBlockerChecker = new MoveBlockerCheckerDefaultImpl();
		moveBlockerChecker.setMoveBlockerRules(new PlayerMoveBlocker(life[0]));
		
		PlayerOverlapRules overlapRules = new PlayerOverlapRules(new Point(1 * SPRITE_SIZE, 1 * SPRITE_SIZE),
				new Point(14 * SPRITE_SIZE, 17 * SPRITE_SIZE), life[0], score[0], endOfGame);
		overlapProcessor.setOverlapRules(overlapRules);

		universe = new GameUniverseDefaultImpl(moveBlockerChecker, overlapProcessor);
		overlapRules.setUniverse(universe);

		gameBoard = new LabyUniverseViewPort(canvas, universe);
		((CanvasDefaultImpl) canvas).setDrawingGameBoard(gameBoard);
		
		// Filling up the universe with basic non movable entities and inclusion in the universe
		for (int i = 0; i < tab.length; ++i) {
			for (int j = 0; j < tab[0].length; ++j) {
				if (tab[i][j] == 1) {
					universe.addGameEntity(new Wall_laby(canvas, j * SPRITE_SIZE, i * SPRITE_SIZE));
				}
				
				if (tab[i][j] == 2) {
					universe.addGameEntity(new Wall_damages(canvas, j * SPRITE_SIZE, i * SPRITE_SIZE));
				}
				
				if (tab[i][j] == 3) {
					universe.addGameEntity(new Start(canvas, j * SPRITE_SIZE, i * SPRITE_SIZE));
				}
			}
		}
		
		// Pacman definition and inclusion in the universe
		Player player = new Player(canvas);
		GameMovableDriverDefaultImpl pacDriver = new GameMovableDriverDefaultImpl();
		MoveStrategyKeyboard keyStr = new MoveStrategyKeyboard();
		pacDriver.setStrategy(keyStr);
		pacDriver.setmoveBlockerChecker(moveBlockerChecker);
		canvas.addKeyListener(keyStr);
		player.setDriver(pacDriver);
		player.setPosition(new Point(0 * SPRITE_SIZE, 0 * SPRITE_SIZE));
		universe.addGameEntity(player);
	}
}
