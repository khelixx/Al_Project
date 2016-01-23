package game_entities;

import java.awt.Canvas;
import java.util.Random;

import gameframework.game.GameEntity;

public class monsterCreation {
	
	Canvas canvas;
	
	public monsterCreation(Canvas canvas){
		this.canvas = canvas;
	}
	public GameEntity createMonsters(){
		Random randx = new Random();
		int monster = randx.nextInt(100 - 1 );
		
		if ((monster % 2) == 0){
			return new Monster_Dragon(canvas);
		}
		else{
			return new Monster_Phenix(canvas);
		}
	}
}
