package main.java.view;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

}
