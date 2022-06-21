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
	 * This Mortar has a growth of the damage linear
	 * 
	 * @param pos       Position of the Mortar
	 * @param enemyList List of all enemy in the map
	 * @return Mortar tower upgradable
	 */
	static public Tower basicMortar(final Position pos, final List<Enemy> enemyList) {
		final TowerBuilder t = new TowerBuilder(pos, DefaultTowersUtils.JSONOBJECTMAP.get(DefaultTowers.BASICMORTAR), enemyList);
		return t.attackType(AttackType.AREA)
				.damageRange(400)
				.setUpgradable(true)
				.upgradeCost(x -> x+10)
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
	 * This Mortar has a growth of the damage quadratic
	 * 
	 * @param pos       Position of the Mortar
	 * @param enemyList List of all enemy in the map
	 * @return Mortar tower upgradable
	 */
	static public Tower superMortar(final Position pos, final List<Enemy> enemyList) {
		final TowerBuilder t =  new TowerBuilder(pos, DefaultTowersUtils.JSONOBJECTMAP.get(DefaultTowers.SUPERMORTAR), enemyList);
		return t.attackType(AttackType.AREA)
				.damageRange(450)
				.setUpgradable(true)
				.upgradeCost(x -> x + 10)
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
	 * This Mortar has a growth of the damage cubic
	 * 
	 * @param pos       Position of the Mortar
	 * @param enemyList List of all enemy in the map
	 * @return Mortar tower upgradable
	 */
	static public Tower godMortar(final Position pos, final List<Enemy> enemyList) {
		final TowerBuilder t = new TowerBuilder(pos, DefaultTowersUtils.JSONOBJECTMAP.get(DefaultTowers.GODMORTAR), enemyList);
		return t.attackType(AttackType.AREA)
				.damageRange(600)
				.setUpgradable(true)
				.upgradeCost(x -> x*x/10)
				.upgradeDamage(x -> x*x*x)
				.maxLevel(20)
				.startUpgradeCost(1000)
				.maximumTarget(Integer.MAX_VALUE).findFirst(() -> {
					return Towers.findFistEnemyBiPredicate(
							e -> Towers.isInRange(pos, e.getPosition(), t.getRange()),
							e ->!Towers.isInRange(pos, e.getPosition(), 140),
							enemyList);
				}).build();
	}
}
