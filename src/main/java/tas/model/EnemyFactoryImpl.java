package main.java.tas.model;

import java.util.List;

import main.java.tas.model.enemies.Enemy;
import main.java.tas.model.enemies.RedEnemy;
import main.java.tas.utils.Position;

public class EnemyFactoryImpl implements EnemyFactory {
    
    private final List<Position> nodesPosition;
    
    public EnemyFactoryImpl(List<Position> nodesPosition) throws IllegalArgumentException {
        if (nodesPosition.isEmpty()) {
            throw new IllegalArgumentException("@nodesPosition can't be an empty array!");
        }
        this.nodesPosition = nodesPosition;
    }
    
    @Override
    public Enemy spawnRedEnemy() {
        return new RedEnemy(this.nodesPosition);
    }
   
}
