package main.java.tas.model;

import java.util.ArrayList;
import java.util.List;

import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;

public class EnemiesLogicImpl implements EnemiesLogic {
    
    private final List<Enemy> aliveEnemiesList = new ArrayList<Enemy>();
    private int actualWave;
    private final EnemyFactory enemyFactory;

    public EnemiesLogicImpl(List<Position> nodesPosition) {
        this.enemyFactory = new EnemyFactoryImpl(nodesPosition);
        this.actualWave = 0;
    }

    @Override
    public Enemy spawnEnemy() {
        Enemy enemy = this.enemyFactory.spawnRedEnemy();
        this.aliveEnemiesList.add(enemy);
        
        return enemy;
    }

    @Override
    public void removeEnemy(Enemy enemy) {
        this.aliveEnemiesList.remove(enemy);
    }

    @Override
    public boolean isWaveClean() {
        return aliveEnemiesList.isEmpty();
    }

    @Override
    public int getWave() {
        return this.actualWave;
    }

    @Override
    public void setNextWave() {
        this.actualWave++;
    }

    @Override
    public List<Enemy> getEnemies() {
        return aliveEnemiesList;
    }

    @Override
    public boolean areEnemiesInQueue() {
        return !this.enemyToBeSpawned.isEmpty();
    }
    
}
