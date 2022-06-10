package main.java.tas.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import main.java.tas.model.GameModel;
import main.java.tas.model.TimeCurve;
import main.java.tas.model.TimeCurveImpl;
import main.java.tas.model.enemies.Enemy;
import main.java.tas.model.tower.factory.DefaultTowers;
import main.java.tas.utils.Position;
import main.java.tas.view.GameScene;

/**
 * Class that implements {@link SceneController}.
 */
public class GameController implements SceneController {

	private final GameScene gameScene;
	private final EnemiesLogic enemiesHandler;
	private final GameModel playerStats;
	private final TimeCurve timer = new TimeCurveImpl((x) -> (int) (10 / (x + 1.5) + 1));
	private final Color pathColor = new Color(255, 255, 255);
	private final int pathThickness = 50;
	private final TowerLogic towerLogic;
	private int currentInventoryMode = 0;
	private DefaultTowers currentTowerSelected;
	private final ScreenListener screenListener = new ScreenListener();
	private final InventoryListener inventoryListener = new InventoryListener();

	/**
	 * Constructor that creates a game controller for the given game scene.
	 * 
	 * @param scene the graphic scene controller
	 */
	public GameController(final GameScene scene, GameModel gameModel) {
		this.gameScene = scene;

		this.playerStats = gameModel;

		List<Position> pathNodes = Arrays.asList(new Position(500, 500), new Position(750, 750), new Position(0, 1000));
		this.enemiesHandler = new EnemiesLogicImpl(pathNodes);
		this.gameScene.getGameView().getGamePanel().setLine(pathNodes, pathColor, pathThickness);

		this.towerLogic = new TowerLogicImpl(this.enemiesHandler.getEnemies(),
				this.gameScene.getGameView().getGamePanel()::addEntity, this.playerStats::spendMoney);

		this.gameScene.getGameView().addTextLabel("♥ " + this.playerStats.getHP(), "healt", "NW");
		this.gameScene.getGameView().addTextLabel("⬤ " + this.enemiesHandler.getWave(), "wave", "NE");
		this.gameScene.getGameView().addTextLabel("＄ " + this.playerStats.getPlayerMoney(), "money", "SE");

		// TODO: manca l'inserimento dinamico della posizione dello spawner e altro...
	}

	/**
	 * Spawns the enemy from the queue and creates the graphic component for it
	 */
	private void spawnEnemies() {
		if (!this.timer.isTimeForAction(this.enemiesHandler.getWave())) {
			return;
		}

		Optional<Enemy> enemy = this.enemiesHandler.spawnEnemy();
		if (enemy.isPresent()) {
			this.gameScene.getGameView().addEntityLabel(enemy.get());
		}
		this.timer.actionPerformed();
	}

	/**
	 * Removes the given enemy from the alive list and deletes it's graphical
	 * component.
	 * 
	 * @param enemy
	 */
	private void killEnemy(Enemy enemy) {
		this.gameScene.getGameView().removeEntityLabel(enemy);
		try {
			this.enemiesHandler.removeEnemy(enemy);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Increases the wave count.
	 */
	private void increaseWave() {
		this.enemiesHandler.setNextWave();
		this.gameScene.getGameView().getTextLabel("wave").setText("⬤ " + this.enemiesHandler.getWave());
	}

	/**
	 * Loops over all enemies and moves them. It also checks if they are dead or if
	 * they completed their path.
	 */
	private void enemiesCheck() {
		for (int i = 0; i < enemiesHandler.getEnemies().size(); i++) {
			Enemy enemy = enemiesHandler.getEnemies().get(i);

			if (enemy.isDead()) {
				this.playerStats.giveMoney2Player(enemy.getMoney());
				this.gameScene.getGameView().getTextLabel("money").setText("＄ " + this.playerStats.getPlayerMoney());
				killEnemy(enemy);
				continue;
			}
			if (enemy.hasCompletedPath()) {
				this.playerStats.dealDamage2Player(enemy.getDamage());
				this.gameScene.getGameView().getTextLabel("healt").setText("♥ " + this.playerStats.getHP());
				killEnemy(enemy);
				continue;
			}

			enemy.moveForward();
			this.gameScene.getGameView().drawEntity(enemy);
		}
	}
	
	public void inventoryUpdate() {
		if (this.inventoryListener.checkUpdate()) {
			this.currentTowerSelected = inventoryListener.getTowerSelected();
			this.currentInventoryMode = 1;
			this.inventoryListener.resetUpdate();
		}
	}
	
	public void screenUpdate() {
		if (this.screenListener.checkUpdate()) {
			
			Position turretPosition = new Position(100, 100);
			this.towerLogic.placeTower(currentTowerSelected, turretPosition);
			this.currentInventoryMode = 0;
			this.screenListener.resetUpdate();
		}
	}
	
	public MouseListener getMouseListener(){
		return this.screenListener;
	}
	
	public ActionListener getListener() {
		return this.inventoryListener;
	}

	/** {@inheritDoc} */
	@Override
	public void nextTick() {
		if (this.enemiesHandler.isWaveClean()) {
			increaseWave();
		}
		if (this.enemiesHandler.areEnemiesInQueue()) {
			spawnEnemies();
		}

		if (this.enemiesHandler.areEnemiesOnBoard()) {
			enemiesCheck();
		}
		
		if (currentInventoryMode == 0) {
			inventoryUpdate();
		}
		
		if (currentInventoryMode == 1) {
			screenUpdate();
		}
		this.towerLogic.drawTowers(this.gameScene.getGameView()::drawEntity);
	}
}
