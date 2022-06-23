package main.java.tas.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import main.java.tas.controller.enemy.EnemiesLogic;
import main.java.tas.controller.enemy.EnemiesLogicImpl;
import main.java.tas.controller.listener.InventoryListener;
import main.java.tas.controller.listener.ScreenListener;
import main.java.tas.controller.observer.SceneActionObserver;
import main.java.tas.controller.observer.SceneMouseObserver;
import main.java.tas.controller.tower.TowerController;
import main.java.tas.controller.tower.TowerControllerImpl;
import main.java.tas.controller.tower.factory.DefaultTowers;
import main.java.tas.controller.tower.factory.DefaultTowersUtils;
import main.java.tas.model.enemy.Enemy;
import main.java.tas.model.game.GameModel;
import main.java.tas.model.menu.MenuModel;
import main.java.tas.utils.Size;
import main.java.tas.utils.GameSpecs;
import main.java.tas.utils.Position;
import main.java.tas.utils.TimeCurve;
import main.java.tas.utils.TimeCurveImpl;
import main.java.tas.view.scene.GameScene;

/**
 * Class that implements {@link SceneMouseObserver}, {@link SceneActionObserver}
 * and {@link SceneController}.
 */
public class GameController implements SceneMouseObserver, SceneActionObserver, SceneController {

	private final GameScene gameScene;
	private final EnemiesLogic enemiesHandler;
	private final GameModel playerStats;
	private final TimeCurve timer = new TimeCurveImpl((x) -> (int) (10 / (x + 1.5) + 1));
	private final Color pathColor = new Color(255, 255, 255);
	private final int pathThickness = 50;
	private final TowerController towerController;
	private int currentInventoryMode = 0;
	private DefaultTowers currentTowerSelected;
	private ScreenListener screenListener = new ScreenListener();
	private final InventoryListener inventoryListener = new InventoryListener();
	private final GameSpecs gameSpecs = new GameSpecs();
	private MenuModel menuModel;
	private HashMap<String, Integer> towerInfo = new HashMap<String, Integer>();

	private final String healthSymbol = "Health";
	private final String waveSymbol = "Wave";
	private final String moneySymbol = "Money";
	private final String pointSymbol = "Points";

	/**
	 * Constructor that creates a game controller for the given game scene.
	 * 
	 * @param gameModel   the game model
	 * @param scene       the graphic scene controller
	 * @param pathNodes   a {@link List} of the nodes of the level
	 * @param menuModelIn the menu model
	 */
	public GameController(final GameScene scene, final GameModel gameModel, final List<Position> pathNodes,
			final MenuModel menuModelIn) {
		this.menuModel = menuModelIn;
		this.gameScene = scene;
		this.playerStats = gameModel;
		for (DefaultTowers tower : DefaultTowers.values()) {
			JSONObject tmp = DefaultTowersUtils.JSONOBJECTMAP.get(tower);
			this.towerInfo.put(tower.toString(), tmp.getInt(DefaultTowersUtils.COSTFIELD));
		}

		this.enemiesHandler = new EnemiesLogicImpl(pathNodes);
		this.gameScene.getGameView().getGamePanel().setLine(pathNodes, pathColor, pathThickness);
		this.towerController = new TowerControllerImpl(this.enemiesHandler.getEnemies(),
				this.gameScene.getGameView().getGamePanel()::addEntity, this.playerStats::spendMoney);

		this.gameScene.getInventoryView().addTextLabel(this.healthSymbol + " " + this.playerStats.getHP(), "health");
		this.gameScene.getInventoryView().addTextLabel(this.waveSymbol + " " + this.enemiesHandler.getWave(), "wave");
		this.gameScene.getInventoryView().addTextLabel(this.moneySymbol + " " + this.playerStats.getPlayerMoney(),
				"money");
		this.gameScene.getInventoryView().addTextLabel(this.pointSymbol + " " + this.playerStats.getPoints(), "points");
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
		this.gameScene.getInventoryView().getTextLabel("wave")
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
	 * Turns off the buttons in the view that should not be enabled
	 */
	public void checkInventoryButtons() {
		List<String> names = new ArrayList<String>();
		for (DefaultTowers tower : DefaultTowers.values()) {
			if (this.playerStats.getPlayerMoney() < towerInfo.get(tower.toString())) {
				names.add(tower.toString());
			}
		}
		this.gameScene.disableButtons(names);
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
			this.gameScene.getInventoryView().resetButtonBackground();
			this.gameScene.getInventoryView().selectButton(this.currentTowerSelected.toString());
		}
	}

	/**
	 * Checks if turretPosition is a valid Position for a new tower.
	 * 
	 * @param turretPosition the Position of the new tower
	 * @return true if the position is valid, false otherwise
	 */

	public boolean checkTurretPosition(final Position turretPosition, final DefaultTowers selected) {

		final Size towerDimension = DefaultTowersUtils.getDefaultTowersDimension(selected);

		if (turretPosition.getY() < towerDimension.getWidth() / 2
				|| turretPosition.getY() > this.gameSpecs.getGameUnits().getWidth() - towerDimension.getWidth() / 2
				|| turretPosition.getX() < towerDimension.getHeight()
				|| turretPosition.getX() > this.gameSpecs.getGameUnits().getWidth() - towerDimension.getWidth() / 2) {

			return false;
		}

		if (this.towerController.thereIsTowerNear(turretPosition, towerDimension)) {
			return false;
		}
		List<Position> linePoints = this.gameScene.getGameView().getGamePanel().getLine();

		for (int i = 1; i < linePoints.size(); i++) {

			double a = linePoints.get(i - 1).getX();
			double b = linePoints.get(i - 1).getY();
			double c = linePoints.get(i).getX();
			double d = linePoints.get(i).getY();
			double e = turretPosition.getX();
			double f = turretPosition.getY();
			if (i == 1) {
				double dist = Math.sqrt(Math.pow(e - a, 2) + Math.pow(f - b, 2));

				if (dist < this.pathThickness + 50) {
					return false;
				}
			}
			double dist = Math.sqrt(Math.pow(e - c, 2) + Math.pow(f - d, 2));

			if (dist < this.pathThickness + 50) {
				return false;
			}
			if (((Math.max(a, c)) > e) && ((Math.max(b, d)) > f) && (Math.min(a, c)) < e && Math.min(b, d) < f) {
				double h = (Math.abs((a * d) - (b * c) + (c * f) - (d * e) + (b * e) - (a * f)))
						/ Math.sqrt(Math.pow(c - a, 2) + Math.pow(d - b, 2));

				if (h <= this.pathThickness + 50) {
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
	private void screenUpdate() {
		if (this.screenListener.checkUpdate()) {
			Position mousePosition = new Position(this.screenListener.getClickLocation().getX(),
					this.screenListener.getClickLocation().getY());
			mousePosition.positionConverter(this.gameSpecs.getGameUnits(),
					new Size(this.gameScene.getGameView().getGamePanel().getPreferredSize().getWidth(),
							this.gameScene.getGameView().getGamePanel().getPreferredSize().getHeight()));

			if (checkTurretPosition(mousePosition, currentTowerSelected)) {
				this.towerController.placeTower(currentTowerSelected, mousePosition);
				this.currentInventoryMode = 0;
				this.screenListener.stopListening();
				this.gameScene.getInventoryView().getTextLabel("money")
						.setText(this.moneySymbol + " " + this.playerStats.getPlayerMoney());
				this.gameScene.getInventoryView().resetButtonBackground();
			}
			this.screenListener.resetUpdate();
		}
	}

	/** {@inheritDoc} */
	@Override
	public MouseListener getMouseListener() {
		return this.screenListener;
	}

	/** {@inheritDoc} */
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

		if (this.enemiesHandler.areEnemiesAlive()) {
			enemiesCheck();
		}

		if (this.playerStats.getHP() <= 0) {
			this.towerController.closeAll();
			this.menuModel.setMainScene(7);
		}

		if (currentInventoryMode == 0) {
			inventoryUpdate();
		}

		if (currentInventoryMode == 1) {
			inventoryUpdate();
			screenUpdate();
		}
		checkInventoryButtons();
		this.towerController.drawTowers(this.gameScene.getGameView()::drawEntity);
	}
}
