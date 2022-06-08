package main.java.tas.controller;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import main.java.tas.model.Entity;
import main.java.tas.model.enemies.Enemy;
import main.java.tas.model.tower.Builder;
import main.java.tas.model.tower.Tower;
import main.java.tas.model.tower.factory.ArcherFactory;
import main.java.tas.model.tower.factory.CannonFactory;
import main.java.tas.model.tower.factory.FactoryList;
import main.java.tas.model.tower.factory.FlameFactory;
import main.java.tas.model.tower.factory.GasFactory;
import main.java.tas.model.tower.factory.MortarFactory;
import main.java.tas.model.tower.factory.TeslaFactory;
import main.java.tas.utils.Position;

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
	private final Map<FactoryList, BiFunction<Position, List<Enemy>, Tower>> buildMap;
	private final List<Enemy> enemyList; 
	
	/**
	 * Initialize the map with all functions that build a tower by the given enumeration code
	 */
	private void initBuildMap() {
		this.buildMap.put(FactoryList.BASICARCHER, ArcherFactory::basicArcher);
		this.buildMap.put(FactoryList.BIARCHER, ArcherFactory::biArcher);
		this.buildMap.put(FactoryList.TRIARCHER, ArcherFactory::triArcher);
		this.buildMap.put(FactoryList.QUADARCHER, ArcherFactory::quadArcher);
		this.buildMap.put(FactoryList.BASICCANNON, CannonFactory::basicCannon);
		this.buildMap.put(FactoryList.BICANNON, CannonFactory::biCannon);
		this.buildMap.put(FactoryList.TRICANNON, CannonFactory::triCannon);
		this.buildMap.put(FactoryList.QUADCANNON, CannonFactory::quadCannon);
		this.buildMap.put(FactoryList.BASICFLAME, FlameFactory::basicFlame);
		this.buildMap.put(FactoryList.BIFLAME, FlameFactory::biFlame);
		this.buildMap.put(FactoryList.TRIFLAME, FlameFactory::triFlame);
		this.buildMap.put(FactoryList.QUADFLAME, FlameFactory::quadFlame);
		this.buildMap.put(FactoryList.GASTOWER, GasFactory::gasTower);
		this.buildMap.put(FactoryList.BASICMORTAR, MortarFactory::basicMortar);
		this.buildMap.put(FactoryList.SUPERMORTAR, MortarFactory::superMortar);
		this.buildMap.put(FactoryList.GODMORTAR, MortarFactory::godMortar);
		this.buildMap.put(FactoryList.BASICTESLA, TeslaFactory::basicTesla);
		this.buildMap.put(FactoryList.SUPERTESLA, TeslaFactory::basicTesla);
		this.buildMap.put(FactoryList.GODTESLA, TeslaFactory::basicTesla);
	}
	
	/**
	 * Add the tower to the list, and create a thread on that
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
	public TowerLogicImpl(final List<Enemy> enemyList, final Consumer<Entity> addToPanel, final Predicate<Integer> spendMoney) {
		this.addToPanel = addToPanel;
		this.spendMoney = spendMoney;
		this.enemyList = enemyList;
		
		this.buildMap = new HashMap<>();
		this.initBuildMap();
	}

	/** {@inheritDoc} */
	@Override
	public boolean placeTower(final FactoryList tower, final Position pos) {
		return this.buildTower(this.buildMap.get(tower).apply(pos, this.enemyList));
	}

	/** {@inheritDoc} */
	@Override
	public boolean placeTower(final Builder preset) {
		return this.buildTower(preset.setEnemylist(this.enemyList)
				   .build());
	}

	/** {@inheritDoc} */
	@Override
	public void closeAll() {
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

}
