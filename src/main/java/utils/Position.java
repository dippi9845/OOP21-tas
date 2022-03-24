package main.java.utils;

public class Position {

    private double X;
    private double Y;
    
    public Position(double X, double Y) {
        setPosition(X, Y);
    }
    
    public void setPosition(double X, double Y) {
        this.X = X;
        this.Y = Y;
    }
    
    public double getX() {
        return this.X;
    }
    
    public double getY() {
        return this.Y;
    }
    
}
