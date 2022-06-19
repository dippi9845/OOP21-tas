package main.java.tas.model.tower.factory;

import java.util.List;
import main.java.tas.model.enemy.Enemy;
import main.java.tas.model.tower.AttackType;
import main.java.tas.model.tower.Tower;
import main.java.tas.model.tower.TowerBuilder;
import main.java.tas.utils.Position;

/**
 * An interface that is specified to build gas tower it basically attack every
 * enemy in the range at low delay
 */
public interface GasFactory {

	/**
	 * This is a basic gas tower
	 * @param pos       Position of the tower
	 * @param enemyList List of all enemy in the map
	 * @return an Gas Tower upgradable
	 */
	static public Tower gasTower(final Position pos, final List<Enemy> enemyList) {
		return new TowerBuilder(pos, DefaultTowersUtils.JSONOBJECTMAP.get(DefaultTowers.GASTOWER), enemyList)
				.attackType(AttackType.MULTIPLE)
				.maximumTarget(Integer.MAX_VALUE)
				.setUpgradable(true)
				.maxLevel(25)
				.startUpgradeCost(1000)
				.upgradeCost(x->15)
				.upgradeDamage(x->15)
				.build();
	}
}
