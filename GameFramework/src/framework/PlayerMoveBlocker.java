package framework;

import java.awt.Canvas;
import java.awt.Point;

import game_entities.*;
import game_entities.blocker.Wall_damages;
import game_entities.blocker.Wall_laby;
import game_entities.movable.Carapace;
import game_entities.movable.Player;
import gameframework.base.ObservableValue;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.GameUniverse;
import gameframework.game.IllegalMoveException;
import gameframework.game.MoveBlockerChecker;
import gameframework.game.MoveBlockerRulesApplierDefaultImpl;

public class PlayerMoveBlocker extends MoveBlockerRulesApplierDefaultImpl {

	private final ObservableValue<Integer> life;
	GameUniverse universe;
	private Canvas canvas;
	private MoveBlockerChecker moveBlockerChecker;

	public PlayerMoveBlocker(ObservableValue<Integer> life, GameUniverse universe, Canvas canvas,
			MoveBlockerChecker moveBlockerChecker) {
		this.universe = universe;
		this.life = life;
		this.canvas = canvas;
		this.moveBlockerChecker = moveBlockerChecker;
	}

	public void moveBlockerRule(Player play, Wall_damages w) throws IllegalMoveException {
		this.life.setValue(life.getValue() - 1);
		universe.removeGameEntity(w);
		play.getSpriteManager().setType("static");
	}

	public void moveBlockerRule(Carapace cara, Wall_laby w) throws CloneNotSupportedException {

		int x = (int) (w.getPos().getX());
		int y = (int) (w.getPos().getY());
		universe.removeGameEntity(w);
		universe.removeGameEntity(cara);

		Player player = new Player(this.canvas);
		GameMovableDriverDefaultImpl pacDriver = new GameMovableDriverDefaultImpl();
		KeyboardExtensionStrategy keyStr = new KeyboardExtensionStrategy();
		pacDriver.setStrategy(keyStr);
		pacDriver.setmoveBlockerChecker(this.moveBlockerChecker);
		canvas.addKeyListener(keyStr);
		player.setDriver(pacDriver);
		player.setPosition(new Point(x, y));
		universe.addGameEntity(player);
	}
}
