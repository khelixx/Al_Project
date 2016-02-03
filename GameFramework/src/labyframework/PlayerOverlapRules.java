package labyframework;

import java.awt.Canvas;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import game_entities.AbstractOverlappables;
import game_entities.CheckPoint;
import game_entities.EndLevel;
import game_entities.Life;
import game_entities.MysteryBox;
import game_entities.Teleportation;
import game_entities.blocker.WallAbstract;
import game_entities.blocker.Wall_damages;
import game_entities.blocker.Wall_laby;
import game_entities.movable.Carapace;
import game_entities.movable.Fireball;
import game_entities.movable.MonsterDragon;
import game_entities.movable.MonsterPhenix;
import game_entities.movable.Player;
import gameframework.base.ObservableValue;
import gameframework.base.Overlap;
import gameframework.base.SpeedVectorDefaultImpl;
import gameframework.game.GameEntity;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.GameUniverse;
import gameframework.game.OverlapRulesApplierDefaultImpl;
import patterns.memento.Creator;
import patterns.memento.Guardian;

public class PlayerOverlapRules extends OverlapRulesApplierDefaultImpl {

	private static final int SPRITE_SIZE = 40;
	protected GameUniverse universe;
	private boolean end = false;
	private final ObservableValue<Integer> score;
	private final ObservableValue<Integer> life;
	private final ObservableValue<Boolean> endOfGame;
	private Canvas canvas;
	Creator creator;
	Guardian guardian;

	public PlayerOverlapRules(ObservableValue<Integer> life, ObservableValue<Integer> score,
			ObservableValue<Boolean> endOfGame, Canvas canvas, Creator creator, Guardian guardian) {
		this.life = life;
		this.score = score;
		this.endOfGame = endOfGame;
		this.canvas = canvas;
		this.creator = creator;
		this.guardian = guardian;
	}

	@Override
	public void setUniverse(GameUniverse universe) {
		this.universe = universe;
	}

	@Override
	public void applyOverlapRules(Vector<Overlap> overlappables) {
		super.applyOverlapRules(overlappables);
	}

	public void overlapRule(Player player, Teleportation start) {
		player.setPosition(start.getDestination());
		player.getSpriteManager().setType("static");
		universe.removeGameEntity(start);
	}

	public void overlapRule(Player player, MonsterPhenix monster) {
		if (player.isVulnerable()) {
			if (this.life.getValue() != 1) {
				this.life.setValue(this.life.getValue() - 1);
				player.setInvulnerable();
			} else {
				player.setPosition(restore().getPosition());
				player.getSpriteManager().setType("static");
			}
		}
	}

	public void overlapRule(Player player, MonsterDragon monster) {
		if (player.isVulnerable()) {
			this.life.setValue(1);
			player.setPosition(restore().getPosition());
			player.getSpriteManager().setType("static");
		}
	}

	public void overlapRule(Player player, Fireball ball) {
		if (player.isVulnerable()) {
			if (this.life.getValue() != 1) {
				this.life.setValue(this.life.getValue() - 1);
				player.setInvulnerable();
			} else {
				player.setPosition(restore().getPosition());
				player.getSpriteManager().setType("static");
			}
		}
	}

	public void overlapRule(Player player, EndLevel end) {
		WallAbstract wall_laby = new Wall_laby(canvas, 0, 0);

		if (this.end == true) {
			this.endOfGame.setValue(true);
		} else {

			int[][] end_level = level();
			for (int i = 0; i < end_level.length; ++i) {
				for (int j = 0; j < end_level[0].length; ++j) {
					if (end_level[i][j] == 1) {
						WallAbstract wall = null;
						wall = wall_laby.clone();
						wall.setPosition(j * SPRITE_SIZE, i * SPRITE_SIZE);
						universe.addGameEntity(wall);
					}
					if (end_level[i][j] == 2) {
						AbstractOverlappables end_level_final = new EndLevel(canvas, j * SPRITE_SIZE, i * SPRITE_SIZE);
						universe.addGameEntity(end_level_final);
					}
				}
			}
			WallAbstract wall = wall_laby.clone();
			wall.setPosition(47 * SPRITE_SIZE, 21 * SPRITE_SIZE);
			universe.addGameEntity(wall);
			this.end = true;
		}

	}

	public void overlapRule(Player player, Carapace carapace) {
		BallStrategy ranStr = new BallStrategy(1, 0, 15);
		((GameMovableDriverDefaultImpl) carapace.getDriver()).setStrategy(ranStr);
		int new_position_x = (int) carapace.getPosition().getX()
				+ (int) carapace.getSpeedVector().getDirection().getX();
		int new_position_y = (int) carapace.getPosition().getY()
				+ (int) carapace.getSpeedVector().getDirection().getY();
		carapace.setPosition(new Point(new_position_x, new_position_y));
		universe.removeGameEntity(player);
	}

	public void overlapRule(Player player, MysteryBox mystery) {

		Random randx = new Random();
		int present = randx.nextInt(2);

		if (present == 1) {
			universe.addGameEntity(new Life(canvas, 2 * SPRITE_SIZE, 0 * SPRITE_SIZE));
		} else if (present == 2) {
			universe.addGameEntity(new Wall_damages(canvas, 11 * SPRITE_SIZE, 8 * SPRITE_SIZE));
			universe.addGameEntity(new Wall_damages(canvas, 9 * SPRITE_SIZE, 8 * SPRITE_SIZE));
		} else {
			Iterator<GameEntity> li = universe.gameEntities();
			while (li.hasNext()) {
				GameEntity obj = li.next();
				if (obj instanceof WallAbstract
						&& ((WallAbstract) obj).getPos().equals(new Point(28 * SPRITE_SIZE, 5 * SPRITE_SIZE))) {
					universe.removeGameEntity(obj);
				}
			}
			universe.addGameEntity(new Wall_damages(canvas, 26 * SPRITE_SIZE, 5 * SPRITE_SIZE));
		}
		universe.removeGameEntity(mystery);
	}

	public void overlapRule(Player player, Life life) {
		this.life.setValue(this.life.getValue() + 1);
		universe.removeGameEntity(life);
	}

	public void overlapRule(Player player, CheckPoint p){
		System.out.println("Check point reached ");
		creator.setState(player.clone());
		guardian.add(creator.saveStateToMemento());
		universe.removeGameEntity(p);
	}
	

	public void overlapRule(Fireball ball, Fireball ball2) {

		if (ball.getPosition().y < ball2.getPosition().y) {
			if (ball.getSpeedVector() != new SpeedVectorDefaultImpl(new Point(1, -1))) {
				((GameMovableDriverDefaultImpl) ball.getDriver()).setStrategy(new BallStrategy(1, -1, 10));
				((GameMovableDriverDefaultImpl) ball2.getDriver()).setStrategy(new BallStrategy(-1, 1, 10));
			} else {
				((GameMovableDriverDefaultImpl) ball.getDriver()).setStrategy(new BallStrategy(-1, -1, 10));
				((GameMovableDriverDefaultImpl) ball2.getDriver()).setStrategy(new BallStrategy(1, 1, 10));
			}
		} else {
			if (ball2.getSpeedVector() != new SpeedVectorDefaultImpl(new Point(1, -1))) {
				((GameMovableDriverDefaultImpl) ball2.getDriver()).setStrategy(new BallStrategy(1, -1, 10));
				((GameMovableDriverDefaultImpl) ball.getDriver()).setStrategy(new BallStrategy(-1, 1, 10));
			} else {
				((GameMovableDriverDefaultImpl) ball2.getDriver()).setStrategy(new BallStrategy(-1, -1, 10));
				((GameMovableDriverDefaultImpl) ball.getDriver()).setStrategy(new BallStrategy(1, 1, 10));
			}
		}
	}

	public int[][] level() {
		Iterator<GameEntity> li = universe.gameEntities();
		while (li.hasNext()) {
			GameEntity obj = li.next();
			if (!(obj instanceof Player)) {
				universe.removeGameEntity(obj);
			}
		}

		File file = new File("files/end_level");
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int[][] end_level = new int[23][47];
		int value = 0;
		while (scanner.hasNextInt()) {
			end_level[value / 47][value % 47] = scanner.nextInt();
			value++;
		}
		scanner.close();
		return end_level;
	}

	public Player restore() {
		this.life.setValue(3);
		this.score.setValue(90);
		return creator.getStateFromMemento(guardian.getLast());
	}
}
