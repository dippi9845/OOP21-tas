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
		try {
			Tower t = new Builder(this.pos, 100, 100, 100, "bla bla")
					  .damageRange(10)
					  .build();
		}
		catch (IllegalStateException e) {
			
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		
		try {
			Tower t = new Builder(this.pos, 100, 100, 100, "bla bla")
					  .maximumTarget(10)
					  .build();
		}
		catch (IllegalStateException e) {
			
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		
		try {
			Tower t = new Builder(this.pos, 100, 100, 100, "bla bla")
					  .findFirst(this.findFirstTest)
					  .build();
		}
		catch (IllegalStateException e) {
			
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	
	@Test
	void testNullValues() {
		try {
			Tower t = new Builder(null, 100, 100, 100, "bla").build();
			fail("Position can't be null");
		}
		catch (IllegalArgumentException e) {
			
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		
		try {
			Tower t = new Builder(this.pos, 0, 100, 100, "bla bla").build();
			fail("zero as parameter can't be accepted");
		}
		catch (IllegalArgumentException e) {
			
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		
		try {
			Tower t = new Builder(this.pos, 100, 0, 100, "bla bla").build();
			fail("zero as parameter can't be accepted");
		}
		catch (IllegalArgumentException e) {
			
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		
		try {
			Tower t = new Builder(this.pos, 100, 100, 0, "bla bla").build();
			fail("zero as parameter can't be accepted");
		}
		catch (IllegalArgumentException e) {
			
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		
		try {
			Tower t = new Builder(this.pos, 100, 100, 100, "bla")
					  .attackType(AttackType.MULTIPLE)
					  .maximumTarget(0)
					  .build();
			fail("zero as parameter can't be accepted");
		}
		catch (IllegalArgumentException e) {
			
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		
		try {
			Tower t = new Builder(this.pos, 100, 100, 100, "bla bla")
					  .setUpgradable(true)
					  .startUpgradeCost(0)
					  .upgradeCost(this.upgradeCostTest)
					  .upgradeDamage(this.upgradeDamageTest)
					  .maxLevel(10)
					  .build();
			fail("zero as parameter can't be accepted");
		}
		catch (IllegalArgumentException e) {
			
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		
		try {
			Tower t = new Builder(this.pos, 100, 100, 100, "bla bla")
					  .setUpgradable(true)
					  .startUpgradeCost(1)
					  .upgradeCost(this.upgradeCostTest)
					  .upgradeDamage(this.upgradeDamageTest)
					  .maxLevel(0)
					  .build();
			fail("zero as parameter can't be accepted");
		}
		catch (IllegalArgumentException e) {
			
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testNegativeValuesBuild() {
		try {
			Tower t = new Builder(this.pos, -100, 100, 100, "bla bla").build();
			fail("negative parameter can't be accepted");
		}
		catch (IllegalArgumentException e) {
			
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		
		try {
			Tower t = new Builder(this.pos, 100, -100, 100, "bla bla").build();
			fail("negative parameter can't be accepted");
		}
		catch (IllegalArgumentException e) {
			
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		
		try {
			Tower t = new Builder(this.pos, 100, 100, -100, "bla bla").build();
			fail("negative parameter can't be accepted");
		}
		catch (IllegalArgumentException e) {
			
		}
		catch (Exception e) {
			fail(e.getMessage());
		}

		try {
			Tower t = new Builder(this.pos, 100, 100, 100, "bla")
					  .cost(-1)
					  .build();
			fail("negative parameter can't be accepted");
		}
		catch (IllegalArgumentException e) {
			
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		
		try {
			Tower t = new Builder(this.pos, 100, 100, 100, "bla")
					  .attackType(AttackType.MULTIPLE)
					  .maximumTarget(-1)
					  .build();
			fail("negative parameter can't be accepted");
		}
		catch (IllegalArgumentException e) {
			
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		
		try {
			Tower t = new Builder(this.pos, 100, 100, 100, "bla bla")
					  .setUpgradable(true)
					  .startUpgradeCost(-1)
					  .upgradeCost(this.upgradeCostTest)
					  .upgradeDamage(this.upgradeDamageTest)
					  .maxLevel(10)
					  .build();
			fail("negative parameter can't be accepted");
		}
		catch (IllegalArgumentException e) {
			
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		
		try {
			Tower t = new Builder(this.pos, 100, 100, 100, "bla bla")
					  .setUpgradable(true)
					  .startUpgradeCost(1)
					  .upgradeCost(this.upgradeCostTest)
					  .upgradeDamage(this.upgradeDamageTest)
					  .maxLevel(-10)
					  .build();
			fail("negative parameter can't be accepted");
		}
		catch (IllegalArgumentException e) {
			
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	
	@Test
	void testBlankImageName() {
		try {
			Tower t = new Builder(this.pos, 100, 100, 100, "").build();
			fail("image name can't be blank");
		}
		catch (IllegalArgumentException e) {
			
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		
		try {
			Tower t = new Builder(this.pos, 100, 100, 100, " ").build();
			fail("image name can't be blank");
		}
		catch (IllegalArgumentException e) {
			
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		
		try {
			Tower t = new Builder(this.pos, 100, 100, 100, "    ").build();
			fail("image name can't be blank");
		}
		catch (IllegalArgumentException e) {
			
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
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
		try {
			Tower t = new Builder(this.pos, 100, 100, 100, "bla bla")
					  .attackType(AttackType.MULTIPLE)
					  .maximumTarget(12)
					  .damageRange(10)
					  .build();
			fail("Damage range can't be defined");
		}
		catch (IllegalStateException e) {
			
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		
		
		try {
			Tower t = new Builder(this.pos, 100, 100, 100, "bla bla")
					  .attackType(AttackType.MULTIPLE)
					  .maximumTarget(12)
					  .findFirst(()->Towers.findFirstEnemyInRange(this.pos, 100))
					  .build();
			fail("find first can't be defined");
		}
		catch (IllegalStateException e) {
			
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
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
		try {
			Tower t = new Builder(this.pos, 100, 100, 100, "bla bla")
					  .attackType(AttackType.AREA)
					  .findFirst(this.findFirstTest)
					  .damageRange(5)
					  .build();
			fail("maximun target not defined");
		}
		catch (IllegalStateException e) {
			
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		
		try {
			Tower t = new Builder(this.pos, 100, 100, 100, "bla bla")
					  .attackType(AttackType.AREA)
					  .maximumTarget(12)
					  .damageRange(5)
					  .build();
			fail("find first not defined");
		}
		catch (IllegalStateException e) {
			
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		
		try {
			Tower t = new Builder(this.pos, 100, 100, 100, "bla bla")
					  .attackType(AttackType.AREA)
					  .maximumTarget(12)
					  .findFirst(this.findFirstTest)
					  .build();
			fail("damage range not defined");
		}
		catch (IllegalStateException e) {
			
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		
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
		try {
			Tower t = new Builder(this.pos, 100, 100, 100, "bla bla")
					  .setUpgradable(true)
					  .startUpgradeCost(1)
					  .upgradeCost(this.upgradeCostTest)
					  .maxLevel(100)
					  .build();
			fail("increase damage not defined");
		}
		catch (IllegalStateException e) {
			
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		
		try {
			Tower t = new Builder(this.pos, 100, 100, 100, "bla bla")
					  .setUpgradable(true)
					  .startUpgradeCost(1)
					  .upgradeDamage(this.upgradeDamageTest)
					  .maxLevel(100)
					  .build();
			fail("increase cost not defined");
		}
		catch (IllegalStateException e) {
			
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
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
