package main.java.tas.model.tower;

import java.util.function.UnaryOperator;
import main.java.tas.utils.Position;

public interface ArcherFactory {
	
	static public int getDamage() {
		return 60;
	}
	
	static public int getRange() {
		return 14;
	}

	static public int getDelay() {
		return 500;
	}
	
	static public int getBuildCost() {
		return 200;
	}
	
	static public int getStartUpCost() {
		return 100;
	}
	
	static public int getMaxLevel() {
		return 25;
	}
	
	static private ClassicTowerFactory constructor(final String imageName) {
		return new ClassicTowerFactory(ArcherFactory.getDamage(),
									   ArcherFactory.getRange(),
									   ArcherFactory.getDelay(),
									   ArcherFactory.getBuildCost(),
									   ArcherFactory.getStartUpCost(),
									   ArcherFactory.getMaxLevel(),
									   imageName);
	}

	static private UnaryOperator<Integer> getIncreaseCost() {
		return (x->20);
	}
	
	static private UnaryOperator<Integer> getIncreaseDamage() {
		return (x->15);
	}
	
	static public Tower basicArcher(final Position pos) {
		return constructor("archer")
			   .upgradableStarndard(pos, ArcherFactory.getIncreaseCost(), ArcherFactory.getIncreaseDamage())
			   .build();
	}
	
	static public Tower biArcher(final Position pos) {
		return constructor("biarcher")
			   .nTargetStandard(pos, ArcherFactory.getIncreaseCost(), ArcherFactory.getIncreaseDamage(), 2)
			   .build();
	}
	
	static public Tower triArcher(final Position pos) {
		return constructor("triarcher")
			   .nTargetStandard(pos, ArcherFactory.getIncreaseCost(), ArcherFactory.getIncreaseDamage(), 3)
			   .build();
	}
	
	static public Tower quadArcher(final Position pos) {
		return constructor("quadarcher")
			   .nTargetStandard(pos, ArcherFactory.getIncreaseCost(), ArcherFactory.getIncreaseDamage(), 4)
			   .build();
	}
}
