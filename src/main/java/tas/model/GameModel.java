package main.java.tas.model;

public interface GameModel {
    
    void giveMoney2Player(int money);
    void dealDamage2Player(int damage);
    
    int getHP();
    int getPlayerMoney();
    
}
