package main.java.tas.model;

import java.util.List;

import main.java.tas.model.enemies.Enemy;

public interface WaveFactory {
    
    List<Enemy> createEnemiesToBeSpawn(int wave);

}
