package main.java.tas.model;

public interface TimeCurve {
    
    boolean isTimeForAction(int x)  throws IllegalArgumentException;
    void actionPerformed();
}
