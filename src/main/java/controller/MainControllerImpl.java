package main.java.controller;

import main.java.view.GameSceneImpl;
import main.java.view.MainView;

public class MainControllerImpl implements MainController {
    
    private Controller controller;
    private final MainView mainView;
    private GameSceneImpl scene;
	
    public MainControllerImpl() {
        this.mainView = new MainView();
        
        this.controller = createGame(this.mainView);    // manca il model
        
	}
    
    @Override
    public void mainLoop() {
        while (true) {
            this.controller.nextTick();
            this.mainView.show();
        }
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
    
    /**
    *
    * @param args not used
    */
   public static void main(final String[] args) {
       new MainControllerImpl().mainLoop();
   }

}