package main.java.tas.utils;

/**
 * Class that models a position
 */
public class Position {

    private double x;
    private double y;
    
    /**
     * Constructor that set up the position
     * @param x the first coordinate
     * @param y the second coordinate
     */
    public Position(double x, double y) {
        setPosition(x, y);
    }
    
    /**
     * Allows to overwrite the old position
     * @param pos the new position
     */
    public void setPosition(Position pos) {
        setPosition(pos.getX(), pos.getY());
    }
    
    /**
     * Allows to overwrite the old position
     * @param x the first coordinate
     * @param y the second coordinate
     */
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * @return the first coordinate
     */
    public double getX() {
        return this.x;
    }
    
    /**
     * @return the second coordinate
     */
    public double getY() {
        return this.y;
    }
    
    /**
     * Calculate the distance between 2 coordinates
     * @param pos1 the first position
     * @param pos2 the second position
     * @return the distance between the 2 coordinate
     */
    public static double findDistance(Position pos1, Position pos2) {
        return Math.hypot(pos1.getX()-pos2.getX(), pos1.getY()-pos2.getY());
    }
    
    /** {@inheritDoc} */
    @Override
    public String toString() {
        return new String(x + " - " + y);
    }
    
}
