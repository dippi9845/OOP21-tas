package main.java.tas.model.tower.factory;

import java.util.List;
import main.java.tas.model.enemy.Enemy;
import main.java.tas.model.tower.AttackType;
import main.java.tas.model.tower.Builder;
import main.java.tas.model.tower.Tower;
import main.java.tas.model.tower.Towers;
import main.java.tas.utils.Position;

/**
 * Interface that build a Mortar tower, this tower is a area tower so it has
 * area damage, the first target must be in the first range, but it must stay
 * outside another small range
 */
public interface MortarFactory {

	/**
	 * this Mortar has a growth of the damage and cost linear
	 * 
	 * @param pos       Position of the Mortar
	 * @param enemyList List of all enemy in the map
	 * @return Mortar tower
	 */
	static public Tower basicMortar(final Position pos, final List<Enemy> enemyList) {
		return new Builder(pos, 25, 650, 5000, "mortar", enemyList).attackType(AttackType.AREA).damageRange(3)
				.setUpgradable(true).upgradeCost(x -> 10).upgradeDamage(x -> 15).maxLevel(15).startUpgradeCost(10)
				.maximumTarget(Integer.MAX_VALUE).findFirst(() -> {
					return Towers.findFistEnemyBiPredicate(e -> Towers.isInRange(pos, e.getPosition(), 16),
							e -> !Towers.isInRange(pos, e.getPosition(), 4), enemyList);
				}).build();
	}

	/**
	 * this Mortar has a growth of the damage and cost quadratic
	 * 
	 * @param pos       Position of the Mortar
	 * @param enemyList List of all enemy in the map
	 * @return Mortar tower
	 */
	static public Tower superMortar(final Position pos, final List<Enemy> enemyList) {
		return new Builder(pos, 10, 650, 8000, "supermortar", enemyList).attackType(AttackType.AREA).damageRange(4)
				.setUpgradable(true).upgradeCost(x -> x * x).upgradeDamage(x -> x * x * 2).maxLevel(10)
				.startUpgradeCost(20).maximumTarget(Integer.MAX_VALUE).findFirst(() -> {
					return Towers.findFistEnemyBiPredicate(e -> Towers.isInRange(pos, e.getPosition(), 12),
							e -> !Towers.isInRange(pos, e.getPosition(), 4), enemyList);
				}).build();
	}

	/**
	 * this Mortar has a growth of the damage and cost exponential
	 * 
	 * @param pos       Position of the Mortar
	 * @param enemyList List of all enemy in the map
	 * @return Mortar tower
	 */
	static public Tower godMortar(final Position pos, final List<Enemy> enemyList) {
		return new Builder(pos, 10, 650, 8000, "godmortar", enemyList).attackType(AttackType.AREA).damageRange(6)
				.setUpgradable(true).upgradeCost(x -> (int) Math.pow(x, x) + 15)
				.upgradeDamage(x -> (int) Math.pow(x, x)).maxLevel(5).startUpgradeCost(100)
				.maximumTarget(Integer.MAX_VALUE).findFirst(() -> {
					return Towers.findFistEnemyBiPredicate(e -> Towers.isInRange(pos, e.getPosition(), 12),
							e -> !Towers.isInRange(pos, e.getPosition(), 4), enemyList);
				}).build();
	}
}
