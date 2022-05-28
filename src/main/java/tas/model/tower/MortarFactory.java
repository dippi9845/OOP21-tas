package main.java.tas.model.tower;

import main.java.tas.utils.Position;

public interface MortarFactory {
	
	static public Tower basicMortar(final Position pos) {
		return new Builder(pos, 25, 16, 5000, "mortar")
				   .attackType(AttackType.AREA)
				   .damageRange(3)
				   .setUpgradable(true)
				   .upgradeCost(x->10)
				   .upgradeDamage(x->15)
				   .maxLevel(15)
				   .findFirst(()->{
					   return Towers.findFistEnemyBiPredicate(e->Towers.isInRange(pos, e.getPosition(), 16),
							   								  e->!Towers.isInRange(pos, e.getPosition(), 4));
				   })
				   .build();
	}
	
	static public Tower superMortar(final Position pos) {
		return new Builder(pos, 10, 12, 8000, "supermortar")
		   .attackType(AttackType.AREA)
		   .damageRange(4)
		   .setUpgradable(true)
		   .upgradeCost(x->x*x)
		   .upgradeDamage(x->x*x*2)
		   .maxLevel(10)
		   .findFirst(()->{
			   return Towers.findFistEnemyBiPredicate(e->Towers.isInRange(pos, e.getPosition(), 12),
					   								  e->!Towers.isInRange(pos, e.getPosition(), 4));
		   })
		   .build();
	}
	
	static public Tower godMortar(final Position pos) {
		return new Builder(pos, 10, 12, 8000, "godmortar")
		   .attackType(AttackType.AREA)
		   .damageRange(6)
		   .setUpgradable(true)
		   .upgradeCost(x->(int)Math.pow(x, x) + 15)
		   .upgradeDamage(x->(int)Math.pow(x, x))
		   .maxLevel(5)
		   .findFirst(()->{
			   return Towers.findFistEnemyBiPredicate(e->Towers.isInRange(pos, e.getPosition(), 12),
					   								  e->!Towers.isInRange(pos, e.getPosition(), 4));
		   })
		   .build();
	}
}
