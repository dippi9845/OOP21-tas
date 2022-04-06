package main.java.model;

import java.util.ArrayList;
import java.util.List;

import main.java.model.enemies.Enemy;
import main.java.model.enemies.RedEnemy;
import main.java.utils.Position;

public class EnemiesHandlerImpl implements EnemiesHandler {
    
    private List<Enemy> enemiesList = new ArrayList<Enemy>();
    private int actualWave;
    private final List<Position> nodesPosition;
    
    public EnemiesHandlerImpl(List<Position> nodesPosition) throws IllegalArgumentException {
        if (nodesPosition.isEmpty()) {
            throw new IllegalArgumentException("@nodesPosition can't be an empty array!");
        }
        this.nodesPosition = nodesPosition;
        this.actualWave = 0;
        
        setNextWave();
    }
    
    @Override
    public Enemy spawnEnemy() {
        Enemy enemy = new RedEnemy(this.nodesPosition);
        this.enemiesList.add(enemy);
        
        return enemy;
    }
    
    @Override
    public void removeEnemy(Enemy enemy) {
        this.enemiesList.remove(enemy);
    }
    
    @Override
    public void setNextWave() {
        this.actualWave++;
    }

    @Override
    public boolean isWaveClean() {
        return enemiesList.isEmpty();
    }
    
    @Override
    public int getWave() {
        return this.actualWave;
    }

    @Override
    public List<Enemy> getEnemies() {
        return enemiesList;
    }

}
