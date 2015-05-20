package com.cgk.game.system;

import java.util.ArrayList;
import java.util.List;

import com.cgk.game.gameobject.units.enemy.Enemy;
import com.cgk.game.gameobject.units.enemy.Wesley;

/**
 * @author ianlukens Apr 29, 2015
 *
 */
public class LevelAssets {

	public LevelAssets(int levelId) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param queue
	 * @param level
	 * @return
	 */
	public List<Enemy> getEnemies(int level) {
		// TODO Auto-generated method stub
		List<Enemy> enemies = new ArrayList<>();
		enemies.add(new Wesley());
		return enemies;
	}

	public boolean hasLevel(int floor) {
		return false;
	}

}
