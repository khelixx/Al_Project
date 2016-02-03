package game_entities.movable;

import java.awt.Canvas;

public class MonsterPhenix extends MonsterAbstract {

	public MonsterPhenix(Canvas canvas) {
		super(canvas);
	}

	@Override
	public String Image() {
		return "images/monstres.gif";
	}

}