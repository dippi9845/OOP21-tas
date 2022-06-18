package main.java.tas.model.tower.factory;

import java.util.List;
import main.java.tas.model.enemy.Enemy;
import main.java.tas.model.tower.Tower;
import main.java.tas.utils.Position;

/**
 * An interface that is specified to build gas tower it basically attack every
 * enemy in the range at low delay
 */
public interface GasFactory {

	/**
	 * @param pos       Position of the tower
	 * @param enemyList List of all enemy in the map
	 * @return an Gas Tower upgradable
	 */
	static public Tower gasTower(final Position pos, final List<Enemy> enemyList) {
		return new ClassicTowerFactory(DefaultTowers.GASTOWER, enemyList)
				.nTargetStandard(pos, 25, 1000, x->15, x->15, Integer.MAX_VALUE)
				.build();
	}
}
