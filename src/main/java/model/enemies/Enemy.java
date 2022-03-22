package main.java.model.enemies;

import main.java.model.Position;

public interface Enemy {
    
    void moveForward();
    void dealDamage();
    
    boolean isDead();
    double getHealth();
    Position getPosition();

}
