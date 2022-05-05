package main.java.tas.controller;

import java.util.ArrayList;
import java.util.List;

import main.java.tas.model.WaveFactory;
import main.java.tas.model.WaveFactoryImpl;
import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;

public class EnemiesLogicImpl implements EnemiesLogic {
    
    private final List<Enemy> aliveEnemiesList = new ArrayList<Enemy>();
    private int actualWave;
    private final WaveFactory waveFactory;
    private List<Enemy> enemyToBeSpawned = new ArrayList<Enemy>();

    /**
     * Constructor that create
     * @param nodesPosition is a list with the nodes that the enemies will have to travel 
     */
    public EnemiesLogicImpl(List<Position> nodesPosition) {
        this.waveFactory = new WaveFactoryImpl(nodesPosition);
        this.actualWave = 0;
    }

    /** {@inheritDoc} */
    @Override
    public Enemy spawnEnemy() {
        if (this.enemyToBeSpawned.isEmpty()) {
            return null;
        }
        
        Enemy enemy = this.enemyToBeSpawned.remove(0);
        this.aliveEnemiesList.add(enemy);

        return enemy;
    }

    /** {@inheritDoc} */
    @Override
    public void removeEnemy(Enemy enemy) throws NoSuchFieldException {
        if (!this.aliveEnemiesList.contains(enemy)) {
            throw new NoSuchFieldException("This enemy is not alive");
        }
        this.aliveEnemiesList.remove(enemy);
    }

    /** {@inheritDoc} */
    @Override
    public boolean isWaveClean() {
        return this.aliveEnemiesList.isEmpty() && this.enemyToBeSpawned.isEmpty();
    }

    /** {@inheritDoc} */
    @Override
    public int getWave() {
        return this.actualWave;
    }

    /** {@inheritDoc} */
    @Override
    public void setNextWave() {
        this.actualWave++;
        this.enemyToBeSpawned = this.waveFactory.createEnemiesToBeSpawn(actualWave);
        System.out.println("Wave increased to: " + this.actualWave);
    }

    /** {@inheritDoc} */
    @Override
    public List<Enemy> getEnemies() {
        return aliveEnemiesList;
    }

    /** {@inheritDoc} */
    @Override
    public boolean areEnemiesOnBoard() {
        return !this.aliveEnemiesList.isEmpty();
    }
    
    /** {@inheritDoc} */
    @Override
    public boolean areEnemiesInQueue() {
        return !this.enemyToBeSpawned.isEmpty();
    }
    
}
