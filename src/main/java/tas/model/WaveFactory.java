package main.java.tas.model;

import java.util.List;

import main.java.tas.model.enemies.Enemy;

public interface WaveFactory {
    
    static List<Class<? extends Enemy>> createEnemiesToBeSpawn(int wave) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("Method not implemented");
    }

}
