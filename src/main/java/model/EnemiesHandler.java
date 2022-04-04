package main.java.model;

import java.util.List;

import main.java.model.enemies.Enemy;

public interface EnemiesHandler {
    
    void setNextWave();
    Enemy spawnTestEnemy();
    boolean cleanWave();
    int getWave();
    List<Enemy> getEnemies();
    
}
