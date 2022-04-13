package main.java.tas.tower;

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
}
