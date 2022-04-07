package main.java.tas.utils;

public class Position {

    private double X;
    private double Y;
    
    public Position(double X, double Y) {
        setPosition(X, Y);
    }
    
    public void setPosition(Position pos) {
        setPosition(pos.getX(), pos.getY());
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
    
    public static double findDistance(Position pos1, Position pos2) {
        return Math.hypot(pos1.getX()-pos2.getX(), pos1.getY()-pos2.getY());
    }
    
    
    @Override
    public String toString() {
        return new String(X + " - " + Y);
    }
    
}
