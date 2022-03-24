package main.java.view;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import main.java.controller.MainController;

public class MainView extends DefaultViewImpl {
    
    private MainController observer;

    public MainView() {
        CreateDefaultWindow();
        
        show();
	}
    
    @Override
    public void setObserver(MainController obs) {
        this.observer = obs;
        this.frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                observer.getController().resize();
            }
        });
    }

}
