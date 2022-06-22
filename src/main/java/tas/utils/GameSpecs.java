package main.java.tas.utils;

import org.json.JSONObject;

/**
 * Class that handles some game attributes.
 */
public final class GameSpecs {

	private static String RESOURCE_PATH = "res";
	private final String enemiesJsonSetup = RESOURCE_PATH + System.getProperty("file.separator") + "data"
			+ System.getProperty("file.separator") + "game" + System.getProperty("file.separator") + "gameSpecs.json";

	private final Size gameUnits;

	private final int tickRate;
	private final int skipTicks;
	private final int maxFrameSkip;

	/**
	 * Sets up the gameSpecs from a json file
	 */
	public GameSpecs() {
		JSONObject jsonSetupList = JsonUtils.getJsonData(enemiesJsonSetup);

		this.gameUnits = new Size(jsonSetupList.getJSONObject("gameUnits").getInt("width"),
				jsonSetupList.getJSONObject("gameUnits").getInt("height"));
		this.tickRate = jsonSetupList.getInt("tickRate");
		this.maxFrameSkip = jsonSetupList.getInt("maxFrameSkip");
		this.skipTicks = 1000 / this.tickRate;
	}

	/**
	 * @return the game board dimension
	 */
	public Size getGameUnits() {
		return this.gameUnits;
	}

	/**
	 * @return the tick rate of the game
	 */
	public int getTickRate() {
		return this.tickRate;
	}

	/**
	 * @return the frame skip margin
	 */
	public int getSkipTicks() {
		return this.skipTicks;
	}

	/**
	 * @return the maximum frames that can be skipped
	 */
	public int getMaxFrameSkip() {
		return this.maxFrameSkip;
	}

}
