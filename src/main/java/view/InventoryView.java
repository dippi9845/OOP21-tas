package main.java.view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;

import main.java.controller.Controller;
import main.java.model.Entity;

public class InventoryView implements ViewComponent {
    
    private final JPanel inventoryCanvas;
    
    public InventoryView() {
        this.inventoryCanvas = new JPanel();
        this.inventoryCanvas.setBackground(new Color(153,102,0));    // testing
        this.inventoryCanvas.add(new JTextField("Example text....."));    // testing
    }

    @Override
    public JPanel getPanel() {
        return this.inventoryCanvas;
    }

    @Override
    public void setObserver(Controller controller) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void drawEntity(Entity entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void resize() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addEntityLabel(Entity entity) {
        // TODO Auto-generated method stub
        
    }

}
