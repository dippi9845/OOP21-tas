package main.java.view;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class GameViewImpl implements GameView {
	
    private final JFrame rootFrame;
    
    public GameViewImpl(JFrame root) {
        this.rootFrame = root;
	}

    @Override
	public void CreateGameView() {
        /*
         * Qui verranno inizializzate le text box, le le finestre etc
         */ 
        this.rootFrame.add(new JTextField("Welcome to Javatpoint."));	// questo e' solo un esempio, da cancellare
	}

}
