package main.java.model.enemies;

import main.java.model.Entity;

public interface Enemy extends Entity {
    
    void moveForward();
    void dealDamage();
    
    boolean isDead();
    double getHealth();

}
