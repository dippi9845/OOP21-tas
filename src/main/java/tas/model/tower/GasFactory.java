package main.java.tas.model.tower;

import java.util.function.UnaryOperator;
import main.java.tas.utils.Position;

interface GasFactory {
	static public int getDamage() {
		return 7;
	}
	
	static public int getRange() {
		return 6;
	}
	
	static public int getDelay() {
		return 100;
	}
	
	static public int getBuildCost() {
		return 275;
	}
	
	static public int getStartUpCost() {
		return 95;
	}
	
	static public int getMaxLevel() {
		return 10;
	}
	
	static public UnaryOperator<Integer> getIncreaseCost() {
		return (x->5);
	}
	
	static public UnaryOperator<Integer> getIncreaseDamage() {
		return (x->5);
	}
	
	static private ClassicTowerFactory constructor(final String imageName) {
		return new ClassicTowerFactory(GasFactory.getDamage(),
									   GasFactory.getRange(),
									   GasFactory.getDelay(),
									   GasFactory.getBuildCost(),
									   GasFactory.getStartUpCost(),
									   GasFactory.getMaxLevel(),
									   imageName);
	}
	
	static public Tower gasTower(final Position pos) {
		return constructor("gas")
				   .nTargetStandard(pos, GasFactory.getIncreaseCost(), GasFactory.getIncreaseDamage(), Integer.MAX_VALUE)
				   .build();
	}
}
