package main.java.model;

import java.util.ArrayList;
import java.util.List;

import main.java.model.enemies.Enemy;
import main.java.model.enemies.RedEnemy;

public class EnemiesHandlerImpl implements EnemiesHandler {
    
    private final Position enemySpawner;
    private List<Enemy> enemiesList = new ArrayList<Enemy>();
    private int actualWave;
    
    public EnemiesHandlerImpl(Position enemySpawnerPosition) {
        this.enemySpawner = enemySpawnerPosition;
        this.actualWave = 0;
        
        setNextWave();
    }

    private void setNextWave() {
        this.actualWave++;
        spawnTestEnemy();
        //TODO: manca l'implementazione dei nemici che spawnano
        
    }
    
    private void spawnTestEnemy() {
        //TODO: questo metodo serve solo per testare il giusto spawn dei nemici. Da cancellare!!!
        this.enemiesList.add(new RedEnemy(enemySpawner));
        System.out.println("Test Enemy spawned");
        
    }
    
    @Override
    public void moveEnemies() {
        for (Enemy enemy: this.enemiesList) {
            enemy.moveForward();
        }
    }

    @Override
    public int getWave() {
        return this.actualWave;
    }

}
