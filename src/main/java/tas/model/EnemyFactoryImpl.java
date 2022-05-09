package main.java.tas.model;

import java.util.List;

import main.java.tas.model.enemies.Enemy;
import main.java.tas.model.enemies.GreenEnemy;
import main.java.tas.model.enemies.PinkEnemy;
import main.java.tas.model.enemies.RedEnemy;
import main.java.tas.utils.Position;

/**
 * Class that implements {@link EnemyFactory}
 */
public class EnemyFactoryImpl implements EnemyFactory {
    
    private final List<Position> nodesPosition;
    
    /**
     * Constructor that creates a factory for the enemies 
     * @param nodesPosition is a list with the nodes that the enemies will have to travel 
     * @throws IllegalArgumentException if the @param nodesPosition is empty
     */
    public EnemyFactoryImpl(List<Position> nodesPosition) throws IllegalArgumentException {
        if (nodesPosition.isEmpty()) {
            throw new IllegalArgumentException("@nodesPosition can't be an empty array!");
        }
        this.nodesPosition = nodesPosition;
    }
    
    /** {@inheritDoc} */
    @Override
    public Enemy spawnRedEnemy() {
        return new RedEnemy(this.nodesPosition);
    }

    /** {@inheritDoc} */
    @Override
    public Enemy spawnGreenEnemy() {
        return new GreenEnemy(this.nodesPosition);
    }

    /** {@inheritDoc} */
    @Override
    public Enemy spawnPinkEnemy() {
        return new PinkEnemy(this.nodesPosition);
    }
   
}
