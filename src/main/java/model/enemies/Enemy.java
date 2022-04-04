package main.java.model.enemies;

import main.java.model.Entity;

public interface Enemy extends Entity {
    
    void moveForward();
    void dealDamage(double damage);
    
    boolean isDead();
    double getHealth();

}
