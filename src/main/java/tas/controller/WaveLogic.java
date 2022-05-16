package main.java.tas.controller;

import java.util.List;

import main.java.tas.model.enemies.Enemy;

/**
 * An interface for
 */
public interface WaveLogic {
    
    /**
     * Generates a list with some enemies given the wave
     * @param wave of the game
     * @return list of enemies
     */
    List<Enemy> createEnemiesToBeSpawn(int wave);

}
