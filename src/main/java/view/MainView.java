package main.java.view;

import main.java.controller.*;

public class MainView extends ViewImpl{
	
    final Controller controller;
    
    final GameView gameView;

    public MainView(Controller controller) {
        this.controller = controller;
        
        CreateDefaultWindow();
        
        gameView = new GameViewImpl(getPanel());
        gameView.CreateGameView();
        
        show();
	}
	
    public static void main() {
        new MainView(new MainController());
    }
}
