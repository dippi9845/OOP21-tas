package main.java.tas.model;

import java.util.ArrayList;
import java.util.List;

import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;

public class WaveFactoryImpl implements WaveFactory {
   private final EnemyFactory enemyFactory;
    
    public WaveFactoryImpl(List<Position> nodesPosition) {
        this.enemyFactory = new EnemyFactoryImpl(nodesPosition);
    }

    @Override
    public List<Enemy> createEnemiesToBeSpawn(int wave) {
        List<Enemy> enemiesList = new ArrayList<Enemy>();
        for (int i=0; i<wave*2; i++) {
            enemiesList.add(this.enemyFactory.spawnRedEnemy());
        }
        return enemiesList;
    }

}
