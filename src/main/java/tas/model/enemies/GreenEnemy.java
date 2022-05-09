package main.java.tas.model.enemies;

import java.util.List;

import main.java.tas.utils.GameSpecs;
import main.java.tas.utils.Position;

public class GreenEnemy extends AbstractEnemy {
    
    private double health = 10;
    private final int money = 100;
    private final int damage = 40;
    private static final double SPEED_PER_SECOND = 30;
    private final double speed = SPEED_PER_SECOND / GameSpecs.TICKS_PER_SECOND;
    
    public GreenEnemy(List<Position> nodesPosition) {
        super.create(nodesPosition, this.health, this.money, this.damage, this.speed);
    }

}
