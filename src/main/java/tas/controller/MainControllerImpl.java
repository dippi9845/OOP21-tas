package main.java.tas.controller;

import main.java.tas.view.MainView;
import main.java.tas.view.scene.EndGameScene;
import main.java.tas.view.scene.FullLevelsScene;
import main.java.tas.view.scene.GameSceneImpl;
import main.java.tas.view.scene.GenericScene;
import main.java.tas.view.scene.LevelSelectSceneImpl;
import main.java.tas.view.scene.MainMenuSceneImpl;
import main.java.tas.view.scene.SandboxModeScene;
import main.java.tas.view.scene.SettingsScene;
import main.java.tas.model.GameModelImpl;
import main.java.tas.model.GameSpecs;
import main.java.tas.model.MenuModel;
import main.java.tas.model.MenuModelImpl;
import main.java.tas.model.tower.factory.DefaultTowers;
import main.java.tas.utils.LevelHandler;

/**
 * Class that implements {@link MainController}.
 */
public class MainControllerImpl implements MainController {

	private SceneController sceneController;
	private GameSpecs gameSpecs = new GameSpecs();

	private int playerHealth = 100;
	private int playerMoney = 1000;

	private MainView mainView = new MainView();;
	private MenuModel menuModel = new MenuModelImpl();;

	/**
	 * Constructor that creates the main controller of the game
	 */
	public MainControllerImpl() {
		this.sceneController = createMenu(this.mainView);
		this.mainView.show();
	}

	/** {@inheritDoc} */
	@Override
	public SceneController createMenu(final MainView view) {
		GenericScene scene = new MainMenuSceneImpl(view.getPanel());
		SceneController controller = new MainMenuController(scene, this.menuModel);
		scene.setObserver(controller);
		return controller;
	}

	/**
	 * Connects the level select model, with it's own view.
	 * 
	 * @param view the main window
	 * @return the scene that was created
	 */
	public SceneController createLevelSelect(final MainView view) {
		GenericScene scene = new LevelSelectSceneImpl(view.getPanel(), this.menuModel.getNLevels());
		SceneController controller = new LevelSelectController(scene, this.menuModel);
		scene.setObserver(controller);
		return controller;
	}

	/**
	 * Connects the sandbox mode model, with it's own view.
	 * 
	 * @param view the main window
	 * @return the scene that was created
	 */
	public SceneController createSandBoxMode(final MainView view) {
		GenericScene scene = new SandboxModeScene(view.getPanel());
		SceneController controller = new SandboxModeController(scene, this.menuModel);
		scene.setObserver(controller);
		return controller;
	}
	
	/**
	 * Connects the end game menu model, with it's own view.
	 * 
	 * @param view the main window
	 * @return the scene that was created
	 */
	public SceneController createEndGame(final MainView view) {
		GenericScene scene = new EndGameScene(view.getPanel());
		SceneController controller = new EndGameController(scene, this.menuModel);
		scene.setObserver(controller);
		return controller;
	}

	/**
	 * Connects the settings model, with it's own view.
	 * 
	 * @param view the main window
	 * @return the scene that was created
	 */
	public SceneController createSettings(final MainView view) {
		GenericScene scene = new SettingsScene(view.getPanel());
		SceneController controller = new SettingsController(scene, this.menuModel);
		scene.setObserver(controller);
		return controller;
	}
	
	/**
	 * Connects the levels full menu model, with it's own view.
	 * 
	 * @param view the main window
	 * @return the scene that was created
	 */
	public SceneController createFullLevels(final MainView view) {
		GenericScene scene = new FullLevelsScene(view.getPanel());
		SceneController controller = new FullLevelsController(scene, this.menuModel);
		scene.setObserver(controller);
		return controller;
	}


	/** {@inheritDoc} */
	@Override
	public SceneController createGame(final MainView view) {
		GameSceneImpl scene = new GameSceneImpl(view.getPanel(), DefaultTowers.class);
		SceneController controller = new GameController(scene, new GameModelImpl(this.playerHealth, this.playerMoney),
		        LevelHandler.readLevel("level" + Integer.toString(this.menuModel.getCurrentLevel())), 
		        this.menuModel);
		scene.setObserver(controller);
		return controller;
	}

	/** {@inheritDoc} */
	@Override
	public SceneController getController() {
		return this.sceneController;
	}

	/**
	 * Checks if the current window is correct and if not it closes the current
	 * window and opens the correct one
	 */
	private int updateCurrentMode(final int menuState) {
		// I check if the currentMenuMode has changed and if it has I update it and open
		// the new window
		this.mainView.clearView();

		if (menuState == 1) {
			this.sceneController = createMenu(this.mainView);
		}
		if (menuState == 2) {
			this.sceneController = createGame(this.mainView);
		}
		if (menuState == 3) {
			this.sceneController = createLevelSelect(this.mainView);
		}
		if (menuState == 5) {
			this.sceneController = createSettings(this.mainView);
		}
		if (menuState == 6) {
			
			if(LevelHandler.getNElements() < this.menuModel.getMaxLevels()) {
				this.sceneController = createSandBoxMode(this.mainView);
			}
			else {
				this.sceneController = createFullLevels(this.mainView);
			}
		}
		if (menuState == 5) {
			this.sceneController = createEndGame(this.mainView);
		}
		return menuState;
	}

	/** {@inheritDoc} */
	@Override
	public void mainLoop() {
		double next_game_tick = System.currentTimeMillis();
		double last_frame_time = System.currentTimeMillis();
		int loops;

		int menuState = this.menuModel.getMainScene();

		while (menuState != 4) {
			loops = 0;

			if (menuState != this.menuModel.getMainScene()) {
				menuState = updateCurrentMode(this.menuModel.getMainScene());
			}

			while (System.currentTimeMillis() > next_game_tick && loops < this.gameSpecs.getMaxFrameSkip()) {
				this.sceneController.nextTick();
				this.mainView.update();

				next_game_tick += this.gameSpecs.getSkipTicks();
				loops++;
			}

			if (System.currentTimeMillis() - last_frame_time > 1000) {
				last_frame_time = System.currentTimeMillis();
			}
		}

		this.mainView.destroyView();

	}

	/**
	 * The main method that starts the game.
	 * 
	 * @param args not used
	 */
	public static void main(final String[] args) {
		new MainControllerImpl().mainLoop();
	}
}
