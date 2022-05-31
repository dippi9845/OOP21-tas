package test.java.tas.model;

import static org.junit.Assert.*;

import org.junit.Test;

import main.java.tas.model.GameModel;
import main.java.tas.model.GameModelImpl;

public class GameModelImplTest {

    @Test
    public void testGiveMoney2Player() {
        final int startingHP = 100;
        final int startingMoney = 100;
        final GameModel model = new GameModelImpl(startingHP, startingMoney);
        final int moneyToBeGiven = 7;
        final int numberOfTransitions = 5;

        for (int i = 0; i < numberOfTransitions; i++) {
            model.giveMoney2Player(moneyToBeGiven);
        }

        assertEquals(model.getPlayerMoney(), startingMoney + (moneyToBeGiven * numberOfTransitions));
    }

    @Test
    public void testDealDamage2Player() {
        final int startingHP = 100;
        final int startingMoney = 100;
        final GameModel model = new GameModelImpl(startingHP, startingMoney);
        final int attackDamage = 10;
        final int numberOfAttacks = 3;

        for (int i = 0; i < numberOfAttacks; i++) {
            model.dealDamage2Player(attackDamage);
        }

        assertEquals(model.getHP(), startingHP - (attackDamage * numberOfAttacks));
    }

}
