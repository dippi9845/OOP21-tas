package main.java.model;

import java.util.List;

import main.java.model.enemies.Enemy;

public interface EnemiesHandler {
    
    
    Enemy spawnEnemy();
    void removeEnemy(Enemy enemy);
    
    boolean isWaveClean();
    int getWave();
    void setNextWave();
    List<Enemy> getEnemies();
 
}
