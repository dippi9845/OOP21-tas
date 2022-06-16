package main.java.tas.controller.tower;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import main.java.tas.model.Entity;
import main.java.tas.model.enemy.Enemy;
import main.java.tas.model.tower.Builder;
import main.java.tas.model.tower.Tower;
import main.java.tas.model.tower.factory.ArcherFactory;
import main.java.tas.model.tower.factory.CannonFactory;
import main.java.tas.model.tower.factory.DefaultTowers;
import main.java.tas.model.tower.factory.FlameFactory;
import main.java.tas.model.tower.factory.GasFactory;
import main.java.tas.model.tower.factory.MortarFactory;
import main.java.tas.model.tower.factory.TeslaFactory;
import main.java.tas.utils.Position;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * A class that has the objective to keep controlled all the built towers, and
 * create them
 */
public class TowerLogicImpl implements TowerLogic {
	private final List<Tower> builtTowers = new LinkedList<Tower>();
	private final List<Thread> towerThreads = new LinkedList<Thread>();
	private final Consumer<Entity> addToPanel;
	private final Predicate<Integer> spendMoney;
	private final Map<DefaultTowers, BiFunction<Position, List<Enemy>, Tower>> buildMap;
	private final List<Enemy> enemyList;

	/**
	 * Initialize the map with all functions that build a tower by the given
	 * enumeration code
	 */
	private void initBuildMap() {
		this.buildMap.put(DefaultTowers.BASICARCHER, ArcherFactory::basicArcher);
		this.buildMap.put(DefaultTowers.BIARCHER, ArcherFactory::biArcher);
		this.buildMap.put(DefaultTowers.TRIARCHER, ArcherFactory::triArcher);
		this.buildMap.put(DefaultTowers.QUADARCHER, ArcherFactory::quadArcher);
		this.buildMap.put(DefaultTowers.BASICCANNON, CannonFactory::basicCannon);
		this.buildMap.put(DefaultTowers.BICANNON, CannonFactory::biCannon);
		this.buildMap.put(DefaultTowers.TRICANNON, CannonFactory::triCannon);
		this.buildMap.put(DefaultTowers.QUADCANNON, CannonFactory::quadCannon);
		this.buildMap.put(DefaultTowers.BASICFLAME, FlameFactory::basicFlame);
		this.buildMap.put(DefaultTowers.BIFLAME, FlameFactory::biFlame);
		this.buildMap.put(DefaultTowers.TRIFLAME, FlameFactory::triFlame);
		this.buildMap.put(DefaultTowers.QUADFLAME, FlameFactory::quadFlame);
		this.buildMap.put(DefaultTowers.GASTOWER, GasFactory::gasTower);
		this.buildMap.put(DefaultTowers.BASICMORTAR, MortarFactory::basicMortar);
		this.buildMap.put(DefaultTowers.SUPERMORTAR, MortarFactory::superMortar);
		this.buildMap.put(DefaultTowers.GODMORTAR, MortarFactory::godMortar);
		this.buildMap.put(DefaultTowers.BASICTESLA, TeslaFactory::basicTesla);
		this.buildMap.put(DefaultTowers.SUPERTESLA, TeslaFactory::superTesla);
		this.buildMap.put(DefaultTowers.GODTESLA, TeslaFactory::godTesla);
	}

	/**
	 * Add the tower to the list, and create a thread on that
	 * 
	 * @param t Tower to add
	 * @return
	 */
	private boolean buildTower(final Tower t) {
		if (this.spendMoney.test(t.getCost())) {
			final Thread th = new Thread(t);
			th.start();
			this.towerThreads.add(th);

			this.builtTowers.add(t);
			this.addToPanel.accept(t);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Constructor
	 * 
	 * @param enemyList  list that contains all the enemies alive
	 * @param addToPanel function that add towers to the panel
	 * @param spendMoney
	 */
	public TowerLogicImpl(final List<Enemy> enemyList, final Consumer<Entity> addToPanel,
			final Predicate<Integer> spendMoney) {
		this.addToPanel = addToPanel;
		this.spendMoney = spendMoney;
		this.enemyList = enemyList;

		this.buildMap = new HashMap<>();
		this.initBuildMap();
	}

	/** {@inheritDoc} */
	@Override
	public boolean placeTower(final DefaultTowers tower, final Position pos) {
		return this.buildTower(this.buildMap.get(tower).apply(pos, this.enemyList));
	}

	/** {@inheritDoc} */
	@Override
	public boolean placeTower(final Builder preset) {
		return this.buildTower(preset.setEnemylist(this.enemyList).build());
	}

	/** {@inheritDoc} */
	@Override
	public void closeAll() {
		this.builtTowers.stream().forEach(x -> x.stop());

		this.towerThreads.stream().forEach(x -> {
			try {
				x.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}

	/** {@inheritDoc} */
	@Override
	public void drawTowers(final Consumer<Entity> draw) {
		this.builtTowers.forEach(draw::accept);
	}
	
	private double getDiagonal(final Dimension d) {
		return Math.hypot(d.getHeight(), d.getWidth());
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean thereIsTowerNear(final Position pos) {
		return this.builtTowers.stream()
				.anyMatch(t->Position.findDistance(t.getPos(), pos) <= getDiagonal(t.getBodyDimension()));
	}

}