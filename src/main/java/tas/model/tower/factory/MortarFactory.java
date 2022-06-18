package main.java.tas.model.tower.factory;

import java.util.List;
import main.java.tas.model.enemy.Enemy;
import main.java.tas.model.tower.AttackType;
import main.java.tas.model.tower.TowerBuilder;
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
		final TowerBuilder t = new TowerBuilder(pos, DefaultTowersInfo.TOWERSJSONOBJECT.get(DefaultTowers.BASICMORTAR), enemyList);
		return t.attackType(AttackType.AREA)
				.damageRange(400)
				.setUpgradable(true)
				.upgradeCost(x -> 10)
				.upgradeDamage(x -> 15)
				.maxLevel(15)
				.startUpgradeCost(1000)
				.maximumTarget(Integer.MAX_VALUE)
				.findFirst(() -> {
					return Towers.findFistEnemyBiPredicate(
							e -> Towers.isInRange(pos, e.getPosition(), t.getRange()),
							e ->!Towers.isInRange(pos, e.getPosition(), 140),
							enemyList);
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
		final TowerBuilder t =  new TowerBuilder(pos, DefaultTowersInfo.TOWERSJSONOBJECT.get(DefaultTowers.SUPERMORTAR), enemyList);
		return t.attackType(AttackType.AREA)
				.damageRange(450)
				.setUpgradable(true)
				.upgradeCost(x -> x * x)
				.upgradeDamage(x -> x * x * 2)
				.maxLevel(10)
				.startUpgradeCost(1000)
				.maximumTarget(Integer.MAX_VALUE)
				.findFirst(() -> {
					return Towers.findFistEnemyBiPredicate(
							e -> Towers.isInRange(pos, e.getPosition(), t.getRange()),
							e ->!Towers.isInRange(pos, e.getPosition(), 140),
							enemyList);
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
		final TowerBuilder t = new TowerBuilder(pos, DefaultTowersInfo.TOWERSJSONOBJECT.get(DefaultTowers.GODMORTAR), enemyList);
		return t.attackType(AttackType.AREA)
				.damageRange(600)
				.setUpgradable(true)
				.upgradeCost(x -> (int) Math.pow(x, x) + 15)
				.upgradeDamage(x -> (int) Math.pow(x, x))
				.maxLevel(5)
				.startUpgradeCost(1000)
				.maximumTarget(Integer.MAX_VALUE).findFirst(() -> {
					return Towers.findFistEnemyBiPredicate(
							e -> Towers.isInRange(pos, e.getPosition(), t.getRange()),
							e ->!Towers.isInRange(pos, e.getPosition(), 140),
							enemyList);
				}).build();
	}
}
