package main.java.tas.model.tower;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import org.json.JSONObject;
import main.java.tas.model.enemy.Enemy;
import main.java.tas.model.tower.factory.DefaultTowersInfo;
import main.java.tas.utils.Position;

/**
 * A class specialized to Build , to start, you have to call the constructor,
 * next you have to call the method to set more parameters, in cascade notation
 */
public class TowerBuilder {

	private AttackType attackType;
	private final Position pos;
	private final int damage;
	private final int radius;
	private final int delay;
	private final String imageName;
	private int cost;
	private boolean upgradable;
	private Optional<List<Enemy>> visibleEnemy;

	private Optional<Integer> maximumTarget;

	private Optional<Integer> attackRange;
	private Optional<Supplier<Optional<Enemy>>> findFirst;

	private Optional<UnaryOperator<Integer>> upgradeDamage;
	private Optional<UnaryOperator<Integer>> upgradeCost;
	private Optional<Integer> startUpgradeCost;
	private Optional<Integer> maxLevel;
	
	/**
	 * The constructor has the basic fields, that without one of them the tower
	 * can't be instanced (except for enemy list, that must be added with the
	 * specific method)
	 * 
	 * @param pos       Position of the Tower
	 * @param damage    Damage of the Tower
	 * @param radius    Radius of the Tower
	 * @param delay     Delay of the Tower
	 * @param imageName Name of the image of the tower
	 * @param enemyList List of all enemy in the map
	 */
	public TowerBuilder(final Position pos, final int damage, final int radius, final int delay, final String imageName,
			final List<Enemy> enemyList) {
		this.pos = pos;
		this.damage = damage;
		this.radius = radius;
		this.delay = delay;
		this.imageName = imageName;
		this.visibleEnemy = Optional.ofNullable(enemyList);

		this.attackType = AttackType.BASIC;
		this.upgradable = false;

		this.maximumTarget = Optional.empty();
		this.attackRange = Optional.empty();
		this.findFirst = Optional.empty();
		this.upgradeDamage = Optional.empty();
		this.upgradeCost = Optional.empty();
		this.startUpgradeCost = Optional.empty();
		this.maxLevel = Optional.empty();
	}
	
	/**
	 * The constructor has the basic fields, without the list of all visible enemy
	 * 
	 * @param pos       Position of the Tower
	 * @param damage    Damage of the Tower
	 * @param radius    Radius of the Tower
	 * @param delay     Delay of the Tower
	 * @param imageName Name of the image of the tower
	 */
	public TowerBuilder(final Position pos, final int damage, final int radius, final int delay, final String imageName) {
		this.pos = pos;
		this.damage = damage;
		this.radius = radius;
		this.delay = delay;
		this.imageName = imageName;
		this.visibleEnemy = Optional.empty();

		this.attackType = AttackType.BASIC;
		this.upgradable = false;

		this.maximumTarget = Optional.empty();
		this.attackRange = Optional.empty();
		this.findFirst = Optional.empty();
		this.upgradeDamage = Optional.empty();
		this.upgradeCost = Optional.empty();
		this.startUpgradeCost = Optional.empty();
		this.maxLevel = Optional.empty();
	}
	
	public TowerBuilder(final Position pos, final JSONObject dataset) {
		this(pos, dataset.getInt(DefaultTowersInfo.DAMAGEFIELD), dataset.getInt(DefaultTowersInfo.RADIUSFIELD), dataset.getInt(DefaultTowersInfo.DELAYFIELD), dataset.getString(DefaultTowersInfo.IMAGENAMEFIELD));
		
		if (dataset.has(DefaultTowersInfo.COSTFIELD)) {
			this.cost = dataset.getInt(DefaultTowersInfo.COSTFIELD);
		}
	}
	
	public TowerBuilder(final Position pos, final JSONObject dataset, final List<Enemy> enemyList) {
		this(pos, dataset);
		this.visibleEnemy = Optional.ofNullable(enemyList);
	}

	public int getRange() {
		return this.radius;
	}
	
	/**
	 * Set the list of all enemy in the map
	 * 
	 * @param enemyList List of all enemy in the map
	 * @return this object
	 */
	public TowerBuilder setEnemylist(final List<Enemy> enemyList) {
		this.visibleEnemy = Optional.ofNullable(enemyList);
		return this;
	}

	/**
	 * Set The attack type of the tower
	 * 
	 * @param attackType the attack type
	 * @return this object
	 */
	public TowerBuilder attackType(final AttackType attackType) {
		this.attackType = attackType;
		return this;
	}

	/**
	 * Give the possibility to be upgradable
	 * 
	 * @param upgradable boolean, True if the tower must be upgradable, false
	 *                   otherwise
	 * @return this object
	 */
	public TowerBuilder setUpgradable(final boolean upgradable) {
		this.upgradable = upgradable;
		return this;
	}

	/**
	 * Set the cost of the Tower
	 * 
	 * @param cost integer that must be greater than 0
	 * @return this object
	 */
	public TowerBuilder cost(final int cost) {
		this.cost = cost;
		return this;
	}

	/**
	 * Set the maximum number of enemies that the tower can target, the attack type
	 * must be Multiple or Area
	 * 
	 * @param max integer that must be greater than 0
	 * @return this object
	 */
	public TowerBuilder maximumTarget(final int max) {
		this.maximumTarget = Optional.ofNullable(max);
		return this;
	}

	/**
	 * Set the damageRange of area attack the attack type must be area
	 * 
	 * @param range integer that must be greater than 0
	 * @return this object
	 */
	public TowerBuilder damageRange(final int range) {
		this.attackRange = Optional.ofNullable(range);
		return this;
	}

	/**
	 * set the finstFirst function for the area tower attack type must be area
	 * 
	 * @param findFirstEnemy supplier that provide the enemies
	 * @return this object
	 */
	public TowerBuilder findFirst(final Supplier<Optional<Enemy>> findFirstEnemy) {
		this.findFirst = Optional.ofNullable(findFirstEnemy);
		return this;
	}

	/**
	 * set the function that provide an increase of damage by giving the level, the
	 * tower must be upgradable
	 * 
	 * @param upgradeDamage UnaryOperator that associate an increase of damage to a
	 *                      level
	 * @return this object
	 */
	public TowerBuilder upgradeDamage(final UnaryOperator<Integer> upgradeDamage) {
		this.upgradeDamage = Optional.ofNullable(upgradeDamage);
		return this;
	}

	/**
	 * set the function that provide an increase of cost by giving the level, the
	 * tower must be upgradable
	 * 
	 * @param upgradeCost UnaryOperator that associate an increase of cost to a
	 *                    level
	 * @return this object
	 */
	public TowerBuilder upgradeCost(final UnaryOperator<Integer> upgradeCost) {
		this.upgradeCost = Optional.ofNullable(upgradeCost);
		return this;
	}

	/**
	 * set the staring upgrade cost, the tower must be upgradable
	 * 
	 * @param startUpgradeCost integer that must be grater than 0
	 * @return this object
	 */
	public TowerBuilder startUpgradeCost(final int startUpgradeCost) {
		this.startUpgradeCost = Optional.ofNullable(startUpgradeCost);
		return this;
	}

	/**
	 * set the maximum level of upgrade, the tower must be upgradable
	 * 
	 * @param maxLevel integer that must be grater than 0
	 * @return this object
	 */
	public TowerBuilder maxLevel(final int maxLevel) {
		this.maxLevel = Optional.ofNullable(maxLevel);
		return this;
	}

	/**
	 * Build the tower requested, by the given parameters, even performing a check
	 * of the parameters
	 * 
	 * @return the actual tower, by the given parameters
	 * @throws IllegalStateException    if there is a bad configuration of the
	 *                                  parameters
	 * @throws IllegalArgumentException if an argument passed is not valid (integer
	 *                                  less than 0, or null)
	 */
	public Tower build() throws IllegalStateException, IllegalArgumentException {

		if (this.pos == null) {
			throw new IllegalArgumentException("position can't be null");
		}

		if (this.damage <= 0) {
			throw new IllegalArgumentException("damage can't be less equal to zero");
		}

		if (this.radius <= 0) {
			throw new IllegalArgumentException("radius can't be less equal to zero");
		}

		if (this.delay <= 0) {
			throw new IllegalArgumentException("delay can't be less equal to zero");
		}

		if (this.cost < 0) {
			throw new IllegalArgumentException("cost can't be less than zero");
		}

		if (this.maximumTarget.isPresent() && this.maximumTarget.get() <= 0) {
			throw new IllegalArgumentException("maximumTarget can't be less equal to zero");
		}

		if (this.attackRange.isPresent() && this.attackRange.get() <= 0) {
			throw new IllegalArgumentException("attackRange can't be less equal to zero");
		}

		if (this.startUpgradeCost.isPresent() && this.startUpgradeCost.get() <= 0) {
			throw new IllegalArgumentException("startUpgradeCost can't be less equal to zero");
		}

		if (this.maxLevel.isPresent() && this.maxLevel.get() <= 0) {
			throw new IllegalArgumentException("maxLevel can't be less equal to zero");
		}

		if (this.imageName.isBlank()) {
			throw new IllegalArgumentException("imageName is blanck");
		}

		if (this.visibleEnemy.isEmpty()) {
			throw new IllegalStateException("Visible enemy list not defined, or defined null");
		}

		final AbstractBasicTower t;
		switch (this.attackType) {
		case BASIC:
			if (this.attackRange.isPresent() || this.maximumTarget.isPresent() || this.findFirst.isPresent()) {
				throw new IllegalStateException(
						"attack range or maximum target or find first cannot be defined in a basic tower");
			}

			t = new BasicTower(this.pos, this.damage, this.radius, this.delay, this.cost, this.imageName,
					this.visibleEnemy.get());
			break;

		case MULTIPLE:
			if (this.maximumTarget.isEmpty() || this.attackRange.isPresent() || this.findFirst.isPresent()) {
				throw new IllegalStateException(
						"maximum target not defined or attack range or find first cannot be definde in a multiple tower");
			}
			t = new BasicMultipleTower(this.pos, this.damage, this.radius, this.delay, this.cost, this.imageName,
					this.visibleEnemy.get(), this.maximumTarget.get());
			break;

		case AREA:
			if (this.maximumTarget.isEmpty() || this.attackRange.isEmpty() || this.findFirst.isEmpty()) {
				throw new IllegalStateException("maximum target or attack range or findfisrt is not defined");
			}
			t = new AbstractAreaTower(this.pos, this.damage, this.radius, this.delay, this.cost, this.imageName,
					this.visibleEnemy.get(), this.maximumTarget.get(), this.attackRange.get()) {

				@Override
				protected Optional<Enemy> firstTarget() {
					return findFirst.get().get();
				}

			};
			break;
		default:
			throw new IllegalStateException("type of tower is not valid");
		}

		if (this.upgradable) {
			if (this.upgradeCost.isEmpty() || this.upgradeDamage.isEmpty() || this.startUpgradeCost.isEmpty()
					|| this.maxLevel.isEmpty()) {
				throw new IllegalStateException(
						"an upgradable tower cannot be instanced if upgrade cost or upgrade damage function is missing or start upgrade cost or max level is empty ");
			}
			return new UpgradableTowerImpl(t, this.upgradeDamage.get(), this.upgradeCost.get(),
					this.startUpgradeCost.get(), this.maxLevel.get());
		} else {
			return t;
		}
	}
}
