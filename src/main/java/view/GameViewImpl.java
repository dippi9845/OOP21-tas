package main.java.view;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameViewImpl implements GameView {
	
    private final JPanel rootPanel;
    
    public GameViewImpl(JPanel root) {
        this.rootPanel = root;
	}

    @Override
	public void CreateGameView() {
        /*
         * Qui verranno inizializzate le text box, le le finestre etc
         */ 
        this.rootPanel.add(new JTextField("Welcome to Javatpoint."));	// questo e' solo un esempio, da cancellare
	}

}
