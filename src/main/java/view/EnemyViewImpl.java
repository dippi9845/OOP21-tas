package main.java.view;

import main.java.model.Position;    //TODO: non so se mettere questa classe in una cartella al di fuori di tutto (es. utils)

public class EnemyViewImpl implements EnemyView {
    
    private Position enemyPosition;
    
    public EnemyViewImpl(Position enemyPosition) {
        this.enemyPosition = enemyPosition;
    }

    @Override
    public void setPosition(Position newPosition) {
        this.enemyPosition = newPosition;
    }

}
