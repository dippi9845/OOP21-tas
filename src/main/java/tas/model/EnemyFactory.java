package main.java.tas.model;

import main.java.tas.model.enemies.Enemy;

public interface EnemyFactory {
    
    Enemy spawnRedEnemy();
    Enemy spawnPinkEnemy();
    
}
