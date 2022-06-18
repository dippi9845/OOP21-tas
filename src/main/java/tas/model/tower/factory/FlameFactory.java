package main.java.tas.model.tower.factory;

import java.util.List;
import main.java.tas.model.enemy.Enemy;
import main.java.tas.model.tower.AttackType;
import main.java.tas.model.tower.Tower;
import main.java.tas.model.tower.TowerBuilder;
import main.java.tas.utils.Position;

/**
 * An interface that is specified to build Flame tower.
 * This tower has a low delay for
 * damage
 */
public interface FlameFactory {

	/**
	 * This is a basic flame tower
	 * 
	 * @param pos       Position of the tower
	 * @param enemyList List of all enemy in the map
	 * @return an Flame with only one target possible, and upgradable
	 */
	static public Tower basicFlame(final Position pos, final List<Enemy> enemyList) {
		return new TowerBuilder(pos, DefaultTowersInfo.TOWERSJSONOBJECT.get(DefaultTowers.BASICFLAME), enemyList)
				.setUpgradable(true)
				.maxLevel(25)
				.startUpgradeCost(1000)
				.upgradeCost(x->15)
				.upgradeDamage(x->15)
				.build();
	}

	/**
	 * This flame tower can attack two enemy at the same time
	 * @param pos       Position of the tower
	 * @param enemyList List of all enemy in the map
	 * @return an Flame with two targets possible, and upgradable
	 */
	static public Tower biFlame(final Position pos, final List<Enemy> enemyList) {
		return new TowerBuilder(pos, DefaultTowersInfo.TOWERSJSONOBJECT.get(DefaultTowers.BIFLAME), enemyList)
				.attackType(AttackType.MULTIPLE)
				.maximumTarget(2)
				.setUpgradable(true)
				.maxLevel(25)
				.startUpgradeCost(1000)
				.upgradeCost(x->25)
				.upgradeDamage(x->25)
				.build();
	}

	/**
	 * This flame tower can attack three enemy at the same time
	 * 
	 * @param pos       Position of the tower
	 * @param enemyList List of all enemy in the map
	 * @return an Flame with three targets possible, and upgradable
	 */
	static public Tower triFlame(final Position pos, final List<Enemy> enemyList) {
		return new TowerBuilder(pos, DefaultTowersInfo.TOWERSJSONOBJECT.get(DefaultTowers.TRIFLAME), enemyList)
				.attackType(AttackType.MULTIPLE)
				.maximumTarget(3)
				.setUpgradable(true)
				.maxLevel(25)
				.startUpgradeCost(1000)
				.upgradeCost(x->35)
				.upgradeDamage(x->35)
				.build();
	}

	/**
	 * This flame tower can attack four enemy at the same time
	 * 
	 * @param pos       Position of the tower
	 * @param enemyList List of all enemy in the map
	 * @return an Flame with four targets possible, and upgradable
	 */
	static public Tower quadFlame(final Position pos, final List<Enemy> enemyList) {
		return new TowerBuilder(pos, DefaultTowersInfo.TOWERSJSONOBJECT.get(DefaultTowers.QUADFLAME), enemyList)
				.attackType(AttackType.MULTIPLE)
				.maximumTarget(4)
				.setUpgradable(true)
				.maxLevel(25)
				.startUpgradeCost(1000)
				.upgradeCost(x->45)
				.upgradeDamage(x->45)
				.build();
	}
}
