package main.java.model.enemies;

import java.awt.Dimension;

import main.java.utils.Position;

public class RedEnemy implements Enemy {
    
    private final static Dimension DEFAULT_DIMENSION = new Dimension(100, 100);
    private Position actualPosition;
    private double health = 1;
    private static final int SPEED = 5;
    
    public RedEnemy(Position position) {
        this.actualPosition = position;
    }

    @Override
    public void moveForward() {
        this.actualPosition.setPosition(this.actualPosition.getX() + SPEED, this.actualPosition.getY());
        System.out.println("Enemy moving");
    }

    @Override
    public void dealDamage() {
        // TODO Auto-generated method stub

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
    public Dimension getDefaultDimension() {
        return DEFAULT_DIMENSION;
    }

}
