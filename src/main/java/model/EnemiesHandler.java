package main.java.model;

import java.util.List;

import main.java.model.enemies.Enemy;

public interface EnemiesHandler {
    
    void setNextWave();
    Enemy spawnTestEnemy();
    boolean isWaveClean();
    int getWave();
    
    List<Enemy> getEnemies();
    
    void removeEnemy(Enemy enemy);
    
}
