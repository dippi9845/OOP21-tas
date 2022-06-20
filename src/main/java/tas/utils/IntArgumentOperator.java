package main.java.tas.utils;

/**
 * Interface for storing lambda operations with one integer parameter.
 */
public interface IntArgumentOperator {

	/**
	 * Generic operator.
	 * 
	 * @param a the argument of the function
	 * @return the value given by the lambda expression
	 */
	public int operate(final int a);

}
