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

    @Override
    public void setNextWave() {
        this.actualWave++;
    }
    
    @Override
    public Enemy spawnTestEnemy() {
        //TODO: questo metodo serve solo per testare il giusto spawn dei nemici. Da cancellare!!!
        Enemy enemy = new RedEnemy(enemySpawner);
        this.enemiesList.add(enemy);
        
        System.out.println("Test Enemy spawned");
        
        return enemy;
        
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

    @Override
    public List<Enemy> getEnemies() {
        return enemiesList;
    }

    @Override
    public boolean cleanWave() {
        return enemiesList.isEmpty();
    }

}
