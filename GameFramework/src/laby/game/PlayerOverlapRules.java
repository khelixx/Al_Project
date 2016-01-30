package laby.game;

import java.awt.Point;
import java.util.Vector;

import game_entities.EndLevel;
import game_entities.Fireball;
import game_entities.Life;
import game_entities.Monster_Dragon;
import game_entities.Monster_Phenix;
import game_entities.Player;
import game_entities.Teleportation;
import game_entities.Wall_damages;
import game_entities.Wall_laby;
import game_entities.Carapace;
import gameframework.base.ObservableValue;
import gameframework.base.Overlap;
import gameframework.base.SpeedVectorDefaultImpl;
import gameframework.game.GameLevel;
import gameframework.game.GameLevelDefaultImpl;
import gameframework.game.GameUniverse;
import gameframework.game.OverlapRulesApplierDefaultImpl;
import labyrinth.FirstLevel;
import pacman.entity.TeleportPairOfPoints;

public class PlayerOverlapRules extends OverlapRulesApplierDefaultImpl {
	
	protected GameUniverse universe;

	private final ObservableValue<Integer> score;
	private final ObservableValue<Integer> life;
	private final ObservableValue<Boolean> endOfGame;

	public PlayerOverlapRules(Point pacPos, Point gPos,
			ObservableValue<Integer> life, ObservableValue<Integer> score,
			ObservableValue<Boolean> endOfGame) {
		this.life = life;
		this.score = score;
		this.endOfGame = endOfGame;
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
		universe.removeGameEntity(carapace);
	}
	
	public void overlapRule(Player player, Life life){
		this.life.setValue(this.life.getValue() + 1);
		universe.removeGameEntity(life);
	}
}
