package main.java.tas.model;

import main.java.tas.utils.IntArgumentOperator;

/**
 * Class that implements {@link TimeCurve}
 */
public class TimeCurveImpl implements TimeCurve {
    
    private double lastActionTime;
    private IntArgumentOperator function;

    /**
     * Constructor that set up the timer
     * @param func is the lambda function that given the x coordinate
     * returns the y one
     */
    public TimeCurveImpl(IntArgumentOperator func) {
        setFunction(func);
        this.lastActionTime = 0;
    }
    
    /** {@inheritDoc} */
    @Override
    public void setFunction(IntArgumentOperator func) {
        this.function = func;
    }
    
    /** {@inheritDoc} */
    @Override
    public boolean isTimeForAction(int x) throws IllegalArgumentException {
        if (x <= 0) {
            throw new IllegalArgumentException("@param x can't be less than or equal to 0");
        }
        return (System.currentTimeMillis() - this.lastActionTime) > (this.function.operate(x) * 1000);
    }
    
    /** {@inheritDoc} */
    @Override
    public void actionPerformed() {
        this.lastActionTime = System.currentTimeMillis();
    }
    
}
