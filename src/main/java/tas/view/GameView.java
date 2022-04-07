package main.java.tas.view;

import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import main.java.tas.model.Entity;

public class GameView implements ViewComponent {
    
    private final JPanel rootCanvas = new JPanel(new GridBagLayout());  //TODO: forse e' meglio creare anche un japanel piu piccola su cui si baseranno tutte le figure minori e tenere questa come bordo (es i video su yt)
    private final JPanel gameBoard = new SquarePanel();
    private final ImageLoader imGetter = new ImageLoaderImpl();
    private final HashMap<Entity, AdaptiveLabel> entityLables = new HashMap<Entity, AdaptiveLabel>();
    
    public GameView() {
        this.rootCanvas.add(this.gameBoard);
        this.gameBoard.setLayout(null); //TODO: se metto null, funziona, ma e' una bad practice
        
        this.gameBoard.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                resize();
            }
        });
        
        this.rootCanvas.setBackground(Color.BLACK);
        this.gameBoard.setBackground(Color.DARK_GRAY);
    }
    
    @Override
    public void drawEntity(Entity entity) {
        this.entityLables.get(entity).setPosition(entity.getPosition());
    }
    
    @Override
    public void addEntityLabel(Entity entity) {
        AdaptiveLabel entityLabel = new AdaptiveLabel();
        entityLabel.setIcon(new ImageIcon(imGetter.getImageByEntity(entity, this.gameBoard.getSize())));
        entityLables.put(entity, entityLabel);
        this.gameBoard.add(entityLabel);
        entityLabel.setAdaptive();
    }
    
    @Override
    public void removeEntityLabel(Entity entity) {
        this.gameBoard.remove(this.entityLables.get(entity));
        this.entityLables.remove(entity);
    }
    
    @Override
    public JPanel getPanel() {
        return this.rootCanvas;
    }

    @Override
    public void setObserver() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void resize() {
        for (Map.Entry<Entity, AdaptiveLabel> entityMap: entityLables.entrySet()) {
            entityMap.getValue().setIcon(new ImageIcon(imGetter.getImageByEntity(entityMap.getKey(), this.gameBoard.getSize())));
        }
    }

}
