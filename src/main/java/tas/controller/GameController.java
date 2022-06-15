package main.java.tas.controller;

import java.util.List;
import java.util.Optional;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import main.java.tas.model.GameModel;
import main.java.tas.model.GameSpecs;
import main.java.tas.model.tower.factory.DefaultTowers;
import main.java.tas.model.enemy.Enemy;
import main.java.tas.utils.Position;
import main.java.tas.utils.TimeCurve;
import main.java.tas.utils.TimeCurveImpl;
import main.java.tas.view.SceneActionObserver;
import main.java.tas.view.SceneMouseObserver;
import main.java.tas.view.scene.GameScene;

/**
 * Class that implements {@link SceneController}.
 */
public class GameController implements SceneController, SceneMouseObserver, SceneActionObserver {

	private final GameScene gameScene;
	private final EnemiesLogic enemiesHandler;
	private final GameModel playerStats;
	private final TimeCurve timer = new TimeCurveImpl((x) -> (int) (10 / (x + 1.5) + 1));
	private final Color pathColor = new Color(255, 255, 255);
	private final int pathThickness = 50;
	private final TowerLogic towerLogic;
	private int currentInventoryMode = 0;
	private DefaultTowers currentTowerSelected;
	private ScreenListener screenListener = new ScreenListener();
	private final InventoryListener inventoryListener = new InventoryListener();
	private final GameSpecs gameSpecs = new GameSpecs();

	private final String healthSymbol = "Health";
	private final String waveSymbol = "Wave";
	private final String moneySymbol = "Money";
	private final String pointSymbol = "Points";

	/**
	 * * Constructor that creates a game controller for the given game scene.
	 * 
	 * @param gameModel the game model
	 * @param scene     the graphic scene controller
	 */
	public GameController(final GameScene scene, final GameModel gameModel, final List<Position> pathNodes) {
		this.gameScene = scene;
		this.playerStats = gameModel;

		this.enemiesHandler = new EnemiesLogicImpl(pathNodes);
		this.gameScene.getGameView().getGamePanel().setLine(pathNodes, pathColor, pathThickness);

		this.towerLogic = new TowerLogicImpl(this.enemiesHandler.getEnemies(),
		        this.gameScene.getGameView().getGamePanel()::addEntity, this.playerStats::spendMoney);

		this.gameScene.getInventoryView().addInvetoryLabel(this.healthSymbol + " " + this.playerStats.getHP(),
		        "health");
		this.gameScene.getInventoryView().addInvetoryLabel(this.waveSymbol + " " + this.enemiesHandler.getWave(),
		        "wave");
		this.gameScene.getInventoryView().addInvetoryLabel(this.moneySymbol + " " + this.playerStats.getPlayerMoney(),
		        "money");
		this.gameScene.getInventoryView().addInvetoryLabel(this.pointSymbol + " " + this.playerStats.getPoints(),
		        "points");

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
	private void killEnemy(final Enemy enemy) {
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
		this.gameScene.getInventoryView().getInventoryLabel("wave")
		        .setText(this.waveSymbol + " " + this.enemiesHandler.getWave());
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
				this.gameScene.getInventoryView().getTextLabel("money")
				        .setText(this.moneySymbol + " " + this.playerStats.getPlayerMoney());

				killEnemy(enemy);

				this.playerStats.increasePoint();
				this.gameScene.getInventoryView().getTextLabel("points")
				        .setText(this.pointSymbol + " " + this.playerStats.getPoints());

				continue;
			}
			if (enemy.isPathCompleted()) {
				this.playerStats.dealDamage2Player(enemy.getDamage());
				this.gameScene.getInventoryView().getTextLabel("health")
				        .setText(this.healthSymbol + " " + this.playerStats.getHP());
				killEnemy(enemy);
				continue;
			}

			enemy.moveForward();
			this.gameScene.getGameView().drawEntity(enemy);
		}
	}

	/**
	 * Called for checking if a tower has been selected by the user in the
	 * inventoryScene.
	 */
	private void inventoryUpdate() {
		if (this.inventoryListener.checkUpdate()) {
			this.currentTowerSelected = inventoryListener.getTowerSelected();
			this.currentInventoryMode = 1;
			this.inventoryListener.resetUpdate();
			this.screenListener.startListening();
		}
	}

	/**
	 * Checks if turretPosition is a valid Position for a new tower.
	 * 
	 * @param turretPosition the Position of the new tower
	 * @return true if the position is valid, false otherwise
	 */

	public boolean checkTurretPosition(Position turretPosition) {

		// DONE check if position is inside the game Board

		if (turretPosition.getY() < 55 || turretPosition.getY() > 945 || turretPosition.getX() < 55
		        || turretPosition.getX() > 945) {
			System.out.println("not inside border");
			return false;
		}

		// then check there isn't a tower overlapping with the new tower

		// DONE then check if the new tower overlaps with the white line

		List<Position> linePoints = this.gameScene.getGameView().getGamePanel().getLine();
		for (int i = 1; i < linePoints.size(); i++) {

			double a = linePoints.get(i - 1).getX();
			double b = linePoints.get(i - 1).getY();
			double c = linePoints.get(i).getX();
			double d = linePoints.get(i).getY();
			double e = turretPosition.getX();
			double f = turretPosition.getY();
			if (((Math.max(a, c)) > e) && ((Math.max(b, d)) > f) && (Math.min(a, c)) < e && Math.min(b, d) < f) {
				double h = (Math.abs((a * d) - (b * c) + (c * f) - (d * e) + (b * e) - (a * f)))
				        / Math.sqrt(Math.pow(c - a, 2) + Math.pow(d - b, 2));

				if (h <= this.pathThickness + 50) {
					System.out.println("cannot place on the enemy path");
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Called for checking if the screen is been clicked, and if so it places a
	 * tower there.
	 */
	public void screenUpdate() {
		if (this.screenListener.checkUpdate()) {
			Position mousePosition = new Position(this.screenListener.getClickLocation().getX(),
			        this.screenListener.getClickLocation().getY());
			mousePosition.positionConverter(this.gameSpecs.getGameUnits(),
			        this.gameScene.getGameView().getGamePanel().getPreferredSize());
			System.out.println(mousePosition.toString());
			if (checkTurretPosition(mousePosition)) {
				this.towerLogic.placeTower(currentTowerSelected, mousePosition);
				this.currentInventoryMode = 0;
				this.screenListener.stopListening();
				this.gameScene.getInventoryView().getTextLabel("money")
				        .setText(this.moneySymbol + " " + this.playerStats.getPlayerMoney());
			}
			this.screenListener.resetUpdate();
		}
	}

	@Override
	public MouseListener getMouseListener() {
		return this.screenListener;
	}

	@Override
	public ActionListener getActionListener() {
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
