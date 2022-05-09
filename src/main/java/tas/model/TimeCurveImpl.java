package main.java.tas.model;

/**
 * Class that implements {@link TimeCurve}
 */
public class TimeCurveImpl implements TimeCurve {
    
    private double lastActionTime;

    /**
     * 
     */
    public TimeCurveImpl() {
        this.lastActionTime = 0;
    }
    
    /**
     * Is the function that determine the y value by giving the x coordinate
     * @param x is the horizontal coordinate
     * @return the y coordinate
     */
    private int curveFunction(int x) {
        return (int) (10/(x+1.5) + 1);
    }
    
    /** {@inheritDoc} */
    @Override
    public boolean isTimeForAction(int wave) throws IllegalArgumentException {
        if (wave <= 0) {
            throw new IllegalArgumentException("@param x can't be less than or equal to 0");
        }
        return (System.currentTimeMillis() - this.lastActionTime) > (curveFunction(wave) * 1000);
    }
    
    /** {@inheritDoc} */
    @Override
    public void actionPerformed() {
        this.lastActionTime = System.currentTimeMillis();
    }
    
}
