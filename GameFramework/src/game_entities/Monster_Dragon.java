package game_entities;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.base.Drawable;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;
import gameframework.game.SpriteManager;
import gameframework.game.SpriteManagerDefaultImpl;

public class Monster_Dragon extends MonsterAbstract{

	public Monster_Dragon(Canvas canvas) {
		super(canvas);
	}
	
	@Override
	public String Image() {
		// TODO Auto-generated method stub
		return "images/monsters.gif";
	}
	
}
