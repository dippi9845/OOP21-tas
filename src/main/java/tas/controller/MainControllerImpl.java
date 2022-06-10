package main.java.tas.controller;

import main.java.tas.view.GameSceneImpl;
import main.java.tas.view.LevelSelectSceneImpl;
import main.java.tas.view.MainMenuSceneImpl;
import main.java.tas.view.MainView;
import main.java.tas.view.SandboxModeScene;
import main.java.tas.view.Scene;
import main.java.tas.view.SettingsSceneImpl;
import main.java.tas.model.GameModelImpl;
import main.java.tas.model.GameSpecs;
import main.java.tas.model.MenuModel;
import main.java.tas.model.MenuModelImpl;

/**
 * Class that implements {@link MainController}.
 */
public class MainControllerImpl implements MainController {

	private SceneController sceneController;
	private Scene scene;
	private GameSpecs gameSpecs = new GameSpecs();

	private int playerHealth = 100;
	private int playerMoney = 150;

	private MainView mainView;
	private MenuModel menuModel;
	private int currentMenuMode = 1;

	/**
	 * Constructor that creates the main controller of the game
	 */
	public MainControllerImpl() {
		this.menuModel = new MenuModelImpl();
		this.mainView = new MainView();
		this.sceneController = createMenu(this.mainView);
		this.mainView.show();
	}

	/** {@inheritDoc} */
	@Override
	public SceneController createMenu(final MainView view) {
		this.scene = new MainMenuSceneImpl(view.getPanel());
		SceneController controller = new MainMenuController(this.scene, this.menuModel);
		this.scene.setObserver(controller);
		return controller;
	}

	public SceneController createLevelSelect(final MainView view) {
		this.scene = new LevelSelectSceneImpl(view.getPanel(), this.menuModel);
		SceneController controller = new LevelSelectController(this.scene, this.menuModel);
		this.scene.setObserver(controller);
		return controller;
	}

	public SceneController createSandBoxMode(final MainView view) {
		this.scene = new SandboxModeScene(view.getPanel(), this.menuModel);
		SceneController controller = new SandboxModeController(this.scene, this.menuModel);
		this.scene.setObserver(controller);
		return controller;
	}

	public SceneController createSettings(final MainView view) {
		this.scene = new SettingsSceneImpl(view.getPanel(), this.menuModel);
		SceneController controller = new SettingsController(this.scene, this.menuModel);
		this.scene.setObserver(controller);
		return controller;
	}

	/** {@inheritDoc} */
	@Override
	public SceneController createGame(final MainView view) {
		this.scene = new GameSceneImpl(view.getPanel());
		SceneController controller = new GameController(((GameSceneImpl) this.scene),
		        new GameModelImpl(this.playerHealth, this.playerMoney));
		this.scene.setObserver(controller);
		return controller;
	}

	/** {@inheritDoc} */
	@Override
	public SceneController getController() {
		return this.sceneController;
	}

	private void updateCurrentMode() {
		// I check if the currentMenuMode has changed and if it has I update it and open
		// the new window
		if (this.currentMenuMode != this.menuModel.getMainScene()) {
			this.currentMenuMode = this.menuModel.getMainScene();
			if (this.currentMenuMode == 1) {
				this.mainView.dispose();
				this.mainView = new MainView();
				this.mainView.show();
				this.sceneController = createMenu(this.mainView);
			}
			if (this.currentMenuMode == 2) {
				this.mainView.dispose();
				this.mainView = new MainView();
				this.mainView.show();
				this.sceneController = createGame(this.mainView);
			}
			if (this.currentMenuMode == 3) {
				this.mainView.dispose();
				this.mainView = new MainView();
				this.mainView.show();
				this.sceneController = createLevelSelect(this.mainView);
			}
			if (this.currentMenuMode == 5) {
				this.mainView.dispose();
				this.mainView = new MainView();
				this.mainView.show();
				this.sceneController = createSettings(this.mainView);
			}
			if (this.currentMenuMode == 6) {
				this.mainView.dispose();
				this.mainView = new MainView();
				this.mainView.show();
				this.sceneController = createSandBoxMode(this.mainView);
			}
			if (this.currentMenuMode == 4) {
				System.exit(0);
			}
		}
	}

	/** {@inheritDoc} */
	@Override
	public void mainLoop() {

		double next_game_tick = System.currentTimeMillis(); // TODO: qui in mezzo c'e' roba per l'FPS counter, sarebbe
		                                                    // meglio rimuoverli
		double last_frame_time = System.currentTimeMillis();
		int loops;
		int fps = 0;
		while (true) { // TODO: cambiare il true con qualcosa di piu' concreto tipo il click di un
			// pulsante o altro
			loops = 0;

			while (System.currentTimeMillis() > next_game_tick && loops < this.gameSpecs.getMaxFrameSkip()) {
				this.sceneController.nextTick();

				next_game_tick += this.gameSpecs.getSkipTicks();
				loops++;
				fps++;
			}

			if (System.currentTimeMillis() - last_frame_time > 1000) {
				last_frame_time = System.currentTimeMillis();
				System.out.println(fps);
				fps = 0;
			}

			updateCurrentMode();

			this.mainView.update();
		}

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
