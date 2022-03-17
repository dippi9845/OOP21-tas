package main.java.view;

import main.java.controller.Controller;
import main.java.controller.MainController;

public class MainView extends DefaultViewImpl{
	
    private final GameView gameView;
    
    private final Controller controller;

    public MainView() {
        this.controller = new MainController();
        
        CreateDefaultWindow();
        gameView = new GameViewImpl(getFrame());
        
        //gameView.CreateGameView();
        
        show();
	}
	
    public static void main() {
        new MainView();
    }
}
