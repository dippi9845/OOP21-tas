package main.java.tas.tower;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;

public class Builder {
	
	enum Type {
		BASIC,
		MULTIPLE,
		AREA
	}
	
	private Type attackType = Type.BASIC;
	private final Position pos;
	private final int damage;
	private final int radius;
	private int delay;
	private int cost;
	private boolean upgradable = false;
	
	private Optional<Integer> maximumTarget = Optional.empty();
	
	private Optional<Integer> attackRange = Optional.empty();
	private Optional<Supplier<Optional<Enemy>>> findFirst = Optional.empty();
	
	private Optional<UnaryOperator<Integer>> upgradeDamage = Optional.empty();
	private Optional<UnaryOperator<Integer>> upgradeCost = Optional.empty();
	private Optional<Integer> startUpgradeCost = Optional.empty();
	private Optional<Integer> maxLevel = Optional.empty();
	
	public Builder(final Position pos, final int damage, final int radius) {
		this.pos = pos;
		this.damage = damage;
		this.radius = radius;
	}
	
	public Builder delay(final int delay) {
		this.delay = delay;
		return this;
	}
	
	public Builder attackType(final Type type) {
		this.attackType = type;
		return this;
	}
	
	public Builder cost(final int cost) {
		this.cost = cost;
		return this;
	}
	
	public Builder maximumTarget(final int max) {
		this.maximumTarget = Optional.ofNullable(max);
		return this;
	}
	
	public Builder attackRange(final int range) {
		this.attackRange = Optional.ofNullable(range);
		return this;
	}
	
	public Builder findFirst(final Supplier<Optional<Enemy>> findFirstEnemy) {
		this.findFirst = Optional.ofNullable(findFirstEnemy);
		return this;
	}
	
	public Builder upgradeDamage(final UnaryOperator<Integer> upgradeDamage) {
		this.upgradeDamage = Optional.ofNullable(upgradeDamage);
		return this;
	}
	
	public Builder upgradeCost(final UnaryOperator<Integer> upgradeCost) {
		this.upgradeCost = Optional.ofNullable(upgradeCost);
		return this;
	}
	
	public Builder startUpgradeCost(final int startUpgradeCost) {
		this.startUpgradeCost = Optional.ofNullable(startUpgradeCost);
		return this;
	}
	
	public Builder maxLevel(final int maxLevel) {
		this.maxLevel = Optional.ofNullable(maxLevel);
		return this;
	}
	
	public Tower build() throws IllegalStateException {
		final AbstractBasicTower t;
		switch (this.attackType) {
		case BASIC:
				if (this.attackRange.isPresent() || this.maximumTarget.isPresent() || this.findFirst.isPresent()) {
					throw new IllegalStateException("attack range or maximum target or find first cannot be defined in a basic tower");
				}
				
				t = new BasicTower(this.pos, this.damage, this.radius, this.delay, this.cost);
				break;
				
		case MULTIPLE:
				if (this.maximumTarget.isEmpty() || this.attackRange.isPresent()  || this.findFirst.isPresent()) {
					throw new IllegalStateException("maximum target not defined or attack range or find first cannot be definde in a multiple tower");
				}
				t = new BasicMultipleTower(this.pos, this.damage, this.radius, this.delay, this.cost, this.maximumTarget.get());
				break;
				
		case AREA:
				if (this.maximumTarget.isEmpty() || this.attackRange.isEmpty() || this.findFirst.isEmpty()) {
					throw new IllegalStateException("maximum target or attack range or findfisrt is not defined");
				}
				t = new AbstractAreaTower(this.pos, this.damage, this.radius, this.delay, this.cost, this.maximumTarget.get(), this.attackRange.get()) {

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
			if (this.upgradeCost.isEmpty() || this.upgradeDamage.isEmpty() || this.startUpgradeCost.isEmpty() || this.maxLevel.isEmpty()) {
				throw new IllegalStateException("an upgradable tower cannot be instanced if upgrade cost or upgrade damage function is missing or start upgrade cost or max level is empty ");
			}
			return new UpgradableTowerImpl(t, this.upgradeDamage.get(), this.upgradeCost.get(), this.startUpgradeCost.get(), this.maxLevel.get());
		}
		else {
			return t;
		}
	}
}
