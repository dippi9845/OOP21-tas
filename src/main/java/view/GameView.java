package main.java.view;

import java.awt.GridBagLayout;
import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.controller.Controller;
import main.java.model.Entity;

public class GameView implements ViewComponent {
    
    private final JPanel rootCanvas = new JPanel(new GridBagLayout());  //TODO: forse e' meglio creare anche un japanel piu piccola su cui si baseranno tutte le figure minori e tenere questa come bordo (es i video su yt)
    private final JPanel gameBoard = new SquarePanel();
    private final ImageLoader imGetter = new ImageLoaderImpl();
    private final HashMap<Entity, JLabel> entityLables = new HashMap<Entity, JLabel>();
    
    public GameView() {
        this.rootCanvas.add(this.gameBoard);
        this.gameBoard.setLayout(null); //TODO: se metto null, funziona, ma e' una bad practice
        
        this.rootCanvas.setBackground(Color.BLACK);
        this.gameBoard.setBackground(Color.DARK_GRAY);
    }
    
    @Override
    public void drawEntities(List<Entity> entities) {
        for (Entity entity: entities) {
            if (entityLables.get(entity).getWidth() != 0) {
                entityLables.get(entity).setBounds(20, 20, entityLables.get(entity).getIcon().getIconHeight(), entityLables.get(entity).getIcon().getIconWidth());
            } else {
                entityLables.get(entity).setBounds(20, 20, (int)entity.getPosition().getX(), (int)entity.getPosition().getY());
            }
        }
    }
    
    public void addEntityLabel(Entity entity) {
        JLabel entityLable = new JLabel();
        entityLable.setIcon(new ImageIcon(imGetter.getImageByEntity(entity, this.gameBoard.getSize())));
        entityLables.put(entity, entityLable);
        this.gameBoard.add(entityLable);
    }
    
    @Override
    public JPanel getPanel() {
        return this.rootCanvas;
    }

    @Override
    public void setObserver(Controller controller) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void resize() {
        for (Map.Entry<Entity, JLabel> entityMap: entityLables.entrySet()) {
            entityMap.getValue().setIcon(new ImageIcon(imGetter.getImageByEntity(entityMap.getKey(), this.gameBoard.getSize())));
        }
    }

}
