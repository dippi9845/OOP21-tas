package main.java.tas.model;

/**
 * An interface for a Game Model
 */
public interface GameModel {
    
    /**
     * Increases the amount of money
     * @param money the amount by which it will be increased
     */
    void giveMoney2Player(int money);
    
    /**
     * Deals an amount of damage to the player
     * @param damage the damage that will be dealt
     */
    void dealDamage2Player(int damage);
    
    /**
     * @return the health of the player
     */
    int getHP();
    
    /**
     * @return the amount of money that the player has
     */
    int getPlayerMoney();
    
}
