package main.java.tas.model.tower.factory;

import java.util.List;
import main.java.tas.model.enemy.Enemy;
import main.java.tas.model.tower.AttackType;
import main.java.tas.model.tower.Tower;
import main.java.tas.model.tower.TowerBuilder;
import main.java.tas.utils.Position;

/**
 * An interface that is specified to build cannon tower
 */
public interface CannonFactory {

	/**
	 * Return an cannon with only one target possible, and upgradable
	 * 
	 * @param pos       Position of the tower
	 * @param enemyList List of all enemy in the map
	 * @return an cannon with only one target possible, and upgradable
	 */
	static public Tower basicCannon(final Position pos, final List<Enemy> enemyList) {
		return new TowerBuilder(pos, DefaultTowersUtils.JSONOBJECTMAP.get(DefaultTowers.BASICCANNON), enemyList)
				.setUpgradable(true)
				.maxLevel(25)
				.startUpgradeCost(1000)
				.upgradeCost(x->x+5)
				.upgradeDamage(x->10)
				.build();
	}

	/**
	 * Return an cannon with two targets possible, and upgradable
	 * 
	 * @param pos       Position of the tower
	 * @param enemyList List of all enemy in the map
	 * @return an cannon with two targets possible, and upgradable
	 */
	static public Tower biCannon(final Position pos, final List<Enemy> enemyList) {
		return new TowerBuilder(pos, DefaultTowersUtils.JSONOBJECTMAP.get(DefaultTowers.BICANNON), enemyList)
				.attackType(AttackType.MULTIPLE)
				.maximumTarget(2)
				.maxLevel(25)
				.startUpgradeCost(1000)
				.upgradeCost(x->x+15)
				.upgradeDamage(x->20)
				.build();
	}

	/**
	 * Return an cannon with three targets possible, and upgradable
	 * 
	 * @param pos       Position of the tower
	 * @param enemyList List of all enemy in the map
	 * @return an cannon with three targets possible, and upgradable
	 */
	static public Tower triCannon(final Position pos, final List<Enemy> enemyList) {
		return new TowerBuilder(pos, DefaultTowersUtils.JSONOBJECTMAP.get(DefaultTowers.TRICANNON), enemyList)
				.attackType(AttackType.MULTIPLE)
				.maximumTarget(3)
				.maxLevel(25)
				.startUpgradeCost(1000)
				.upgradeCost(x->x+35)
				.upgradeDamage(x->30)
				.build();
	}

	/**
	 * Return an cannon with four targets possible, and upgradable
	 * 
	 * @param pos       Position of the tower
	 * @param enemyList List of all enemy in the map
	 * @return an cannon with four targets possible, and upgradable
	 */
	static public Tower quadCannon(final Position pos, final List<Enemy> enemyList) {
		return new TowerBuilder(pos, DefaultTowersUtils.JSONOBJECTMAP.get(DefaultTowers.QUADCANNON), enemyList)
				.attackType(AttackType.MULTIPLE)
				.maximumTarget(4)
				.maxLevel(25)
				.startUpgradeCost(1000)
				.upgradeCost(x->x+45)
				.upgradeDamage(x->40)
				.build();
	}

}
