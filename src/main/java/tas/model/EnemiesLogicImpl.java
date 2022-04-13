package main.java.tas.model;

import java.util.ArrayList;
import java.util.List;

import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;

public class EnemiesLogicImpl implements EnemiesLogic {
    
    private final List<Enemy> aliveEnemiesList = new ArrayList<Enemy>();
    private int actualWave;
    private final WaveFactory waveFactory;
    private List<Enemy> enemyToBeSpawned = new ArrayList<Enemy>();

    public EnemiesLogicImpl(List<Position> nodesPosition) {
        this.waveFactory = new WaveFactoryImpl(nodesPosition);
        this.actualWave = 0;
    }

    @Override
    public Enemy spawnEnemy() throws IllegalArgumentException {
        if (this.enemyToBeSpawned.isEmpty()) {
            throw new IllegalArgumentException("There are no enemies to be spawn");
        }
        
        Enemy enemy = this.enemyToBeSpawned.remove(0);
        this.aliveEnemiesList.add(enemy);

        return enemy;
    }

    @Override
    public void removeEnemy(Enemy enemy) {
        this.aliveEnemiesList.remove(enemy);
    }

    @Override
    public boolean isWaveClean() {
        return this.aliveEnemiesList.isEmpty() && this.enemyToBeSpawned.isEmpty();
    }

    @Override
    public int getWave() {
        return this.actualWave;
    }

    @Override
    public void setNextWave() {
        this.actualWave++;
        this.enemyToBeSpawned = this.waveFactory.createEnemiesToBeSpawn(actualWave);
        System.out.println("Wave increased to: " + this.actualWave);
    }

    @Override
    public List<Enemy> getEnemies() {
        return aliveEnemiesList;
    }

    @Override
    public boolean areEnemiesOnBoard() {
        return !this.aliveEnemiesList.isEmpty();
    }
    
    @Override
    public boolean areEnemiesInQueue() {
        return !this.enemyToBeSpawned.isEmpty();
    }
    
}
