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
        
        enemiesList.addAll(getRedEnemyByWave(wave));
        enemiesList.addAll(getPinkEnemyByWave(wave));
        
        return enemiesList;
    }
    
    private List<Enemy> getRedEnemyByWave(int wave) {
        int enemiesForWave = 0;
        List<Enemy> eList = new ArrayList<Enemy>();
        
        if (0 < wave && wave <= 3) {
            enemiesForWave = (int) Math.round(0.655 * wave + 5);
        }
        if (3 < wave && wave < 10) {
            enemiesForWave = (int) Math.round(-(0.095*Math.pow((wave-5.2), 4) - 0.2*Math.pow((wave-5.2), 3) - 1.2*Math.pow((wave-5.2), 2) + 0.8*(wave-5.2)) + 3.7);
        }
        
        for (int i=0; i < enemiesForWave; i++) {
            eList.add(this.enemyFactory.spawnRedEnemy());
        }
        
        return eList;
    }
    
    private List<Enemy> getPinkEnemyByWave(int wave) {
        int enemiesForWave = 0;
        List<Enemy> eList = new ArrayList<Enemy>();
        
        if (5.4 < wave && wave < 12) {
            enemiesForWave = (int) Math.round(0.57*Math.pow((wave-10), 3) + 2.6*Math.pow((wave-10), 2));
        }
        if (wave >= 12) {
            enemiesForWave = (int) Math.round(0.5*wave + 9);
        }
        
        for (int i=0; i < enemiesForWave; i++) {
            eList.add(this.enemyFactory.spawnPinkEnemy());
        }
        
        return eList;
    }

}
