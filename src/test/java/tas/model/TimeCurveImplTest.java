package test.java.tas.model;

import static org.junit.Assert.*;

import org.junit.Test;

import main.java.tas.model.TimeCurve;
import main.java.tas.model.TimeCurveImpl;

public class TimeCurveImplTest {

    @Test
    public void testIsTimeForAction() {
        final int waitingTime = 5;
        final TimeCurve timer = new TimeCurveImpl((x) -> waitingTime);
        timer.actionPerformed();
        assertFalse(timer.isTimeForAction(waitingTime-2));
        assertFalse(timer.isTimeForAction(waitingTime+2));
        
    }

}
