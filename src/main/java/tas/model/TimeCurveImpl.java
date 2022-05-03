package main.java.tas.model;

public class TimeCurveImpl implements TimeCurve {
    
    private double lastActionTime;

    public TimeCurveImpl() {
        this.lastActionTime = 0;
    }
    
    private int curveFunction(int x) {
        return (int) (10/(x+1.5) + 1);
    }
    
    public boolean isTimeForAction(int x) throws IllegalArgumentException {
        if (x <= 0) {
            throw new IllegalArgumentException("@param x can't be less than or equal to 0");
        }
        return (System.currentTimeMillis() - this.lastActionTime) > (curveFunction(x)* 1000);
    }
    
    public void actionPerformed() {
        this.lastActionTime = System.currentTimeMillis();
    }
    
}
