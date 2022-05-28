package main.java.tas.model.tower;

import main.java.tas.utils.Position;

public interface TeslaFactory {
	
	static public Tower basicTesla(final Position pos) {
		return new Builder(pos, 100, 9, 1100, "tesla")
		   .attackType(AttackType.AREA)
		   .damageRange(7)
		   .setUpgradable(true)
		   .upgradeCost(x->15)
		   .upgradeDamage(x->50)
		   .maxLevel(15)
		   .findFirst(()->{
			   return Towers.findFirstEnemyInRange(pos, 9);
		   })
		   .build();
	}
	
	static public Tower superTesla(final Position pos) {
		return new Builder(pos, 150, 9, 1100, "superTesla")
		   .attackType(AttackType.AREA)
		   .damageRange(7)
		   .setUpgradable(true)
		   .upgradeCost(x->x*x)
		   .upgradeDamage(x->x*x*3)
		   .maxLevel(10)
		   .findFirst(()->{
			   return Towers.findFirstEnemyInRange(pos, 9);
		   })
		   .build();
	}
	
	static public Tower godTesla(final Position pos) {
		return new Builder(pos, 200, 9, 1100, "godtesla")
		   .attackType(AttackType.AREA)
		   .damageRange(7)
		   .setUpgradable(true)
		   .upgradeCost(x->(int)Math.pow(x, x) + 20)
		   .upgradeDamage(x->(int)Math.pow(x, x))
		   .maxLevel(5)
		   .findFirst(()->{
			   return Towers.findFirstEnemyInRange(pos, 9);
		   })
		   .build();
	}
}
