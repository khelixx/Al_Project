package labygame;

import java.awt.Canvas;
import java.awt.Point;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

import game_entities.Fireball;
import game_entities.Monster_Dragon;
import game_entities.Monster_Phenix;
import game_entities.Player;
import game_entities.blocker.Wall;
import game_entities.blocker.Wall_damages;
import game_entities.blocker.Wall_laby;
import game_entities.overlappableNoMovable.Carapace;
import game_entities.overlappableNoMovable.EndLevel;
import game_entities.overlappableNoMovable.Life;
import game_entities.overlappableNoMovable.MysteryBox;
import game_entities.overlappableNoMovable.Teleportation;
import gameframework.base.ObservableValue;
import gameframework.base.Overlap;
import gameframework.game.GameEntity;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.GameUniverse;
import gameframework.game.OverlapRulesApplierDefaultImpl;

public class PlayerOverlapRules extends OverlapRulesApplierDefaultImpl {
	
	private static final int SPRITE_SIZE = 40;
	protected GameUniverse universe;

	private final ObservableValue<Integer> score;
	private final ObservableValue<Integer> life;
	private final ObservableValue<Boolean> endOfGame;
	private Canvas canvas;

	public PlayerOverlapRules(
			ObservableValue<Integer> life, ObservableValue<Integer> score,
			ObservableValue<Boolean> endOfGame, Canvas canvas) {
		this.life = life;
		this.score = score;
		this.endOfGame = endOfGame;
		this.canvas = canvas;
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
	
	public void overlapRule(Player player, Monster_Phenix monster){
		if (player.isVulnerable()){
			this.life.setValue(this.life.getValue() - 1);
			player.setInvulnerable(20);
		}		
	}
	
	public void overlapRule(Player player, Monster_Dragon monster){
		this.life.setValue(0);
	}

	public void overlapRule(Player player, Fireball ball){
		this.life.setValue(this.life.getValue() - 1);
	}
	
	public void overlapRule(Player player, EndLevel end){
		this.endOfGame.setValue(true);
	}
	
	public void overlapRule(Player player, Carapace carapace){
		BallStrategy ranStr = new BallStrategy(1,0,15);
	    ((GameMovableDriverDefaultImpl) carapace.getDriver()).setStrategy(ranStr);
	    int new_position_x = (int)carapace.getPosition().getX() + (int)carapace.getSpeedVector().getDirection().getX();
	    int new_position_y = (int)carapace.getPosition().getY() + (int)carapace.getSpeedVector().getDirection().getY();
	    carapace.setPosition(new Point(new_position_x,new_position_y));
	    universe.removeGameEntity(player);
	}
	
	public void overlapRule(Player player, MysteryBox mystery){
		
		Random randx = new Random();
		int present = randx.nextInt(2);
		
		if (present == 1){
			universe.addGameEntity(new Life(canvas , 2 * SPRITE_SIZE, 0 * SPRITE_SIZE));
		}
		else if(present ==2){
			universe.addGameEntity(new Wall_damages(canvas , 11 * SPRITE_SIZE, 8 * SPRITE_SIZE));
			universe.addGameEntity(new Wall_damages(canvas , 9 * SPRITE_SIZE, 8 * SPRITE_SIZE));
		}
		else{
			Iterator<GameEntity> li = universe.gameEntities();
			while(li.hasNext()){
				GameEntity obj = li.next();
				if( obj instanceof Wall && ((Wall) obj).getPos().equals(new Point(28*SPRITE_SIZE, 5*SPRITE_SIZE))){
					universe.removeGameEntity(obj);
				}
			}
			universe.addGameEntity(new Wall_damages(canvas , 26 * SPRITE_SIZE, 5 * SPRITE_SIZE));
		}
		universe.removeGameEntity(mystery);
	}

	public void overlapRule(Player player, Life life){
		this.life.setValue(this.life.getValue() + 1);
		universe.removeGameEntity(life);
	}
	
	public void overlapRule(Fireball ball, Fireball ball2){
		
		if (ball.getPosition().y < ball2.getPosition().y){
			Random randx = new Random();
			int direction = randx.nextInt(1);
			
			if (direction == 0){
				((GameMovableDriverDefaultImpl)ball.getDriver()).setStrategy(new BallStrategy(1,-1, 10));
				((GameMovableDriverDefaultImpl)ball2.getDriver()).setStrategy(new BallStrategy(-1, 1, 10));
			}
			else{
				((GameMovableDriverDefaultImpl)ball.getDriver()).setStrategy(new BallStrategy(-1,-1, 10));
				((GameMovableDriverDefaultImpl)ball2.getDriver()).setStrategy(new BallStrategy(1, 1, 10));
			}
		}
		else{
			Random randx = new Random();
			int direction = randx.nextInt(1);
			
			if (direction == 0){
				((GameMovableDriverDefaultImpl)ball2.getDriver()).setStrategy(new BallStrategy(1,-1, 10));
				((GameMovableDriverDefaultImpl)ball.getDriver()).setStrategy(new BallStrategy(-1, 1, 10));
			}
			else{
				((GameMovableDriverDefaultImpl)ball2.getDriver()).setStrategy(new BallStrategy(-1,-1, 10));
				((GameMovableDriverDefaultImpl)ball.getDriver()).setStrategy(new BallStrategy(1, 1, 10));
			}
		}
	}
}
