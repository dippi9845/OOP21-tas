package main.java.tas.model.tower;

import main.java.tas.utils.Position;

public interface TowerFactory {
	default Tower basicCannon(final Position pos) {
		return new Builder(pos, 50, 9, 500, "cannon.png")
				   .build();
	}
	
	default Tower biCannon(final Position pos) {
		return new Builder(pos, 50, 9, 500, "cannon.png")
				   .attackType(Type.MULTIPLE)
				   .maximumTarget(2)
				   .build();
	}
	
	default Tower triCannon(final Position pos) {
		return new Builder(pos, 50, 9, 500, "cannon.png")
				   .attackType(Type.MULTIPLE)
				   .maximumTarget(3)
				   .build();
	}
	
	default Tower basicArcherTower(final Position pos) {
		return new Builder(pos, 60, 14, 500, "archer.png")
				   .build();
	}
	
	default Tower biArcherTower(final Position pos) {
		return new Builder(pos, 60, 14, 500, "archer.png")
				   .attackType(Type.MULTIPLE)
				   .maximumTarget(2)
				   .build();
	}
	
	default Tower triArcherTower(final Position pos) {
		return new Builder(pos, 60, 14, 500, "archer.png")
				   .attackType(Type.MULTIPLE)
				   .maximumTarget(3)
				   .build();
	}
	
	default Tower flameTrower(final Position pos) {
		return new Builder(pos, 10, 6, 100, "flame.png")
				   .build();
	}
	
	default Tower biFlameTrower(final Position pos) {
		return new Builder(pos, 10, 6, 100, "flame.png")
				   .attackType(Type.MULTIPLE)
				   .maximumTarget(2)
				   .build();
	}
	
	default Tower triFlameTrower(final Position pos) {
		return new Builder(pos, 10, 6, 100, "flame.png")
				   .attackType(Type.MULTIPLE)
				   .maximumTarget(3)
				   .build();
	}
	
	default Tower quadFlameTrower(final Position pos) {
		return new Builder(pos, 10, 6, 100, "flame.png")
				   .attackType(Type.MULTIPLE)
				   .maximumTarget(4)
				   .build();
	}
	
	default Tower gasTower(final Position pos) {
		return new Builder(pos, 7, 6, 100, "gas.png")
				   .attackType(Type.MULTIPLE)
				   .maximumTarget(Integer.MAX_VALUE)
				   .build();
	}
	
	default Tower mortar(final Position pos) {
		return new Builder(pos, 25, 16, 5000, "mortar.png")
				   .attackType(Type.AREA)
				   .attackRange(Integer.MAX_VALUE)
				   .findFirst(()->{
					   return Towers.findFistEnemyBiPredicate(e->Towers.isInRange(pos, e.getPosition(), 16),
							   								  e->!Towers.isInRange(pos, e.getPosition(), 4));
				   })
				   .build();
	}
	
	default Tower tesla(final Position pos) {
		return new Builder(pos, 40, 10, 1100, "tesla.png")
				   .attackType(Type.AREA)
				   .attackRange(5)
				   .findFirst(()->{return Towers.findFirstEnemyInRange(pos, 10);})
				   .build();
	}
}
