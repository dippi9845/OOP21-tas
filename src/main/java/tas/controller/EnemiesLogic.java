package main.java.tas.controller;

import java.util.List;

import main.java.tas.model.enemies.Enemy;

public interface EnemiesLogic {

    Enemy spawnEnemy();
    void removeEnemy(Enemy enemy);
    
    boolean isWaveClean();
    int getWave();
    void setNextWave();
    List<Enemy> getEnemies();
    boolean areEnemiesOnBoard();
    boolean areEnemiesInQueue();
    
}
