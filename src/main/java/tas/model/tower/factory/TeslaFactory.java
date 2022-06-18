package main.java.tas.model.tower.factory;

import java.util.List;
import main.java.tas.model.enemy.Enemy;
import main.java.tas.model.tower.AttackType;
import main.java.tas.model.tower.TowerBuilder;
import main.java.tas.model.tower.Tower;
import main.java.tas.model.tower.Towers;
import main.java.tas.utils.Position;

/**
 * Interface that build a Tesla tower, is an area tower, and the first target is
 * the nearest
 */
public interface TeslaFactory {

	/**
	 * this tesla has a growth of the damage and cost linear
	 * 
	 * @param pos       Position of the Tesla
	 * @param enemyList List of all enemy in the map
	 * @return Tesla tower
	 */
	static public Tower basicTesla(final Position pos, final List<Enemy> enemyList) {
		final TowerBuilder t = new TowerBuilder(pos, DefaultTowersInfo.TOWERSJSONOBJECT.get(DefaultTowers.BASICTESLA), enemyList);
		return t.attackType(AttackType.AREA)
				.damageRange(t.getRange())
				.setUpgradable(true)
				.upgradeCost(x -> 15)
				.upgradeDamage(x -> 50)
				.maxLevel(15)
				.startUpgradeCost(1000)
				.maximumTarget(4)
				.findFirst(() -> {
					return Towers.findFirstEnemyInRange(pos, t.getRange(), enemyList);
				}).build();
		
	}

	/**
	 * this tesla has a growth of the damage and cost quadratic
	 * 
	 * @param pos       Position of the Tesla
	 * @param enemyList List of all enemy in the map
	 * @return Super Tesla tower
	 */
	static public Tower superTesla(final Position pos, final List<Enemy> enemyList) {
		final TowerBuilder t = new TowerBuilder(pos, DefaultTowersInfo.TOWERSJSONOBJECT.get(DefaultTowers.SUPERTESLA), enemyList);
		return t.attackType(AttackType.AREA)
				.damageRange(t.getRange())
				.setUpgradable(true)
				.upgradeCost(x -> x * x)
				.upgradeDamage(x -> x * x * 3)
				.maxLevel(1000)
				.startUpgradeCost(20)
				.maximumTarget(8)
				.findFirst(() -> {
					return Towers.findFirstEnemyInRange(pos, t.getRange(), enemyList);
				}).build();
	}

	/**
	 * this tesla has a growth of the damage and cost exponential
	 * 
	 * @param pos       Position of the Tesla
	 * @param enemyList List of all enemy in the map
	 * @return God Tesla tower
	 */
	static public Tower godTesla(final Position pos, final List<Enemy> enemyList) {
		final TowerBuilder t = new TowerBuilder(pos, DefaultTowersInfo.TOWERSJSONOBJECT.get(DefaultTowers.GODTESLA), enemyList);
		return t.attackType(AttackType.AREA)
				.damageRange(t.getRange())
				.setUpgradable(true)
				.upgradeCost(x -> (int) Math.pow(x, x) + 20)
				.upgradeDamage(x -> (int) Math.pow(x, x))
				.maxLevel(5)
				.startUpgradeCost(1000)
				.maximumTarget(100)
				.findFirst(() -> {
					return Towers.findFirstEnemyInRange(pos, t.getRange(), enemyList);
				}).build();
	}
}
