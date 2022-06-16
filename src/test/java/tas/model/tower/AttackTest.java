package test.java.tas.model.tower;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Dimension;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import main.java.tas.controller.tower.TowerLogic;
import main.java.tas.controller.tower.TowerLogicImpl;
import main.java.tas.model.enemy.Enemy;
import main.java.tas.model.tower.AttackType;
import main.java.tas.model.tower.TowerBuilder;
import main.java.tas.model.tower.Tower;
import main.java.tas.model.tower.Towers;
import main.java.tas.model.tower.factory.CannonFactory;
import main.java.tas.model.tower.factory.DefaultTowers;
import main.java.tas.utils.Position;
import main.java.tas.model.enemy.GenericEnemy;

/**
 * Class for testing every type of tower that attack
 *
 */
class AttackTest {
	
	private Enemy getGenericEnemy(final Position pos, final double health) {
		List<Position> nodesPosition = new LinkedList<>();
		nodesPosition.add(pos);
		nodesPosition.add(pos);
		nodesPosition.add(pos);
		
		return new GenericEnemy(nodesPosition, health, 0, 0, 0, new Dimension(1, 1), "Niente");
	}

	@Test
	void BasicTower() throws InterruptedException {
		List<Enemy> enemies = new LinkedList<>();
		enemies.add(new FakeEnemy(new Position(50, 50), 100));

		Tower t = new TowerBuilder(new Position(51, 51), 50, 10, 10, "BlaBla", enemies).build();
		t.compute();
		t.compute();

		assertTrue(enemies.get(0).getHealth() < 51);
	}

	@Test
	void BasicTowerWithMovement() throws InterruptedException {
		List<Enemy> enemies = new LinkedList<>();
		FakeEnemy e = new FakeEnemy(new Position(50, 50), 100);
		enemies.add(e);

		Tower t = new TowerBuilder(new Position(51, 51), 50, 10, 10, "BlaBla", enemies).build();
		t.compute();
		t.compute();

		e.setPosition(new Position(150, 150));

		t.compute();

		assertTrue(enemies.get(0).getHealth() < 51);
	}

	@Test
	void BasicMultipleTower() throws InterruptedException {
		List<Enemy> enemies = new LinkedList<>();
		enemies.add(new FakeEnemy(new Position(50, 50), 100));
		enemies.add(new FakeEnemy(new Position(51, 50), 100));

		Tower t = new TowerBuilder(new Position(51, 51), 50, 10, 10, "BlaBla", enemies).attackType(AttackType.MULTIPLE)
				.maximumTarget(2).build();
		t.compute();

		assertTrue(enemies.get(0).getHealth() < 51 && enemies.get(1).getHealth() < 51);
	}

	@Test
	void BasicMultipleTowerWithMovement() throws InterruptedException {
		List<Enemy> enemies = new LinkedList<>();
		FakeEnemy e = new FakeEnemy(new Position(50, 50), 100);
		FakeEnemy e1 = new FakeEnemy(new Position(51, 50), 100);
		enemies.add(e);
		enemies.add(e1);

		Tower t = new TowerBuilder(new Position(51, 51), 50, 10, 10, "BlaBla", enemies).attackType(AttackType.MULTIPLE)
				.maximumTarget(2).build();
		t.compute();

		e.setPosition(new Position(150, 150));

		t.compute();

		assertTrue(e.getHealth() < 51 && e1.isDead());
	}
	
	@Test
	void BasicMultipleTowerRemoveDeath() throws InterruptedException {
		List<Enemy> enemies = new LinkedList<>();
		FakeEnemy e = new FakeEnemy(new Position(50, 50), 100);
		FakeEnemy e1 = new FakeEnemy(new Position(51, 50), 100);
		FakeEnemy e2 = new FakeEnemy(new Position(50, 50), 100);
		FakeEnemy e3 = new FakeEnemy(new Position(51, 50), 100);
		
		enemies.add(e);
		enemies.add(e1);
		enemies.add(e2);
		enemies.add(e3);

		Tower t = new TowerBuilder(new Position(51, 51), 50, 10, 10, "BlaBla", enemies).attackType(AttackType.MULTIPLE)
				.maximumTarget(2).build();
		t.compute();
		t.compute();
		
		enemies.remove(e); // the enemies dead are removed from list
		enemies.remove(e1);
		
		t.compute();
		t.compute();

		assertTrue(e.isDead() && e1.isDead() && e2.isDead() && e3.isDead());
	}

	@Test
	void BasicMultipleTowerMaximumTargetTest() throws InterruptedException {
		List<Enemy> enemies = new LinkedList<>();
		FakeEnemy e = new FakeEnemy(new Position(50, 50), 100);
		FakeEnemy e1 = new FakeEnemy(new Position(51, 50), 100);
		FakeEnemy e2 = new FakeEnemy(new Position(51, 52), 100);

		enemies.add(e);
		enemies.add(e1);
		enemies.add(e2);

		Tower t = new TowerBuilder(new Position(51, 51), 50, 10, 10, "BlaBla", enemies).attackType(AttackType.MULTIPLE)
				.maximumTarget(2).build();
		t.compute();

		e.setPosition(new Position(150, 150));

		t.compute();

		assertTrue(e.getHealth() < 51 && e1.isDead() && e2.getHealth() < 51);
	}

	@Test
	void AreaTower() throws InterruptedException {
		List<Enemy> enemies = new LinkedList<>();
		FakeEnemy e = new FakeEnemy(new Position(50, 50), 100);
		FakeEnemy e1 = new FakeEnemy(new Position(51, 50), 100);
		enemies.add(e);
		enemies.add(e1);

		Tower t = new TowerBuilder(new Position(51, 51), 100, 9, 10, "tesla", enemies).attackType(AttackType.AREA)
				.damageRange(7).maximumTarget(4).findFirst(() -> {
					return Towers.findFirstEnemyInRange(new Position(51, 51), 9, enemies);
				}).build();
		t.compute();

		assertTrue(e.isDead() && e1.isDead());
	}

	@Test
	void AreaTowerWithMovement() throws InterruptedException {
		List<Enemy> enemies = new LinkedList<>();
		FakeEnemy e = new FakeEnemy(new Position(50, 50), 200);
		FakeEnemy e1 = new FakeEnemy(new Position(51, 50), 200);
		enemies.add(e);
		enemies.add(e1);

		Tower t = new TowerBuilder(new Position(51, 51), 100, 9, 10, "tesla", enemies).attackType(AttackType.AREA)
				.damageRange(7).maximumTarget(4).findFirst(() -> {
					return Towers.findFirstEnemyInRange(new Position(51, 51), 9, enemies);
				}).build();
		t.compute();

		e.setPosition(new Position(1150, 1150));

		t.compute();

		assertTrue(e.getHealth() < 200 && e1.isDead());
	}

	@Test
	void AreaTowerWithMovement2() throws InterruptedException {
		List<Enemy> enemies = new LinkedList<>();
		FakeEnemy e = new FakeEnemy(new Position(50, 50), 200);
		FakeEnemy e1 = new FakeEnemy(new Position(51, 50), 200);
		FakeEnemy e2 = new FakeEnemy(new Position(1151, 1150), 200);

		enemies.add(e);
		enemies.add(e1);
		enemies.add(e2);

		Tower t = new TowerBuilder(new Position(51, 51), 100, 9, 10, "tesla", enemies).attackType(AttackType.AREA)
				.damageRange(7).maximumTarget(4).findFirst(() -> {
					return Towers.findFirstEnemyInRange(new Position(51, 51), 9, enemies);
				}).build();

		t.compute();

		e2.setPosition(e.getPosition());
		e.setPosition(new Position(1150, 1150));

		t.compute();

		assertTrue(e.getHealth() == 100 && e1.isDead() && e2.getHealth() == 100);
	}

	@Test
	void AreaTowerOverTheRange() throws InterruptedException {
		List<Enemy> enemies = new LinkedList<>();
		FakeEnemy e = new FakeEnemy(new Position(60, 51), 100);
		FakeEnemy e1 = new FakeEnemy(new Position(62, 53), 100);
		enemies.add(e);
		enemies.add(e1);

		Tower t = new TowerBuilder(new Position(51, 51), 100, 9, 10, "tesla", enemies).attackType(AttackType.AREA)
				.damageRange(7).maximumTarget(4).findFirst(() -> {
					return Towers.findFirstEnemyInRange(new Position(51, 51), 9, enemies);
				}).build();
		t.compute();

		assertTrue(enemies.get(0).isDead() && enemies.get(1).isDead());
	}

	@Test
	void ConcurrencyAttack() throws InterruptedException {
		List<Enemy> enemies = new LinkedList<>();
		FakeEnemy e = new FakeEnemy(new Position(51, 51), 100);
		enemies.add(e);

		TowerLogic manager = new TowerLogicImpl(enemies, x -> {
		}, x -> true);

		manager.placeTower(DefaultTowers.BASICCANNON, new Position(52, 52));
		manager.placeTower(DefaultTowers.BASICCANNON, new Position(53, 53));

		Thread.sleep(650);

		manager.closeAll();

		assertTrue(e.isDead());
	}
	
	@Test
	void CannonUnmodifiable() throws InterruptedException {
		List<Enemy> enemies = new LinkedList<>();
		enemies.add(new FakeEnemy(new Position(50, 50), 100));
		enemies.add(getGenericEnemy(new Position(51, 51), 100));

		Tower t = CannonFactory.basicCannon(new Position(51, 51), Collections.unmodifiableList(enemies));
		t.compute();
		t.compute();
		t.compute();
		enemies.remove(0);
		t.compute();
		t.compute();
		t.compute();

		assertTrue(enemies.get(0).getHealth() < 51);
	}
}
