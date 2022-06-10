package test.java.tas.model;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import main.java.tas.model.enemy.EnemyFactory;
import main.java.tas.model.enemy.EnemyFactoryImpl;
import main.java.tas.utils.Position;

public class EnemyFactoryImplTest {

	@Test
	public void testCreateEnemiesToBeSpawn() {
		EnemyFactory enemyFactory = new EnemyFactoryImpl(Arrays.asList(new Position(0, 0)));

		assertFalse(enemyFactory.createEnemiesToBeSpawn(1).isEmpty());
	}

}
