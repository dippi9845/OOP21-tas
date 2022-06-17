package main.java.tas.model.tower.factory;

import java.util.List;
import java.util.function.UnaryOperator;
import main.java.tas.model.enemy.Enemy;
import main.java.tas.model.tower.AttackType;
import main.java.tas.model.tower.TowerBuilder;
import main.java.tas.utils.Position;

/**
 * This class is used to avoid repetition by the interfaces Factory, like
 * ArcherFactory, CannonFactory, FlameFactory, GasFactory
 */
class ClassicTowerFactory {

	private final DefaultTowers tower;
 	private final List<Enemy> enemyList;

	/**
	 * Constructor
	 * 
	 * @param damage      damage of the Tower
	 * @param range       range of the Tower
	 * @param delay       delay of the Tower
	 * @param buildCost   build cost of the Tower
	 * @param startUpCost start upgrade cost of the Tower
	 * @param maxLevel    maximum level of the Tower
	 * @param imageName   image name of the Tower
	 * @param enemyList   List of all enemy in the map
	 */
	protected ClassicTowerFactory(final DefaultTowers tower, final List<Enemy> enemyList) {
		this.tower = tower;
		this.enemyList = enemyList;
	}

	/**
	 * @param pos position of the tower
	 * @return builder with all preset already set
	 */
	public TowerBuilder basicStarndard(final Position pos) {
		return new TowerBuilder(pos, 
				DefaultTowersInfo.JSONDATAFILE.getJSONObject(DefaultTowersInfo.TOWERSJSONNAME.get(this.tower)),
				this.enemyList);
	}

	/**
	 * @param pos      Position of the tower
	 * @param upCost   the UnaryOperator that associate for a level an increase of
	 *                 cost
	 * @param upDamage the UnaryOperator that associate for a level an increase of
	 *                 damage
	 * @return builder with all preset already set
	 */
	public TowerBuilder upgradableStarndard(final Position pos, final int maxLevel, final int startUpCost, final UnaryOperator<Integer> upCost,
			final UnaryOperator<Integer> upDamage) {
		return this.basicStarndard(pos)
				.setUpgradable(true)
				.maxLevel(maxLevel)
				.upgradeCost(upCost)
				.upgradeDamage(upDamage)
				.startUpgradeCost(startUpCost);
	}

	/**
	 * 
	 * @param pos      Position of the tower
	 * @param upCost   upCost the UnaryOperator that associate for a level an
	 *                 increase of cost
	 * @param upDamage the UnaryOperator that associate for a level an increase of
	 *                 damage
	 * @param n        number of maximum target for the tower
	 * @return builder with all preset already set
	 */
	public TowerBuilder nTargetStandard(final Position pos, final int maxLevel, final int startUpCost, final UnaryOperator<Integer> upCost,
			final UnaryOperator<Integer> upDamage, final int n) {
		return this.upgradableStarndard(pos, maxLevel, startUpCost, upCost, upDamage)
				.attackType(AttackType.MULTIPLE)
				.maximumTarget(n);
	}
}
