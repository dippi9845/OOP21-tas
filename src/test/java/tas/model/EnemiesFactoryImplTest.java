package test.java.tas.model;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import main.java.tas.model.EnemyFactory;
import main.java.tas.model.EnemyFactoryImpl;
import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;

public class EnemiesFactoryImplTest {

    @Test
    public void testSpawnMultipleEnemies() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        final EnemyFactory enemiesFactory = new EnemyFactoryImpl(Arrays.asList(new Position(0, 0)));
        final List<Enemy> enemiesList = new ArrayList<Enemy>();
        final int enemiesNumber = 1000; // spawning x enemies of each type
        
        Method[] allMethods = enemiesFactory.getClass().getDeclaredMethods();
        for (Method method : allMethods) {
            if (Modifier.isPublic(method.getModifiers())) {
                for (int i=0; i < enemiesNumber; i++) {
                    System.out.println(method.invoke(enemiesFactory).getClass());
                    enemiesList.add((Enemy) method.invoke(enemiesFactory));
                }
            }
        }
        
        assertEquals(enemiesList.size(), enemiesNumber * allMethods.length);
    }
    
}
