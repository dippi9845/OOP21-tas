package main.java.tas.model.tower.factory;

import java.util.List;
import main.java.tas.model.enemy.Enemy;
import main.java.tas.model.tower.Tower;
import main.java.tas.utils.Position;

/**
 * An interface that is specified to build archer tower
 */
public interface ArcherFactory {

	/**
	 * Return an archer with only one target possible, and upgradable
	 * 
	 * @param pos       Position of the tower
	 * @param enemyList List of all enemy in the map
	 * @return an archer with only one target possible, and upgradable
	 */
	static public Tower basicArcher(final Position pos, final List<Enemy> enemyList) {
		return new ClassicTowerFactory(DefaultTowers.BASICARCHER, enemyList)
				.upgradableStarndard(pos, 25, 1000, x->20, x->15)
				.build();
	}

	/**
	 * Return an archer with two targets possible, and upgradable
	 * 
	 * @param pos       Position of the tower
	 * @param enemyList List of all enemy in the map
	 * @return an archer with two targets possible, and upgradable
	 */
	static public Tower biArcher(final Position pos, final List<Enemy> enemyList) {
		return new ClassicTowerFactory(DefaultTowers.BIARCHER, enemyList)
				.nTargetStandard(pos, 25, 1000, x->25, x->20, 2)
				.build();
	}

	/**
	 * Return an archer with three targets possible, and upgradable
	 * 
	 * @param pos       Position of the tower
	 * @param enemyList List of all enemy in the map
	 * @return an archer with three targets possible, and upgradable
	 */
	static public Tower triArcher(final Position pos, final List<Enemy> enemyList) {
		return new ClassicTowerFactory(DefaultTowers.TRIARCHER, enemyList)
				.nTargetStandard(pos, 25, 1000, x->30, x->25, 3)
				.build();
	}

	/**
	 * Return an archer with four targets possible, and upgradable
	 * 
	 * @param pos       Position of the tower
	 * @param enemyList List of all enemy in the map
	 * @return an archer with four targets possible, and upgradable
	 */
	static public Tower quadArcher(final Position pos, final List<Enemy> enemyList) {
		return new ClassicTowerFactory(DefaultTowers.QUADARCHER, enemyList)
				.nTargetStandard(pos, 25, 1000, x->35, x->30, 4)
				.build();
	}
}
