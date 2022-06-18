package main.java.tas.model.tower.factory;

import java.util.List;
import main.java.tas.model.enemy.Enemy;
import main.java.tas.model.tower.Tower;
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
		return new ClassicTowerFactory(DefaultTowers.BASICCANNON, enemyList)
				.upgradableStarndard(pos, 25, 1000, x->5, x->10)
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
		return new ClassicTowerFactory(DefaultTowers.BICANNON, enemyList)
				.nTargetStandard(pos, 25, 1000, x->15, x->20, 2)
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
		return new ClassicTowerFactory(DefaultTowers.TRICANNON, enemyList)
				.nTargetStandard(pos, 25, 1000, x->35, x->30, 3)
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
		return new ClassicTowerFactory(DefaultTowers.QUADCANNON, enemyList)
				.nTargetStandard(pos, 25, 1000, x->45, x->40, 4)
				.build();
	}

}
