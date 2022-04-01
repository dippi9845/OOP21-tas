package main.java.controller;

import main.java.view.GameScene;
import main.java.view.GameSceneImpl;
import main.java.view.MainView;
import main.java.utils.GameSpecs;

public class MainControllerImpl implements MainController {
    
    private Controller sceneController;
    private final MainView mainView;
    private GameScene scene;
	
    public MainControllerImpl() {
        this.mainView = new MainView();
        this.sceneController = createGame(this.mainView);    // manca il model
        
	}
    
    @Override
    public Controller createMenu(final MainView view) {
        // TODO
        return null;
    }
    
    @Override
    public Controller createGame(final MainView view) {
        this.scene = new GameSceneImpl(view.getPanel());
        Controller controller = new GameControllerImpl(this.scene);
        this.scene.setObserver(controller);
        return controller;
    }
    
    @Override
    public Controller getController() {
        return this.sceneController;
    }
    
    @Override
    public void mainLoop() {
        this.sceneController.nextTick();
        this.mainView.show();
        
        double next_game_tick = System.currentTimeMillis(); //TODO: qui in mezzo c'e' roba per l'FPS counter, sarebbe meglio rimuoverli
        double last_frame_time = System.currentTimeMillis();
        int loops;
        int fps = 0;
        
        while (true) {  //TODO: cambiare il true con qualcosa di piu' concreto tipo il click di un pulsante o altro
            loops = 0;
            
            while (System.currentTimeMillis() > next_game_tick && loops < GameSpecs.MAX_FRAMESKIP) {
                this.sceneController.nextTick();

                next_game_tick += GameSpecs.SKIP_TICKS;
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
    *
    * @param args not used
    */
   public static void main(final String[] args) {
       new MainControllerImpl().mainLoop();
   }

}
