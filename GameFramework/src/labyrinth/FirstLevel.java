package labyrinth;

import java.awt.Canvas;
import java.awt.Point;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import game_entities.MonsterPhenix;
import game_entities.Player;
import game_entities.blocker.WallAbstract;
import game_entities.blocker.Wall_damages;
import game_entities.blocker.Wall_laby;
import game_entities.overlappableNoMovable.*;
import game_entities.Carapace;
import game_entities.Fireball;
import game_entities.MonsterAbstract;
import game_entities.MonsterDragon;
import gameframework.base.ObservableValue;
import gameframework.game.CanvasDefaultImpl;
import gameframework.game.Game;
import gameframework.game.GameEntity;
import gameframework.game.GameLevelDefaultImpl;
import gameframework.game.GameMovable;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.GameUniverseDefaultImpl;
import gameframework.game.MoveBlockerChecker;
import gameframework.game.MoveBlockerCheckerDefaultImpl;
import gameframework.game.OverlapProcessor;
import gameframework.game.OverlapProcessorDefaultImpl;
import labygame.*;
import patterns.memento.Creator;
import patterns.memento.Guardian;
import sun.font.CreatedFontTracker;

public class FirstLevel extends GameLevelDefaultImpl {
	private Canvas canvas;
	private Creator creator = new Creator();
    private Guardian guardian = new Guardian();
    
	public FirstLevel(Game g) {
		super(g);
		canvas = g.getCanvas();
	}
	
	static int[][] tab = { 
		    { 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 , 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
		    { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 , 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 4, 1},
		    { 1, 0, 2, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1 , 1, 0, 1, 1, 1, 1, 1, 2, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1},
		    { 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 , 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1},
		    { 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1 , 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1},
		    { 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 , 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1},
		    { 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2 , 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1},
		    { 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 0 , 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1},
		    { 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 , 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1},
		    { 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 8, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2 , 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1},
		    { 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 , 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1},
		    { 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 , 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1},
		    { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 , 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
		    { 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 , 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{ 1, 0, 0, 0, 5, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 1 , 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 , 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{ 1, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 1 , 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 , 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{ 1, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 1 , 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 , 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{ 1, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 1 , 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{ 1, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 1 , 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1},
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 , 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	};
	
	public static final int SPRITE_SIZE = 40;

	@Override
	protected void init() {

		// init prototypes
		WallAbstract wall_laby = new Wall_laby(canvas, 0, 0);
		WallAbstract wall_damages = new Wall_damages(canvas, 0, 0);
		Fireball fire = new Fireball(canvas, 0, 0);
		MonsterAbstract monster_dragon = new MonsterDragon(canvas);
		MonsterAbstract monster_phenix = new MonsterPhenix(canvas);

		OverlapProcessor overlapProcessor = new OverlapProcessorDefaultImpl();

		MoveBlockerChecker moveBlockerChecker = new MoveBlockerCheckerDefaultImpl();

		PlayerOverlapRules overlapRules = new PlayerOverlapRules(life[0], score[0], endOfGame, canvas,creator,guardian);
		overlapProcessor.setOverlapRules(overlapRules);

		universe = new GameUniverseDefaultImpl(moveBlockerChecker, overlapProcessor);
		moveBlockerChecker.setMoveBlockerRules(new PlayerMoveBlocker(life[0], universe, canvas, moveBlockerChecker));
		overlapRules.setUniverse(universe);

		gameBoard = new LabyUniverseViewPort(canvas, universe);
		((CanvasDefaultImpl) canvas).setDrawingGameBoard(gameBoard);

		// Filling up the universe with basic non movable entities and inclusion
		// in the universe
		for (int i = 0; i < tab.length; ++i) {
			for (int j = 0; j < tab[0].length; ++j) {
				if (tab[i][j] == 1) {
					WallAbstract wall = wall_laby.clone();
					wall.setPosition(j * SPRITE_SIZE, i * SPRITE_SIZE);
					universe.addGameEntity(wall);
				}
				if (tab[i][j] == 2) {

					WallAbstract wall = wall_damages.clone();
					wall.setPosition(j * SPRITE_SIZE, i * SPRITE_SIZE);
					universe.addGameEntity(wall);
				}
				if (tab[i][j] == 3) {
					AbstractOverlappables end = new EndLevel(canvas, j * SPRITE_SIZE, i * SPRITE_SIZE);
					universe.addGameEntity(end);

				}
				if (tab[i][j] == 4) {
					universe.addGameEntity(new Life(canvas, j * SPRITE_SIZE, i * SPRITE_SIZE));
				}
				if (tab[i][j] == 5) {

					GameMovableDriverDefaultImpl monsterDriver = new MonstersDriver();
					BallStrategy ranStr = new BallStrategy(0, 1, 8);
					monsterDriver.setStrategy(ranStr);
					monsterDriver.setmoveBlockerChecker(moveBlockerChecker);
					MonsterAbstract phen = monster_phenix.clone();
					phen.setDriver(monsterDriver);
					phen.setPosition(new Point(j * SPRITE_SIZE, i * SPRITE_SIZE));
					universe.addGameEntity(phen);
				}

				if (tab[i][j] == 6) {
					GameMovableDriverDefaultImpl monsterDriver = new MonstersDriver();
					BallStrategy ranStr = new BallStrategy(1, 0, 8);
					monsterDriver.setStrategy(ranStr);
					monsterDriver.setmoveBlockerChecker(moveBlockerChecker);
					MonsterAbstract Dragon = monster_dragon.clone();
					Dragon.setDriver(monsterDriver);
					Dragon.setPosition(new Point(j * SPRITE_SIZE, i * SPRITE_SIZE));
					universe.addGameEntity(Dragon);
				}
				if (tab[i][j] == 7) {
					GameMovableDriverDefaultImpl carapaceDriver = new MonstersDriver();
					carapaceDriver.setmoveBlockerChecker(moveBlockerChecker);
					Carapace cara = new Carapace(canvas);
					cara.setPosition(new Point(j * SPRITE_SIZE, i * SPRITE_SIZE));
					cara.setDriver(carapaceDriver);
					universe.addGameEntity(cara);
				}

				if (tab[i][j] == 8) {
					universe.addGameEntity(new MysteryBox(canvas, j * SPRITE_SIZE, i * SPRITE_SIZE));
				}

			}
		}

		universe.addGameEntity(new Teleportation(canvas, new Point(27 * SPRITE_SIZE, 5 * SPRITE_SIZE),
				new Point(1 * SPRITE_SIZE, 14 * SPRITE_SIZE)));

		universe.addGameEntity(new Teleportation(canvas, new Point(26 * SPRITE_SIZE, 21 * SPRITE_SIZE),
				new Point(28 * SPRITE_SIZE, 14 * SPRITE_SIZE)));

		universe.addGameEntity(new Teleportation(canvas, new Point(13 * SPRITE_SIZE, 1 * SPRITE_SIZE),
				new Point(29 * SPRITE_SIZE, 5 * SPRITE_SIZE)));

		WallAbstract wall = wall_laby.clone();
		wall.setPosition(2 * SPRITE_SIZE, -1 * SPRITE_SIZE);
		universe.addGameEntity(wall);

		Player player = new Player(canvas);
		GameMovableDriverDefaultImpl pacDriver = new GameMovableDriverDefaultImpl();
		KeyboardExtensionStrategy keyStr = new KeyboardExtensionStrategy();
		pacDriver.setStrategy(keyStr);
		pacDriver.setmoveBlockerChecker(moveBlockerChecker);
		canvas.addKeyListener(keyStr);
		player.setDriver(pacDriver);
		player.setPosition(new Point(2 * SPRITE_SIZE, 0 * SPRITE_SIZE));
		universe.addGameEntity(player);

		for (int x = 0; x < 8; ++x) {
			GameEntity fireball = fire.clone();
			GameMovableDriverDefaultImpl ghostDriv = new BallMovableDriver();
			BallStrategy ranStr = new BallStrategy(1, 1, 10);
			ghostDriv.setStrategy(ranStr);
			ghostDriv.setmoveBlockerChecker(moveBlockerChecker);
			((GameMovable) fireball).setDriver(ghostDriv);
			Random randx = new Random();
			int monster_x = randx.nextInt(14) + 28;
			int monster_y = randx.nextInt(7) + 14;
			((GameMovable) fireball).setPosition(new Point(monster_x * SPRITE_SIZE, monster_y * SPRITE_SIZE));
			universe.addGameEntity(fireball);
		}
	
		creator.setState(player.clone());
		guardian.add(creator.saveStateToMemento());
	}
}
