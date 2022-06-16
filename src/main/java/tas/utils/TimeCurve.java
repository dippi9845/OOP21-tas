package main.java.tas.utils;

/**
 * An interface that models a time curve.
 */
public interface TimeCurve {

	/**
	 * Set a mathematical function as for the timer.
	 * 
	 * @param func a lambda function with one variable e.g. "(x) &gt; x + 1" can be
	 *             seen as "f(x) = x + 1"
	 */
	void setFunction(final IntArgumentOperator func);

	/**
	 * This method returns True when the difference of time between now and the last
	 * time the method {@link #actionPerformed()} is greater than the result given
	 * by the setup function with the x parameter. e.g. with a function "(x) &gt; x
	 * * 2", when you call this method with the
	 * 
	 * @param x is horizontal coordinate of the function
	 * @return True if the the difference between the last time the actionPerformed
	 *         method was called and the actual time is greater then a fixed amount
	 * @throws IllegalArgumentException if @param x is less than 0
	 */
	boolean isTimeForAction(final int x) throws IllegalArgumentException;

	/**
	 * Set up a flag that confirms that an action was performed
	 */
	void actionPerformed();
}
