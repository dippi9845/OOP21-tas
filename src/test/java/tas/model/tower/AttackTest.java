package test.java.tas.model.tower;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import main.java.tas.controller.TowerLogic;
import main.java.tas.controller.TowerLogicImpl;
import main.java.tas.model.enemies.Enemy;
import main.java.tas.model.tower.AttackType;
import main.java.tas.model.tower.Builder;
import main.java.tas.model.tower.Tower;
import main.java.tas.utils.Position;

/**
 * Class for testing every type of tower that attack
 *
 */
class AttackTest {
	
	private boolean SpendMoney(final int money) {
		return true;
	}
	
	@Test
	void BasicTower() throws InterruptedException {
		List<Enemy> enemies = new LinkedList<>();
		enemies.add(new FakeEnemy(new Position(50, 50), 100));
		
		Tower t = new Builder(new Position(51, 51), 50, 10, 10, "BlaBla", enemies).build();
		t.compute();
		t.compute();
		
		assertTrue(enemies.get(0).getHealth() < 51);
	}
	
	@Test
	void BasicTowerWithMovement() throws InterruptedException {
		List<Enemy> enemies = new LinkedList<>();
		FakeEnemy e = new FakeEnemy(new Position(50, 50), 100);
		enemies.add(e);
		
		Tower t = new Builder(new Position(51, 51), 50, 10, 10, "BlaBla", enemies).build();
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
		
		Tower t = new Builder(new Position(51, 51), 50, 10, 10, "BlaBla", enemies)
					  .attackType(AttackType.MULTIPLE)
					  .maximumTarget(2)
					  .build();
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
		
		Tower t = new Builder(new Position(51, 51), 50, 10, 10, "BlaBla", enemies)
					  .attackType(AttackType.MULTIPLE)
					  .maximumTarget(2)
					  .build();
		t.compute();
		
		e.setPosition(new Position(150, 150));
		
		t.compute();
		
		assertTrue(enemies.get(0).getHealth() < 51 && enemies.get(1).isDead());
	}
	
	@Test
	void AreaTower() throws InterruptedException {
		
	}
}
