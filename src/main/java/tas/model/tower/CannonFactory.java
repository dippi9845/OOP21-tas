package main.java.tas.model.tower;

import java.util.function.UnaryOperator;
import main.java.tas.utils.Position;

public interface CannonFactory{
	
	static public int getDamage() {
		return 50;
	}
	
	static public int getRange() {
		return 9;
	}
	
	static public int getDelay() {
		return 500;
	}
	
	static public int getBuildCost() {
		return 200;
	}
	
	static public int getStartUpCost() {
		return 50;
	}
	
	static public int getMaxLevel() {
		return 20;
	}
	
	static private ClassicTowerFactory constructor(final String imageName) {
		return new ClassicTowerFactory(CannonFactory.getDamage(),
									   CannonFactory.getRange(),
									   CannonFactory.getDelay(),
									   CannonFactory.getBuildCost(),
									   CannonFactory.getStartUpCost(),
									   CannonFactory.getMaxLevel(),
									   imageName);
	}
	
	static private UnaryOperator<Integer> getIncreaseCost() {
		return (x->5);
	}
	
	static private UnaryOperator<Integer> getIncreaseDamage() {
		return (x->10);
	}
	
	static public Tower basicCannon(final Position pos) {
		return constructor("cannon")
				   .upgradableStarndard(pos, CannonFactory.getIncreaseCost(), CannonFactory.getIncreaseDamage())
				   .build();
	}
	
	static public Tower biCannon(final Position pos) {
		return constructor("bicannon")
				   .nTargetStandard(pos, CannonFactory.getIncreaseCost(), CannonFactory.getIncreaseDamage(), 2)
				   .build();
	}
	
	static public Tower triCannon(final Position pos) {
		return constructor("tricannon")
				   .nTargetStandard(pos, CannonFactory.getIncreaseCost(), CannonFactory.getIncreaseDamage(), 3)
				   .build();
	}
	
	static public Tower quadCannon(final Position pos) {
		return constructor("quadcannon")
				   .nTargetStandard(pos, CannonFactory.getIncreaseCost(), CannonFactory.getIncreaseDamage(), 4)
				   .build();
	}
	
	
}
