package main.java.tas.model;

import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Test;

import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;

public class EnemiesHandlerImplTest {

    @Test
    public void testSpawnMultipleEnemies() {
        final EnemyFactory enemiesHanlder = new EnemyFactoryImpl(Arrays.asList(new Position(0, 0)));
        final int enemiesNumber = 1000;
        
        for (int i=0; i < enemiesNumber; i++) {
            enemiesHanlder.spawnEnemy();
        }
        
        assertEquals(enemiesHanlder.getEnemies().size(), enemiesNumber);
    }

    @Test
    public void testRemoveEnemies() {
        final EnemyFactory enemiesHanlder = new EnemyFactoryImpl(Arrays.asList(new Position(0, 0)));
        final int enemiesNumber = 100;
        final int enemyEliminationRate = 2;
        
        for (int i=0; i < enemiesNumber; i++) {
            Enemy enemy = enemiesHanlder.spawnEnemy();
            if (i % enemyEliminationRate == 0) {
                enemiesHanlder.removeEnemy(enemy);
            }
        }
        assertEquals(enemiesHanlder.getEnemies().size(), Math.ceil(enemiesNumber/enemyEliminationRate), 0.5);
    }
    
    @Test
    public void testWaveLogic() {
        final int wavesToSkip = 15;
        final EnemyFactory enemiesHanlder = new EnemyFactoryImpl(Arrays.asList(new Position(0, 0)));
        
        for (int i=0; i < wavesToSkip; i++) {
            enemiesHanlder.setNextWave();
        }
        
        assertEquals(enemiesHanlder.getWave(), wavesToSkip);
    }

}
