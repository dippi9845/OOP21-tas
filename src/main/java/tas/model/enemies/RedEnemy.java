package main.java.tas.model.enemies;

import java.awt.Dimension;
import java.util.List;

import main.java.tas.utils.Position;
import main.java.tas.utils.GameSpecs;

public class RedEnemy implements Enemy {
    
    private final Dimension bodyDimension = new Dimension(100, 100);
    private Position actualPosition;
    private double health = 1;
    private final int money = 50;
    private final int damage = 10;
    private static final double SPEED_PER_SECOND = 60;
    private final double speed = SPEED_PER_SECOND / GameSpecs.TICKS_PER_SECOND;
    
    private int reachedNode = 0;
    private final List<Position> nodesPosition;
    
    public RedEnemy(List<Position> nodesPosition) throws IllegalArgumentException  {
        if (nodesPosition.isEmpty()) {
            throw new IllegalArgumentException("@nodesPosition can't be an empty array!");
        }
        
        this.nodesPosition = nodesPosition;
        this.actualPosition = this.nodesPosition.get(this.reachedNode);
    }

    @Override
    public void moveForward() {
        double distanceToBeTraveled = this.speed;
        while (distanceToBeTraveled > 0 && (this.nodesPosition.size() - 1 > this.reachedNode)) {
            Position nextPos = this.nodesPosition.get(this.reachedNode+1);
            
            // checks if the distance to the next node is higher than the speed x tick to avoid overshooting the objective
            if (Position.findDistance(this.actualPosition, nextPos) > this.speed) {
                double angle = Math.atan2(nextPos.getY()-this.actualPosition.getY(),nextPos.getX()-this.actualPosition.getX());
                double newX = this.actualPosition.getX() + this.speed * Math.cos( angle );
                double newY = this.actualPosition.getY() + this.speed * Math.sin( angle );
                
                this.actualPosition.setPosition(newX, newY);
                distanceToBeTraveled = 0;
            } else {
                distanceToBeTraveled -= Position.findDistance(this.actualPosition, nextPos);
                this.actualPosition.setPosition(nextPos);
                this.reachedNode++;
            }
        }
    }

    @Override
    public void dealDamage(double damage) {
        this.health -= damage;
    }

    @Override
    public double getHealth() {
        return this.health;
    }

    @Override
    public Position getPosition() {
        return this.actualPosition;
    }

    @Override
    public boolean isDead() {
        return this.health <= 0;
    }

    @Override
    public Dimension getBodyDimension() {
        return bodyDimension;
    }

    @Override
    public int getMoney() {
        return money;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public boolean hasCompletedPath() {
        return this.reachedNode + 1 >= this.nodesPosition.size();
    }

}
