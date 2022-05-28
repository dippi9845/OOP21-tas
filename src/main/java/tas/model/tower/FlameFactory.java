package main.java.tas.model.tower;

import java.util.function.UnaryOperator;

import main.java.tas.utils.Position;

public interface FlameFactory {
	
	static public int getDamage() {
		return 10;
	}
	
	static public int getRange() {
		return 6;
	}
	
	static public int getDelay() {
		return 100;
	}
	
	static public int getBuildCost() {
		return 250;
	}
	
	static public int getStartUpCost() {
		return 75;
	}
	
	static public int getMaxLevel() {
		return 10;
	}
	
	static private UnaryOperator<Integer> getIncreaseCost() {
		return (x->5);
	}
	
	static private UnaryOperator<Integer> getIncreaseDamage() {
		return (x->5);
	}
	
	static private ClassicTowerFactory constructor(final String imageName) {
		return new ClassicTowerFactory(getDamage(), getRange(), getDelay(), getBuildCost(), getStartUpCost(), getMaxLevel(), imageName);
	}
	
	static public Tower basicFlame(final Position pos) {
		return constructor("flame")
				   .upgradableStarndard(pos, getIncreaseCost(), getIncreaseDamage())
				   .build();
	}
	
	static public Tower biFlame(final Position pos) {
		return constructor("biflame")
			   .nTargetStandard(pos, getIncreaseCost(), getIncreaseDamage(), 2)
			   .build();
	}
	
	static public Tower triFlame(final Position pos) {
		return constructor("triflame")
				   .nTargetStandard(pos, getIncreaseCost(), getIncreaseDamage(), 3)
				   .build();
	}
	
	static public Tower quadFlame(final Position pos) {
		return constructor("quadflame")
				   .nTargetStandard(pos, getIncreaseCost(), getIncreaseDamage(), 4)
				   .build();
	}
}
