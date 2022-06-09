package main.java.tas.controller;

import main.java.tas.view.GameScene;
import main.java.tas.view.GameSceneImpl;
import main.java.tas.view.MainView;
import main.java.tas.model.GameModelImpl;
import main.java.tas.model.GameSpecs;

/**
 * Class that implements {@link MainController}.
 */
public class MainControllerImpl implements MainController {
    private SceneController sceneController;
    private final MainView mainView;
    private GameScene scene;
    private GameSpecs gameSpecs = new GameSpecs();

    private int playerHealth = 100;
    private int playerMoney = 150;

    /**
     * Constructor that creates the main controller of the game.
     */
    public MainControllerImpl() {
        this.mainView = new MainView();
        this.sceneController = createGame(this.mainView);

        this.mainView.show();
    }

    /** {@inheritDoc} */
    @Override
    public SceneController createMenu(final MainView view) {
        // TODO
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public SceneController createGame(final MainView view) {
        this.scene = new GameSceneImpl(view.getPanel());
        SceneController controller = new GameController(this.scene, new GameModelImpl(playerHealth, playerMoney));
        this.scene.setObserver(controller);
        return controller;
    }

    /** {@inheritDoc} */
    @Override
    public SceneController getController() {
        return this.sceneController;
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
