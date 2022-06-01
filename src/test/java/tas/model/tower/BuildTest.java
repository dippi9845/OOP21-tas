package test.java.tas.model.tower;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import org.junit.jupiter.api.Test;
import main.java.tas.model.tower.factory.*;
import main.java.tas.utils.Position;
import main.java.tas.model.enemies.Enemy;
import main.java.tas.model.tower.AttackType;
import main.java.tas.model.tower.Builder;
import main.java.tas.model.tower.Tower;
import main.java.tas.model.tower.Towers;

@SuppressWarnings("unused")
class BuildTest {
	
	private final Position pos = new Position(0,0);
	private final Supplier<Optional<Enemy>> findFirstTest = ()->Towers.findFirstEnemyInRange(this.pos, 100);
	private final UnaryOperator<Integer> upgradeDamageTest = x->x;
	private final UnaryOperator<Integer> upgradeCostTest = x->x;
	
	private void raiseIllegalStateException(final Supplier<Tower> towerMaker, final String error) {
		try {
			Tower t = towerMaker.get();
			fail(error);
		}
		catch (IllegalStateException e) {
			
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	private void raiseIllegalArgumentException(final Supplier<Tower> towerMaker, final String error) {
		try {
			Tower t = towerMaker.get();
			fail(error);
		}
		catch (IllegalArgumentException e) {
			
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testBasicBuild() {
		Tower t = new Builder(this.pos, 100, 100, 100, "bla bla").build();
	}
	
	@Test
	void testBasicBuildWithCost() {
		Tower t = new Builder(this.pos, 100, 100, 100, "bla bla")
					  .cost(1)
					  .build();
	}
	
	@Test
	void testWrongBasicBuild() {
		this.raiseIllegalStateException(() -> {
			return new Builder(this.pos, 100, 100, 100, "bla bla")
					  .damageRange(10)
					  .build();
		}, "You cannot define damage range to basic tower");

		
		this.raiseIllegalStateException(() ->  {
			return new Builder(this.pos, 100, 100, 100, "bla bla")
					  .maximumTarget(10)
					  .build();
		}, "You cannot define maximum target to basic tower");

		
		this.raiseIllegalStateException(() ->  {
			return new Builder(this.pos, 100, 100, 100, "bla bla")
					  .findFirst(this.findFirstTest)
					  .build();
		}, "You cannot define find fisrt method to basic tower");
		
	}
	
	@Test
	void testNullValues() {
		this.raiseIllegalArgumentException(() -> {
			return new Builder(null, 100, 100, 100, "bla").build();
		}, "Position can't be null");
		
		this.raiseIllegalArgumentException(() -> {
			return new Builder(this.pos, 0, 100, 100, "bla bla").build();
		}, "zero as parameter can't be accepted");
		
		this.raiseIllegalArgumentException(() -> {
			return new Builder(this.pos, 100, 0, 100, "bla bla").build();
		}, "zero as parameter can't be accepted");
		
		this.raiseIllegalArgumentException(() -> {
			return new Builder(this.pos, 100, 100, 0, "bla bla").build();
		}, "zero as parameter can't be accepted");
		
		this.raiseIllegalArgumentException(() -> {
			return new Builder(this.pos, 100, 100, 100, "bla")
					  .attackType(AttackType.MULTIPLE)
					  .maximumTarget(0)
					  .build();
		}, "zero as parameter can't be accepted");
		
		this.raiseIllegalArgumentException(() -> {
			return new Builder(this.pos, 100, 100, 100, "bla bla")
					  .setUpgradable(true)
					  .startUpgradeCost(0)
					  .upgradeCost(this.upgradeCostTest)
					  .upgradeDamage(this.upgradeDamageTest)
					  .maxLevel(10)
					  .build();
		}, "zero as parameter can't be accepted");
		
		this.raiseIllegalArgumentException(() -> {
			return new Builder(this.pos, 100, 100, 100, "bla bla")
					  .setUpgradable(true)
					  .startUpgradeCost(1)
					  .upgradeCost(this.upgradeCostTest)
					  .upgradeDamage(this.upgradeDamageTest)
					  .maxLevel(0)
					  .build();
		}, "zero as parameter can't be accepted");
	}
	
	@Test
	void testNegativeValuesBuild() {
		
		this.raiseIllegalArgumentException(() -> {
			return new Builder(this.pos, -100, 100, 100, "bla bla").build();
		}, "negative parameter can't be accepted");
		
		this.raiseIllegalArgumentException(() -> {
			return new Builder(this.pos, 100, -100, 100, "bla bla").build();
		}, "negative parameter can't be accepted");
		
		this.raiseIllegalArgumentException(() -> {
			return new Builder(this.pos, 100, 100, -100, "bla bla").build();
		}, "negative parameter can't be accepted");

		this.raiseIllegalArgumentException(() -> {
			return new Builder(this.pos, 100, 100, 100, "bla")
					  .cost(-1)
					  .build();
		}, "negative parameter can't be accepted");
		
		this.raiseIllegalArgumentException(() -> {
			return new Builder(this.pos, 100, 100, 100, "bla")
					  .attackType(AttackType.MULTIPLE)
					  .maximumTarget(-1)
					  .build();
		}, "negative parameter can't be accepted");
		
		this.raiseIllegalArgumentException(() -> {
			return new Builder(this.pos, 100, 100, 100, "bla bla")
					  .setUpgradable(true)
					  .startUpgradeCost(-1)
					  .upgradeCost(this.upgradeCostTest)
					  .upgradeDamage(this.upgradeDamageTest)
					  .maxLevel(10)
					  .build();
		}, "negative parameter can't be accepted");
		
		this.raiseIllegalArgumentException(() -> {
			return new Builder(this.pos, 100, 100, 100, "bla bla")
					  .setUpgradable(true)
					  .startUpgradeCost(1)
					  .upgradeCost(this.upgradeCostTest)
					  .upgradeDamage(this.upgradeDamageTest)
					  .maxLevel(-10)
					  .build();
		}, "negative parameter can't be accepted");
		
	}
	
	@Test
	void testBlankImageName() {
		this.raiseIllegalArgumentException(() -> {
			return new Builder(this.pos, 100, 100, 100, "").build();
		}, "image name can't be blank");
		
		this.raiseIllegalArgumentException(() -> {
			return new Builder(this.pos, 100, 100, 100, " ").build();
		}, "image name can't be blank");
		
		this.raiseIllegalArgumentException(() -> {
			return new Builder(this.pos, 100, 100, 100, "    ").build();
		}, "image name can't be blank");
	}
	
	@Test
	void testMultiple() {
		Tower t = new Builder(this.pos, 100, 100, 100, "bla bla")
					  .attackType(AttackType.MULTIPLE)
					  .maximumTarget(12)
					  .build();
	}
	
	@Test
	void testWrongMultiple() {
		this.raiseIllegalStateException(() -> {
			return new Builder(this.pos, 100, 100, 100, "bla bla")
					  .attackType(AttackType.MULTIPLE)
					  .maximumTarget(12)
					  .damageRange(10)
					  .build();
		}, "Damage range can't be defined");
		
		
		this.raiseIllegalStateException(() -> {
			return new Builder(this.pos, 100, 100, 100, "bla bla")
					  .attackType(AttackType.MULTIPLE)
					  .maximumTarget(12)
					  .findFirst(()->Towers.findFirstEnemyInRange(this.pos, 100))
					  .build();
		}, "find first can't be defined");
	}
	
	@Test
	void testArea() {
		Tower t = new Builder(this.pos, 100, 100, 100, "bla bla")
				  .attackType(AttackType.AREA)
				  .maximumTarget(12)
				  .findFirst(this.findFirstTest)
				  .damageRange(5)
				  .build();
	}
	
	@Test
	void testwrongArea() {
		this.raiseIllegalStateException(() -> {
			return new Builder(this.pos, 100, 100, 100, "bla bla")
					  .attackType(AttackType.AREA)
					  .findFirst(this.findFirstTest)
					  .damageRange(5)
					  .build();
		}, "maximun target not defined");
		
		this.raiseIllegalStateException(() -> {
			return new Builder(this.pos, 100, 100, 100, "bla bla")
					  .attackType(AttackType.AREA)
					  .maximumTarget(12)
					  .damageRange(5)
					  .build();
		}, "find first not defined");
		
		this.raiseIllegalStateException(() -> {
			return new Builder(this.pos, 100, 100, 100, "bla bla")
					  .attackType(AttackType.AREA)
					  .maximumTarget(12)
					  .findFirst(this.findFirstTest)
					  .build();
		}, "damage range not defined");
		
	}

	@Test
	void testUpgradable() {
		Tower t = new Builder(this.pos, 100, 100, 100, "bla bla")
				  .setUpgradable(true)
				  .startUpgradeCost(1)
				  .upgradeCost(this.upgradeCostTest)
				  .upgradeDamage(this.upgradeDamageTest)
				  .maxLevel(100)
				  .build();
	}
	
	@Test
	void testWrongUpgradable() {
		this.raiseIllegalStateException(() -> {
			return new Builder(this.pos, 100, 100, 100, "bla bla")
					  .setUpgradable(true)
					  .startUpgradeCost(1)
					  .upgradeCost(this.upgradeCostTest)
					  .maxLevel(100)
					  .build();
		}, "increase damage not defined");
		
		this.raiseIllegalStateException(() -> {
			return new Builder(this.pos, 100, 100, 100, "bla bla")
					  .setUpgradable(true)
					  .startUpgradeCost(1)
					  .upgradeDamage(this.upgradeDamageTest)
					  .maxLevel(100)
					  .build();
		}, "increase cost not defined");
	}
	
	@Test
	void testBasicArcher() {
		ArcherFactory.basicArcher(this.pos);
	}
	
	@Test
	void testBiArcher() {
		ArcherFactory.biArcher(this.pos);
	}
	
	@Test
	void testTriArcher() {
		ArcherFactory.triArcher(this.pos);
	}
	
	@Test
	void testQuadArcher() {
		ArcherFactory.quadArcher(this.pos);
	}
	
	@Test
	void testBasicCannon() {
		CannonFactory.basicCannon(this.pos);
	}
	
	@Test
	void testBiCannon() {
		CannonFactory.biCannon(this.pos);
	}
	
	@Test
	void testTriCannon() {
		CannonFactory.triCannon(this.pos);
	}
	
	@Test
	void testQuadCannon() {
		CannonFactory.quadCannon(this.pos);
	}

	@Test
	void testBasicFlame() {
		FlameFactory.basicFlame(this.pos);
	}
	
	@Test
	void testBiFlame() {
		FlameFactory.biFlame(this.pos);
	}
	
	@Test
	void testTriFlame() {
		FlameFactory.triFlame(this.pos);
	}
	
	@Test
	void testQuadFlame() {
		FlameFactory.quadFlame(this.pos);
	}
	
	@Test
	void testGasTower() {
		GasFactory.gasTower(this.pos);
	}
	
	@Test
	void testBasicMortar() {
		MortarFactory.basicMortar(this.pos);
	}
	
	@Test
	void testSuperMortar() {
		MortarFactory.superMortar(this.pos);
	}
	
	@Test
	void testGodMortar() {
		MortarFactory.godMortar(this.pos);
	}
	
	@Test
	void testBasicTesla() {
		TeslaFactory.basicTesla(this.pos);
	}
	
	@Test
	void testSuperTesla() {
		TeslaFactory.superTesla(this.pos);
	}
	
	@Test
	void testGodTesla() {
		TeslaFactory.godTesla(this.pos);
	}
}
