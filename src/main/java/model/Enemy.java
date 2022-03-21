package main.java.model;

public interface Enemy {
    
    void moveForward();
    void dealDamage();
    
    boolean isDead();
    double getHealth();
    Position getPosition();

}
