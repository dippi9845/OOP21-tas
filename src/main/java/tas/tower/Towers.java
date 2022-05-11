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
	
	static public boolean isInRange(final Position x, final Position y, final int radius) {
		return Position.findDistance(x, y) <= radius;
	}
	
	static public boolean isTargetInRange(final Enemy e, final Tower t) {
		return isInRange(e.getPosition(), t.getPos(), t.getRadius());
	}
	
	static public Optional<Enemy> findFirstEnemyInRange(final Position t, final int radius) {
		return findFistEnemyByPredicate(e->Towers.isInRange(e.getPosition(), t, radius));
	}
	
	static public Optional<Enemy> findFirstEnemyInRange(final Tower t) {
		return findFistEnemyByPredicate(x->Towers.isTargetInRange(x, t));
	}
	
	static public Optional<Enemy> findFistEnemyByPredicate(final Predicate<Enemy> f) {
		return ENEMYLIST.stream().filter(f).findFirst();
	}
	
	static public Optional<Enemy> findFistEnemyBiPredicate(final Predicate<Enemy> f, final Predicate<Enemy> g) {
		return ENEMYLIST.stream().filter(f).filter(g).findFirst();
	}
	
	static public List<Enemy> findAll(final Predicate<Enemy> f) {
		return ENEMYLIST.stream().filter(f).collect(Collectors.toList());
	}
}