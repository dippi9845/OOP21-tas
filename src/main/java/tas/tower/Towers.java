package main.java.tas.tower;

import java.util.List;

import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;

public class Towers {
	static public boolean isValidTarget(final Enemy e, final Tower t) {
		if (Position.findDistance(t.getPos(), e.getPosition()) <= t.getRadius()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	static public Enemy findFirstEnemy(final List<Enemy> enemyList, final Tower t) {
		Enemy e = null;
		for (var i : enemyList) {
			if (Towers.isValidTarget(i, t)) {
				e = i;
				break; // Brutto ma necessario
			}
		}
		return e;
	}
}
