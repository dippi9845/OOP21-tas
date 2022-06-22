package test.java.tas.model.tower;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import org.junit.jupiter.api.Test;
import main.java.tas.utils.Position;
import main.java.tas.controller.tower.builder.AttackType;
import main.java.tas.controller.tower.builder.TowerBuilder;
import main.java.tas.controller.tower.factory.ArcherFactory;
import main.java.tas.controller.tower.factory.CannonFactory;
import main.java.tas.controller.tower.factory.FlameFactory;
import main.java.tas.controller.tower.factory.GasFactory;
import main.java.tas.controller.tower.factory.MortarFactory;
import main.java.tas.controller.tower.factory.TeslaFactory;
import main.java.tas.model.enemy.Enemy;
import main.java.tas.model.tower.Tower;
import main.java.tas.model.tower.Towers;

@SuppressWarnings("unused")
class BuildTest {

	private final Position pos = new Position(0, 0);
	private final List<Enemy> enemylist = new LinkedList<>();
	private final Supplier<Optional<Enemy>> findFirstTest = () -> Towers.findFirstEnemyInRange(this.pos, 100,
			this.enemylist);
	private final UnaryOperator<Integer> upgradeDamageTest = x -> x;
	private final UnaryOperator<Integer> upgradeCostTest = x -> x;

	private void raiseIllegalStateException(final Supplier<Tower> towerMaker, final String error) {
		try {
			Tower t = towerMaker.get();
			fail(error);
		} catch (IllegalStateException e) {

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	private void raiseIllegalArgumentException(final Supplier<Tower> towerMaker, final String error) {
		try {
			Tower t = towerMaker.get();
			fail(error);
		} catch (IllegalArgumentException e) {

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testBasicBuild() {
		Tower t = new TowerBuilder(this.pos, 100, 100, 100, "bla bla", this.enemylist).build();
	}

	@Test
	void testBasicBuildWithCost() {
		Tower t = new TowerBuilder(this.pos, 100, 100, 100, "bla bla", this.enemylist).cost(1).build();
	}

	@Test
	void testWrongBasicBuild() {
		this.raiseIllegalStateException(() -> {
			return new TowerBuilder(this.pos, 100, 100, 100, "bla bla", this.enemylist).damageRange(10).build();
		}, "You cannot define damage range to basic tower");

		this.raiseIllegalStateException(() -> {
			return new TowerBuilder(this.pos, 100, 100, 100, "bla bla", this.enemylist).maximumTarget(10).build();
		}, "You cannot define maximum target to basic tower");

		this.raiseIllegalStateException(() -> {
			return new TowerBuilder(this.pos, 100, 100, 100, "bla bla", this.enemylist).findFirst(this.findFirstTest)
					.build();
		}, "You cannot define find fisrt method to basic tower");

	}

	/**
	 * Test all possible combination of buildable towers with a parameter that is 0
	 * or null
	 */
	@Test
	void testNullValues() {
		this.raiseIllegalArgumentException(() -> {
			return new TowerBuilder(null, 100, 100, 100, "bla", this.enemylist).build();
		}, "Position can't be null");

		this.raiseIllegalArgumentException(() -> {
			return new TowerBuilder(this.pos, 0, 100, 100, "bla bla", this.enemylist).build();
		}, "zero as parameter can't be accepted");

		this.raiseIllegalArgumentException(() -> {
			return new TowerBuilder(this.pos, 100, 0, 100, "bla bla", this.enemylist).build();
		}, "zero as parameter can't be accepted");

		this.raiseIllegalArgumentException(() -> {
			return new TowerBuilder(this.pos, 100, 100, 0, "bla bla", this.enemylist).build();
		}, "zero as parameter can't be accepted");

		this.raiseIllegalStateException(() -> {
			return new TowerBuilder(this.pos, 100, 100, 100, "bla bla", null).build();
		}, "null as parameter can't be accepted");

		this.raiseIllegalArgumentException(() -> {
			return new TowerBuilder(this.pos, 100, 100, 100, "bla", this.enemylist).attackType(AttackType.MULTIPLE)
					.maximumTarget(0).build();
		}, "zero as parameter can't be accepted");

		this.raiseIllegalArgumentException(() -> {
			return new TowerBuilder(this.pos, 100, 100, 100, "bla bla", this.enemylist).setUpgradable(true)
					.startUpgradeCost(0).upgradeCost(this.upgradeCostTest).upgradeDamage(this.upgradeDamageTest)
					.maxLevel(10).build();
		}, "zero as parameter can't be accepted");

		this.raiseIllegalStateException(() -> {
			return new TowerBuilder(this.pos, 100, 100, 100, "bla bla", this.enemylist).setUpgradable(true)
					.startUpgradeCost(1).upgradeCost(null).upgradeDamage(this.upgradeDamageTest).maxLevel(1).build();
		}, "null as upgrade cost can't be accepted");

		this.raiseIllegalStateException(() -> {
			return new TowerBuilder(this.pos, 100, 100, 100, "bla bla", this.enemylist).setUpgradable(true)
					.startUpgradeCost(1).upgradeCost(this.upgradeCostTest).upgradeDamage(null).maxLevel(1).build();
		}, "zero as upgrade damage can't be accepted");

		this.raiseIllegalArgumentException(() -> {
			return new TowerBuilder(this.pos, 100, 100, 100, "bla bla", this.enemylist).setUpgradable(true)
					.startUpgradeCost(1).upgradeCost(this.upgradeCostTest).upgradeDamage(this.upgradeDamageTest)
					.maxLevel(0).build();
		}, "zero as parameter can't be accepted");

		this.raiseIllegalArgumentException(() -> {
			return new TowerBuilder(this.pos, 100, 100, 100, "bla", this.enemylist).attackType(AttackType.AREA)
					.maximumTarget(0).damageRange(10).findFirst(findFirstTest).build();
		}, "zero as parameter can't be accepted");

		this.raiseIllegalArgumentException(() -> {
			return new TowerBuilder(this.pos, 100, 100, 100, "bla", this.enemylist).attackType(AttackType.AREA)
					.maximumTarget(10).damageRange(0).findFirst(findFirstTest).build();
		}, "zero as parameter can't be accepted");

		this.raiseIllegalStateException(() -> {
			return new TowerBuilder(this.pos, 100, 100, 100, "bla", this.enemylist).attackType(AttackType.AREA)
					.maximumTarget(10).damageRange(10).findFirst(null).build();
		}, "null as find first can't be accepted");
	}

	/**
	 * Test all possible negative values
	 */
	@Test
	void testNegativeValuesBuild() {

		this.raiseIllegalArgumentException(() -> {
			return new TowerBuilder(this.pos, -100, 100, 100, "bla bla", this.enemylist).build();
		}, "negative parameter can't be accepted");

		this.raiseIllegalArgumentException(() -> {
			return new TowerBuilder(this.pos, 100, -100, 100, "bla bla", this.enemylist).build();
		}, "negative parameter can't be accepted");

		this.raiseIllegalArgumentException(() -> {
			return new TowerBuilder(this.pos, 100, 100, -100, "bla bla", this.enemylist).build();
		}, "negative parameter can't be accepted");

		this.raiseIllegalArgumentException(() -> {
			return new TowerBuilder(this.pos, 100, 100, 100, "bla", this.enemylist).cost(-1).build();
		}, "negative parameter can't be accepted");

		this.raiseIllegalArgumentException(() -> {
			return new TowerBuilder(this.pos, 100, 100, 100, "bla", this.enemylist).attackType(AttackType.MULTIPLE)
					.maximumTarget(-1).build();
		}, "negative parameter can't be accepted");

		this.raiseIllegalArgumentException(() -> {
			return new TowerBuilder(this.pos, 100, 100, 100, "bla bla", this.enemylist).setUpgradable(true)
					.startUpgradeCost(-1).upgradeCost(this.upgradeCostTest).upgradeDamage(this.upgradeDamageTest)
					.maxLevel(10).build();
		}, "negative parameter can't be accepted");

		this.raiseIllegalArgumentException(() -> {
			return new TowerBuilder(this.pos, 100, 100, 100, "bla bla", this.enemylist).setUpgradable(true)
					.startUpgradeCost(1).upgradeCost(this.upgradeCostTest).upgradeDamage(this.upgradeDamageTest)
					.maxLevel(-10).build();
		}, "negative parameter can't be accepted");

		this.raiseIllegalArgumentException(() -> {
			return new TowerBuilder(this.pos, 100, 100, 100, "bla", this.enemylist).attackType(AttackType.AREA)
					.maximumTarget(-1).damageRange(10).findFirst(findFirstTest).build();
		}, "zero as parameter can't be accepted");

		this.raiseIllegalArgumentException(() -> {
			return new TowerBuilder(this.pos, 100, 100, 100, "bla", this.enemylist).attackType(AttackType.AREA)
					.maximumTarget(10).damageRange(-1).findFirst(findFirstTest).build();
		}, "zero as parameter can't be accepted");
	}

	@Test
	void testBlankImageName() {
		this.raiseIllegalArgumentException(() -> {
			return new TowerBuilder(this.pos, 100, 100, 100, "", this.enemylist).build();
		}, "image name can't be blank");

		this.raiseIllegalArgumentException(() -> {
			return new TowerBuilder(this.pos, 100, 100, 100, " ", this.enemylist).build();
		}, "image name can't be blank");

		this.raiseIllegalArgumentException(() -> {
			return new TowerBuilder(this.pos, 100, 100, 100, "    ", this.enemylist).build();
		}, "image name can't be blank");
	}

	@Test
	void testMultiple() {
		Tower t = new TowerBuilder(this.pos, 100, 100, 100, "bla bla", this.enemylist).attackType(AttackType.MULTIPLE)
				.maximumTarget(12).build();
	}

	@Test
	void testWrongMultiple() {
		this.raiseIllegalStateException(() -> {
			return new TowerBuilder(this.pos, 100, 100, 100, "bla bla", this.enemylist).attackType(AttackType.MULTIPLE)
					.maximumTarget(12).damageRange(10).build();
		}, "Damage range can't be defined");

		this.raiseIllegalStateException(() -> {
			return new TowerBuilder(this.pos, 100, 100, 100, "bla bla", this.enemylist).attackType(AttackType.MULTIPLE)
					.maximumTarget(12).findFirst(() -> Towers.findFirstEnemyInRange(this.pos, 100, this.enemylist))
					.build();
		}, "find first can't be defined");
	}

	@Test
	void testArea() {
		Tower t = new TowerBuilder(this.pos, 100, 100, 100, "bla bla", this.enemylist).attackType(AttackType.AREA)
				.maximumTarget(12).findFirst(this.findFirstTest).damageRange(5).build();
	}

	@Test
	void testwrongArea() {
		this.raiseIllegalStateException(() -> {
			return new TowerBuilder(this.pos, 100, 100, 100, "bla bla", this.enemylist).attackType(AttackType.AREA)
					.findFirst(this.findFirstTest).damageRange(5).build();
		}, "maximun target not defined");

		this.raiseIllegalStateException(() -> {
			return new TowerBuilder(this.pos, 100, 100, 100, "bla bla", this.enemylist).attackType(AttackType.AREA)
					.maximumTarget(12).damageRange(5).build();
		}, "find first not defined");

		this.raiseIllegalStateException(() -> {
			return new TowerBuilder(this.pos, 100, 100, 100, "bla bla", this.enemylist).attackType(AttackType.AREA)
					.maximumTarget(12).findFirst(this.findFirstTest).build();
		}, "damage range not defined");

	}

	@Test
	void testUpgradable() {
		Tower t = new TowerBuilder(this.pos, 100, 100, 100, "bla bla", this.enemylist).setUpgradable(true)
				.startUpgradeCost(1).upgradeCost(this.upgradeCostTest).upgradeDamage(this.upgradeDamageTest)
				.maxLevel(100).build();
	}

	@Test
	void testWrongUpgradable() {
		this.raiseIllegalStateException(() -> {
			return new TowerBuilder(this.pos, 100, 100, 100, "bla bla", this.enemylist).setUpgradable(true)
					.startUpgradeCost(1).upgradeCost(this.upgradeCostTest).maxLevel(100).build();
		}, "increase damage not defined");

		this.raiseIllegalStateException(() -> {
			return new TowerBuilder(this.pos, 100, 100, 100, "bla bla", this.enemylist).setUpgradable(true)
					.startUpgradeCost(1).upgradeDamage(this.upgradeDamageTest).maxLevel(100).build();
		}, "increase cost not defined");
	}

	@Test
	void testBasicArcher() {
		ArcherFactory.basicArcher(this.pos, this.enemylist);
	}

	@Test
	void testBiArcher() {
		ArcherFactory.biArcher(this.pos, this.enemylist);
	}

	@Test
	void testTriArcher() {
		ArcherFactory.triArcher(this.pos, this.enemylist);
	}

	@Test
	void testQuadArcher() {
		ArcherFactory.quadArcher(this.pos, this.enemylist);
	}

	@Test
	void testBasicCannon() {
		CannonFactory.basicCannon(this.pos, this.enemylist);
	}

	@Test
	void testBiCannon() {
		CannonFactory.biCannon(this.pos, this.enemylist);
	}

	@Test
	void testTriCannon() {
		CannonFactory.triCannon(this.pos, this.enemylist);
	}

	@Test
	void testQuadCannon() {
		CannonFactory.quadCannon(this.pos, this.enemylist);
	}

	@Test
	void testBasicFlame() {
		FlameFactory.basicFlame(this.pos, this.enemylist);
	}

	@Test
	void testBiFlame() {
		FlameFactory.biFlame(this.pos, this.enemylist);
	}

	@Test
	void testTriFlame() {
		FlameFactory.triFlame(this.pos, this.enemylist);
	}

	@Test
	void testQuadFlame() {
		FlameFactory.quadFlame(this.pos, this.enemylist);
	}

	@Test
	void testGasTower() {
		GasFactory.gasTower(this.pos, this.enemylist);
	}

	@Test
	void testBasicMortar() {
		MortarFactory.basicMortar(this.pos, this.enemylist);
	}

	@Test
	void testSuperMortar() {
		MortarFactory.superMortar(this.pos, this.enemylist);
	}

	@Test
	void testGodMortar() {
		MortarFactory.godMortar(this.pos, this.enemylist);
	}

	@Test
	void testBasicTesla() {
		TeslaFactory.basicTesla(this.pos, this.enemylist);
	}

	@Test
	void testSuperTesla() {
		TeslaFactory.superTesla(this.pos, this.enemylist);
	}

	@Test
	void testGodTesla() {
		TeslaFactory.godTesla(this.pos, this.enemylist);
	}
}
