package test.java.tas.model;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.util.Arrays;

import org.junit.Test;

import main.java.tas.model.enemies.Enemy;
import main.java.tas.model.enemies.GenericEnemy;
import main.java.tas.utils.Position;

public class GenricEnemyTest {

	@Test
	public void testGetPosition() {
		final Position pos = new Position(10, 10);
		final Enemy enemy = new GenericEnemy(Arrays.asList(pos), 1, 50, 10, 60, new Dimension(100, 100), "redEnemy");

		assertEquals(pos.toString(), enemy.getPosition().toString());
	}

	@Test
	public void testMoveForward() {
		final Position pos1 = new Position(10, 10);
		final Position pos2 = new Position(20, 20);
		final Position pos3 = new Position(10, 10);
		final Enemy enemy1 = new GenericEnemy(Arrays.asList(pos1, pos2), 1, 50, 10, 60, new Dimension(100, 100),
				"redEnemy");
		final Enemy enemy2 = new GenericEnemy(Arrays.asList(pos1, pos3), 1, 50, 10, 60, new Dimension(100, 100),
				"redEnemy");

		enemy1.moveForward();
		enemy2.moveForward();

		assertNotEquals(pos1.toString(), enemy1.getPosition().toString());
		assertEquals(pos1.toString(), enemy2.getPosition().toString());
	}

	@Test
	public void testDealDamage() {
		final Position pos = new Position(10, 10);
		final Enemy enemy = new GenericEnemy(Arrays.asList(pos), 1, 50, 10, 60, new Dimension(100, 100), "redEnemy");
		final double maxHealth = enemy.getHealth();
		final double damage = 5;

		enemy.dealDamage(5);

		assertEquals(maxHealth - damage, enemy.getHealth(), 0.01);
	}

	@Test
	public void testIsDead() {
		final Position pos = new Position(10, 10);
		final Enemy enemy = new GenericEnemy(Arrays.asList(pos), 1, 50, 10, 60, new Dimension(100, 100), "redEnemy");

		assertFalse(enemy.isDead());

		enemy.dealDamage(enemy.getHealth());

		assertTrue(enemy.isDead());
	}

	@Test
	public void testHasCompletedPath() {
		final Position pos1 = new Position(10, 10);
		final Position pos2 = new Position(20, 20);
		final Enemy enemy1 = new GenericEnemy(Arrays.asList(pos1), 1, 50, 10, 60, new Dimension(100, 100), "redEnemy");
		final Enemy enemy2 = new GenericEnemy(Arrays.asList(pos1, pos2), 1, 50, 10, 60, new Dimension(100, 100),
				"redEnemy");

		assertFalse(enemy2.hasCompletedPath());
		assertTrue(enemy1.hasCompletedPath());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreationNoNode() {
		new GenericEnemy(Arrays.asList(), 1, 50, 10, 60, new Dimension(100, 100), "redEnemy");
	}

}
