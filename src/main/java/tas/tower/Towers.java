package main.java.tas.tower;

import java.util.List;
import java.util.Optional;
import java.util.LinkedList;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;

public class Towers {
	static public final List<Enemy> ENEMYLIST = new LinkedList<Enemy>();
	
	static public boolean isTargetInRange(final Enemy e, final Tower t) {
		return Position.findDistance(t.getPos(), e.getPosition()) <= t.getRadius();
	}
	
	static public Optional<Enemy> findFirstEnemy(final Tower t) {
		return ENEMYLIST.stream().filter(x->Towers.isTargetInRange(x, t)).findFirst();
	}
	
	static public List<Enemy> findAll(Predicate<Enemy> f) {
		return ENEMYLIST.stream().filter(f).collect(Collectors.toList());
	}
}