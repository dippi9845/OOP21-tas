package main.java.model.enemies;

import main.java.model.Position;

public class RedEnemy implements Enemy {
    
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

}
