package main.java.tas.model.tower.factory;

import java.util.List;
import main.java.tas.model.enemy.Enemy;
import main.java.tas.model.tower.Tower;
import main.java.tas.utils.Position;

/**
 * An interface that is specified to build Flame tower it has a low delay for
 * damage
 */
public interface FlameFactory {

	/**
	 * @param pos       Position of the tower
	 * @param enemyList List of all enemy in the map
	 * @return an Flame with only one target possible, and upgradable
	 */
	static public Tower basicFlame(final Position pos, final List<Enemy> enemyList) {
		return new ClassicTowerFactory(DefaultTowers.BASICFLAME, enemyList)
				.upgradableStarndard(pos, 25, 400, x->15, x->15)
				.build();
	}

	/**
	 * @param pos       Position of the tower
	 * @param enemyList List of all enemy in the map
	 * @return an Flame with two targets possible, and upgradable
	 */
	static public Tower biFlame(final Position pos, final List<Enemy> enemyList) {
		return new ClassicTowerFactory(DefaultTowers.BIFLAME, enemyList)
				.nTargetStandard(pos, 25, 600, x->25, x->25, 2)
				.build();
	}

	/**
	 * @param pos       Position of the tower
	 * @param enemyList List of all enemy in the map
	 * @return an Flame with three targets possible, and upgradable
	 */
	static public Tower triFlame(final Position pos, final List<Enemy> enemyList) {
		return new ClassicTowerFactory(DefaultTowers.TRIFLAME, enemyList)
				.nTargetStandard(pos, 25, 800, x->35, x->35, 3)
				.build();
	}

	/**
	 * @param pos       Position of the tower
	 * @param enemyList List of all enemy in the map
	 * @return an Flame with four targets possible, and upgradable
	 */
	static public Tower quadFlame(final Position pos, final List<Enemy> enemyList) {
		return new ClassicTowerFactory(DefaultTowers.QUADFLAME, enemyList)
				.nTargetStandard(pos, 25, 1000, x->45, x->45, 4)
				.build();
	}
}
