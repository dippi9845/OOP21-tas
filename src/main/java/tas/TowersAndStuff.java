package main.java.tas;

import main.java.tas.controller.MainControllerImpl;

/**
 * The main class that launch the game {@link MainControllerImpl}
 *
 */
public final class TowersAndStuff {

    private TowersAndStuff() { }

    /**
     *
     * @param args not used
     */
    public static void main(final String[] args) {
        MainControllerImpl.main(args);
    }

}
