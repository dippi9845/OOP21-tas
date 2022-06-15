package main.java.tas.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import main.java.tas.model.enemy.Enemy;
import main.java.tas.model.enemy.EnemyFactory;
import main.java.tas.model.enemy.EnemyFactoryImpl;
import main.java.tas.utils.Position;

/**
 * Class that implements {@link EnemiesLogic}.
 */
public class EnemiesLogicImpl implements EnemiesLogic {

	private final List<Enemy> aliveEnemiesList = new ArrayList<Enemy>();
	private int actualWave;
	private final EnemyFactory waveFactory;
	private List<Enemy> enemyToBeSpawned = new ArrayList<Enemy>();

	/**
	 * Constructor that creates the logic of the enemy waves.
	 * 
	 * @param nodesPosition is a list with the nodes that the enemies will have to
	 *                      travel
	 */
	public EnemiesLogicImpl(final List<Position> nodesPosition) {
		this.waveFactory = new EnemyFactoryImpl(nodesPosition);
		this.actualWave = 0;
	}

	/** {@inheritDoc} */

	@Override
	public Optional<Enemy> spawnEnemy() {
		if (this.enemyToBeSpawned.isEmpty()) {
			return Optional.empty();
		}

		Enemy enemy = this.enemyToBeSpawned.remove(0);
		
		synchronized (aliveEnemiesList) {
			this.aliveEnemiesList.add(enemy);						
		}

		return Optional.of(enemy);
	}

	/** {@inheritDoc} */
	@Override
	public void removeEnemy(final Enemy enemy) throws NoSuchFieldException {
		
		synchronized (aliveEnemiesList) {
			
			if (!this.aliveEnemiesList.contains(enemy)) {
				throw new NoSuchFieldException("This enemy is not alive");
			}
		
			this.aliveEnemiesList.remove(enemy);
		}
	}

	/** {@inheritDoc} */
	@Override
	public boolean isWaveClean() {
		return this.aliveEnemiesList.isEmpty() && this.enemyToBeSpawned.isEmpty();
	}

	/** {@inheritDoc} */
	@Override
	public int getWave() {
		return this.actualWave;
	}

	/** {@inheritDoc} */
	@Override
	public void setNextWave() {
		this.actualWave++;
		this.enemyToBeSpawned = this.waveFactory.createEnemiesToBeSpawn(actualWave);
	}

	/** {@inheritDoc} */
	@Override
	public List<Enemy> getEnemies() {
		return this.aliveEnemiesList;
	}

	/** {@inheritDoc} */
	@Override
	public boolean areEnemiesAlive() {
		return !this.aliveEnemiesList.isEmpty();
	}

	/** {@inheritDoc} */
	@Override
	public boolean areEnemiesInQueue() {
		return !this.enemyToBeSpawned.isEmpty();
	}

}
