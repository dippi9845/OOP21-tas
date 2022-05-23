package main.java.tas.model.enemies;

import java.awt.Dimension;
import java.util.List;

import main.java.tas.utils.Position;

/**
 * Class that implements {@link EnemyBuilder}
 */
public class EnemyBuilderImpl implements EnemyBuilder {
    
    private final List<Position> nodesPosition;
    
    /**
     * Constructor that creates a factory for the enemies 
     * @param nodesPosition is a list with the nodes that the enemies will have to travel 
     * @throws IllegalArgumentException if the @param nodesPosition is empty
     */
    public EnemyBuilderImpl(List<Position> nodesPosition) throws IllegalArgumentException {
        if (nodesPosition.isEmpty()) {
            throw new IllegalArgumentException("@nodesPosition can't be an empty array!");
        }
        this.nodesPosition = nodesPosition;
    }
    
    /** {@inheritDoc} */
    @Override
    public Enemy spawnRedEnemy() {
        return new GenericEnemy(this.nodesPosition, 1, 50, 10, 60, new Dimension(100, 100), "redEnemy");
    }

    /** {@inheritDoc} */
    @Override
    public Enemy spawnGreenEnemy() {
        return new GenericEnemy(this.nodesPosition, 10, 100, 40, 30, new Dimension(100, 100), "greenEnemy");
    }

    /** {@inheritDoc} */
    @Override
    public Enemy spawnPinkEnemy() {
        return new GenericEnemy(this.nodesPosition, 2, 100, 10, 90, new Dimension(100, 100), "pinkEnemy");
    }
   
}
