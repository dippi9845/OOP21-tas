package test.java.tas.model;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import main.java.tas.model.enemy.Enemy;
import main.java.tas.model.enemy.EnemyBuilder;
import main.java.tas.model.enemy.EnemyBuilderImpl;
import main.java.tas.utils.Position;

public class EnemiesBuilderImplTest {

	@Test
	public void testSpawnMultipleEnemies()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		final EnemyBuilder enemiesFactory = new EnemyBuilderImpl(Arrays.asList(new Position(0, 0)));
		final List<Enemy> enemiesList = new ArrayList<Enemy>();
		final int enemiesNumber = 1000; // spawning x enemies of each type
		int enemyTypeNumber = 0;

		Method[] allMethods = enemiesFactory.getClass().getDeclaredMethods();
		for (Method method : allMethods) {
			if (!method.getName().contains("spawn")) {
				continue;
			}
			if (Modifier.isPublic(method.getModifiers())) {
				enemyTypeNumber++;
				for (int i = 0; i < enemiesNumber; i++) {
					enemiesList.add((Enemy) method.invoke(enemiesFactory));
				}
			}
		}
		System.out.println(enemyTypeNumber);
		assertEquals(enemiesList.size(), enemiesNumber * enemyTypeNumber);
	}

}
