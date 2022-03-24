package main.java.controller;

import main.java.view.GameScene;
import main.java.view.GameSceneImpl;
import main.java.view.MainView;

public class MainControllerImpl implements MainController {
    
    private Controller sceneController;
    private final MainView mainView;
    private GameScene scene;
	
    public MainControllerImpl() {
        this.mainView = new MainView();
        this.sceneController = createGame(this.mainView);    // manca il model
        
	}
    
    public Controller createMenu(final MainView view) {
        // TODO
        return null;
    }
    
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
        while (true) {  //TODO: cambiare il true con qualcosa di piu' concreto
            this.sceneController.nextTick();
            this.mainView.show();
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
