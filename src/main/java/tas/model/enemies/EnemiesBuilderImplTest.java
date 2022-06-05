package main.java.tas.model.enemies;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import main.java.tas.utils.Position;

public class EnemiesBuilderImplTest {

    @Test
    public void testSpawnMultipleEnemies()
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        final EnemyBuilder enemiesFactory = new EnemyBuilderImpl(Arrays.asList(new Position(0, 0)));
        final List<Enemy> enemiesList = new ArrayList<Enemy>();
        final int enemiesNumber = 1000; // spawning x enemies of each type
        int enemyTypeNumber;

        Method[] allMethods = enemiesFactory.getClass().getDeclaredMethods();
        enemyTypeNumber = allMethods.length;
        for (Method method : allMethods) {
            if (!method.getName().contains("spawn")) {
                enemyTypeNumber--;
                continue;
            }
            if (Modifier.isPublic(method.getModifiers())) {
                for (int i = 0; i < enemiesNumber; i++) {
                    enemiesList.add((Enemy) method.invoke(enemiesFactory));
                }
            }
        }

        assertEquals(enemiesList.size(), enemiesNumber * enemyTypeNumber);
    }

}
