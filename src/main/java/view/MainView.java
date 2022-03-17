package main.java.view;

import main.java.controller.Controller;
import main.java.controller.MainController;

public class MainView extends DefaultViewImpl{
	
    private final GameScene gameView;
    
    private final Controller controller;

    public MainView() {
        this.controller = new MainController();
        
        CreateDefaultWindow();
        this.gameView = new GameSceneImpl(getPanel());
        
        show();

	}
	
    public static void main() {
        new MainView();
    }
}
