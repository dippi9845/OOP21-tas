package main.java.tas.model.enemies;

import java.util.List;

import main.java.tas.utils.Position;
import main.java.tas.utils.GameSpecs;

public class RedEnemy extends AbstractEnemy {
    
    private double health = 1;
    private final int money = 50;
    private final int damage = 10;
    private static final double SPEED_PER_SECOND = 60;
    private final double speed = SPEED_PER_SECOND / GameSpecs.TICKS_PER_SECOND;
    
    public RedEnemy(List<Position> nodesPosition) {
        super.create(nodesPosition, this.health, this.money, this.damage, this.speed);
    }

}
