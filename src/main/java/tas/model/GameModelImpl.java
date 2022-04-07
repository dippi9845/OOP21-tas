package main.java.tas.model;

public class GameModelImpl implements GameModel {
    
    private static final int DEFAULT_MAX_HEALT = 150;
    private static final int DEFAULT_STARTING_MONEY = 100;
    
    private int playerHP;
    private int playerMoney;
    
    public GameModelImpl(int playerMaxHealth, int playerStartingMoney) {
        this.playerHP = playerMaxHealth;
        this.playerMoney = playerStartingMoney;
    }
    
    public GameModelImpl() {
        this(DEFAULT_MAX_HEALT, DEFAULT_STARTING_MONEY);
    }

    @Override
    public void giveMoney2Player(int money) {
        this.playerMoney += money;
    }

    @Override
    public void dealDamage2Player(int damage) {
        this.playerHP -= damage;
    }

    @Override
    public int getHP() {
        return this.playerHP;
    }

    @Override
    public int getPlayerMoney() {
        return this.playerMoney;
    }

}
