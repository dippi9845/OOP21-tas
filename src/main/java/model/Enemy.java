package main.java.model;

public interface Enemy {
    
    void moveForward();
    void dealDamage();
    
    double getHealth();
    Position getPosition();

}
