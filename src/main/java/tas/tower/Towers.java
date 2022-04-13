package main.java.tas.tower;

import java.util.List;
import java.util.LinkedList;
import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;

public class Towers {
	static public final List<Enemy> ENEMYLIST = new LinkedList<Enemy>();
	
	static public boolean isValidTarget(final Enemy e, final Tower t) {
		if (Position.findDistance(t.getPos(), e.getPosition()) <= t.getRadius()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	static public Enemy findFirstEnemy(final Tower t) {
		Enemy e = null;
		for (var i : ENEMYLIST) {
			if (Towers.isValidTarget(i, t)) {
				e = i;
				break; // Brutto ma necessario
			}
		}
		return e;
	}
}
