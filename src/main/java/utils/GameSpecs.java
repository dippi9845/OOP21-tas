package main.java.utils;

import java.awt.Dimension;

public final class GameSpecs {

    private final static int MAX_UNIT = 1000;
    public final static Dimension GAME_UNITS = new Dimension(MAX_UNIT, MAX_UNIT);
    
    private final static int TICKS_PER_SECOND = 30;
    public final static int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
    public final static int MAX_FRAMESKIP = 5;
    
}
