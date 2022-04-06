package main.java.model;

public interface GameModel {
    
    void giveMoney2Player(int money);
    void dealDamage2Player(int damage);
    
    int getHP();
    int getPlayerMoney();
    
}
