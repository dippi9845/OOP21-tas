package main.java.tas.model.enemies;

import main.java.tas.model.Entity;

public interface Enemy extends Entity {
    
    void moveForward();
    void dealDamage(double damage);
    
    boolean isDead();
    double getHealth();
    int getMoney();
    int getDamage();
    boolean hasCompletedPath();

}
