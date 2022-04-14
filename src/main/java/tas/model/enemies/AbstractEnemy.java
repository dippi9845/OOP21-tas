package main.java.tas.model.enemies;

import java.awt.Dimension;
import java.util.List;

import main.java.tas.utils.Position;

public class AbstractEnemy implements Enemy {
    
    private final Dimension bodyDimension = new Dimension(100, 100);
    private Position actualPosition;
    private double health;
    private int money;
    private int damage;
    private double speed;
    
    private int reachedNode;
    private List<Position> nodesPosition;
    
    public AbstractEnemy create(List<Position> nodesPosition, double health, int money, int damage, double speed) throws IllegalArgumentException  {
        if (nodesPosition.isEmpty()) {
            throw new IllegalArgumentException("@nodesPosition can't be an empty array!");
        }
        
        this.nodesPosition = nodesPosition;
        this.reachedNode = 0;
        this.actualPosition = new Position(this.nodesPosition.get(this.reachedNode).getX(), this.nodesPosition.get(this.reachedNode).getY());
        
        this.health = health;
        this.money = money;
        this.damage = damage;
        this.speed = speed;
        return this;
    }

    @Override
    public Position getPosition() {
        return this.actualPosition;
    }

    @Override
    public Dimension getBodyDimension() {
        return this.bodyDimension;
    }

    @Override
    public void moveForward() {
        double distanceToBeTraveled = this.speed;
        while (distanceToBeTraveled > 0 && (this.nodesPosition.size() - 1 > this.reachedNode)) {
            Position nextPos = this.nodesPosition.get(this.reachedNode+1);
            
            // checks if the distance to the next node is higher than the speed x tick to avoid overshooting the objective
            if (Position.findDistance(this.actualPosition, nextPos) > this.speed) {
                double angle = Math.atan2(nextPos.getY()-this.actualPosition.getY(),nextPos.getX()-this.actualPosition.getX());
                double newX = this.actualPosition.getX() + this.speed * Math.cos(angle);
                double newY = this.actualPosition.getY() + this.speed * Math.sin(angle);
                
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
    public boolean isDead() {
        return this.health <= 0;
    }

    @Override
    public double getHealth() {
        return this.health;
    }

    @Override
    public int getMoney() {
        return this.money;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public boolean hasCompletedPath() {
        return this.reachedNode + 1 >= this.nodesPosition.size();
    }

}
