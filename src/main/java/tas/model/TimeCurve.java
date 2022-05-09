package main.java.tas.model;

/**
 * An interface that models a time curve
 */
public interface TimeCurve {
    
    /**
     * @param x is horizontal coordinate of the function
     * @return True if the the difference between the last time the actionPerformed method
     * was called and the actual time is greater then a fixed amount
     * @throws IllegalArgumentException if @param x is less than 0
     */
    boolean isTimeForAction(int wave)  throws IllegalArgumentException;
    
    /**
     * Set up a flag that confirms that an action was performed
     */
    void actionPerformed();
}
