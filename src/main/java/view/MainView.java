package main.java.view;

import main.java.controller.*;

public class MainView extends ViewImpl{
	
	final Controller controller;

	public MainView(Controller controller) {
		this.controller = controller;
	}
	
	public static void main() {
		new MainView(new MainController());
    }
}
