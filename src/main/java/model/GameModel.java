package main.java.model;

public interface GameModel {
    
    void givePlayerMoney(int money);
    void dealDamage2Player(int damage);
    
    int getHP();
    int getPlayerMoney();
    
}
