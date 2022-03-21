package main.java.model;

public class GameModel implements Model {
    
    private final EnemiesHandlerImpl enemiesHandler;
    
    public GameModel() {
        this.enemiesHandler = new EnemiesHandlerImpl(new Position(100, 100));
    }

    public void moveEnemies() {
        this.enemiesHandler.moveEnemies();
    }
    
}
