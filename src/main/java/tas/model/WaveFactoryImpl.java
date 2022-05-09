package main.java.tas.model;

import java.util.ArrayList;
import java.util.List;

import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;

/**
 * Class that implements {@link WaveFactory}
 */
public class WaveFactoryImpl implements WaveFactory {
   private final EnemyFactory enemyFactory;
    
   /**
    * Constructor that generates the wave factory
    * @param nodesPosition is a list with the nodes that the enemies will have to travel 
    */
    public WaveFactoryImpl(List<Position> nodesPosition) {
        this.enemyFactory = new EnemyFactoryImpl(nodesPosition);
    }

    /** {@inheritDoc} */
    @Override
    public List<Enemy> createEnemiesToBeSpawn(int wave) {
        List<Enemy> enemiesList = new ArrayList<Enemy>();
        
        enemiesList.addAll(getRedEnemyByWave(wave));
        enemiesList.addAll(getGreenEnemyByWave(wave));
        enemiesList.addAll(getPinkEnemyByWave(wave));
        
        return enemiesList;
    }
    
    /**
     * Generates a list of RedEnemy's given the wave
     * @param wave of the game
     * @return a list with the enemies
     */
    private List<Enemy> getRedEnemyByWave(int wave) {
        int enemiesForWave = 0;
        List<Enemy> eList = new ArrayList<Enemy>();
        
        if (0 < wave && wave <= 3) {
            // y = 0.655 * x + 5
            enemiesForWave = (int) Math.round(0.655 * wave + 5);
        }
        if (3 < wave && wave < 10) {
            // y = -(0.095 * (x - 5.2)^4 - 0.2 * (x - 5.2)^3 -1.2 * (x - 5.2)^2 +0.8 * (x - 5.2))
            enemiesForWave = (int) Math.round(-(0.095*Math.pow((wave-5.2), 4) - 0.2*Math.pow((wave-5.2), 3) - 1.2*Math.pow((wave-5.2), 2) + 0.8*(wave-5.2)) + 3.7);
        }
        
        for (int i=0; i < enemiesForWave; i++) {
            eList.add(this.enemyFactory.spawnRedEnemy());
        }
        
        return eList;
    }
    
    /**
     * Generates a list of GreenEnemy's given the wave
     * @param wave of the game
     * @return a list with the enemies
     */
    private List<Enemy> getGreenEnemyByWave(int wave) {
        int enemiesForWave = 0;
        List<Enemy> eList = new ArrayList<Enemy>();
        
        if (3 < wave && wave < 10) {
            // y = log_{50}((x - 2.9^9) + 5.5
            enemiesForWave = (int) (Math.round(Math.log((Math.pow((wave-2.9), 9))) / Math.log(50)) + 5.5);
        }
        if (wave >= 10) {
            // y = x
            enemiesForWave = wave;
        }
        
        for (int i=0; i < enemiesForWave; i++) {
            eList.add(this.enemyFactory.spawnGreenEnemy());
        }
        
        return eList;
    }
    
    /**
     * Generates a list of PinkEnemy's given the wave
     * @param wave of the game
     * @return a list with the enemies
     */
    private List<Enemy> getPinkEnemyByWave(int wave) {
        int enemiesForWave = 0;
        List<Enemy> eList = new ArrayList<Enemy>();
        
        if (5.4 < wave && wave < 12) {
            // y = 0.57 * (x - 10)^3 +2.6 * (x - 10)^2
            enemiesForWave = (int) Math.round(0.57*Math.pow((wave-10), 3) + 2.6*Math.pow((wave-10), 2));
        }
        if (wave >= 12) {
            // y = 0.5 * x + 9
            enemiesForWave = (int) Math.round(0.5*wave + 9);
        }
        
        for (int i=0; i < enemiesForWave; i++) {
            eList.add(this.enemyFactory.spawnPinkEnemy());
        }
        
        return eList;
    }

}
