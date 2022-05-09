package main.java.tas.utils;

/**
 * Interface for storing lambda operations with one integer parameter
 */
public interface IntArgumentOperator {
    
    /**
     * Generic operator
     * @param a
     * @return the value given by the lambda expression
     */
    public int operate(int a);
    
}
