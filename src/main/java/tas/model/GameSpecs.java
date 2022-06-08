package main.java.tas.model;

import java.awt.Dimension;

import org.json.JSONObject;

import main.java.tas.utils.JsonUtils;

/**
 * Class that handles some game attributes.
 */
public final class GameSpecs {

    private static String RESOURCE_PATH = "res";
    private final String enemiesJsonSetup = RESOURCE_PATH + System.getProperty("file.separator") + "data"
            + System.getProperty("file.separator") + "game" + System.getProperty("file.separator") + "gameSpecs.json";

    private final Dimension gameUnits;

    private final int tickRate;
    private final int skipTicks;
    private final int maxFrameSkip;

    public GameSpecs() {
        JSONObject jsonSetupList = JsonUtils.getJsonData(enemiesJsonSetup);

        this.gameUnits = new Dimension(jsonSetupList.getJSONObject("gameUnits").getInt("width"),
                jsonSetupList.getJSONObject("gameUnits").getInt("height"));
        this.tickRate = jsonSetupList.getInt("tickRate");
        this.maxFrameSkip = jsonSetupList.getInt("maxFrameSkip");
        this.skipTicks = 1000 / this.tickRate;
    }

    public Dimension getGameUnits() {
        return this.gameUnits;
    }

    public int getTickRate() {
        return this.tickRate;
    }

    public int getSkipTicks() {
        return this.skipTicks;
    }

    public int getMaxFrameSkip() {
        return this.maxFrameSkip;
    }

}
