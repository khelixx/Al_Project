package labyrinth;

import java.awt.Canvas;
import java.awt.Point;
import java.util.Date;
import java.util.Random;

import game_entities.EndLevel;
import game_entities.AbstractOverlappables;
import game_entities.Monster_Phenix;
import game_entities.MysteryBox;
import game_entities.Player;
import game_entities.Teleportation;
import game_entities.blocker.Wall_damages;
import game_entities.blocker.Wall_laby;
import game_entities.Carapace;
import game_entities.Fireball;
import game_entities.Life;
import game_entities.Monster_Dragon;
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
import laby.game.BallMovableDriver;
import laby.game.BallStrategy;
import laby.game.KeyboardExtensionStrategy;
import laby.game.LabyUniverseViewPort;
import laby.game.MonstersDriver;
import laby.game.PlayerMoveBlocker;
import laby.game.PlayerOverlapRules;

public class FirstLevel extends GameLevelDefaultImpl {
	private Canvas canvas ;

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
		    { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 , 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
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
		OverlapProcessor overlapProcessor = new OverlapProcessorDefaultImpl();

		MoveBlockerChecker moveBlockerChecker = new MoveBlockerCheckerDefaultImpl();
		
		
		PlayerOverlapRules overlapRules = new PlayerOverlapRules(new Point(1 * SPRITE_SIZE, 1 * SPRITE_SIZE),
				new Point(14 * SPRITE_SIZE, 17 * SPRITE_SIZE), life[0], score[0], endOfGame, canvas);
		overlapProcessor.setOverlapRules(overlapRules);

		universe = new GameUniverseDefaultImpl(moveBlockerChecker, overlapProcessor);
		moveBlockerChecker.setMoveBlockerRules(new PlayerMoveBlocker(life[0],universe,canvas, moveBlockerChecker));
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
						AbstractOverlappables end = new EndLevel(canvas, j * SPRITE_SIZE, i * SPRITE_SIZE);
					    universe.addGameEntity( end );
					    
					}
					if (tab[i][j] == 4) {
					    universe.addGameEntity(new Life(canvas, j * SPRITE_SIZE, i * SPRITE_SIZE));
					}
					if (tab[i][j] == 5) {
						
						GameMovableDriverDefaultImpl monsterDriver = new MonstersDriver();
						BallStrategy ranStr = new BallStrategy(0,1,8);
						monsterDriver.setStrategy(ranStr);
						monsterDriver.setmoveBlockerChecker(moveBlockerChecker);
						Monster_Phenix phen = new Monster_Phenix(canvas);
						phen.setDriver(monsterDriver);
						phen.setPosition(new Point(j * SPRITE_SIZE, i * SPRITE_SIZE));
					    universe.addGameEntity(phen);
					}
					
					if (tab[i][j] == 6) {
						GameMovableDriverDefaultImpl monsterDriver = new MonstersDriver();
						BallStrategy ranStr = new BallStrategy(1,0,8);
						monsterDriver.setStrategy(ranStr);
						monsterDriver.setmoveBlockerChecker(moveBlockerChecker);
						Monster_Dragon Dragon = new Monster_Dragon(canvas);
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
					    System.out.println(j + ":" + i);
					}
					
				}
		}
		
		universe.addGameEntity(new Teleportation(canvas,new Point(27 * SPRITE_SIZE, 5 * SPRITE_SIZE),
				new Point(1 * SPRITE_SIZE, 14 * SPRITE_SIZE)));
		
		universe.addGameEntity(new Teleportation(canvas,new Point(26 * SPRITE_SIZE, 21 * SPRITE_SIZE),
				new Point(28 * SPRITE_SIZE, 14 * SPRITE_SIZE)));
		
		universe.addGameEntity(new Teleportation(canvas,new Point(13 * SPRITE_SIZE, 1 * SPRITE_SIZE),
				new Point(29 * SPRITE_SIZE, 5 * SPRITE_SIZE)));
		
		universe.addGameEntity(new Wall_laby(canvas, 2 * SPRITE_SIZE, -1 * SPRITE_SIZE));

		Player player = new Player(canvas);
		GameMovableDriverDefaultImpl pacDriver = new GameMovableDriverDefaultImpl();
		KeyboardExtensionStrategy keyStr = new KeyboardExtensionStrategy();
		pacDriver.setStrategy(keyStr);
		pacDriver.setmoveBlockerChecker(moveBlockerChecker);
		canvas.addKeyListener(keyStr);
		player.setDriver(pacDriver);
		player.setPosition(new Point(2 * SPRITE_SIZE, 0 * SPRITE_SIZE));
		universe.addGameEntity(player);
		
		for ( int x = 0 ; x <8 ; ++x){
			GameEntity sonics =  new Fireball(canvas);
			GameMovableDriverDefaultImpl ghostDriv = new BallMovableDriver();
			BallStrategy ranStr = new BallStrategy(1,-1,10);
			ghostDriv.setStrategy(ranStr);
			ghostDriv.setmoveBlockerChecker(moveBlockerChecker);
			((GameMovable) sonics).setDriver(ghostDriv);
			Random randx = new Random();
			int monster_x = randx.nextInt(14) + 28;
			int monster_y = randx.nextInt(7) + 14;
			((GameMovable) sonics).setPosition(new Point(monster_x * SPRITE_SIZE, monster_y * SPRITE_SIZE));
			universe.addGameEntity(sonics);
		}
	}
}
