package main.java.tas.model;

import java.util.ArrayList;
import java.util.List;

import main.java.tas.model.enemies.Enemy;
import main.java.tas.model.enemies.RedEnemy;

public class WaveFactoryImpl implements WaveFactory {

    public static List<Class<? extends Enemy>> createEnemiesToBeSpawn(int wave) {
        List<Class<? extends Enemy>> enemiesList = new ArrayList<Class<? extends Enemy>>();
        for (int i=0; i<wave; i++) {
            enemiesList.add(RedEnemy.class);
        }
        return enemiesList;
    }

}
