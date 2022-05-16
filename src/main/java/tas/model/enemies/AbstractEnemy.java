package main.java.tas.model.enemies;

import java.awt.Dimension;
import java.util.List;

import main.java.tas.utils.Position;

/**
 * An abstract class that models an Enemy
 */
public abstract class AbstractEnemy implements Enemy {
    
    private Dimension bodyDimension;
    private Position actualPosition;
    private double health;
    private int money;
    private int damage;
    private double speed;
    
    private int reachedNode;
    private List<Position> nodesPosition;
    
    /**
     * Set up the enemy with the given parameters
     * @param nodesPosition the nodes that the enemy will have to travel 
     * @param health the health of the enemy
     * @param money the money that the enemy can drop
     * @param damage the damage that the enemy can deal
     * @param speed the speed of the enemy (pixels/seconds)
     * @param bodyDimension the dimension of the enemy
     * @return the enemy
     * @throws IllegalArgumentException if @param nodesPosition is empty
     */
    public AbstractEnemy create(List<Position> nodesPosition, double health, int money, int damage, double speed, Dimension bodyDimension) throws IllegalArgumentException  {
        if (nodesPosition.isEmpty()) {
            throw new IllegalArgumentException("@param nodesPosition can't be an empty array!");
        }
        
        this.nodesPosition = nodesPosition;
        this.reachedNode = 0;
        this.actualPosition = new Position(this.nodesPosition.get(this.reachedNode).getX(), this.nodesPosition.get(this.reachedNode).getY());
        
        this.bodyDimension = bodyDimension;
        this.health = health;
        this.money = money;
        this.damage = damage;
        this.speed = speed;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public Position getPosition() {
        return this.actualPosition;
    }

    /** {@inheritDoc} */
    @Override
    public Dimension getBodyDimension() {
        return this.bodyDimension;
    }

    /** {@inheritDoc} */
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

    /** {@inheritDoc} */
    @Override
    public void dealDamage(double damage) {
        this.health -= damage;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isDead() {
        return this.health <= 0;
    }

    /** {@inheritDoc} */
    @Override
    public double getHealth() {
        return this.health;
    }

    /** {@inheritDoc} */
    @Override
    public int getMoney() {
        return this.money;
    }

    /** {@inheritDoc} */
    @Override
    public int getDamage() {
        return this.damage;
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasCompletedPath() {
        return this.reachedNode + 1 >= this.nodesPosition.size();
    }

}
