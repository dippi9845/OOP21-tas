package main.java.tas.controller.tower;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import main.java.tas.controller.tower.builder.TowerBuilder;
import main.java.tas.controller.tower.factory.DefaultTowers;
import main.java.tas.controller.tower.factory.DefaultTowersUtils;
import main.java.tas.model.Entity;
import main.java.tas.model.enemy.Enemy;
import main.java.tas.model.tower.Towers;
import main.java.tas.model.tower.Tower;
import main.java.tas.utils.Position;
import main.java.tas.utils.Size;
import java.util.Collections;
import java.util.LinkedList;

/**
 * A class that has the objective to keep controlled all the built towers, and
 * create them
 */
public class TowerControllerImpl implements TowerController {
	private final List<TowerThread> builtTowers = new LinkedList<TowerThread>();
	private final List<Thread> towerThreads = new LinkedList<Thread>();
	private final Consumer<Entity> addToPanel;
	private final Predicate<Integer> spendMoney;
	private final List<Enemy> enemyList;

	/**
	 * Add the tower to the list, and create a thread on that
	 * 
	 * @param t Tower to add
	 * @return
	 */
	private boolean buildTower(final Tower t) {
		if (this.spendMoney.test(t.getCost())) {
			final TowerThread twth = new TowerThreadImpl(t);
			final Thread th = new Thread(twth);

			th.start();
			this.towerThreads.add(th);

			this.builtTowers.add(twth);
			this.addToPanel.accept(twth);

			return true;
		} else {
			return false;
		}
	}

	/**
	 * Try to join the thread passed in input, and in case of exception will be
	 * handled
	 * 
	 * @param th Thread to be joined
	 */
	private void tryToJoin(final Thread th) {
		try {
			th.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Constructor
	 * 
	 * @param enemyList  list that contains all the enemies alive
	 * @param addToPanel function that add towers to the panel
	 * @param spendMoney
	 */
	public TowerControllerImpl(final List<Enemy> enemyList, final Consumer<Entity> addToPanel,
			final Predicate<Integer> spendMoney) {
		this.addToPanel = addToPanel;
		this.spendMoney = spendMoney;
		this.enemyList = enemyList;
	}

	/** {@inheritDoc} */
	@Override
	public boolean placeTower(final DefaultTowers tower, final Position pos) {
		return this.buildTower(DefaultTowersUtils.BUILDMAP.get(tower).apply(pos, this.enemyList));
	}

	/** {@inheritDoc} */
	@Override
	public boolean placeTower(final TowerBuilder preset) {
		return this.buildTower(preset.setEnemylist(this.enemyList).build());
	}


	/** {@inheritDoc} */
	@Override
	public void closeAll() {
		this.builtTowers.stream().forEach(x -> x.stop());
		this.builtTowers.clear();

		this.towerThreads.stream().forEach(this::tryToJoin);
		this.towerThreads.clear();
	}

	/** {@inheritDoc} */
	@Override
	public void drawTowers(final Consumer<Entity> draw) {
		this.builtTowers.forEach(draw::accept);
	}

	/** {@inheritDoc} */
	@Override
	public boolean thereIsTowerNear(final Position pos, final Size dim) {
		return this.builtTowers.stream()
				.anyMatch(x -> Position.findDistance(x.getPos(), pos) <= dim.getDiagonal() + Towers.getFarthest(x));
	}

	/** {@inheritDoc} */
	@Override
	public List<Tower> getBuildTowers() {
		return Collections.unmodifiableList(this.builtTowers);
	}

	/** {@inheritDoc} */
	@Override
	public List<Thread> getBuildThread() {
		return Collections.unmodifiableList(this.towerThreads);
	}
}
