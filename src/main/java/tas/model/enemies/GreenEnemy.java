package main.java.tas.model.enemies;

import java.awt.Dimension;
import java.util.List;

import main.java.tas.utils.GameSpecs;
import main.java.tas.utils.Position;

/**
 * Class that models an {@link AbstractEnemy}
 */
public class GreenEnemy extends AbstractEnemy {
    
    private final Dimension bodyDimension = new Dimension(100, 100);
    private double health = 10;
    private final int money = 100;
    private final int damage = 40;
    private static final double SPEED_PER_SECOND = 30;
    private final double speed = SPEED_PER_SECOND / GameSpecs.TICKS_PER_SECOND;
    
    /**
     * Contractor of the GreenEnemy
     * @param nodesPosition the nodes that the enemy will have to travel 
     */
    public GreenEnemy(List<Position> nodesPosition) {
        super.create(nodesPosition, this.health, this.money, this.damage, this.speed, this.bodyDimension);
    }

}
