package main.java.tas.model.enemies;

import java.awt.Dimension;
import java.util.List;

import main.java.tas.utils.Position;
import main.java.tas.utils.GameSpecs;

/**
 * Class that models an {@link AbstractEnemy}
 */
public class RedEnemy extends AbstractEnemy {
    
    private final Dimension bodyDimension = new Dimension(100, 100);
    private double health = 1;
    private final int money = 50;
    private final int damage = 10;
    private static final double SPEED_PER_SECOND = 60;
    private final double speed = SPEED_PER_SECOND / GameSpecs.TICKS_PER_SECOND;
    
    /**
     * Contractor of the RedEnemy
     * @param nodesPosition the nodes that the enemy will have to travel 
     */
    public RedEnemy(List<Position> nodesPosition) {
        super.create(nodesPosition, this.health, this.money, this.damage, this.speed, this.bodyDimension);
    }

    @Override
    public String getImageName() {
        return "redEnemy";
    }

}
